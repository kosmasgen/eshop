import java.sql.Connection;
import java.sql.Statement;

public class CreateTableSuppliers {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Εντολή SQL για τη δημιουργία του πίνακα suppliers
            String sql = "CREATE TABLE IF NOT EXISTS suppliers (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "first_name VARCHAR(50) NOT NULL, " +
                    "last_name VARCHAR(50) NOT NULL, " +
                    "telephone VARCHAR(15) NOT NULL, " +
                    "afm VARCHAR(15) NOT NULL UNIQUE, " +
                    "location VARCHAR(100)" + // Προσθήκη στήλης τοποθεσίας
                    ");";

            // Εκτέλεση της SQL εντολής
            stmt.executeUpdate(sql);
            System.out.println("Ο πίνακας 'suppliers' δημιουργήθηκε επιτυχώς!");

        } catch (Exception e) {
            System.out.println("Παρουσιάστηκε σφάλμα κατά τη δημιουργία του πίνακα:");
            e.printStackTrace();
        }
    }
}
