package auth;

import utils.Credentials;

/**
 * Created by Andrii Mozharovskyi on 10.07.2015.
 */
public interface IAuthenticationValidator {

    public boolean isAuthenticated(String securityCode);

    public Credentials getCredentials(String securityCode);

}
