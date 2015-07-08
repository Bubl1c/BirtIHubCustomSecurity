
import javax.servlet.http.*;

import com.actuate.iportal.security.iPortalSecurityAdapter;

public class SecurityCode extends iPortalSecurityAdapter {
    private String volumeProfile = "CustomAccess";
    private String userName = null;
    private String password = null;

    public SecurityCode() {
    }

    public boolean authenticate(
            HttpServletRequest httpservletrequest) {
        String param = httpservletrequest.getParameter("code");
        boolean secured = true;
        if ("12345".equalsIgnoreCase(param)) {
            userName = "user1";
            password = "user1";
        } else if ("abc".equalsIgnoreCase(param)) {
            userName = "BasicUser";
            password = "";
        } else {
            secured = false;
        }
        return secured;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getVolumeProfile() {
        return volumeProfile;
    }
}