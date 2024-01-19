import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CashRegister {
    private Map<Double, Integer> denominationCounts;

    public CashRegister() {
        this.denominationCounts = new HashMap<>();
        initializeDenominationCounts();
    }
    private void initializeDenominationCounts() {
        double[] denominations = {500.00, 200.00, 100.00, 50.00, 20.00, 10.00, 5.00, 2.00, 1.00,
                0.5, 0.2, 0.1, 0.05, 0.02, 0.01};
        for (double denomination : denominations) {
            denominationCounts.put(denomination, 5);
        }
    }
    public void addDenomination(double denomination, int count) {
        int currentCount = denominationCounts.get(denomination);
        denominationCounts.put(denomination, currentCount + count);
        System.out.println("dodano " + count + " " + denomination + "zł do kasy fiskalnej.");
    }
    public void removeDenomination(double denomination, int count) {
        int currentCount = denominationCounts.get(denomination);
        if (currentCount < count) {
            throw new IllegalArgumentException("nie ma wystarczająco" + denomination + "złotówek w kasie fiskalnej");
        }
        denominationCounts.put(denomination, currentCount - count);
        System.out.println("oddano " + count + " " + denomination + "zł z kasy fiskalnej.");
    }
    public int getDenominationCount(double denomination) {
        return denominationCounts.get(denomination);
    }
    public double getTotalAmount() {
        double totalAmount = 0.0;
        for (double denomination : denominationCounts.keySet()) {
            totalAmount += denomination * denominationCounts.get(denomination);
        }
        return totalAmount;
    }
    public Map<Double, Integer> getChange(double purchaseAmount, double paymentAmount) {
        double changeAmount = paymentAmount - purchaseAmount;
        if (changeAmount < 0) {
            throw new IllegalArgumentException("Niewystarczająco pieniędzy do zakupu");
        }
        Map<Double, Integer> change = new TreeMap<>((d1, d2) -> Double.compare(d2, d1));
        for (double denomination : denominationCounts.keySet()) {
            int count = (int) (changeAmount / denomination);
            if (count > 0 && count <= denominationCounts.get(denomination)) {
                change.put(denomination, count);
                changeAmount -= count * denomination;
            }
        }
        if (changeAmount > 0) {
            throw new IllegalArgumentException("Nie ma wystarczająco pieniędzy w kasie aby wydać dokładną resztę");
        }
        for (double denomination : change.keySet()) {
            removeDenomination(denomination, change.get(denomination));
        }
        System.out.println("Pieniądze w reszcie:");
        for (double denomination : change.keySet()) {
            System.out.println(denomination + " złoty(ch): " + change.get(denomination) + " denomination(s)");
        }
        return change;
    }
}