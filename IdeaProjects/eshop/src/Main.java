public class Main {
    public static void main(String[] args) {
        // Δημιουργία του repository
        CustomerRepository repo = new CreateRepository();

        // Δοκιμή της μεθόδου εγγραφής
        ClassCustomers newCustomer = new ClassCustomers(0, "Γιάννης", "Παπαδόπουλος"
                , "2101234567", "123456789", true, 150.50);
        repo.create(newCustomer);
        System.out.println("Η δημιουργία νέου πελάτη ολοκληρώθηκε.");

        // Δοκιμή της μεθόδου ανάκτησης με βάση το ID
        ClassCustomers customer = repo.findById(9); // Προσαρμόστε το ID σε ένα που υπάρχει στη βάση
        if (customer != null) {
            System.out.println("Βρέθηκε πελάτης: " + customer.getFirstName() + " " + customer.getLastName());
        } else {
            System.out.println("Δεν βρέθηκε πελάτης με αυτό το ID.");
        }

        // Δοκιμή της μεθόδου ανανέωσης εγγραφής
        if (customer!= null) {
            customer.setBalance(200.00);
            repo.update(customer);
            System.out.println("Η εγγραφή ενημερώθηκε.");
        }

        // Δοκιμή της μεθόδου διαγραφής
        // repo.delete(1); // Προσαρμόστε το ID σε ένα που θέλετε να διαγράψετε
        //System.out.println("Η εγγραφή διαγράφηκε.");
    }
}