import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    static final ReentrantLock LOCK = new ReentrantLock();
    static final Condition CONDITION = LOCK.newCondition();

    public static void main(String[] args) {
        new Thread(() -> {
            LOCK.lock();
            try {
                Task.run();
                CONDITION.signalAll();
            } finally {
                LOCK.unlock();
            }
        }).start();
        LOCK.lock();
        try {
            // 循环放在lock后，无论哪个线程先拿到锁，都不至于死锁
            while (Task.result == null) {
                CONDITION.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
        System.out.println(Task.result);
    }

}
