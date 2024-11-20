import java.sql.Connection;
import java.sql.Statement;

public class CreateTableUsers {
    public static void main(String[] args) {
        // SQL εντολή για τη δημιουργία του πίνακα
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "username VARCHAR(50) NOT NULL UNIQUE, " // Ονομασία χρήστη μοναδική
                + "password VARCHAR(255) NOT NULL, " // Κωδικός πρόσβασης (προτιμάται ασφαλής hash)
                + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" // Ημερομηνία δημιουργίας εγγραφής
                + ");";

        try (Connection conn = DatabaseConnection.getConnection(); // Χρήση της σωστής μεθόδου
             Statement stmt = conn.createStatement()) {

            // Εκτέλεση της εντολής δημιουργίας του πίνακα
            stmt.executeUpdate(createTableSQL);
            System.out.println("Ο πίνακας 'users' δημιουργήθηκε επιτυχώς.");

        } catch (Exception e) {
            System.out.println("Παρουσιάστηκε σφάλμα κατά τη δημιουργία του πίνακα.");
            e.printStackTrace();
        }
    }
}
