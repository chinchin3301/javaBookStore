package filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private String encoding = "UTF-8";
    private String contentType = "text/html";
    public void init(FilterConfig config) throws ServletException {
        String encodingParam = config.getInitParameter("encoding");
        String contentTypeParam = config.getInitParameter("contentType");

        if(encodingParam != null)
            encoding = encodingParam;
        if(contentTypeParam != null)
            contentType = contentTypeParam;
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding(encoding);
        response.setContentType(contentType);
        chain.doFilter(request, response);
    }
}
