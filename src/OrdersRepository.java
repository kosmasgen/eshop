import java.sql.*;
import java.time.LocalDateTime;

public class OrdersRepository {

    // Έλεγχος αν υπάρχει ο προμηθευτής
    public boolean supplierExists(int supplierId) {
        String sql = "SELECT COUNT(*) FROM suppliers WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, supplierId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Σφάλμα κατά την αναζήτηση του προμηθευτή: " + e.getMessage());
        }
        return false;
    }

    // Έλεγχος αν υπάρχει το προϊόν
    public boolean productExists(int productId) {
        String sql = "SELECT COUNT(*) FROM products WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Σφάλμα κατά την αναζήτηση του προϊόντος: " + e.getMessage());
        }
        return false;
    }

    // Έλεγχος αν υπάρχει επαρκής ποσότητα προϊόντος
    public boolean isQuantityAvailable(int productId, int requestedQuantity) {
        String sql = "SELECT quantity FROM products WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int availableQuantity = rs.getInt("quantity");
                return availableQuantity >= requestedQuantity;
            }
        } catch (SQLException e) {
            System.err.println("Σφάλμα κατά την αναζήτηση ποσότητας: " + e.getMessage());
        }
        return false;
    }

    // Ανάκτηση τιμής προϊόντος
    public double getProductPrice(int productId) {
        String sql = "SELECT price FROM products WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("price");
            }
        } catch (SQLException e) {
            System.err.println("Σφάλμα κατά την ανάκτηση της τιμής του προϊόντος: " + e.getMessage());
        }
        return 0.0;
    }

    // Ενημέρωση ποσότητας προϊόντος μετά την παραγγελία
    public void updateProductQuantity(int productId, int orderedQuantity) {
        String sql = "UPDATE products SET quantity = quantity - ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderedQuantity);
            pstmt.setInt(2, productId);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Η ποσότητα του προϊόντος ενημερώθηκε επιτυχώς.");
            } else {
                System.out.println("Αποτυχία ενημέρωσης της ποσότητας του προϊόντος.");
            }

        } catch (SQLException e) {
            System.err.println("Σφάλμα κατά την ενημέρωση της ποσότητας του προϊόντος: " + e.getMessage());
        }
    }

    // Μέθοδος για εισαγωγή παραγγελίας
    public void insertOrder(ClassOrders order) {
        String sql = "INSERT INTO orders (supplier_id, product_id, quantity, price, total_price, created_at) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            double totalPrice = order.getQuantity() * order.getPrice(); // Υπολογισμός συνολικού κόστους

            pstmt.setInt(1, order.getSupplierId());
            pstmt.setInt(2, order.getProductId());
            pstmt.setInt(3, order.getQuantity());
            pstmt.setDouble(4, order.getPrice());
            pstmt.setDouble(5, totalPrice);
            pstmt.setTimestamp(6, Timestamp.valueOf(order.getCreated_at()));

            pstmt.executeUpdate();
            System.out.println("Η παραγγελία εισήχθη επιτυχώς!");

        } catch (SQLException e) {
            System.err.println("Σφάλμα κατά την εισαγωγή παραγγελίας: " + e.getMessage());
        }
    }

    // Υπολογισμός τζίρου για συγκεκριμένο διάστημα
    public double calculateSupplierRevenue(int supplierId, LocalDateTime startDate, LocalDateTime endDate) {
        String sql = "SELECT SUM(o.total_price) AS total_revenue " +
                "FROM orders o " +
                "WHERE o.supplier_id = ? AND o.created_at BETWEEN ? AND ?";

        double totalRevenue = 0;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Εμφάνιση παραμέτρων για debugging
            System.out.println("Εκτέλεση SQL Ερωτήματος: " + sql);
            System.out.println("ID Προμηθευτή: " + supplierId);
            System.out.println("Ημερομηνία Έναρξης: " + Timestamp.valueOf(startDate));
            System.out.println("Ημερομηνία Λήξης: " + Timestamp.valueOf(endDate));

            pstmt.setInt(1, supplierId);
            pstmt.setTimestamp(2, Timestamp.valueOf(startDate));
            pstmt.setTimestamp(3, Timestamp.valueOf(endDate));

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                totalRevenue = rs.getDouble("total_revenue");
                System.out.println("Συνολικός Τζίρος από Ερώτημα: " + totalRevenue); // Debugging
            } else {
                System.out.println("Δεν επιστράφηκαν αποτελέσματα για τον συγκεκριμένο προμηθευτή και διάστημα.");
            }

        } catch (SQLException e) {
            System.err.println("Σφάλμα κατά τον υπολογισμό του τζίρου: " + e.getMessage());
        }

        return totalRevenue;
    }
}
