public class JoinDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(Task::run);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Task.result);
    }

}
