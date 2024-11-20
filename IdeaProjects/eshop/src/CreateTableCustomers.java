import java.sql.Connection;
import java.sql.Statement;

public class CreateTableCustomers {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection(); // Χρήση της σωστής μεθόδου
             Statement stmt = conn.createStatement()) {

            // Εντολή SQL για τη δημιουργία του πίνακα
            String sql = "CREATE TABLE IF NOT EXISTS customers ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "first_name VARCHAR(15) NOT NULL, "
                    + "last_name VARCHAR(15) NOT NULL, "
                    + "telephone VARCHAR(13) NOT NULL, "
                    + "afm VARCHAR(9) NOT NULL, "
                    + "wholesale TINYINT, "
                    + "balance DECIMAL(5,2) NOT NULL"
                    + ");";

            // Εκτέλεση της εντολής SQL
            stmt.executeUpdate(sql);
            System.out.println("Ο πίνακας 'customers' δημιουργήθηκε επιτυχώς!");

        } catch (Exception e) {
            System.out.println("Παρουσιάστηκε σφάλμα κατά τη σύνδεση ή τη δημιουργία του πίνακα.");
            e.printStackTrace();
        }
    }
}
