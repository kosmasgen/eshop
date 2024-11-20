import java.util.List;
public class MainAllUsers {
    public static void main(String[] args) {
        // Δημιουργία του repository
        CustomerRepository repo = new CreateRepository();

        // Ανάκτηση όλων των πελατών και εμφάνιση των στοιχείων τους
        List<ClassCustomers> customersList = repo.findAll();

        if (!customersList.isEmpty()) {
            System.out.println("Λίστα όλων των πελατών:");
            for (ClassCustomers customer : customersList) {
                System.out.println("ID: " + customer.getId() + ", Όνομα: " + customer.getFirstName() +
                        ", Επώνυμο: " + customer.getLastName() + ", Τηλέφωνο: " + customer.getTelephone() +
                        ", ΑΦΜ: " + customer.getAfm() + ", Χονδρική: " + customer.isWholesale() +
                        ", Υπόλοιπο: " + customer.getBalance());
            }
        } else {
            System.out.println("Δεν υπάρχουν πελάτες στη βάση δεδομένων.");
        }
    }
}
