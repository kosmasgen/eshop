import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsersInsertion {

    // Μέθοδος για τη δημιουργία της σύνδεσης με τη βάση δεδομένων
    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/eshop"; // Αντικατάστησε με τη βάση δεδομένων σου
        String user = "root"; // Αντικατάστησε με το όνομα χρήστη σου
        String password = "1212"; // Αντικατάστησε με τον κωδικό πρόσβασής σου

        // Φόρτωση του MySQL JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    // Μέθοδος για εισαγωγή δεδομένων στον πίνακα users
    public static void insertUser(String username, String password) throws SQLException, ClassNotFoundException {
        Connection conn = createConnection();
        PreparedStatement pstmt = null;

        try {
            String insertSql = "INSERT INTO users (username, password) VALUES (?, ?);";
            pstmt = conn.prepareStatement(insertSql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            // Εκτέλεση της εντολής SQL
            pstmt.executeUpdate();
            System.out.println("Ο χρήστης εισήχθη επιτυχώς στη βάση δεδομένων!");

        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }

    public static void main(String[] args) {
        try {
            // Εισαγωγή εγγραφών στον πίνακα users
            insertUser("john_doe", "securePassword123");
            insertUser("jane_smith", "password456");
            insertUser("mike_jones", "myPass789");
            insertUser("anna_brown", "secretKey987");
            insertUser("paul_davis", "simplePass321");

        } catch (ClassNotFoundException e) {
            System.out.println("Δεν βρέθηκε ο driver για τη βάση δεδομένων.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Παρουσιάστηκε σφάλμα κατά την εισαγωγή δεδομένων.");
            e.printStackTrace();
        }
    }
}
