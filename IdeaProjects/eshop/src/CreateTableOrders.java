import java.sql.Connection;
import java.sql.Statement;

public class CreateTableOrders {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS orders (" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "supplier_id INT NOT NULL, " +
                    "product_id INT NOT NULL, " +
                    "order_quantity INT NOT NULL, " +
                    "total_price DECIMAL(10, 2) NOT NULL, " +
                    "PRIMARY KEY (id), " +
                    "FOREIGN KEY (supplier_id) REFERENCES suppliers(id) ON DELETE CASCADE ON UPDATE CASCADE, " +
                    "FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");";

            stmt.executeUpdate(sql);
            System.out.println("Ο πίνακας 'orders' δημιουργήθηκε επιτυχώς!");

        } catch (Exception e) {
            System.out.println("Παρουσιάστηκε σφάλμα κατά τη δημιουργία του πίνακα:");
            e.printStackTrace();
        }
    }
}
