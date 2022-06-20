package actions.actions;

import static util.EssentialVars.*;
import database.dao.factory.DAOFactory;
import database.dao.interfaces.*;
import entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static database.dao.factory.DAOEnum.*;
import static util.JSPPages.BOOK_JSP;
import static util.JSPPages.ERROR_JSP;
import static util.EssentialVars.*;

public class SearchAction implements Action{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private BookDAO bookDAO = (BookDAO) daoFactory.getDAO(BOOK_DAO);
    private CountryDAO countryDAO = (CountryDAO) daoFactory.getDAO(COUNTRY_DAO);
    private PublisherDAO publisherDAO = (PublisherDAO) daoFactory.getDAO(PUBLISHER_DAO);
    private BookGenreDAO bookGenreDAO = (BookGenreDAO) daoFactory.getDAO(BOOK_GENRE_DAO);
    private LanguageDAO languageDAO = (LanguageDAO) daoFactory.getDAO(LANGUAGE_DAO);
    private BookAuthorDAO bookAuthorDAO = (BookAuthorDAO) daoFactory.getDAO(BOOK_AUTHOR_DAO);
    private AuthorDAO authorDAO = (AuthorDAO) daoFactory.getDAO(AUTHOR_DAO);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        HttpSession session = request.getSession(true);
        String bookTitle = null;
        bookTitle = request.getParameter("searchTitle");

        Long localId = (Long)session.getAttribute(LOCAL_ID);

        List<Long> bookIdList = bookDAO.getBookId(bookTitle, localId);
        Book book = new Book();
        if(bookIdList == null || bookIdList.isEmpty()){
            session.setAttribute("aBook", book);
            session.setAttribute("bookId", book.getId());
            response.sendRedirect(BOOK_JSP);
        }else {
            book = bookDAO.getById(bookIdList.get(0));
            Map<Long, String> publishersByBookId = new HashMap<>();
            Map<Long, String> genresByBookId = new HashMap<>();
            Map<Long, String> languagesByBookId = new HashMap<>();
            Map<Long, List<Author>> authorsByBookId = new HashMap<>();
            Map<Long, Publisher> publishers = new HashMap<>();
            Long curBookId = book.getId();
            Long curBookPublisherId = book.getPublishersId();
            Long curBookGenreId = book.getGenreId();
            Long curBookLanguageId = book.getLanguagesId();
            List<Country> countries = countryDAO.getAll(localId);
            List<BookAuthor> bookAuthorList = bookAuthorDAO.getByBookId(curBookId);
            List<Author> authorList = new ArrayList<>();
            List<BookGenre> bookGenres = bookGenreDAO.getAll(localId);
            Map<Long, BookGenre> bookGenreMap = new HashMap<>();
            for (BookGenre bookGenre :
                    bookGenres) {
                bookGenreMap.put(bookGenre.getId(), bookGenre);
            }
            for (BookAuthor curBookAuthor :
                    bookAuthorList) {
                authorList.add(authorDAO.getById(curBookAuthor.getAuthorsId()));
            }
            for (Country country :
                    countries) {
                List<Publisher> publisherListByCountry = publisherDAO.getByCountryId(country.getId());
                for (Publisher publisher :
                        publisherListByCountry) {
                    publishers.put(publisher.getId(), publisher);
                }
            }
            authorsByBookId.put(curBookId, authorList);

            publishersByBookId.put(curBookId, publishers.get(curBookPublisherId).getName());

            genresByBookId.put(curBookId, bookGenreMap.get(curBookGenreId).getTitle());
            List<Language> languages = languageDAO.getAll();
            for (Language language :
                    languages) {
                if (language.getId() == (curBookLanguageId)) {
                    languagesByBookId.put(curBookId, language.getName());
                }
            }
            session.setAttribute("aBook", book);
            session.setAttribute("bookId", book.getId());
            session.setAttribute("publisherMap", publishersByBookId);
            session.setAttribute("genreMap", genresByBookId);
            session.setAttribute("languageMap", languagesByBookId);
            session.setAttribute("authorMap", authorsByBookId);
            response.sendRedirect(BOOK_JSP);
        }
    }
}
