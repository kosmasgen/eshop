import java.util.Scanner;

public class MainOrders {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Δώστε το ID του προμηθευτή:");
            int supplierId = scanner.nextInt();
            System.out.println("Δώστε το ID του προϊόντος:");
            int productId = scanner.nextInt();
            System.out.println("Δώστε την ποσότητα:");
            int quantity = scanner.nextInt();
            OrdersManager.createOrder(supplierId, productId, quantity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
