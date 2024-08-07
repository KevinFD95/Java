package classes;

public class Calculator {

    private double num1;
    private double num2;

    public Calculator(double num1, double num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public double plus() {
        return num1 + num2;
    }

    public double minus() {
        return num1 - num2;
    }

    public double multi() {
        return num1 * num2;
    }

    public double split() {

        if (num2 != 0) {
            return num1 / num2;
        }
        else {
            return 0;
        }
    }
}