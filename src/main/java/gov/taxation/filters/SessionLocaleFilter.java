package gov.taxation.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * if session attribute "lang" is absent - set "en"
 * if request gets parameter "lang" - set this parameter in session attribute
 * does not need validation - initial language is English
 */
@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
public class SessionLocaleFilter implements Filter {
    @Override
    public void init(FilterConfig arg0) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getParameter("locale") != null) {
            req.getSession().setAttribute("lang", req.getParameter("locale"));
        } else if (req.getSession().getAttribute("lang") == null) {
            req.getSession().setAttribute("lang", "en");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}

