import java.util.List;

public interface CustomerRepository {
    // Μέθοδος για τη δημιουργία νέου πελάτη, επιστρέφει το παραγόμενο ID
    int create(ClassCustomers customer);

    // Μέθοδος για την εύρεση πελάτη με βάση το ID
    ClassCustomers findById(int id);

    // Μέθοδος για την ανάκτηση όλων των πελατών
    List<ClassCustomers> findAll();

    // Μέθοδος για την ανανέωση ενός υπάρχοντος πελάτη
    void update(ClassCustomers customer);

    // Μέθοδος για τη διαγραφή ενός πελάτη με βάση το ID
    void delete(int id);
}