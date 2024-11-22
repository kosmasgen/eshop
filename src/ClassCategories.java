public class ClassCategories {
    private int id;
    private String type;
    private String description; // Νέα ιδιότητα για την περιγραφή

    // Κατασκευαστής που δέχεται το type και την περιγραφή
    public ClassCategories(String type, String description) {
        this.type = type;
        this.description = description;
    }

    // Getter για το id
    public int getId() {
        return id;
    }

    // Setter για το id
    public void setId(int id) {
        this.id = id;
    }

    // Getter για το type
    public String getType() {
        return type;
    }

    // Setter για το type
    public void setType(String type) {
        this.type = type;
    }

    // Getter για την περιγραφή
    public String getDescription() {
        return description;
    }

    // Setter για την περιγραφή
    public void setDescription(String description) {
        this.description = description;
    }
}