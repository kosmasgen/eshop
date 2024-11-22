import java.util.List;
import java.util.Scanner;

public class MainAllSuppliers {
    public static void run() {  // Προσθήκη της μεθόδου run()
        SuppliersRepository repo = new SuppliersRepository();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Διαχείριση Προμηθευτών ===");
            System.out.println("1. Προσθήκη Προμηθευτή");
            System.out.println("2. Εμφάνιση Όλων των Προμηθευτών");
            System.out.println("3. Αναζήτηση Προμηθευτή");
            System.out.println("0. Έξοδος");
            System.out.print("Επιλέξτε μια επιλογή: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Καθαρισμός buffer

            switch (choice) {
                case 1 -> {
                    System.out.print("Όνομα: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Επώνυμο: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Τηλέφωνο: ");
                    String telephone = scanner.nextLine();
                    System.out.print("ΑΦΜ: ");
                    String afm = scanner.nextLine();
                    System.out.print("Τοποθεσία: ");
                    String location = scanner.nextLine();

                    ClassSuppliers newSupplier = new ClassSuppliers(firstName, lastName, telephone, afm, location);
                    int id = repo.create(newSupplier);
                    if (id != -1) {
                        System.out.println("Ο προμηθευτής προστέθηκε επιτυχώς με ID: " + id);
                    } else {
                        System.out.println("Αποτυχία προσθήκης προμηθευτή.");
                    }
                }
                case 2 -> {
                    List<ClassSuppliers> suppliers = repo.findAll();
                    if (!suppliers.isEmpty()) {
                        System.out.println("Λίστα Προμηθευτών:");
                        suppliers.forEach(System.out::println);
                    } else {
                        System.out.println("Δεν βρέθηκαν προμηθευτές.");
                    }
                }
                case 3 -> {
                    System.out.print("Δώστε λέξη-κλειδί για αναζήτηση: ");
                    String keyword = scanner.nextLine();
                    List<ClassSuppliers> searchResults = repo.search(keyword);
                    if (!searchResults.isEmpty()) {
                        System.out.println("Αποτελέσματα Αναζήτησης:");
                        searchResults.forEach(System.out::println);
                    } else {
                        System.out.println("Δεν βρέθηκαν προμηθευτές που να ταιριάζουν με την αναζήτηση.");
                    }
                }
                case 0 -> {
                    System.out.println("Έξοδος...");
                    return; // Επιστροφή στο Κεντρικό Μενού
                }
                default -> System.out.println("Μη έγκυρη επιλογή. Δοκιμάστε ξανά.");
            }
        }
    }
}
