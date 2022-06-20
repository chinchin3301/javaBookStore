package actions.actions;

import database.dao.factory.DAOFactory;
import database.dao.interfaces.*;
import entity.Book;
import entity.OrderItem;

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
import static database.dao.factory.DAOEnum.USER_DAO;
import static util.JSPPages.ADMIN_ORDER_JSP;
import static util.JSPPages.ERROR_JSP;
import static util.EssentialVars.*;

public class OrderAdminAction implements Action{
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
            Long orderId = Long.parseLong(request.getParameter("orderId"));
            Long localId = (Long)session.getAttribute(LOCAL_ID);
            List<Book> books = bookDAO.getAll(localId);
            List<OrderItem> orderItemList = orderItemDAO.getByOrderId(orderId);
            Map<Long, String> bookTitleMap = new HashMap<>();
            for (Book book:
                    books) {
                bookTitleMap.put(book.getId(), book.getTitle());
            }
            session.setAttribute("adminOrderItemList", orderItemList);
            session.setAttribute("adminBookTitleMap", bookTitleMap);
            response.sendRedirect(ADMIN_ORDER_JSP);
        }

    }
}
