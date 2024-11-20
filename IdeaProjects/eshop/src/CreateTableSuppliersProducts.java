import java.sql.Connection;
import java.sql.Statement;

public class CreateTableSuppliersProducts {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection(); // Χρήση της σωστής μεθόδου
             Statement stmt = conn.createStatement()) {

            // SQL εντολή για τη δημιουργία του πίνακα
            String sql = "CREATE TABLE IF NOT EXISTS suppliers_products (" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "supplier_id INT NOT NULL, " +
                    "product_id INT NOT NULL, " +
                    "quantity INT NOT NULL DEFAULT 0, " + // Αρχική ποσότητα 0
                    "PRIMARY KEY (id), " +
                    "FOREIGN KEY (supplier_id) REFERENCES suppliers(id) ON DELETE CASCADE ON UPDATE CASCADE, " +
                    "FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");";

            // Εκτέλεση της SQL εντολής
            stmt.executeUpdate(sql);
            System.out.println("Ο πίνακας 'suppliers_products' δημιουργήθηκε επιτυχώς!");

        } catch (Exception e) {
            System.out.println("Παρουσιάστηκε σφάλμα κατά τη δημιουργία του πίνακα:");
            e.printStackTrace();
        }
    }
}
