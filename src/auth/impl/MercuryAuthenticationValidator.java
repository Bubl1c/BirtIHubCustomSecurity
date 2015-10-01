package auth.impl;

import auth.IAuthenticationValidator;
import http.RequestHelper;
import http.impl.HttpClientRequestHelper;
import http.impl.RestEasyRequestHelper;
import utils.Credentials;
import utils.PropertiesFileReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Developer on 10.07.2015.
 */
public class MercuryAuthenticationValidator implements IAuthenticationValidator {
    public static final String REST_API_URL_PROP_NAME = "auth_validation_url";
    public static final String IS_AUTHENTICATED_METHOD_PATH_PROP_NAME = "auth_validation_method_path";

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
        String restApiUrl = null;
        String methodPath = null;
        try {
            restApiUrl = PropertiesFileReader.get(REST_API_URL_PROP_NAME);
            methodPath = PropertiesFileReader.get(IS_AUTHENTICATED_METHOD_PATH_PROP_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        RequestHelper request = new HttpClientRequestHelper(restApiUrl);
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", securityCode);
        String response = request.get(methodPath, params);
        return Boolean.parseBoolean(response);
    }

    @Override
    public Credentials getCredentials(String securityCode) {
        return null;
    }
}
