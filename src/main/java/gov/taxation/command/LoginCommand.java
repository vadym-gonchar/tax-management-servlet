package gov.taxation.command;

import gov.taxation.dao.DBException;
import gov.taxation.dao.entity.User;
import gov.taxation.service.UserService;
import gov.taxation.utils.Prop;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Logging in functionality
 * Get username and password as input, make validation.
 * Authentication: by username and password, and check if user already logged in
 * Authorization by role
 * <p>
 * Set errorMsg if:
 * - username does not match (cannot be found in DB)
 * - password does not match (BCrypt encoding)
 */
public class LoginCommand implements Command {
    private final UserService userService;

    public LoginCommand() {
        this.userService = new UserService();
    }

    /**
     * Validation and Authentication by username
     *
     * @param request request
     * @return page
     * @throws ServletException if role not user or admin
     */
    @Override
    public String execute(HttpServletRequest request) throws ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        logger.info(username + " " + password);

        if (username == null || username.equals("") || password == null || password.equals("")) {
            return "/WEB-INF/login.jsp";
        }
        User user;
        try {
            HttpSession session = request.getSession();
            user = userService.findByUsername(username);
            if (!BCrypt.checkpw(password, user.getPassword())) {
                String errorMsg = Prop.getDBProperty("select.login.byLogin.dbe.pass") + user.getUserName();
                logger.warn(errorMsg);
                request.setAttribute("errorMsg", errorMsg);
                return "/WEB-INF/login.jsp";
            }
            CommandUtility.setUserIntoSession(request, user);
            session.setAttribute("authenticated", true);

            logger.info("User logged successfully, user:" + user.getUserName());

            return "redirect:/home";

        } catch (DBException e) {
            logger.warn(e.getMessage());
            request.setAttribute("errorMsg", e.getMessage());

            return "/WEB-INF/login.jsp";
        }

    }

}

