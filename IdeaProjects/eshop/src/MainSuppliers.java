public class MainSuppliers {
    public static void main(String[] args) {
        // Δημιουργία του repository για τους προμηθευτές
        SuppliersRepository repo = new SuppliersRepositoryImpl();

        // Δοκιμή της μεθόδου εγγραφής
        ClassSuppliers newSupplier = new ClassSuppliers("Νίκος", "Αθανασίου", "2101234567", "987654321");
        int newId = repo.create(newSupplier);
        if (newId != -1) {
            System.out.println("Η δημιουργία νέου προμηθευτή ολοκληρώθηκε με ID: " + newId);
        } else {
            System.out.println("Η δημιουργία απέτυχε. Το ΑΦΜ υπάρχει ήδη.");
        }

        // Δοκιμή της μεθόδου ανάκτησης με βάση το ID
        ClassSuppliers supplier = repo.findById(newId); // Προσαρμόστε το ID σε ένα που υπάρχει στη βάση
        if (supplier != null) {
            System.out.println("Βρέθηκε προμηθευτής: " + supplier.getFirstName() + " " + supplier.getLastName());
        } else {
            System.out.println("Δεν βρέθηκε προμηθευτής με αυτό το ID.");
        }

        // Δοκιμή της μεθόδου ανανέωσης εγγραφής
        if (supplier != null) {
            supplier.setTelephone("2107654321");
            repo.update(supplier);
            System.out.println("Η εγγραφή του προμηθευτή ενημερώθηκε.");
        }

        // Δοκιμή της μεθόδου διαγραφής
        // repo.delete(newId); // Προσαρμόστε το ID σε ένα που θέλετε να διαγράψετε
        // System.out.println("Η εγγραφή διαγράφηκε.");
    }
}
