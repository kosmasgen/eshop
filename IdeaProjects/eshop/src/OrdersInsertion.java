import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrdersInsertion {

    public static void insertOrder(int supplierId, int productId, int orderQuantity) {
        String sql = "INSERT INTO orders (supplier_id, product_id, order_quantity) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); // Χρήση της σωστής μεθόδου
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, supplierId);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, orderQuantity);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Η παραγγελία καταχωρήθηκε επιτυχώς!");
            }

        } catch (Exception e) {
            System.out.println("Παρουσιάστηκε σφάλμα κατά την εισαγωγή της παραγγελίας:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Παράδειγμα εισαγωγής παραγγελίας
        insertOrder(1, 2, 50); // Supplier ID: 1, Product ID: 2, Quantity: 50
    }
}
