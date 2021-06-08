package gov.taxation.command;

import gov.taxation.dao.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Logging out functionality
 */
public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        logger.info("Success logout user:" + ((User) session.getAttribute("principal")).getUserName());

        CommandUtility.deleteUserFromSession(session);
        return "redirect:/login";
    }
}

