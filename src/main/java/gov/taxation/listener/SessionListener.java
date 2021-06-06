package gov.taxation.listener;

import gov.taxation.command.CommandUtility;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * logs out user if the session ends (30 minutes by default)
 */
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        CommandUtility.deleteUserFromSession(httpSessionEvent.getSession());
    }
}
