import java.util.List;

public class MainAllSuppliers {
    public static void main(String[] args) {
        // Δημιουργία του repository για τους προμηθευτές
        SuppliersRepository repo = new SuppliersRepositoryImpl();

        // Ανάκτηση όλων των προμηθευτών και εμφάνιση των στοιχείων τους
        List<ClassSuppliers> suppliersList = repo.findAll();

        if (!suppliersList.isEmpty()) {
            System.out.println("Λίστα όλων των προμηθευτών:");
            for (ClassSuppliers supplier : suppliersList) {
                System.out.println("ID: " + supplier.getId() + ", Όνομα: " + supplier.getFirstName() +
                        ", Επώνυμο: " + supplier.getLastName() + ", Τηλέφωνο: " + supplier.getTelephone() +
                        ", ΑΦΜ: " + supplier.getAfm());
            }
        } else {
            System.out.println("Δεν υπάρχουν προμηθευτές στη βάση δεδομένων.");
        }
    }
}
