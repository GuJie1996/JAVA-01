import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class HttpClientDemo {

    private static final String URL = "http://localhost:8801";
    private static final int TIME_OUT = 60_000;
    private static final long RUN_SECONDS = 30;
    private static final int THREADS = 5;

    public static void main(String[] args) {
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIME_OUT)
                .setConnectTimeout(TIME_OUT).build();
        long startMillis = System.currentTimeMillis();
        long timeoutMillis = TimeUnit.SECONDS.toMillis(RUN_SECONDS);
        long endMillis = startMillis + timeoutMillis;
        AtomicInteger count = new AtomicInteger();
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        for (int i = 0; i < THREADS; i++) {
            executorService.execute(() -> {
                try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                    while (System.currentTimeMillis() < endMillis && !Thread.interrupted()) {
                        doGet(httpClient, requestConfig);
                        count.incrementAndGet();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        // 停止接收新任务，不然一直不结束
        executorService.shutdown();
        try {
            if (executorService.awaitTermination(RUN_SECONDS, TimeUnit.SECONDS)) {
                System.out.println("所有线程执行结束");
            } else {
                System.out.println("任务没执行完，时间到");
                // 给所有在执行的线程中断信号
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("总共%d次请求", count.get());
    }

    private static void doGet(CloseableHttpClient httpClient, RequestConfig requestConfig) {
        HttpGet httpGet = new HttpGet(URL);
        httpGet.setConfig(requestConfig);
        try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
            System.out.printf("status:%s\ncontent:%s%n",
                    httpResponse.getStatusLine(),
                    EntityUtils.toString(httpResponse.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
