package actions.actions;

import database.dao.factory.DAOFactory;
import database.dao.interfaces.*;
import entity.Book;
import entity.Order;
import entity.OrderItem;
import entity.Status;

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

import static database.dao.factory.DAOEnum.*;
import static util.EssentialVars.USER_ID;
import static util.JSPPages.ORDERS_JSP;
import static util.EssentialVars.*;

public class OrdersAction implements Action{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private OrderDAO orderDAO = (OrderDAO) daoFactory.getDAO(ORDER_DAO);
    private OrderItemDAO orderItemDAO = (OrderItemDAO) daoFactory.getDAO(ORDER_ITEM_DAO);
    private BasketDAO basketDAO = (BasketDAO) daoFactory.getDAO(BASKET_DAO);
    private StatusDAO statusDAO = (StatusDAO) daoFactory.getDAO(STATUS_DAO);
    private BookDAO bookDAO = (BookDAO) daoFactory.getDAO(BOOK_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        HttpSession session = request.getSession(true);
        if(session.getAttribute(USER_ID) == null) {
            response.sendRedirect("loginUser");
        }else {
            Long userId = (Long)session.getAttribute(USER_ID);
            Long localId = (Long)session.getAttribute(LOCAL_ID);
            List<Order> orders = orderDAO.getByUserId(userId);
            Map<Long, List<OrderItem>> orderItemMap = new HashMap<>();
            List<Book> books = bookDAO.getAll(localId);
            List<Status> statuses = statusDAO.getAll(localId);
            Map<Long, String> bookTitleMap = new HashMap<>();
            Map<Long, String> statusMap = new HashMap<>();
            for (Status status:
                 statuses) {
                statusMap.put(status.getId(), status.getTitle());
            }
            for (Book book:
                    books) {
                bookTitleMap.put(book.getId(), book.getTitle());
            }
            for (Order order:
                 orders) {
                List<OrderItem> orderItemList = orderItemDAO.getByOrderId(order.getId());
                orderItemMap.put(order.getId(), orderItemList);
            }
            session.setAttribute("orders", orders);
            session.setAttribute("orderItemMap", orderItemMap);
            session.setAttribute("bookTitleMap", bookTitleMap);
            session.setAttribute("statusMap", statusMap);
            response.sendRedirect(ORDERS_JSP);
        }
    }
}
