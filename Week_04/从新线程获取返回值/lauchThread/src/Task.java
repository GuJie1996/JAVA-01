public class Task {

    public static volatile String result = null;

    public static String run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            result = "task failed";
        }
        result = "task finished";
        return result;
    }

}
