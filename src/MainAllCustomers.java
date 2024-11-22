import java.util.List;
import java.util.Scanner;

public class MainAllCustomers {

    public static void run() {
        CustomersRepository repo = new CustomersRepository();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Διαχείριση Πελατών ===");
            System.out.println("1. Προσθήκη Νέου Πελάτη");
            System.out.println("2. Εμφάνιση Όλων των Πελατών");
            System.out.println("3. Αναζήτηση Πελάτη");
            System.out.println("0. Επιστροφή στο Κεντρικό Μενού");
            System.out.print("Επιλέξτε μια επιλογή: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Καθαρισμός buffer

            switch (choice) {
                case 1 -> {
                    // Προσθήκη νέου πελάτη
                    System.out.print("Όνομα: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Επώνυμο: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Τηλέφωνο: ");
                    String telephone = scanner.nextLine();
                    System.out.print("ΑΦΜ: ");
                    String afm = scanner.nextLine();
                    System.out.print("Χονδρική (true/false): ");
                    boolean wholesale = scanner.nextBoolean();
                    System.out.print("Υπόλοιπο: ");
                    double balance = scanner.nextDouble();
                    scanner.nextLine(); // Καθαρισμός buffer

                    ClassCustomers newCustomer = new ClassCustomers(firstName, lastName, telephone, afm, wholesale, balance);
                    int id = repo.create(newCustomer);
                    if (id != -1) {
                        System.out.println("Ο πελάτης προστέθηκε επιτυχώς με ID: " + id);
                    } else {
                        System.out.println("Αποτυχία προσθήκης πελάτη.");
                    }
                }
                case 2 -> {
                    // Εμφάνιση όλων των πελατών
                    List<ClassCustomers> customers = repo.findAll();
                    if (!customers.isEmpty()) {
                        System.out.println("Λίστα Πελατών:");
                        customers.forEach(System.out::println);
                    } else {
                        System.out.println("Δεν βρέθηκαν πελάτες.");
                    }
                }
                case 3 -> {
                    // Αναζήτηση πελάτη
                    System.out.print("Δώστε λέξη-κλειδί για αναζήτηση: ");
                    String keyword = scanner.nextLine();
                    List<ClassCustomers> searchResults = repo.search(keyword);
                    if (!searchResults.isEmpty()) {
                        System.out.println("Αποτελέσματα Αναζήτησης:");
                        searchResults.forEach(System.out::println);
                    } else {
                        System.out.println("Δεν βρέθηκαν πελάτες που να ταιριάζουν με την αναζήτηση.");
                    }
                }
                case 0 -> {
                    // Επιστροφή στο κεντρικό μενού
                    System.out.println("Επιστροφή στο Κεντρικό Μενού...");
                    return;
                }
                default -> System.out.println("Μη έγκυρη επιλογή. Δοκιμάστε ξανά.");
            }
        }
    }
}
