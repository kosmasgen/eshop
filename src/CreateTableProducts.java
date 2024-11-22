import java.sql.Connection;
import java.sql.Statement;

public class CreateTableProducts {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection(); // Χρήση της σωστής μεθόδου
             Statement stmt = conn.createStatement()) {

            // Εντολή SQL για τη δημιουργία του πίνακα
            String sql = "CREATE TABLE IF NOT EXISTS products ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "product_name VARCHAR(50) NOT NULL, " // Μεγαλύτερο όνομα προϊόντος
                    + "type VARCHAR(50), "                  // Πεδίο τύπου προϊόντος
                    + "price FLOAT(10,2) NOT NULL, "        // Τιμή με ακρίβεια δύο δεκαδικών
                    + "supplier_id INT, "
                    + "quantity INT NOT NULL DEFAULT 0, "   // Αρχικό απόθεμα 0
                    + "uuid CHAR(36) NOT NULL UNIQUE, "     // Προσθήκη στήλης UUID
                    + "FOREIGN KEY (supplier_id) REFERENCES suppliers(id) ON DELETE SET NULL ON UPDATE CASCADE"
                    + ");";

            // Εκτέλεση της εντολής SQL
            stmt.executeUpdate(sql);
            System.out.println("Ο πίνακας 'products' δημιουργήθηκε επιτυχώς με τη στήλη 'uuid'!");

        } catch (Exception e) {
            System.out.println("Παρουσιάστηκε σφάλμα κατά τη σύνδεση ή τη δημιουργία του πίνακα.");
            e.printStackTrace();
        }
    }
}
