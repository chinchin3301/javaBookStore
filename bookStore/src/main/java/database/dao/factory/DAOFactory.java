package database.dao.factory;

import database.dao.implement.*;
import database.dao.interfaces.GenericDAO;

import java.util.HashMap;
import java.util.Map;
import static database.dao.factory.DAOEnum.*;

public class DAOFactory {
    private static volatile DAOFactory instance = null;
    private static Map<Enum, GenericDAO> map = new HashMap<>();

    private DAOFactory() {
        init();
    }
    private static void init() {
        map.put(AUTHOR_DAO, new AuthorDAOImpl());
        map.put(BASKET_DAO, new BasketDAOImpl());
        map.put(BOOK_AUTHOR_DAO, new BookAuthorDAOImpl());
        map.put(BOOK_DAO, new BookDAOImpl());
        map.put(BOOK_GENRE_DAO, new BookGenreDAOImpl());
        map.put(COUNTRY_DAO, new CountryDAOImpl());
        map.put(LANGUAGE_DAO, new LanguageDAOImpl());
        map.put(LOCAL_DAO, new LocalDAOImpl());
        map.put(ORDER_DAO, new OrderDAOImpl());
        map.put(ORDER_ITEM_DAO, new OrderItemDAOImpl());
        map.put(PUBLISHER_DAO, new PublisherDAOImpl());
        map.put(STATUS_DAO, new StatusDAOImpl());
        map.put(USER_DAO, new UserDAOImpl());

    }

    public static DAOFactory getInstance() {
        DAOFactory result = instance;
        if(result != null) return result;


        synchronized (DAOFactory.class) {
            if(instance == null) {
                instance = new DAOFactory();
            }
            return instance;
        }
    }

    public GenericDAO getDAO(Enum e) {
        return map.get(e);
    }
}
