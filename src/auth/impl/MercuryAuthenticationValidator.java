package auth.impl;

import auth.IAuthenticationValidator;
import http.RequestHelper;
import http.impl.HttpClientRequestHelper;
import http.impl.RestEasyRequestHelper;
import utils.Credentials;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Developer on 10.07.2015.
 */
public class MercuryAuthenticationValidator implements IAuthenticationValidator {

    public static final String REST_API_URL = "http://192.168.1.139:8080/scheduler-server/emlogis/rest";
    public static final String IS_AUTHENTICATED_METHOD_PATH = "/sessions/isauthenticated";

//    @Override
//    public boolean isAuthenticated(String securityCode) {
//        RequestHelper request = new RestEasyRequestHelper(REST_API_URL);
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("token", securityCode);
//        String response = request.get(IS_AUTHENTICATED_METHOD_PATH, params);
//        return Boolean.parseBoolean(response);
//    }

    @Override
    public boolean isAuthenticated(String securityCode) {
        RequestHelper request = new HttpClientRequestHelper(REST_API_URL);
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", securityCode);
        String response = request.get(IS_AUTHENTICATED_METHOD_PATH, params);
        return Boolean.parseBoolean(response);
    }

    @Override
    public Credentials getCredentials(String securityCode) {
        return null;
    }
}
