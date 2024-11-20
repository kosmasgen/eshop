import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersInsertion {

    // Μέθοδος για έλεγχο αν το ΑΦΜ υπάρχει ήδη στη βάση δεδομένων
    public static boolean isAfmExists(String afm) throws SQLException {
        String checkSql = "SELECT COUNT(*) FROM Customers WHERE afm = ?";
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
    public static int insertCustomer(ClassCustomers customer) throws SQLException {
        if (isAfmExists(customer.getAfm())) {
            System.out.println("Το ΑΦΜ υπάρχει ήδη στη βάση δεδομένων. Η εγγραφή ακυρώνεται.");
            return -1; // Επιστρέφει -1 για να δείξει ότι η εισαγωγή δεν πραγματοποιήθηκε
        }

        int generatedId = -1;

        try (Connection conn = DatabaseConnection.getConnection(); // Χρήση της σωστής μεθόδου
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO Customers (first_name, last_name, telephone, afm, wholesale, balance) VALUES (?, ?, ?, ?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getLastName());
            pstmt.setString(3, customer.getTelephone());
            pstmt.setString(4, customer.getAfm());
            pstmt.setBoolean(5, customer.isWholesale());
            pstmt.setDouble(6, customer.getBalance());

            // Εκτέλεση της εντολής SQL
            pstmt.executeUpdate();
            System.out.println("Το αντικείμενο εισήχθη επιτυχώς στη βάση δεδομένων!");

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
            // Δημιουργία ενός αντικειμένου ClassCustomers
            ClassCustomers newCustomer = new ClassCustomers("Γιάννης", "Παπαδόπουλος", "2101234567", "123456789", true, 150.50);

            // Εισαγωγή του αντικειμένου στη βάση δεδομένων και λήψη του id
            int id = insertCustomer(newCustomer);
            if (id != -1) {
                System.out.println("Το νέο id που εισήχθη είναι: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Παρουσιάστηκε σφάλμα κατά την εισαγωγή δεδομένων.");
            e.printStackTrace();
        }
    }
}
