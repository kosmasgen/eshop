
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreateRepository implements CustomerRepository {
    private String url = "jdbc:mysql://localhost:3306/eshop"; // Προσαρμόστε με τη βάση σας
    private String user = "root";
    private String password = "1212";

    // Μέθοδος για τη δημιουργία σύνδεσης με τη βάση δεδομένων
    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Μέθοδος για εισαγωγή και επιστροφή του παραγόμενου ID
    @Override
    public int create(ClassCustomers customer) {
        // Έλεγχος αν υπάρχει ήδη πελάτης με το ίδιο ΑΦΜ
        if (afmExists(customer.getAfm())) {
            System.out.println("Η εισαγωγή απέτυχε: Υπάρχει ήδη πελάτης με το ίδιο ΑΦΜ.");
            return -1; // Επιστροφή ένδειξης αποτυχίας
        }

        String insertSql = "INSERT INTO customers (first_name, last_name, telephone, afm, wholesale, balance) VALUES (?, ?, ?, ?, ?, ?)";
        int generatedId = -1; // Αρχικοποιούμε με -1 για περίπτωση αποτυχίας
        try (Connection conn = createConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {

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
                        generatedId = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    // Μέθοδος για τον έλεγχο αν υπάρχει πελάτης με το ίδιο ΑΦΜ
    private boolean afmExists(String afm) {
        String query = "SELECT COUNT(*) FROM customers WHERE afm = ?";
        try (Connection conn = createConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, afm);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Επιστρέφει true αν υπάρχει ήδη εγγραφή με το ίδιο ΑΦΜ
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Υλοποίηση της μεθόδου εύρεσης πελάτη με βάση το ID
    @Override
    public ClassCustomers findById(int id) {
        String query = "SELECT * FROM customers WHERE id = ?";
        try (Connection conn = createConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new ClassCustomers(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("telephone"),
                        rs.getString("afm"),
                        rs.getBoolean("wholesale"),
                        rs.getDouble("balance")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Επιστρέφει null αν δεν βρεθεί ο πελάτης
    }

    // Υλοποίηση της μεθόδου για την ανάκτηση όλων των πελατών
    @Override
    public List<ClassCustomers> findAll() {
        List<ClassCustomers> customersList = new ArrayList<>();
        String query = "SELECT * FROM customers";
        try (Connection conn = createConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                customersList.add(new ClassCustomers(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("telephone"),
                        rs.getString("afm"),
                        rs.getBoolean("wholesale"),
                        rs.getDouble("balance")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customersList;
    }

    // Υλοποίηση της μεθόδου για την ενημέρωση ενός πελάτη
    @Override
    public void update(ClassCustomers customer) {
        String updateSql = "UPDATE customers SET first_name = ?, last_name = ?, telephone = ?, afm = ?, wholesale = ?, balance = ? WHERE id = ?";
        try (Connection conn = createConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getLastName());
            pstmt.setString(3, customer.getTelephone());
            pstmt.setString(4, customer.getAfm());
            pstmt.setBoolean(5, customer.isWholesale());
            pstmt.setDouble(6, customer.getBalance());
            pstmt.setInt(7, customer.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Υλοποίηση της μεθόδου για τη διαγραφή ενός πελάτη με βάση το ID
    @Override
    public void delete(int id) {
        String deleteSql = "DELETE FROM customers WHERE id = ?";
        try (Connection conn = createConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteSql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
