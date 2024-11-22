import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsRepository {

    // Μέθοδος για τη σύνδεση με τη βάση δεδομένων
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection();
    }

    // Αναζήτηση προϊόντων με προμηθευτές και ποσότητες
    public List<String> findProductsWithSuppliers(String keyword) {
        List<String> results = new ArrayList<>();
        String sql = "SELECT p.product_name, s.first_name, s.last_name, sp.quantity " +
                "FROM products p " +
                "LEFT JOIN suppliers_products sp ON p.id = sp.product_id " +
                "LEFT JOIN suppliers s ON sp.supplier_id = s.id " +
                "WHERE p.product_name LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + keyword + "%"); // Χρήση LIKE για αναζήτηση μερικού ονόματος
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String productName = rs.getString("product_name");
                String supplierName = rs.getString("first_name") + " " + rs.getString("last_name");
                int quantity = rs.getInt("quantity");

                // Αν δεν υπάρχει προμηθευτής
                if (supplierName.trim().equals("null null")) {
                    results.add("Προϊόν: " + productName + " - Δεν βρέθηκαν προμηθευτές");
                } else {
                    results.add("Προϊόν: " + productName +
                            ", Προμηθευτής: " + supplierName +
                            ", Ποσότητα: " + quantity);
                }
            }
        } catch (SQLException e) {
            System.err.println("Σφάλμα κατά την εκτέλεση του SQL: " + e.getMessage());
            e.printStackTrace();
        }
        return results;
    }
}
