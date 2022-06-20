package actions.factory;

import actions.actions.*;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static volatile ActionFactory instance = null;


    Map<String, Action> actions = new HashMap<>();

    private ActionFactory() {
        actions.put("/", new ActionIndex());
        actions.put("/loginUser", new LoginUserAction());
        actions.put("/logout", new LogoutAction());
        actions.put("/registerUser", new RegisterUserAction());
        actions.put("/filterBooks", new FilterBooksAction());
        actions.put("/book", new BookAction());
        actions.put("/addToBasket", new AddToBasketAction());
        actions.put("/search", new SearchAction());
        actions.put("/changeUserPassword", new ChangeUserPasswordAction());
        actions.put("/basket", new BasketAction());
        actions.put("/removeFromBasket", new RemoveFromBasketAction());
        actions.put("/checkout", new CheckoutAction());
        actions.put("/orders", new OrdersAction());
        actions.put("/order", new OrderAction());
        actions.put("/ordersAdmin", new OrdersAdminAction());
        actions.put("/orderAdmin", new OrderAdminAction());
        actions.put("/adminChangeStatus", new ChangeOrderStatusAdminAction());
        actions.put("/usersAdmin", new UsersAdminAction());
        actions.put("/adminChangeUserStatus", new ChangeUserStatusAdminAction());
        actions.put("/addGenre", new AddGenreAdminAction());
        actions.put("/addAuthor", new AddAuthorAdminAction());
        actions.put("/preparePublisher", new PreparePublisherAdminAction());
        actions.put("/addPublisher", new AddPublisherAdminAction());
        actions.put("/prepareBookAdd", new PrepareBookAddAction());
        actions.put("/addBook", new AddBookAdminAction());
        actions.put("/changeLanguage", new ChangeLanguageAction());
    }

    public static ActionFactory getInstance() {
        ActionFactory result = instance;
        if(result != null) return result;
        synchronized (ActionFactory.class) {
            if (instance == null) instance = new ActionFactory();
            return instance;
        }
    }

    public Action getAction(String url) {
        return actions.get(url);
    }
}
