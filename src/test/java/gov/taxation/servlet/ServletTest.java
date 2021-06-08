package gov.taxation.servlet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import gov.taxation.utils.PropUtil;

import static org.mockito.Mockito.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;

@RunWith(MockitoJUnitRunner.class)
public class ServletTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    HttpSession session;
    @Mock
    ServletContext servletContext;
    @Mock
    ServletConfig servletConfig;

    Servlet servlet;

    @Before
    public void setUp(){
        servlet = new Servlet();
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        servlet.init(servletConfig);
    }

    @Test
    public void testServletLogin() throws IOException, ServletException {
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getServletContext()).thenReturn(servletContext);

        when(request.getRequestURI()).thenReturn("/login");
        when(request.getParameter("username")).thenReturn(PropUtil.getProperty("test.user"));
        when(request.getParameter("password")).thenReturn(PropUtil.getProperty("test.pass"));

        when(request.getRequestDispatcher("/WEB-INF/login.jsp")).thenReturn(dispatcher);

        servlet.init(servletConfig);
        servlet.doPost(request, response);
    }
}