import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuppliersProductsRepositoryImpl implements SuppliersProductsRepository {

    @Override
    public int create(ClassSuppliersProducts entry) throws SQLException {
        String sql = "INSERT INTO suppliers_products (supplier_id, product_id, quantity) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, entry.getSupplierId());
            pstmt.setInt(2, entry.getProductId());
            pstmt.setInt(3, entry.getQuantity());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }

    @Override
    public ClassSuppliersProducts findById(int id) throws SQLException {
        String sql = "SELECT * FROM suppliers_products WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new ClassSuppliersProducts(
                        rs.getInt("id"),
                        rs.getInt("supplier_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity")
                );
            }
        }
        return null;
    }

    @Override
    public boolean update(ClassSuppliersProducts entry) throws SQLException {
        String sql = "UPDATE suppliers_products SET supplier_id = ?, product_id = ?, quantity = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, entry.getSupplierId());
            pstmt.setInt(2, entry.getProductId());
            pstmt.setInt(3, entry.getQuantity());
            pstmt.setInt(4, entry.getId());
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM suppliers_products WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public List<ClassSuppliersProducts> findAll() throws SQLException {
        List<ClassSuppliersProducts> products = new ArrayList<>();
        String sql = "SELECT * FROM suppliers_products";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                products.add(new ClassSuppliersProducts(
                        rs.getInt("id"),
                        rs.getInt("supplier_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity")
                ));
            }
        }
        return products;
    }

    @Override
    public boolean exists(int supplierId, int productId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM suppliers_products WHERE supplier_id = ? AND product_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, supplierId);
            pstmt.setInt(2, productId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    @Override
    public int getAvailableQuantity(int supplierId, int productId) throws SQLException {
        String sql = "SELECT quantity FROM suppliers_products WHERE supplier_id = ? AND product_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, supplierId);
            pstmt.setInt(2, productId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantity");
            }
        }
        return -1;
    }
}
