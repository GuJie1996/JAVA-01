import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) {
        final CyclicBarrier barrier = new CyclicBarrier(1, () -> {
            System.out.println(Task.result);
        });
        new Thread(() -> {
            Task.run();
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
