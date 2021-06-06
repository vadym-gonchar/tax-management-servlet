package gov.taxation.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import gov.taxation.utils.Prop;
import gov.taxation.utils.PropUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RegistrationCommandTest {

    @Mock
    HttpServletRequest request;
    @Mock
    HttpSession session;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("lang")).thenReturn("en");
    }

    @Test
    public void emptyJSON() throws IOException {
        when((InputStream)request.getInputStream()).thenReturn(getServletInputStream(""));
        String result = new RegistrationCommand().execute(request);
        assertEquals("/WEB-INF/registration.jsp", result);
    }

    @Test
    public void invalidJson() throws IOException {
        String json = "{\"id\":null,\"login\":\"wwfdhadfh\",\"password\":\"vv\",\""
                + PropUtil.getProperty("test.invalid.email\"")
                + "\":\"galskfg\",\"role\":null,\"time\":null";
        when((InputStream)request.getInputStream()).thenReturn(getServletInputStream(json));
        String result = new RegistrationCommand().execute(request);
        assertEquals("/WEB-INF/registration.jsp", result);
    }

    @Test
    public void invalidEmptyFields() throws IOException {
        String json = "{\"id\":null,\"login\":\"\",\"password\":\"vv\",\"email\":\"olo@gmail.com\",\"role\":null,\"time\":null}";
        when((InputStream)request.getInputStream()).thenReturn(getServletInputStream(json));
        String result = new RegistrationCommand().execute(request);
        assertEquals("/WEB-INF/registration.jsp", result);
    }

    @Test
    public void invalidUsernameExists() throws IOException {
        String json = "{\"id\":null,\"login\":\""
                + PropUtil.getProperty("test.user")
                + "\",\"password\":\"vv\",\"email\":\"olo@gmail.com\",\"role\":null,\"time\":null}";
        when((InputStream)request.getInputStream()).thenReturn(getServletInputStream(json));
        String result = new RegistrationCommand().execute(request);
        assertEquals("/WEB-INF/registration.jsp", result);
    }

    @Test
    public void invalidLogin() throws IOException {
        String json = "{\"id\":null,\"login\":\""
                + PropUtil.getProperty("test.invalid.login")
                + "\",\"password\":\"vv\",\"email\":\"olo@gmail.com\",\"role\":null,\"time\":null}";
        when((InputStream)request.getInputStream()).thenReturn(getServletInputStream(json));
        String result = new RegistrationCommand().execute(request);
        assertEquals("/WEB-INF/registration.jsp", result);
    }

    @Test
    public void invalidEmail() throws IOException {
        String json = "{\"id\":null,\"login\":\"wwfdhadfh\",\"password\":\"vv\",\"email\":\""
                + PropUtil.getProperty("test.invalid.email")
                + "\",\"role\":null,\"time\":null}";
        when((InputStream)request.getInputStream()).thenReturn(getServletInputStream(json));
        String result = new RegistrationCommand().execute(request);
        assertEquals("/WEB-INF/registration.jsp", result);
    }

    @Test
    public void invalidPassword() throws IOException {
        String json = "{\"id\":null,\"login\":\"wwfdhadfh\",\"password\":\""
                + PropUtil.getProperty("test.invalid.password")
                + "\",\"email\":\"olo@gmail.com\",\"role\":null,\"time\":null}";
        when((InputStream)request.getInputStream()).thenReturn(getServletInputStream(json));
        String result = new RegistrationCommand().execute(request);
        assertEquals("/WEB-INF/registration.jsp", result);
    }

    private ServletInputStream getServletInputStream(String input) {
        ByteArrayInputStream byteArrayInputStream =
                new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        return new ServletInputStream(){
            public boolean isFinished() {
                return false;
            }
            public boolean isReady() {
                return false;
            }
            public void setReadListener(ReadListener readListener) {

            }
            public int read() {
                return byteArrayInputStream.read();
            }
        };
    }

}
