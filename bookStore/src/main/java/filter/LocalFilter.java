package filter;

import database.dao.factory.DAOFactory;
import database.dao.interfaces.LocalDAO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static database.dao.factory.DAOEnum.LOCAL_DAO;
import static util.EssentialVars.*;

public class LocalFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
        //todo write init params in web xml
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest)(request)).getSession(true);
        Long localId = null;
        if(session.getAttribute(LOCAL_ID) != null)
            localId = Long.parseLong(session.getAttribute(LOCAL_ID).toString());
        String local = (String)session.getAttribute(LOCAL);
        if(localId == null || local == null) {
            session.setAttribute(LOCAL_ID, 1L);
            session.setAttribute(LOCAL, "en_US");
        }
        chain.doFilter(request, response);
    }
}
