package gov.taxation.servlet;

import gov.taxation.command.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Servlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Servlet.class);

    private final Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {

        commands.put("login", new LoginCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("logout", new LogOutCommand());
        commands.put("home", new MainCommand());

        commands.put("create", new ReportCreateCommand());
        commands.put("openReportForUpdate", new SelectReportForUpdateCommand());
        commands.put("updateDo", new ReportUpdateDoCommand());
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {

        String path = request.getRequestURI();
        logger.info(path);
        path = path.replaceAll(";jsessionid.*", "");
        path = StringUtils.stripStart(path, "/");
        logger.info(path);

        Command command = commands.getOrDefault(path, (r) -> "/WEB-INF/index.jsp");
        String page = command.execute(request);

        logger.info(command.getClass().getName());
        logger.info(request.getSession().getServletContext().getAttribute("loggedUsers"));

        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
