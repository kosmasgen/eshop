import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuppliersRepositoryImpl implements SuppliersRepository {

    // Μέθοδος για έλεγχο αν το ΑΦΜ υπάρχει ήδη στη βάση δεδομένων
    @Override
    public boolean isAfmExists(String afm) {
        String sql = "SELECT COUNT(*) FROM suppliers WHERE afm = ?";
        try (Connection conn = DatabaseConnection.getConnection(); // Χρήση της σωστής μεθόδου
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, afm);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Επιστρέφει true αν βρεθεί τουλάχιστον μία εγγραφή
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int create(ClassSuppliers supplier) {
        // Έλεγχος αν το ΑΦΜ υπάρχει ήδη
        if (isAfmExists(supplier.getAfm())) {
            System.out.println("Το ΑΦΜ υπάρχει ήδη στη βάση δεδομένων. Η εγγραφή ακυρώνεται.");
            return -1; // Επιστρέφει -1 για να δείξει ότι η εισαγωγή δεν πραγματοποιήθηκε
        }

        String sql = "INSERT INTO suppliers (first_name, last_name, telephone, afm) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); // Χρήση της σωστής μεθόδου
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, supplier.getFirstName());
            pstmt.setString(2, supplier.getLastName());
            pstmt.setString(3, supplier.getTelephone());
            pstmt.setString(4, supplier.getAfm());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public ClassSuppliers findById(int id) {
        String sql = "SELECT * FROM suppliers WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); // Χρήση της σωστής μεθόδου
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                ClassSuppliers supplier = new ClassSuppliers(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("telephone"),
                        rs.getString("afm")
                );
                supplier.setId(rs.getInt("id"));
                return supplier;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ClassSuppliers> findAll() {
        List<ClassSuppliers> suppliersList = new ArrayList<>();
        String sql = "SELECT * FROM suppliers";
        try (Connection conn = DatabaseConnection.getConnection(); // Χρήση της σωστής μεθόδου
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ClassSuppliers supplier = new ClassSuppliers(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("telephone"),
                        rs.getString("afm")
                );
                supplier.setId(rs.getInt("id"));
                suppliersList.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliersList;
    }

    @Override
    public void update(ClassSuppliers supplier) {
        String sql = "UPDATE suppliers SET first_name = ?, last_name = ?, telephone = ?, afm = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); // Χρήση της σωστής μεθόδου
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, supplier.getFirstName());
            pstmt.setString(2, supplier.getLastName());
            pstmt.setString(3, supplier.getTelephone());
            pstmt.setString(4, supplier.getAfm());
            pstmt.setInt(5, supplier.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM suppliers WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); // Χρήση της σωστής μεθόδου
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
