import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SuppliersProductsInsertion {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) { // Χρήση της getConnection()

            // Εισαγωγή ή ενημέρωση προμηθευτών-προϊόντων
            insertOrUpdateSupplierProduct(conn, 4, 1, 10);
            insertOrUpdateSupplierProduct(conn, 5, 2, 15);
            insertOrUpdateSupplierProduct(conn, 6, 3, 20);

        } catch (SQLException e) {
            System.out.println("Παρουσιάστηκε σφάλμα κατά τη σύνδεση ή την εισαγωγή δεδομένων.");
            e.printStackTrace();
        }
    }

    public static void insertOrUpdateSupplierProduct(Connection conn, int supplierId, int productId, int quantity) {
        // SQL εντολή για εισαγωγή ή ενημέρωση
        String sql = "INSERT INTO suppliers_products (supplier_id, product_id, Quantity) " +
                "VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE Quantity = Quantity + VALUES(Quantity)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, supplierId); // Θέτουμε το supplier_id
            pstmt.setInt(2, productId); // Θέτουμε το product_id
            pstmt.setInt(3, quantity);  // Θέτουμε την ποσότητα

            // Εκτέλεση της εντολής
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Η εγγραφή ενημερώθηκε ή προστέθηκε επιτυχώς.");
            }
        } catch (SQLException e) {
            System.out.println("Σφάλμα κατά την εισαγωγή ή την ενημέρωση του προϊόντος: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
