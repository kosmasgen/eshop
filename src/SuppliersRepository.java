import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuppliersRepository {

    public int create(ClassSuppliers supplier) {
        String sql = "INSERT INTO suppliers (first_name, last_name, telephone, afm, location) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, supplier.getFirstName());
            pstmt.setString(2, supplier.getLastName());
            pstmt.setString(3, supplier.getTelephone());
            pstmt.setString(4, supplier.getAfm());
            pstmt.setString(5, supplier.getLocation());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // Return generated ID
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while creating supplier: " + e.getMessage());
        }
        return -1;
    }

    public List<ClassSuppliers> findAll() {
        List<ClassSuppliers> suppliersList = new ArrayList<>();
        String sql = "SELECT * FROM suppliers";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                suppliersList.add(new ClassSuppliers(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("telephone"),
                        rs.getString("afm"),
                        rs.getString("location")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving suppliers: " + e.getMessage());
        }
        return suppliersList;
    }

    public List<ClassSuppliers> search(String keyword) {
        List<ClassSuppliers> suppliersList = new ArrayList<>();
        String sql = "SELECT * FROM suppliers WHERE first_name LIKE ? OR last_name LIKE ? OR location LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            pstmt.setString(3, "%" + keyword + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                suppliersList.add(new ClassSuppliers(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("telephone"),
                        rs.getString("afm"),
                        rs.getString("location")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while searching for suppliers: " + e.getMessage());
        }
        return suppliersList;
    }
}
