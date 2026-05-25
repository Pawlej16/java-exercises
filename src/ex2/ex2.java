package ex2;

record ServiceOrder(String clientName, int hours, double hourRate) {}

interface PriceStrategy {
    double calculate(ServiceOrder order);
}

class PriceCalculator {
    public double calculate(ServiceOrder order, PriceStrategy strategy) {
        return strategy.calculate(order);
    }
}

public class ex2 {
    public static void main(String[] args) {
        ServiceOrder order = new ServiceOrder("Alpha Company", 10, 120.0);
        PriceCalculator calculator = new PriceCalculator();

        PriceStrategy standard = o -> o.hours() * o.hourRate();
        PriceStrategy discount = o -> o.hours() * o.hourRate() * 0.90;
        PriceStrategy weekend  = o -> o.hours() * o.hourRate() * 1.25;

        System.out.printf("Standard: %.2f PLN%n", calculator.calculate(order, standard));
        System.out.printf("Discount: %.2f PLN%n", calculator.calculate(order, discount));
        System.out.printf("Weekend:  %.2f PLN%n", calculator.calculate(order, weekend));
    }
}