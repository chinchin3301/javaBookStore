package controller;

import actions.actions.Action;
import actions.factory.ActionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class ControlServlet extends HttpServlet {
    private Logger logger = LogManager.getLogger(this.getClass().getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionFactory actionFactory = ActionFactory.getInstance();
        Action action = actionFactory.getAction(request.getRequestURI());

        try{
            action.execute(request, response);
        }catch (SQLException e) {
            logger.warn(e);
        }catch (Exception e) {
            logger.warn(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
