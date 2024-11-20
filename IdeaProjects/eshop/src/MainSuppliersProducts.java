public class MainSuppliersProducts {
    public static void main(String[] args) {
        SuppliersProductsRepositoryImpl repository = new SuppliersProductsRepositoryImpl();

        try {
            // Δημιουργία νέας εγγραφής
            ClassSuppliersProducts newEntry = new ClassSuppliersProducts(1, 2, 0, 50); // Προμηθευτής 1, Προϊόν 2, Ποσότητα 50
            int id = repository.create(newEntry);
            System.out.println("Νέα εγγραφή με ID: " + id);

            // Έλεγχος διαθέσιμης ποσότητας
            int availableQuantity = repository.getAvailableQuantity(1, 2); // Έλεγχος ποσότητας για Προμηθευτή 1, Προϊόν 2
            if (availableQuantity != -1) {
                System.out.println("Διαθέσιμη ποσότητα για Προμηθευτή 1 και Προϊόν 2: " + availableQuantity);
            } else {
                System.out.println("Δεν βρέθηκε εγγραφή για τον Προμηθευτή 1 και το Προϊόν 2.");
            }

            // Ενημέρωση εγγραφής
            ClassSuppliersProducts retrieved = repository.findById(id);
            if (retrieved != null) {
                retrieved.setQuantity(100); // Ενημέρωση ποσότητας σε 100
                boolean updated = repository.update(retrieved);
                System.out.println("Η εγγραφή ενημερώθηκε: " + updated);

                // Έλεγχος ξανά μετά την ενημέρωση
                availableQuantity = repository.getAvailableQuantity(1, 2);
                System.out.println("Νέα διαθέσιμη ποσότητα για Προμηθευτή 1 και Προϊόν 2: " + availableQuantity);
            } else {
                System.out.println("Η εγγραφή με ID " + id + " δεν βρέθηκε.");
            }

            // Διαγραφή εγγραφής
            boolean deleted = repository.delete(id);
            System.out.println("Η εγγραφή διαγράφηκε: " + deleted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
