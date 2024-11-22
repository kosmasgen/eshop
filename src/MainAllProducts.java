import java.util.List;
import java.util.Scanner;

public class MainAllProducts {
    public static void run() {  // Προσθήκη της μεθόδου run()
        ProductsRepository repo = new ProductsRepository();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Διαχείριση Προϊόντων ===");
            System.out.println("1. Αναζήτηση Προϊόντος");
            System.out.println("2. Εμφάνιση Όλων των Προϊόντων");
            System.out.println("0. Έξοδος");
            System.out.print("Επιλέξτε μια επιλογή: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Μη έγκυρη επιλογή. Παρακαλώ εισάγετε έναν αριθμό.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Εισάγετε όνομα ή τμήμα ονόματος για αναζήτηση: ");
                    String keyword = scanner.nextLine();
                    List<String> results = repo.findProductsWithSuppliers(keyword);

                    if (!results.isEmpty()) {
                        System.out.println("Αποτελέσματα Αναζήτησης:");
                        results.forEach(System.out::println);
                    } else {
                        System.out.println("Δεν βρέθηκαν προϊόντα.");
                    }
                }
                case 2 -> {
                    List<String> allProducts = repo.findProductsWithSuppliers("");
                    if (!allProducts.isEmpty()) {
                        System.out.println("Λίστα Όλων των Προϊόντων:");
                        allProducts.forEach(System.out::println);
                    } else {
                        System.out.println("Δεν υπάρχουν προϊόντα στη βάση δεδομένων.");
                    }
                }
                case 0 -> {
                    System.out.println("Έξοδος...");
                    return;
                }
                default -> System.out.println("Μη έγκυρη επιλογή. Δοκιμάστε ξανά.");
            }
        }
    }
}
