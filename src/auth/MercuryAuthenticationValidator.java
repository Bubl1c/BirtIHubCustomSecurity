package auth;

import http.RequestHelper;
import utils.Credentials;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Developer on 10.07.2015.
 */
public class MercuryAuthenticationValidator implements IAuthenticationValidator {

    public static final String REST_API_URL = "http://192.168.1.138:8080/scheduler-server/emlogis/rest";
    public static final String IS_AUTHENTICATED_METHOD_PATH = "sessions/isauthenticated";

    @Override
    public boolean isAuthenticated(String securityCode) {
        RequestHelper request = new RequestHelper(REST_API_URL);
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", securityCode);
        String response = request.get(IS_AUTHENTICATED_METHOD_PATH, params, MediaType.TEXT_PLAIN_TYPE);
        return Boolean.parseBoolean(response);
    }

    @Override
    public Credentials getCredentials(String securityCode) {
        return null;
    }
}
