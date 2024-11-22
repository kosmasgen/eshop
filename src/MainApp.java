import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nΚεντρικό Μενού:");
            System.out.println("1. Πελάτες");
            System.out.println("2. Προμηθευτές");
            System.out.println("3. Προϊόντα");
            System.out.println("4. Παραγγελίες");
            System.out.println("5. Έξοδος");
            System.out.print("Επιλογή: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    MainAllCustomers.run();  // Υπο-μενού Πελατών
                    break;
                case 2:
                    MainAllSuppliers

                            .run();  // Υπο-μενού Προμηθευτών
                    break;
                case 3:
                    MainAllProducts.run();   // Υπο-μενού Προϊόντων
                    break;
                case 4:
                    MainOrders.run();        // Υπο-μενού Παραγγελιών
                    break;
                case 5:
                    System.out.println("Έξοδος από την εφαρμογή...");
                    scanner.close();
                    return; // Τερματισμός εφαρμογής
                default:
                    System.out.println("Μη έγκυρη επιλογή. Προσπαθήστε ξανά.");
            }
        }
    }
}
