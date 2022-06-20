package actions.actions;

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
import java.util.Date;
import java.util.List;

import static database.dao.factory.DAOEnum.*;
import static util.JSPPages.ERROR_JSP;
import static util.EssentialVars.*;

public class CheckoutAction implements Action{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private OrderDAO orderDAO = (OrderDAO) daoFactory.getDAO(ORDER_DAO);
    private OrderItemDAO orderItemDAO = (OrderItemDAO) daoFactory.getDAO(ORDER_ITEM_DAO);
    private BasketDAO basketDAO = (BasketDAO) daoFactory.getDAO(BASKET_DAO);
    private StatusDAO statusDAO = (StatusDAO) daoFactory.getDAO(STATUS_DAO);
    private BookDAO bookDAO = (BookDAO) daoFactory.getDAO(BOOK_DAO);

    //private Logger logger = LogManager.getLogger(this.getClass().getName());
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        HttpSession session = request.getSession(true);
        if(session.getAttribute(USER_ID) != null) {
            Long userId = (Long)session.getAttribute(USER_ID);
            Long localId = (Long)session.getAttribute(LOCAL_ID);
            Long totalCost = 0L;
            List<Basket> baskets = (List<Basket>) session.getAttribute("baskets");
            if(baskets == null) baskets = basketDAO.getByUserId(userId);
            for (Basket basket:
                    baskets) {
                Book book = bookDAO.getById(basket.getBooksId());
                Long curCost = basket.getCount() * book.getPrice();
                totalCost += curCost;
            }
            Long statusId = null;
            if(localId == 1L) {
                statusId = PROCESS_FOR_LOCAL_ONE;
            }else if(localId == 2L){
                statusId = PROCESS_FOR_LOCAL_TWO;
            }else {
                throw new SQLException();
            }
            Order order = new Order();
            order.setUsersId(userId);
            order.setStatusId(statusId);
            order.setTotalCost(totalCost);
            order.setCreateTime(new Date());
            order.setFinishTime(new Date());
            orderDAO.create(order);
            List<Order> orderList = orderDAO.getAll();
            order = orderList.get(orderList.size() - 1);
            for (Basket basket:
                 baskets) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrdersId(order.getId());
                Book book = bookDAO.getById(basket.getBooksId());
                Long curCost = basket.getCount() * book.getPrice();
                orderItem.setCost(curCost);
                orderItem.setCount(basket.getCount());
                orderItem.setBooksId(basket.getBooksId());
                orderItemDAO.create(orderItem);
                book.setCount(book.getCount() - basket.getCount());
                bookDAO.update(book.getId(),book,localId);
                basketDAO.delete(basket.getId());
            }
            session.setAttribute("baskets", baskets);
            response.sendRedirect("orders");
        }else {
            response.sendRedirect(ERROR_JSP);
        }
    }
}
