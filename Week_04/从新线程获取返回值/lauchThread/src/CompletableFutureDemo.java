import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {

    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(Task::run);
        future.thenAccept(System.out::println);
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
