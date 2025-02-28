import java.util.Scanner;

abstract class Account {
    double amount;
    abstract double calculateInterest();
}

class FDAccount extends Account {
    int days, age;
    FDAccount(double amount, int days, int age) {
        if (amount < 0 || days < 0 || age < 0) throw new IllegalArgumentException("Invalid input.");
        this.amount = amount;
        this.days = days;
        this.age = age;
    }
    double calculateInterest() {
        double[][] rates = {{4.5, 5}, {4.75, 5.25}, {5.5, 6}, {7, 7.5}, {7.5, 8}, {8, 8.5}};
        int[] range = {14, 29, 45, 60, 184, 365};
        for (int i = 0; i < range.length; i++) {
            if (days <= range[i]) return amount * rates[i][age >= 60 ? 1 : 0] / 100;
        }
        return 0;
    }
}

class SBAccount extends Account {
    boolean isNRI;
    SBAccount(double amount, boolean isNRI) {
        if (amount < 0) throw new IllegalArgumentException("Invalid amount.");
        this.amount = amount;
        this.isNRI = isNRI;
    }
    double calculateInterest() {
        return amount * (isNRI ? 6 : 4) / 100;
    }
}

class RDAccount extends Account {
    int months;
    RDAccount(double amount, int months) {
        if (amount < 0 || months < 0) throw new IllegalArgumentException("Invalid input.");
        this.amount = amount;
        this.months = months;
    }
    double calculateInterest() {
        double[] rates = {7.5, 7.75, 8, 8.25, 8.5, 8.75};
        int[] range = {6, 9, 12, 15, 18, 21};
        for (int i = 0; i < range.length; i++) {
            if (months == range[i]) return amount * rates[i] * months / 100;
        }
        return 0;
    }
}

public class InterestCalculator {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("1. SB Interest\n2. FD Interest\n3. RD Interest\n4. Exit");
            int choice = s.nextInt();
            if (choice == 4) break;
            try {
                System.out.println("Enter amount:");
                double amount = s.nextDouble();
                switch (choice) {
                    case 1:
                        System.out.println("NRI? (true/false):");
                        boolean isNRI = s.nextBoolean();
                        System.out.println("Interest: Rs. " + new SBAccount(amount, isNRI).calculateInterest());
                        break;
                    case 2:
                        System.out.println("Days:");
                        int days = s.nextInt();
                        System.out.println("Age:");
                        int age = s.nextInt();
                        System.out.println("Interest: Rs. " + new FDAccount(amount, days, age).calculateInterest());
                        break;
                    case 3:
                        System.out.println("Months:");
                        int months = s.nextInt();
                        System.out.println("Interest: Rs. " + new RDAccount(amount, months).calculateInterest());
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        s.close();
    }
}
