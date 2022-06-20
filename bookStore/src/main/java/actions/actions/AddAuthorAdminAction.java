package actions.actions;

import actions.helpers.UChecker;
import database.dao.factory.DAOFactory;
import database.dao.interfaces.*;
import entity.Author;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static database.dao.factory.DAOEnum.*;
import static util.JSPPages.ADD_BOOK_JSP;
import static util.EssentialVars.USER_ID;
import static util.JSPPages.ERROR_JSP;
import static util.EssentialVars.*;

public class AddAuthorAdminAction implements Action{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private OrderDAO orderDAO = (OrderDAO) daoFactory.getDAO(ORDER_DAO);
    private OrderItemDAO orderItemDAO = (OrderItemDAO) daoFactory.getDAO(ORDER_ITEM_DAO);
    private BasketDAO basketDAO = (BasketDAO) daoFactory.getDAO(BASKET_DAO);
    private StatusDAO statusDAO = (StatusDAO) daoFactory.getDAO(STATUS_DAO);
    private BookDAO bookDAO = (BookDAO) daoFactory.getDAO(BOOK_DAO);
    private UserDAO userDAO = (UserDAO) daoFactory.getDAO(USER_DAO);
    private BookGenreDAO bookGenreDAO = (BookGenreDAO) daoFactory.getDAO(BOOK_GENRE_DAO);
    private AuthorDAO authorDAO = (AuthorDAO) daoFactory.getDAO(AUTHOR_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        HttpSession session = request.getSession(true);
        if(session.getAttribute(USER_ID) == null || session.getAttribute(IS_ADMIN) == null
                || session.getAttribute(IS_ADMIN).equals(false)){
            response.sendRedirect(ERROR_JSP);
        }else {
            String authorFullName = request.getParameter("new_author_name");
            if(!UChecker.isCheckTitle(authorFullName)) {
                response.sendRedirect(ERROR_JSP);
            }else {
                Author author = new Author();
                author.setFullName(authorFullName);
                authorDAO.create(author);
                response.sendRedirect(ADD_BOOK_JSP);
            }
        }
    }
}
