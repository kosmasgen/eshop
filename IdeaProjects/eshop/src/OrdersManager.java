import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersManager {

    public static void createOrder(int supplierId, int productId, int orderQuantity) {
        if (!isProductAvailable(productId, orderQuantity)) {
            System.out.println("Η ποσότητα δεν επαρκεί για την παραγγελία!");
            return;
        }

        String sql = "INSERT INTO orders (supplier_id, product_id, order_quantity, total_price) VALUES (?, ?, ?, ?)";
        String selectPriceSql = "SELECT price FROM products WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement selectPriceStmt = conn.prepareStatement(selectPriceSql);
             PreparedStatement insertOrderStmt = conn.prepareStatement(sql)) {
            selectPriceStmt.setInt(1, productId);
            ResultSet rs = selectPriceStmt.executeQuery();
            if (!rs.next()) {
                System.out.println("Το προϊόν δεν βρέθηκε.");
                return;
            }
            double productPrice = rs.getDouble("price");
            double totalPrice = productPrice * orderQuantity;
            insertOrderStmt.setInt(1, supplierId);
            insertOrderStmt.setInt(2, productId);
            insertOrderStmt.setInt(3, orderQuantity);
            insertOrderStmt.setDouble(4, totalPrice);
            int rowsInserted = insertOrderStmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Η παραγγελία καταχωρήθηκε με συνολικό ποσό: " + totalPrice);
                updateProductQuantity(productId, orderQuantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isProductAvailable(int productId, int requiredQuantity) {
        String sql = "SELECT Quantity FROM products WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int availableQuantity = rs.getInt("Quantity");
                return availableQuantity >= requiredQuantity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void updateProductQuantity(int productId, int quantityToDeduct) {
        String sql = "UPDATE products SET Quantity = Quantity - ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, quantityToDeduct);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
