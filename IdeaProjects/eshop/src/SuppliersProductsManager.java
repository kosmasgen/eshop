import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SuppliersProductsManager {

    // Ενημέρωση ποσότητας
    public void updateQuantity(int supplierId, int productId, int changeInQuantity) throws SQLException {
        String sql = "UPDATE suppliers_products SET Quantity = Quantity + ? WHERE supplier_id = ? AND product_id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); // Χρήση της σωστής μεθόδου
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, changeInQuantity);
            pstmt.setInt(2, supplierId);
            pstmt.setInt(3, productId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Η ποσότητα ενημερώθηκε επιτυχώς.");
            } else {
                System.out.println("Δεν βρέθηκε καταχώρηση για ενημέρωση.");
            }
        }
    }

    public static void main(String[] args) {
        SuppliersProductsManager manager = new SuppliersProductsManager();

        try {
            // Παράδειγμα ενημέρωσης ποσότητας
            manager.updateQuantity(4, 1, 5); // Προσθήκη 5 μονάδων για supplier_id 4 και product_id 1
            manager.updateQuantity(5, 2, -3); // Αφαίρεση 3 μονάδων για supplier_id 5 και product_id 2
        } catch (SQLException e) {
            System.out.println("Σφάλμα κατά την ενημέρωση της ποσότητας:");
            e.printStackTrace();
        }
    }
}
