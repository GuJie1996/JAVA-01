import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    static final Semaphore SEMAPHORE = new Semaphore(1);

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            Task.run();
            SEMAPHORE.release();
        });
        try {
            SEMAPHORE.acquire();
            thread.start();
            SEMAPHORE.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            SEMAPHORE.release();
        }
        System.out.println(Task.result);
    }

}
