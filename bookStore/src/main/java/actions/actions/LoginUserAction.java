package actions.actions;

import database.dao.factory.DAOFactory;
import database.dao.interfaces.UserDAO;
import entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static database.dao.factory.DAOEnum.USER_DAO;
import static util.JSPPages.ERROR_JSP;
import static util.JSPPages.INDEX_JSP;
import static util.EssentialVars.*;

public class LoginUserAction implements Action{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserDAO userDAO = (UserDAO) daoFactory.getDAO(USER_DAO);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        HttpSession session = request.getSession(true);
        if(session.getAttribute(USER_ID) != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(INDEX_JSP);
            dispatcher.forward(request, response);
        }else {
            String email = (String)request.getParameter("email");
            String password = (String)request.getParameter("password");
            password = DigestUtils.md5Hex(password);
            if(userDAO.getByEmailPassword(email, password).getId() != null && userDAO.getByEmailPassword(email, password).isIs_banned() != true) {
                User user = userDAO.getByEmailPassword(email, password);
                session.setAttribute(USER_ID, user.getId());
                session.setAttribute(IS_ADMIN, user.isIs_admin());

                RequestDispatcher dispatcher = request.getRequestDispatcher(INDEX_JSP);
                dispatcher.forward(request, response);
            }else {
                RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_JSP);
                dispatcher.forward(request, response);
            }
        }
    }
}
