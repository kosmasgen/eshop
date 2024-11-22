import java.time.LocalDateTime;

public class ClassUsers {
    private int id;
    private String username;
    private String password;
    private LocalDateTime createdAt;

    // Κατασκευαστής χωρίς παραμέτρους
    public ClassUsers() {
    }

    // Κατασκευαστής με παραμέτρους
    public ClassUsers(int id, String username, String password, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }

    // Getters και Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Μέθοδος toString για εκτύπωση του αντικειμένου

    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
