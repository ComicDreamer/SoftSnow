package cuculi.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fr.idm.sk.publish.api.client.light.SkPublishAPI;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.springframework.web.client.RestTemplate;

public class GetWord {
    static RestTemplate restTemplate = new RestTemplate();

//    @Value("${collins.baseurl}")
//    static String base;

    public static void main(String[] args) throws Exception{
        SkPublishAPI api = generateApi();
        String snow = api.getEntry("english", "snow_1", null);
        System.out.println(snow);
    }

    public static SkPublishAPI generateApi(){
        String baseUrl = "https://api.collinsdictionary.com/api/v1";
        String accessKey = "ltVXq35UiId5jcQm9YmpV7ZoSvm7n3tHygquJ7NuptZCVEnTC2YWbnOhYxenqeKi";
        DefaultHttpClient httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager());
        SkPublishAPI api = new SkPublishAPI(baseUrl, accessKey, httpClient);
        api.setRequestHandler(new SkPublishAPI.RequestHandler() {
            @Override
            public void prepareGetRequest(HttpGet request) {
                System.out.println(request.getURI());
                request.setHeader("Accept", "application/json");
            }
        });
        return api;
    }
}
