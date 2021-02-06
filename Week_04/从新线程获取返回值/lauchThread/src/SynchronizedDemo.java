public class SynchronizedDemo {

    static final Object OBJ = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (OBJ) {
                Task.run();
                OBJ.notifyAll();
            }
        }).start();
        synchronized (OBJ) {
            while (Task.result == null) {
                try {
                    OBJ.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(Task.result);
    }

}
