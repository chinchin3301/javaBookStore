package actions.actions;

import database.dao.factory.DAOFactory;
import database.dao.interfaces.*;
import entity.Book;
import entity.BookAuthor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static database.dao.factory.DAOEnum.*;
import static database.dao.factory.DAOEnum.AUTHOR_DAO;
import static util.JSPPages.ERROR_JSP;
import static util.EssentialVars.*;

public class AddBookAdminAction implements Action{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private OrderDAO orderDAO = (OrderDAO) daoFactory.getDAO(ORDER_DAO);
    private OrderItemDAO orderItemDAO = (OrderItemDAO) daoFactory.getDAO(ORDER_ITEM_DAO);
    private BasketDAO basketDAO = (BasketDAO) daoFactory.getDAO(BASKET_DAO);
    private StatusDAO statusDAO = (StatusDAO) daoFactory.getDAO(STATUS_DAO);
    private BookDAO bookDAO = (BookDAO) daoFactory.getDAO(BOOK_DAO);
    private UserDAO userDAO = (UserDAO) daoFactory.getDAO(USER_DAO);
    private BookGenreDAO bookGenreDAO = (BookGenreDAO) daoFactory.getDAO(BOOK_GENRE_DAO);
    private AuthorDAO authorDAO = (AuthorDAO) daoFactory.getDAO(AUTHOR_DAO);
    private BookAuthorDAO bookAuthorDAO = (BookAuthorDAO) daoFactory.getDAO(BOOK_AUTHOR_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        HttpSession session = request.getSession(true);
        if(session.getAttribute(USER_ID) == null || session.getAttribute(IS_ADMIN) == null
                || session.getAttribute(IS_ADMIN).equals(false)){
            response.sendRedirect(ERROR_JSP);
        }else {
            Long localId = (Long) session.getAttribute(LOCAL_ID);
            String newBookTitle = request.getParameter("new_book_title");
            String newBookDescription = request.getParameter("new_book_description");
            Long newBookPrice = Long.parseLong(request.getParameter("new_book_price"));
            Long newBookCount = Long.parseLong(request.getParameter("new_book_count"));
            Long newBookPublisherId = Long.parseLong(request.getParameter("new_book_publisher_id"));
            Long newBookLanguageId = Long.parseLong(request.getParameter("new_book_language_id"));
            Long newBookGenreId = Long.parseLong(request.getParameter("new_book_genre_id"));
            String[] rawAuthorId = request.getParameterValues("new_book_author_id");
            Long[] authorId = new Long[0];
            if(rawAuthorId != null) {
                authorId = new Long[rawAuthorId.length];
                for (int i = 0; i < rawAuthorId.length; i++) {
                    authorId[i] = Long.parseLong(rawAuthorId[i]);
                }
            }
            if(newBookCount <= 0l || newBookPrice <= 0l){
                response.sendRedirect(ERROR_JSP);
            }else {
                Book book = new Book();
                book.setTitle(newBookTitle);
                book.setDescription(newBookDescription);
                book.setPrice(newBookPrice);
                book.setCount(newBookCount);
                book.setLocalsId(localId);
                book.setLanguagesId(newBookLanguageId);
                book.setGenreId(newBookGenreId);
                book.setPublishersId(newBookPublisherId);
                bookDAO.create(book);
                List<Long> books = bookDAO.getBookId(newBookTitle, localId);
                book = bookDAO.getById(books.get(books.size() - 1));
                for (int i = 0; i < authorId.length; i++) {
                    BookAuthor bookAuthor = new BookAuthor();
                    bookAuthor.setBooksId(book.getId());
                    bookAuthor.setAuthorsId(authorId[i]);
                    bookAuthorDAO.create(bookAuthor);
                }
                response.sendRedirect("filterBooks");
            }
        }
    }
}
