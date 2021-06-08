package gov.taxation.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.taxation.dao.DBException;
import gov.taxation.dao.entity.User;
import gov.taxation.service.UserService;
import gov.taxation.utils.JsonBody;
import gov.taxation.utils.Prop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RegistrationCommand implements Command {
    Logger logger = LogManager.getLogger(Command.class);
    private final UserService userService;

    public RegistrationCommand() {
        this.userService = new UserService();
    }

    /**
     * Get json string as input data, parse it into User class and write to DB
     * Set errorMsg:
     * - if catch DBException
     * - if cannot get json or it is invalid
     *
     * @param request request
     * @return registration page
     */
    @Override
    public String execute(HttpServletRequest request) {
        try {
            String json = JsonBody.getBody(request);
            logger.info(json);
            if (json.equals("")) {
                return "/WEB-INF/registration.jsp";
            }
            User user = new ObjectMapper().readValue(json, User.class);
            if (Validator.invalidRegistration(user, request)) {
                return "/WEB-INF/registration.jsp";
            }
            logger.info("Create new user:" + user.getUserName());
            logger.info(user.toString());

            userService.create(user);
            return "/WEB-INF/login.jsp";
        } catch (DBException e) {
            String errorMsg = Prop.getDBProperty("invalid.username");
            request.setAttribute("errorMsg", errorMsg);
            logger.warn(errorMsg);
        } catch (IOException e) {
            String errorMsg = Prop.getDBProperty("error.json");
            request.setAttribute("errorMsg", Prop.getDBProperty("error.try.again"));
            logger.error(errorMsg);
        }
        return "/WEB-INF/registration.jsp";
    }

}

