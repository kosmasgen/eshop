import java.util.Scanner;

public class MainOrders {
    public static void run() {  // Προσθήκη της μεθόδου run()
        OrdersRepository ordersRepository = new OrdersRepository();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nΕπιλογές:");
            System.out.println("1. Δημιουργία Νέας Παραγγελίας");
            System.out.println("2. Υπολογισμός Τζίρου Προμηθευτή");
            System.out.println("3. Έξοδος");
            System.out.print("Επιλογή: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Λογική για Δημιουργία Νέας Παραγγελίας
                    break;
                case 2:
                    // Λογική για Υπολογισμό Τζίρου Προμηθευτή
                    break;
                case 3:
                    System.out.println("Έξοδος...");
                    return;
                default:
                    System.out.println("Μη έγκυρη επιλογή, παρακαλώ προσπαθήστε ξανά.");
            }
        }
    }
}
