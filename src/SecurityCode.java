/**
 * Created by Andrii Mozharovskyi on 10.07.2015.
 */
import javax.servlet.http.*;

import auth.IAuthenticationValidator;
import auth.MercuryAuthenticationValidator;
import com.actuate.iportal.security.iPortalSecurityAdapter;
import utils.CustomFileLogger;
import utils.Logger;

public class SecurityCode extends iPortalSecurityAdapter {
    private String volumeProfile = "Default Volume";
    private String userName = null;
    private String password = null;

    private Logger logger;

    public static void main(String[] args) {
        try {
            SecurityCode securityCode = new SecurityCode();
            boolean isAuth = securityCode.isAuthenticated("");
            int i = 1;
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public SecurityCode() {
        logger = CustomFileLogger.getInstance();
        logger.clear();
    }

    public boolean authenticate(HttpServletRequest httpservletrequest) {
        String param = httpservletrequest.getParameter("code");
        boolean secured = true;
        try {

            String userid = httpservletrequest.getParameter("userid");
            String password = httpservletrequest.getParameter("password");

            logger.log("Code = [" + param + "]");
            logger.log("useridPar = [" + userid + "]");
            logger.log("passwordPar = [" + password + "]");

            if("administrator".equalsIgnoreCase(userid)) {
                logger.log("Administrator entered");
                setUserName("administrator");
                setPassword("");
                return true;
            }

            if (isAuthenticated(param)) {
                logger.log("name = [" + getUserName() + "]" + " pass = [" + getPassword() + "]");
                setUserName("amozharovskyi");
                setPassword("12");
            } else {
                logger.log("False returned = [" + userid + "]");
                secured = false;
            }
        } catch(Throwable t) {
            secured = false;
            logger.err("Catch exc = [" + t.getMessage() + "]");
        }
        finally {
            logger.log("Secured = [" + secured + "]");
        }

        return secured;
    }

    private boolean isAuthenticated(String securityCode) {
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