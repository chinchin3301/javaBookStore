package actions.actions;

import actions.helpers.DateConverter;
import actions.helpers.UChecker;
import database.dao.factory.DAOFactory;
import database.dao.interfaces.UserDAO;
import entity.User;
import org.apache.commons.codec.digest.DigestUtils;
/*import org.mindrot.jbcrypt.BCrypt;*/

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

public class RegisterUserAction implements Action{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserDAO userDAO = (UserDAO) daoFactory.getDAO(USER_DAO);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        HttpSession session = request.getSession(true);

        if(session.getAttribute(USER_ID) != null) {
            response.sendRedirect(INDEX_JSP);
        }else {
            if (request.getParameter("email") == null || userDAO.isEmailRegistered(request.getParameter("email")) == true) {
                response.sendRedirect(ERROR_JSP);
            } else if (request.getParameter("password") == null ||
                    UChecker.isCheckPassword(request.getParameter("password")) != true) {
                response.sendRedirect(ERROR_JSP);
            } else if (request.getParameter("phonenumber") == null ||
                    UChecker.isCheckPhone(request.getParameter("phonenumber")) != true) {
                response.sendRedirect(ERROR_JSP);
            }else {

                User user = new User();
                user.setEmail(request.getParameter("email"));
                String hashedPassword = DigestUtils.md5Hex(request.getParameter("password"));
                user.setPassword(hashedPassword);
                user.setName(request.getParameter("name"));
                user.setSurname(request.getParameter("surname"));
                user.setPhonenumber(request.getParameter("phonenumber"));
                user.setBirthday(DateConverter.StringToDate(request.getParameter("birthday")));
                user.setAddress(request.getParameter("address"));
                user.setIs_banned(false);
                user.setIs_admin(false);
                userDAO.create(user);
                user = userDAO.getByEmailPassword(request.getParameter("email"), hashedPassword);
                session.setAttribute(USER_ID, user.getId());
                session.setAttribute(IS_ADMIN, user.isIs_admin());
                response.sendRedirect(INDEX_JSP);
            }
        }
    }
}
