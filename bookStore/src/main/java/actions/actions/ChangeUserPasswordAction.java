package actions.actions;

import actions.helpers.UChecker;
import database.dao.factory.DAOFactory;
import database.dao.interfaces.UserDAO;
import entity.User;
import org.apache.commons.codec.digest.DigestUtils;

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

public class ChangeUserPasswordAction implements Action{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserDAO userDAO = (UserDAO) daoFactory.getDAO(USER_DAO);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        HttpSession session = request.getSession(false);
        if(session.getAttribute(USER_ID) == null) {
            response.sendRedirect("error.jsp");
        }else {
            String oldPassword = request.getParameter("old_password");
            String newPassword = request.getParameter("new_password");
            Long userId = (Long)session.getAttribute(USER_ID);
            User user = userDAO.getById(userId);
            String hashedOldPassword = DigestUtils.md5Hex(oldPassword);
            String hashedNewPassword = DigestUtils.md5Hex(newPassword);
            if(!user.getPassword().equals(hashedOldPassword)) {
                response.sendRedirect(ERROR_JSP);
            }else if(!UChecker.isCheckPassword(newPassword)){
                response.sendRedirect(ERROR_JSP);
            }else {
                userDAO.changePassword(userId, hashedNewPassword);
                response.sendRedirect(INDEX_JSP);
            }
        }
    }
}
