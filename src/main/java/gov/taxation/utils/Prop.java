package gov.taxation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

/**
 * Get Strings from properties files
 */
public class Prop {
    private static final Logger logger = LogManager.getLogger(Prop.class);

    private static final String PATH = "/";
    private static final String PROPERTIES = PATH + "application.properties";
    private static final String DB = PATH + "db.properties";

    private static String getter(String propName, String source) {
        try (InputStream is = Prop.class.getResourceAsStream(source)) {
            Properties p = new Properties();
            p.load(is);
            return (String) p.get(propName);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static String getProperty(String propName) {
        return getter(propName, PROPERTIES);
    }

    public static String getDBProperty(String propName) {
        return getter(propName, DB);
    }
}

