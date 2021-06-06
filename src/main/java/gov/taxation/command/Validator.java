package gov.taxation.command;

import gov.taxation.dao.entity.User;
import gov.taxation.utils.Prop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final Logger logger = LogManager.getLogger(Validator.class);
    private final static String LOGIN_REGEX = "^[a-zA-Z0-9]{2,}$";
    private final static String EMAIL_REGEX = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$";
    private final static String PASS_REGEX = "^[a-z0-9._%+-]{2,6}$";

    /**
     * Set errors into request and log it (with validation logger),
     * and returns true because it is invalid branch end
     *
     * @param request request
     * @param error   error string
     * @return true
     */
    static boolean setErrorAndLog(HttpServletRequest request, String error) {
        String errorMsg = Prop.getDBProperty(error);
        request.setAttribute("errorMsg", errorMsg);
        logger.warn(errorMsg);
        return true;
    }

    private static boolean doesNotMatch(HttpServletRequest request, String regex, String arg, String error) {
        Matcher m;
        m = Pattern.compile(regex).matcher(arg);
        if (!m.matches()) {
            return setErrorAndLog(request, error);
        }
        return false;
    }

    /**
     * Validation for null or empty fields
     *
     * @param request request
     * @param args    input fields
     * @return true if invalid, false if valid
     */
    static boolean hasEmptyFields(HttpServletRequest request, String... args) {
        for (String s : args) {
            if (s == null || s.equals("")) {
                return setErrorAndLog(request, "invalid.fields");
            }
        }
        return false;
    }

    /**
     * Registration validation (for empty fields and with regex)
     *
     * @param user    input fields
     * @param request request
     * @return true if invalid, false if valid
     */
    static boolean invalidRegistration(User user, HttpServletRequest request) {
        return hasEmptyFields(request, user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail())
                || doesNotMatch(request, LOGIN_REGEX, user.getUserName(), "invalid.login")
                || doesNotMatch(request, EMAIL_REGEX, user.getEmail(), "invalid.email")
                || doesNotMatch(request, PASS_REGEX, user.getPassword(), "invalid.password");
    }

}

