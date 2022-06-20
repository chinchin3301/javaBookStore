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
import java.util.List;

import static database.dao.factory.DAOEnum.*;
import static util.JSPPages.ADD_BOOK_JSP;
import static util.JSPPages.ERROR_JSP;
import static util.EssentialVars.*;

public class PrepareBookAddAction implements Action{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private OrderDAO orderDAO = (OrderDAO) daoFactory.getDAO(ORDER_DAO);
    private OrderItemDAO orderItemDAO = (OrderItemDAO) daoFactory.getDAO(ORDER_ITEM_DAO);
    private BasketDAO basketDAO = (BasketDAO) daoFactory.getDAO(BASKET_DAO);
    private StatusDAO statusDAO = (StatusDAO) daoFactory.getDAO(STATUS_DAO);
    private BookDAO bookDAO = (BookDAO) daoFactory.getDAO(BOOK_DAO);
    private UserDAO userDAO = (UserDAO) daoFactory.getDAO(USER_DAO);
    private BookGenreDAO bookGenreDAO = (BookGenreDAO) daoFactory.getDAO(BOOK_GENRE_DAO);
    private AuthorDAO authorDAO = (AuthorDAO) daoFactory.getDAO(AUTHOR_DAO);
    private PublisherDAO publisherDAO = (PublisherDAO) daoFactory.getDAO(PUBLISHER_DAO);
    private CountryDAO countryDAO = (CountryDAO) daoFactory.getDAO(COUNTRY_DAO);
    private LanguageDAO languageDAO = (LanguageDAO) daoFactory.getDAO(LANGUAGE_DAO);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        HttpSession session = request.getSession(true);
        if(session.getAttribute(USER_ID) == null || session.getAttribute(IS_ADMIN) == null
                || session.getAttribute(IS_ADMIN).equals(false)){
            response.sendRedirect(ERROR_JSP);
        }else {
            Long localId = (Long) session.getAttribute(LOCAL_ID);
            List<Language> languages = languageDAO.getAll();
            List<BookGenre> bookGenres = bookGenreDAO.getAll(localId);
            List<Country> countries = countryDAO.getAll(localId);
            List<Author> authors = authorDAO.getAll();
            List<Publisher> publishers = new ArrayList<>();
            for (Country country:
                 countries) {
                List<Publisher> publisherList = publisherDAO.getByCountryId(country.getId());
                publishers.addAll(publisherList);
            }
            session.setAttribute("adminLanguages", languages);
            session.setAttribute("adminGenres", bookGenres);
            session.setAttribute("adminPublishers", publishers);
            session.setAttribute("adminAuthors", authors);
            response.sendRedirect(ADD_BOOK_JSP);
        }
    }
}
