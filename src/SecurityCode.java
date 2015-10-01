/**
 * Created by Andrii Mozharovskyi on 10.07.2015.
 */
import javax.servlet.http.*;

import auth.IAuthenticationValidator;
import auth.impl.MercuryAuthenticationValidator;
import com.actuate.iportal.security.iPortalSecurityAdapter;
import utils.PropertiesFileReader;
import utils.logging.impl.CustomFileLogger;
import utils.logging.Logger;

public class SecurityCode extends iPortalSecurityAdapter {
    private String volumeProfile = PropertiesFileReader.get("volume_profile");
    private String userName = null;
    private String password = null;

    public Logger logger;

    public static void main(String[] args) {
        try {
            SecurityCode securityCode = new SecurityCode();
            boolean isAuth = securityCode.isAuthenticated("d95bae23-fe18-42fd-a942-b667be1e99c1");
            securityCode.logger.err(new Exception("Except"));
            securityCode.logger.log(isAuth+"");
            System.out.println(isAuth);
            int i = 1;
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public SecurityCode() {
        logger = CustomFileLogger.getInstance();
        logger.setDebug(Boolean.valueOf(PropertiesFileReader.get("debugging")));
        if(Boolean.valueOf(PropertiesFileReader.get("clear_logs_on_server_restart"))) {
            logger.clear();
        }
    }

    public boolean authenticate(HttpServletRequest httpservletrequest) {
        String ADMIN_USR = PropertiesFileReader.get("admin_username");
        String ADMIN_PWD = "";
        String securityTokenName = PropertiesFileReader.get("security_token_parameter_name");

        String param = httpservletrequest.getParameter(securityTokenName);
        boolean secured = true;
        try {

            String userid = httpservletrequest.getParameter("userid");
            String password = httpservletrequest.getParameter("password");

            logger.log("Code = [" + param + "]");
            logger.log("useridPar = [" + userid + "]");
            logger.log("passwordPar = [" + password + "]");

            if("administrator".equalsIgnoreCase(userid)) {
                logger.log("Authenticated as administrator");
                setUserName(ADMIN_USR);
                setPassword(ADMIN_PWD);
                return true;
            }

            if (isAuthenticated(param)) {
                logger.log("Authenticated as user!");
                setUserName(ADMIN_USR);
                setPassword(ADMIN_PWD);
            } else {
                logger.log("NOT Authenticated");
                secured = false;
            }
        } catch(Throwable t) {
            secured = false;
            logger.err(t);
        }
        finally {
            logger.log("Secured = [" + secured + "]");
        }

        return secured;
    }

    public boolean isAuthenticated(String securityCode) {
        if(securityCode == null || securityCode.equals("")) {
            return false;
        }
        IAuthenticationValidator validator = new MercuryAuthenticationValidator();
        return validator.isAuthenticated(securityCode);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getVolumeProfile() {
        return volumeProfile;
    }
}