package actions.actions;

import database.dao.factory.DAOFactory;
import database.dao.interfaces.LocalDAO;
import entity.Local;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static database.dao.factory.DAOEnum.LOCAL_DAO;
import static util.EssentialVars.LOCALS;
import static util.JSPPages.INDEX_JSP;

public class ActionIndex implements Action{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private LocalDAO localDAO = (LocalDAO) daoFactory.getDAO(LOCAL_DAO);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        HttpSession session = request.getSession(true);
        if(request.getParameter(LOCALS) == null) {
            List<Local> locals = localDAO.getAll();
            session.setAttribute(LOCALS, locals);
        }
        response.sendRedirect(INDEX_JSP);
    }
}
