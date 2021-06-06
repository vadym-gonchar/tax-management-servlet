package gov.taxation.command;

import gov.taxation.dao.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * context has all logged users
 * session has current user as principal and user language
 */
public class CommandUtility {
    /**
     * Set 'principal' in user session
     *
     * @param request request
     * @param user    user
     */
    public static void setUserIntoSession(HttpServletRequest request, User user) {
        request.getSession().setAttribute("principal", user);
    }

    /**
     * Delete a user from context list of logged users
     * if a user logs out or if session time expires.
     *
     * @param session user session
     */
    public static void deleteUserFromSession(HttpSession session) {
        session.removeAttribute("principal");
    }

    /**
     * Get 'principal' from session.
     *
     * @param request request
     * @return User user
     */
    public static User getUserFromSession(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("principal");
    }
}