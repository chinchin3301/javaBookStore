package actions.actions;

import database.dao.factory.DAOFactory;
import database.dao.interfaces.*;
import entity.Order;
import entity.Status;
import entity.User;

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
import static util.JSPPages.ADMIN_ORDERS_JSP;
import static util.JSPPages.ERROR_JSP;
import static util.EssentialVars.*;

public class OrdersAdminAction implements Action{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private OrderDAO orderDAO = (OrderDAO) daoFactory.getDAO(ORDER_DAO);
    private OrderItemDAO orderItemDAO = (OrderItemDAO) daoFactory.getDAO(ORDER_ITEM_DAO);
    private BasketDAO basketDAO = (BasketDAO) daoFactory.getDAO(BASKET_DAO);
    private StatusDAO statusDAO = (StatusDAO) daoFactory.getDAO(STATUS_DAO);
    private BookDAO bookDAO = (BookDAO) daoFactory.getDAO(BOOK_DAO);
    private UserDAO userDAO = (UserDAO) daoFactory.getDAO(USER_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        HttpSession session = request.getSession(true);
        if(session.getAttribute(USER_ID) == null || session.getAttribute(IS_ADMIN) == null
        || session.getAttribute(IS_ADMIN).equals(false)){
            response.sendRedirect(ERROR_JSP);
        }else {
            Long userId = (Long)session.getAttribute(USER_ID);
            Long localId = (Long)session.getAttribute(LOCAL_ID);
            List<Order> orders = orderDAO.getAll();
            List<User> users = userDAO.getAll();
            List<Status> statuses = statusDAO.getAll(localId);
            Map<Long, String> userEmailMap = new HashMap<>();
            Map<Long, String> statusMap = new HashMap<>();
            for (User user:
                 users) {
                userEmailMap.put(user.getId(), user.getEmail());
            }
            for (Status status:
                 statuses) {
                statusMap.put(status.getId(), status.getTitle());
            }
            session.setAttribute("adminOrders", orders);
            session.setAttribute("adminUserEmailMap", userEmailMap);
            session.setAttribute("adminStatusMap", statusMap);
            response.sendRedirect(ADMIN_ORDERS_JSP);
        }
    }
}
