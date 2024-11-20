import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoresInsertion {

    // Μέθοδος για εισαγωγή εγγραφής στον πίνακα store
    public static int insertStore(String firstName, String lastName, String telephone, String afm, boolean wholesale, double balance) throws SQLException {
        int generatedId = -1;

        try (Connection conn = DatabaseConnection.getConnection(); // Χρήση της σωστής μεθόδου
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO store (first_name, last_name, telephone, afm, wholesale, balance) VALUES (?, ?, ?, ?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, telephone);
            pstmt.setString(4, afm);
            pstmt.setBoolean(5, wholesale);
            pstmt.setDouble(6, balance);

            // Εκτέλεση της εντολής SQL
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Η εγγραφή εισήχθη επιτυχώς στη βάση δεδομένων!");
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
            // Εισαγωγή πέντε διαφορετικών εγγραφών στον πίνακα store
            insertStore("John", "Doe", "2101234567", "123456789", true, 150.50);
            insertStore("Jane", "Smith", "2102345678", "987654321", false, 200.75);
            insertStore("Mike", "Johnson", "2103456789", "456789123", true, 300.00);
            insertStore("Anna", "Brown", "2104567890", "789123456", false, 120.40);
            insertStore("Paul", "Davis", "2105678901", "321456987", true, 500.00);

        } catch (SQLException e) {
            System.out.println("Παρουσιάστηκε σφάλμα κατά την εισαγωγή δεδομένων.");
            e.printStackTrace();
        }
    }
}
