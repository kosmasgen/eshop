import java.sql.SQLException;
public class MainAllProducts {
    public static void main(String[] args) {
        ProductsRepository repo = new ProductsRepositoryImpl();

        try {
            // Δοκιμή μεθόδου εισαγωγής
            ClassProducts newProduct = new ClassProducts(0, "Smartphone", 299.99, 15, 1);
            int newProductId = repo.create(newProduct);
            if (newProductId != -1) {
                System.out.println("Το νέο προϊόν εισήχθη με ID: " + newProductId);
            } else {
                System.out.println("Η εισαγωγή απέτυχε.");
            }

            // Δοκιμή μεθόδου εύρεσης προϊόντος με ID
            ClassProducts product = repo.findById(newProductId);
            if (product != null) {
                System.out.println("Βρέθηκε προϊόν: " + product);
            } else {
                System.out.println("Το προϊόν δεν βρέθηκε.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Παρουσιάστηκε σφάλμα: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
