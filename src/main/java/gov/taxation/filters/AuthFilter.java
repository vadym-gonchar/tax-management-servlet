package gov.taxation.filters;

import gov.taxation.dao.entity.RoleType;
import gov.taxation.dao.entity.User;
import gov.taxation.utils.Prop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    Logger logger = LogManager.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        logger.info(session);
        logger.info(session.getAttribute("principal"));

        String path = request.getRequestURI();

        RoleType role = session.getAttribute("principal") == null ? null : ((User) session.getAttribute("principal")).getRole();

        if (role == null) {
            if (path.equals("") || path.equals("/") || path.contains("/login") || path.contains("/registration") || path.contains("/js/")) {
                // Allow anonymous users login entry points
            } else {
                throw new ServletException(Prop.getDBProperty("error.forbidden.page"));
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}

