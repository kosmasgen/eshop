import java.time.LocalDateTime;

public class ClassOrders {
    private int id; // Δημιουργείται αυτόματα από τη βάση
    private int supplierId;
    private int productId;
    private int quantity;
    private double price;
    private double totalPrice; // Νέο πεδίο
    private LocalDateTime created_at;

    // Constructor χωρίς id (για νέες παραγγελίες)
    public ClassOrders(int supplierId, int productId, int quantity, double price, LocalDateTime created_at) {
        this.supplierId = supplierId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = quantity * price; // Υπολογισμός του συνολικού κόστους
        this.created_at = created_at;
    }

    // Constructor με id (για δεδομένα από τη βάση)
    public ClassOrders(int id, int supplierId, int productId, int quantity, double price, double totalPrice, LocalDateTime created_at) {
        this.id = id;
        this.supplierId = supplierId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
        this.created_at = created_at;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = this.quantity * this.price; // Ενημέρωση του συνολικού κόστους
    }

    public void setPrice(double price) {
        this.price = price;
        this.totalPrice = this.quantity * this.price; // Ενημέρωση του συνολικού κόστους
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", supplierId=" + supplierId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", created_at=" + created_at +
                '}';
    }
}
