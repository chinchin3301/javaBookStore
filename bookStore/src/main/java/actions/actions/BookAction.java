package actions.actions;

import database.dao.factory.DAOFactory;
import database.dao.interfaces.BookDAO;
import entity.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static database.dao.factory.DAOEnum.BOOK_DAO;
import static util.JSPPages.BOOK_JSP;
import static util.JSPPages.ERROR_JSP;
import static util.EssentialVars.*;

public class BookAction implements Action{

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private BookDAO bookDAO = (BookDAO) daoFactory.getDAO(BOOK_DAO);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        HttpSession session = request.getSession(false);
        if(session == null || request.getParameter("bookId") == null) response.sendRedirect(ERROR_JSP);
        else {
            Long bookId = Long.parseLong(request.getParameter("bookId"));
            Long localId = (Long) session.getAttribute(LOCAL_ID);

            Book book = bookDAO.getById(bookId);
            session.setAttribute("aBook", book);
            response.sendRedirect(BOOK_JSP);
        }
    }
}
