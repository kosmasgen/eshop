import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/eshop";
    private static final String USER = "root";
    private static final String PASSWORD = "1212";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
            throw new RuntimeException("Database connection error", e);
        }
    }
}
