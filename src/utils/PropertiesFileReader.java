package utils;

import utils.logging.Logger;
import utils.logging.impl.CustomFileLogger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Andrii Mozharovskyi on 01.10.2015.
 */
public class PropertiesFileReader {

    private static final String PROPERTIES_FILE_NAME = "ihub_custom_security_config.properties";

    public static String get(String propertyName){
        Logger logger = CustomFileLogger.getInstance();
        InputStream inputStream = null;
        try {
            Properties prop = new Properties();
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                logger.err(new FileNotFoundException("Properties file '" + PROPERTIES_FILE_NAME + "' is not found in the classpath!"));
                return null;
            }
            return prop.getProperty(propertyName);
        } catch (Exception e) {
            logger.err(e);
            return null;
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.err(e);
                }
            }
        }

    }
}
