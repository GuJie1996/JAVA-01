package java0.nio01.client;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtil {

    private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom().setSocketTimeout(60_000)
            .setConnectTimeout(60_000).build();

    public static String doGet(HttpGet httpGet) {
        IOException resultException = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            httpGet.setConfig(REQUEST_CONFIG);
            try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
                return EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (IOException e) {
            resultException = e;
        }
        return resultException.getMessage();
    }

}