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
import java.util.List;

import static database.dao.factory.DAOEnum.BASKET_DAO;
import static database.dao.factory.DAOEnum.BOOK_DAO;
import static util.JSPPages.ERROR_JSP;
import static util.JSPPages.INDEX_JSP;
import static util.EssentialVars.*;

public class AddToBasketAction implements Action{
    DAOFactory daoFactory = DAOFactory.getInstance();
    BasketDAO basketDAO = (BasketDAO) daoFactory.getDAO(BASKET_DAO);
    BookDAO bookDAO = (BookDAO) daoFactory.getDAO(BOOK_DAO);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        HttpSession session = request.getSession(true);

        if (session.getAttribute(USER_ID) == null) {
            response.sendRedirect(ERROR_JSP);
        }else {
            Long bookId = Long.parseLong(request.getParameter("bookId"));
            List<Basket> baskets = basketDAO.getByUserId((Long)session.getAttribute(USER_ID));
            Basket basket = new Basket();
            boolean isBookPresent = false;
            Book book = bookDAO.getById(bookId);
            Long maxCount = book.getCount();
            if(maxCount == null) maxCount = 0L;
            if(!baskets.isEmpty()) {
                for (Basket curBasket:
                     baskets) {
                    if(curBasket.getBooksId().equals(bookId)) {
                        isBookPresent = true;
                        curBasket.setCount(Math.min(curBasket.getCount()+1, maxCount));
                        basketDAO.update(curBasket.getId(), curBasket);
                    }
                }
            }
            if(!isBookPresent && maxCount > 0) {
                basket.setUsersId((Long)session.getAttribute(USER_ID));
                basket.setBooksId(bookId);
                basket.setCount(Math.min(1L, maxCount));
                basketDAO.create(basket);
            }
            response.sendRedirect(INDEX_JSP);
        }
    }
}
