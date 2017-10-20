class Computer {
    public void calculrate() {
        System.out.println("computer calculating");
    }

    public void calculrate(int a, int b) {
    int result = a+b;
        System.out.println("overloading test " + result);
    }
}

public class OverLoading {
    public static void main(String[] args) {
        Computer com = new Computer();
        com.calculrate();
        com.calculrate(2, 3);
    }
}
