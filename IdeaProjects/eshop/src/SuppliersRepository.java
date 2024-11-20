import java.util.List;

public interface SuppliersRepository {
    // Μέθοδος για τη δημιουργία νέου προμηθευτή, επιστρέφει το παραγόμενο ID
    int create(ClassSuppliers supplier);

    // Μέθοδος για την εύρεση προμηθευτή με βάση το ID
    ClassSuppliers findById(int id);

    // Μέθοδος για την ανάκτηση όλων των προμηθευτών
    List<ClassSuppliers> findAll();

    // Μέθοδος για την ανανέωση ενός υπάρχοντος προμηθευτή
    void update(ClassSuppliers supplier);

    // Μέθοδος για τη διαγραφή ενός προμηθευτή με βάση το ID
    void delete(int id);

    // Προαιρετική μέθοδος για έλεγχο αν το ΑΦΜ υπάρχει ήδη
    boolean isAfmExists(String afm);
}
