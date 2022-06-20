package actions.actions;

import database.dao.factory.DAOFactory;
import database.dao.interfaces.LocalDAO;
import entity.Local;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static database.dao.factory.DAOEnum.LOCAL_DAO;
import static util.EssentialVars.LOCAL;
import static util.EssentialVars.LOCAL_ID;
import static util.JSPPages.INDEX_JSP;

public class ChangeLanguageAction implements Action{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private LocalDAO localDAO = (LocalDAO) daoFactory.getDAO(LOCAL_DAO);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        HttpSession session = request.getSession(true);
        Long newLocalId = Long.parseLong(request.getParameter("new_local_id"));
        Local newLocal = localDAO.getById(newLocalId);
        session.setAttribute(LOCAL, newLocal.getShortName());
        session.setAttribute(LOCAL_ID, newLocalId);
        response.sendRedirect(INDEX_JSP);
    }
}
