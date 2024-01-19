import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CashRegister kasa = new CashRegister();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Proszę podać nazwę wartości zakupów w wartości double (np 2.50):");
        double value = keyboard.nextDouble();
        System.out.println("Proszę podać nazwę wartości pieniędzy podanych przez kupującego w " +
                "wartości double (np 2.50):");
        double given = keyboard.nextDouble();
        kasa.getChange(value, given);
    }
}