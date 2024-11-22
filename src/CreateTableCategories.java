import java.sql.Connection;
import java.sql.Statement;

public class CreateTableCategories {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Εντολή SQL για τη δημιουργία του πίνακα
            String sql = "CREATE TABLE IF NOT EXISTS categories ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "type VARCHAR(50) NOT NULL, "
                    + "description VARCHAR(255)"
                    + ");";

            // Εκτέλεση της εντολής SQL
            stmt.executeUpdate(sql);
            System.out.println("Ο πίνακας 'categories' δημιουργήθηκε επιτυχώς!");

        } catch (Exception e) {
            System.out.println("Παρουσιάστηκε σφάλμα κατά τη σύνδεση ή τη δημιουργία του πίνακα.");
            e.printStackTrace();
        }
    }
}
