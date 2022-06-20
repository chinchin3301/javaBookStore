package actions.actions;

import database.dao.factory.DAOFactory;
import database.dao.interfaces.BasketDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static database.dao.factory.DAOEnum.BASKET_DAO;
import static util.JSPPages.ERROR_JSP;
import static util.EssentialVars.*;
public class RemoveFromBasketAction implements Action{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private BasketDAO basketDAO = (BasketDAO) daoFactory.getDAO(BASKET_DAO);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        HttpSession session = request.getSession(true);
        if(session.getAttribute(USER_ID) == null) {
            response.sendRedirect(ERROR_JSP);
        }else {
            Long userId = (Long)session.getAttribute(USER_ID);
            if(request.getParameter("basketId") == null) {
                response.sendRedirect(ERROR_JSP);
            }else {
                Long basketId = Long.parseLong(request.getParameter("basketId"));
                basketDAO.delete(basketId);
                response.sendRedirect("basket");
            }
        }
    }
}
