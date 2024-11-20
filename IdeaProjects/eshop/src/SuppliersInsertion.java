import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SuppliersInsertion {

    // Μέθοδος για έλεγχο αν το ΑΦΜ υπάρχει ήδη στη βάση δεδομένων
    public static boolean isAfmExists(String afm) throws SQLException {
        String checkSql = "SELECT COUNT(*) FROM Suppliers WHERE afm = ?";
        try (Connection conn = DatabaseConnection.getConnection(); // Χρήση της σωστής μεθόδου
             PreparedStatement pstmt = conn.prepareStatement(checkSql)) {
            pstmt.setString(1, afm);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Επιστρέφει true αν βρεθεί τουλάχιστον μία εγγραφή
            }
        }
        return false;
    }

    // Μέθοδος για εισαγωγή του αντικειμένου στη βάση δεδομένων και επιστροφή του id
    public static int insertSupplier(ClassSuppliers supplier) throws SQLException {
        if (isAfmExists(supplier.getAfm())) {
            System.out.println("Το ΑΦΜ υπάρχει ήδη στη βάση δεδομένων. Η εγγραφή ακυρώνεται.");
            return -1; // Επιστρέφει -1 για να δείξει ότι η εισαγωγή δεν πραγματοποιήθηκε
        }

        int generatedId = -1;

        try (Connection conn = DatabaseConnection.getConnection(); // Χρήση της σωστής μεθόδου
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO Suppliers (first_name, last_name, telephone, afm) VALUES (?, ?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, supplier.getFirstName());
            pstmt.setString(2, supplier.getLastName());
            pstmt.setString(3, supplier.getTelephone());
            pstmt.setString(4, supplier.getAfm());

            // Εκτέλεση της εντολής SQL
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Το αντικείμενο εισήχθη επιτυχώς στη βάση δεδομένων!");
            }

            // Λήψη του παραγόμενου id
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
        }

        return generatedId;
    }

    public static void main(String[] args) {
        try {
            // Δημιουργία ενός αντικειμένου ClassSuppliers
            ClassSuppliers newSupplier = new ClassSuppliers("Γιώργος", "Παπαδόπουλος", "2109876543", "123456789");

            // Εισαγωγή του αντικειμένου στη βάση δεδομένων και λήψη του id
            int id = insertSupplier(newSupplier);
            if (id != -1) {
                System.out.println("Το νέο id που εισήχθη είναι: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Παρουσιάστηκε σφάλμα κατά την εισαγωγή δεδομένων.");
            e.printStackTrace();
        }
    }
}
