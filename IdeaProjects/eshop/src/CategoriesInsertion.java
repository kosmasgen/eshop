import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriesInsertion {

    // Μέθοδος για τη δημιουργία της σύνδεσης με τη βάση δεδομένων
    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/eshop"; // Αντικατάστησε με τη βάση δεδομένων σου
        String user = "root"; // Αντικατάστησε με το όνομα χρήστη σου
        String password = "1212"; // Αντικατάστησε με τον κωδικό πρόσβασής σου

        // Φόρτωση του MySQL JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    // Μέθοδος για έλεγχο αν η κατηγορία με το ίδιο type υπάρχει ήδη στη βάση δεδομένων
    public static boolean isTypeExists(String type) throws SQLException, ClassNotFoundException {
        String checkSql = "SELECT COUNT(*) FROM Categories WHERE type = ?";
        try (Connection conn = createConnection();
             PreparedStatement pstmt = conn.prepareStatement(checkSql)) {
            pstmt.setString(1, type);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Επιστρέφει true αν βρεθεί τουλάχιστον μία εγγραφή
            }
        }
        return false;
    }

    // Μέθοδος για εισαγωγή του αντικειμένου στη βάση δεδομένων και επιστροφή του id
    public static int insertCategory(ClassCategories category) throws SQLException, ClassNotFoundException {
        if (isTypeExists(category.getType())) {
            System.out.println("Η κατηγορία με αυτό το type υπάρχει ήδη στη βάση δεδομένων. Η εγγραφή ακυρώνεται.");
            return -1; // Επιστρέφει -1 για να δείξει ότι η εισαγωγή δεν πραγματοποιήθηκε
        }

        Connection conn = createConnection();
        PreparedStatement pstmt = null;
        int generatedId = -1;

        try {
            // Εντολή SQL για εισαγωγή δεδομένων χωρίς το id
            String insertSql = "INSERT INTO Categories (type, description) VALUES (?, ?);";
            pstmt = conn.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, category.getType());
            pstmt.setString(2, category.getDescription());

            // Εκτέλεση της εντολής SQL
            pstmt.executeUpdate();
            System.out.println("Η κατηγορία εισήχθη επιτυχώς στη βάση δεδομένων!");

            // Λήψη του παραγόμενου id
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }

        } finally {
            // Κλείσιμο των πόρων
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return generatedId;
    }

    public static void main(String[] args) {
        try {
            // Δημιουργία και εισαγωγή της πρώτης κατηγορίας
            ClassCategories category1 = new ClassCategories("Υπολογιστές", "Κατηγορία για υπολογιστές και εξοπλισμό");
            int id1 = insertCategory(category1);
            if (id1 != -1) {
                System.out.println("Το νέο id που εισήχθη είναι: " + id1);
            }

            // Δημιουργία και εισαγωγή της δεύτερης κατηγορίας
            ClassCategories category2 = new ClassCategories("Περιφερειακά", "Κατηγορία για περιφερειακές συσκευές υπολογιστών");
            int id2 = insertCategory(category2);
            if (id2 != -1) {
                System.out.println("Το νέο id που εισήχθη είναι: " + id2);
            }

            // Δημιουργία και εισαγωγή της τρίτης κατηγορίας
            ClassCategories category3 = new ClassCategories("Υδραυλικά", "Κατηγορία για υδραυλικό εξοπλισμό και προϊόντα");
            int id3 = insertCategory(category3);
            if (id3 != -1) {
                System.out.println("Το νέο id που εισήχθη είναι: " + id3);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Δεν βρέθηκε ο driver για τη βάση δεδομένων.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Παρουσιάστηκε σφάλμα κατά την εισαγωγή δεδομένων.");
            e.printStackTrace();
        }
    }
}
