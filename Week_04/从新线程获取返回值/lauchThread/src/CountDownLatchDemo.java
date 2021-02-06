import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            Task.run();
            COUNT_DOWN_LATCH.countDown();
        });
        thread.start();
        try {
            COUNT_DOWN_LATCH.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Task.result);
    }

}
