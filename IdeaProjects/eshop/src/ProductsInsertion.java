import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ProductsInsertion {

    public static void insertProduct(String productName, String type, int quantity, double price, int supplierId) {
        String sql = "INSERT INTO products (product_name, type, Quantity, price, supplier_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); // Χρήση της σωστής μεθόδου
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, productName);
            pstmt.setString(2, type);
            pstmt.setInt(3, quantity);
            pstmt.setDouble(4, price);
            pstmt.setInt(5, supplierId);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Το προϊόν καταχωρήθηκε επιτυχώς!");
            }

        } catch (Exception e) {
            System.out.println("Παρουσιάστηκε σφάλμα κατά την εισαγωγή του προϊόντος:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Ζήτηση δεδομένων από τον χρήστη
            System.out.print("Δώστε το όνομα του προϊόντος: ");
            String productName = scanner.nextLine();

            // Ζήτηση κατηγορίας από τον χρήστη
            System.out.println("Επιλέξτε κατηγορία: ");
            System.out.println("1. Υπολογιστές");
            System.out.println("2. Περιφερειακά");
            System.out.println("3. Υδραυλικά");

            int categoryChoice = scanner.nextInt();
            scanner.nextLine(); // Καθαρισμός της εισόδου μετά το nextInt()

            String type;
            switch (categoryChoice) {
                case 1:
                    type = "Υπολογιστές";
                    break;
                case 2:
                    type = "Περιφερειακά";
                    break;
                case 3:
                    type = "Υδραυλικά";
                    break;
                default:
                    System.out.println("Μη έγκυρη επιλογή. Η κατηγορία θα οριστεί ως 'Άγνωστη'.");
                    type = "Άγνωστη";
            }

            System.out.print("Δώστε την ποσότητα του προϊόντος: ");
            int quantity = scanner.nextInt();

            System.out.print("Δώστε την τιμή του προϊόντος: ");
            double price = scanner.nextDouble();

            System.out.print("Δώστε το ID του προμηθευτή: ");
            int supplierId = scanner.nextInt();

            // Κλήση της μεθόδου εισαγωγής προϊόντος
            insertProduct(productName, type, quantity, price, supplierId);

        } catch (Exception e) {
            System.out.println("Παρουσιάστηκε σφάλμα κατά την εισαγωγή των δεδομένων:");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
