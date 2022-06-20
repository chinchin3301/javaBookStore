package actions.actions;

import database.dao.factory.DAOFactory;
import database.dao.interfaces.BasketDAO;
import database.dao.interfaces.BookDAO;
import entity.Basket;
import entity.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static database.dao.factory.DAOEnum.BASKET_DAO;
import static database.dao.factory.DAOEnum.BOOK_DAO;
import static util.JSPPages.BASKET_JSP;
import static util.JSPPages.ERROR_JSP;
import static util.EssentialVars.*;

public class BasketAction implements Action{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private BasketDAO basketDAO = (BasketDAO) daoFactory.getDAO(BASKET_DAO);
    private BookDAO bookDAO = (BookDAO) daoFactory.getDAO(BOOK_DAO);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        HttpSession session = request.getSession(true);
        if(session.getAttribute(USER_ID) == null) {
            response.sendRedirect(ERROR_JSP);
        }else {
            Long userId = (Long)session.getAttribute(USER_ID);
            Long localId = (Long)session.getAttribute(LOCAL_ID);
            List<Basket> baskets = basketDAO.getByUserId(userId);
            List<Book> books = bookDAO.getAll(localId);

            Map<Long, String> bookTitleMap = new HashMap<>();
            for (Book book:
                 books) {
                bookTitleMap.put(book.getId(), book.getTitle());
            }
            session.setAttribute("baskets", baskets);
            session.setAttribute("bookTitleMap", bookTitleMap);
            response.sendRedirect(BASKET_JSP);
        }
    }
}
