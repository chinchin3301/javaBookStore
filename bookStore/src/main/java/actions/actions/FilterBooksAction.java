package actions.actions;

import database.dao.factory.DAOFactory;
import database.dao.interfaces.*;
import entity.*;

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
import static util.JSPPages.CATALOG_JSP;
import static util.EssentialVars.*;

public class FilterBooksAction implements Action{
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

        Long localId = (Long) session.getAttribute(LOCAL_ID);
        List<Book> books = bookDAO.getAll(localId);
        List<Country> countries = countryDAO.getAll(localId);
        Map<Long, Publisher> publishers = new HashMap<>();
        List<BookGenre> bookGenres = bookGenreDAO.getAll(localId);
        Map<Long, BookGenre> bookGenreMap = new HashMap<>();
        String[] selectedGenres = request.getParameterValues("genres");
        String[] selectedCountries = request.getParameterValues("countries");
        if(selectedGenres == null || selectedGenres.length == 0) {
            selectedGenres = new String[bookGenres.size()];
            for (int i = 0; i < bookGenres.size(); i++) {
                selectedGenres[i] = bookGenres.get(i).getTitle();
            }
        }
        if(selectedCountries == null || selectedCountries.length == 0) {
            selectedCountries = new String[countries.size()];
            for (int i = 0; i < countries.size(); i++) {
                selectedCountries[i] = countries.get(i).getName();
            }
        }
        List<Country> tmpCountries = new ArrayList<>();
        for (Country country:
             countries) {
            for (String countryName:
                 selectedCountries) {
                if(countryName.equals(country.getName())){
                    tmpCountries.add(country);
                }
            }
        }
        countries = tmpCountries;
        for (BookGenre bookGenre:
             bookGenres) {
            for (String genreTitle:
                 selectedGenres){
                if(genreTitle.equals(bookGenre.getTitle()))
                bookGenreMap.put(bookGenre.getId(), bookGenre);
            }
        }
        List<Language> languages = languageDAO.getAll();
        List<BookAuthor> bookAuthors = new ArrayList<>();
        List<Author> authors = new ArrayList<>();
        List<Publisher> publisherList = new ArrayList<>();
        for (Country country:
             countries) {
            List<Publisher> publisherListByCountry = publisherDAO.getByCountryId(country.getId());
            for (Publisher publisher:
                 publisherListByCountry) {
                publishers.put(publisher.getId(), publisher);
            }
        }
        countries = countryDAO.getAll(localId);
        Map<Long, String> publishersByBookId = new HashMap<>();
        Map<Long, String> genresByBookId = new HashMap<>();
        Map<Long, String> languagesByBookId = new HashMap<>();
        Map<Long, List<Author>> authorsByBookId = new HashMap<>();
        List<Book> tmpBooks = new ArrayList<>();
        for (Book book:
             books) {
            Long curBookId = book.getId();
            Long curBookPublisherId = book.getPublishersId();
            Long curBookGenreId = book.getGenreId();
            Long curBookLanguageId = book.getLanguagesId();
            if(bookGenreMap.get(curBookGenreId) == null || publishers.get(curBookPublisherId) == null)
                continue;
            tmpBooks.add(book);
            List<BookAuthor> bookAuthorList = bookAuthorDAO.getByBookId(curBookId);
            List<Author> authorList = new ArrayList<>();
            for (BookAuthor curBookAuthor:
                 bookAuthorList) {
                authorList.add(authorDAO.getById(curBookAuthor.getAuthorsId()));
            }
            authorsByBookId.put(curBookId, authorList);
            publishersByBookId.put(curBookId, publishers.get(curBookPublisherId).getName());
            genresByBookId.put(curBookId, bookGenreMap.get(curBookGenreId).getTitle());
            for (Language language:
                 languages) {
                if(language.getId() == (curBookLanguageId)) {
                    languagesByBookId.put(curBookId, language.getName());
                }
            }
        }
        books = tmpBooks;
        session.setAttribute("bookList", books);
        session.setAttribute("publisherMap", publishersByBookId);
        session.setAttribute("genreMap", genresByBookId);
        session.setAttribute("languageMap", languagesByBookId);
        session.setAttribute("authorMap", authorsByBookId);
        session.setAttribute("allGenres", bookGenres);
        session.setAttribute("allCountries", countries);
        response.sendRedirect(CATALOG_JSP);
    }
}
