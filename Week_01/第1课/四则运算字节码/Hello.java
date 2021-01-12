public class Hello {

    private int count = 0;
    private double result = 1;

    public static void main(String[] args) {
        Hello hello = new Hello();
        for (;hello.autoIncrease();) {
            hello.calc();
        }
        System.out.println(hello.getResult());
    }

    public double getResult() {
        return result;
    }

    public void calc() {
        switch (count % 4) {
            case 0:
                result += 1;
                break;
            case 1:
                result -= 0.5;
                break;
            case 2:
                result *= result;
                break;
            case 3:
                result /= 1.25;
        }
    }

    public boolean autoIncrease() {
        return count++ < 8;
    }

}
