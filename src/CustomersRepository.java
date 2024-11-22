import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomersRepository {

    public int create(ClassCustomers customer) {
        String sql = "INSERT INTO customers (first_name, last_name, telephone, afm, wholesale, balance) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getLastName());
            pstmt.setString(3, customer.getTelephone());
            pstmt.setString(4, customer.getAfm());
            pstmt.setBoolean(5, customer.isWholesale());
            pstmt.setDouble(6, customer.getBalance());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // Return generated ID
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while creating customer: " + e.getMessage());
        }
        return -1;
    }

    public List<ClassCustomers> findAll() {
        List<ClassCustomers> customersList = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                customersList.add(new ClassCustomers(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("telephone"),
                        rs.getString("afm"),
                        rs.getBoolean("wholesale"),
                        rs.getDouble("balance")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving customers: " + e.getMessage());
        }
        return customersList;
    }

    public List<ClassCustomers> search(String keyword) {
        List<ClassCustomers> customersList = new ArrayList<>();
        String sql = "SELECT * FROM customers WHERE first_name LIKE ? OR last_name LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                customersList.add(new ClassCustomers(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("telephone"),
                        rs.getString("afm"),
                        rs.getBoolean("wholesale"),
                        rs.getDouble("balance")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while searching for customers: " + e.getMessage());
        }
        return customersList;
    }
}
