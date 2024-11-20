import java.sql.SQLException;
import java.util.List;

public interface ProductsRepository {
    /**
     * Δημιουργεί ένα νέο προϊόν και επιστρέφει το ID του.
     *
     * @param product Το προϊόν που θα εισαχθεί.
     * @return Το ID του προϊόντος που εισήχθη, ή -1 αν η εισαγωγή απέτυχε.
     * @throws SQLException Αν υπάρξει σφάλμα στη βάση δεδομένων.
     * @throws ClassNotFoundException Αν δε βρεθεί ο driver για τη βάση δεδομένων.
     */
    int create(ClassProducts product) throws SQLException, ClassNotFoundException;

    /**
     * Επιστρέφει ένα προϊόν με βάση το ID του.
     *
     * @param id Το ID του προϊόντος που αναζητείται.
     * @return Το προϊόν που βρέθηκε, ή null αν δν βρέθηκε.
     * @throws SQLException Αν υπάρξει σφάλμα στη βάση δεδομένων.
     * @throws ClassNotFoundException Αν δε βρεθεί ο driver για τη βάση δεδομένων.
     */
    ClassProducts findById(int id) throws SQLException, ClassNotFoundException;

    /**
     * Επιστρέφει όλα τα προϊόντα από τη βάση δεδομένων.
     *
     * @return Μια λίστα με όλα τα προϊόντα.
     * @throws SQLException Αν υπάρξει σφάλμα στη βάση δεδομένων.
     * @throws ClassNotFoundException Αν δε βρεθεί ο driver για τη βάση δεδομένων.
     */
    List<ClassProducts> findAll() throws SQLException, ClassNotFoundException;

    /**
     * Ενημερώνει ένα υπάρχον προϊόν στη βάση δεδομένων.
     *
     * @param product Το προϊόν που θα ενημερωθεί.
     * @return true αν η ενημέρωση ήταν επιτυχής, αλλιώς false.
     * @throws SQLException Αν υπάρξει σφάλμα στη βάση δεδομένων.
     * @throws ClassNotFoundException Αν δε βρεθεί ο driver για τη βάση δεδομένων.
     */
    boolean update(ClassProducts product) throws SQLException, ClassNotFoundException;

    /**
     * Διαγράφει ένα προϊόν με βάση το ID του.
     *
     * @param id Το ID του προϊόντος που θα διαγραφεί.
     * @return true αν η διαγραφή ήταν επιτυχής, αλλιώς false.
     * @throws SQLException Αν υπάρξει σφάλμα στη βάση δεδομένων.
     * @throws ClassNotFoundException Αν δε βρεθεί ο driver για τη βάση δεδομένων.
     */
    boolean delete(int id) throws SQLException, ClassNotFoundException;
}
