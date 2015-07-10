package http; /**
 * Created by Andrii Mozharovskyi on 10.07.2015.
 */

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RequestHelper {
    private String restApiUrl = "";
    private Client client;

    public RequestHelper(String restApiUrl) {
        client = ClientBuilder.newClient();
        if (restApiUrl != null) {
            this.restApiUrl = restApiUrl;
        }
    }

    public String get(String path, Map<String, String> params) {
        return get(path, params, MediaType.APPLICATION_JSON_TYPE);
    }

    public String get(String path, Map<String, String> params, MediaType mediaType) {
        WebTarget target = client.target(restApiUrl + "/" + path + buildURLParamsString(params));
//        Response response = target.request(MediaType.APPLICATION_JSON).header("EmlogisToken", token).get();
        Response response = target.request(mediaType).get();
        String result = response.readEntity(String.class);
        response.close();
        return result;
    }

    private String buildURLParamsString(Map<String, String> params) {
        if(params == null || params.isEmpty()) {
            return "";
        }
        StringBuilder paramsString = new StringBuilder("?");
        for(Map.Entry<String, String> entry : params.entrySet()){
            paramsString.append(entry.getKey());
            paramsString.append("=");
            paramsString.append(entry.getValue());
            paramsString.append("&");
        }
        if(paramsString.charAt(paramsString.length()-1) == '&'){
            paramsString.deleteCharAt(paramsString.length()-1);
        }
        return paramsString.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

        try {
            client.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
