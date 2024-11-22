public class ClassProducts {
    private int id;
    private String name;
    private String type;
    private double price;
    private int quantity;
    private int supplierId;
    private String uuid;

    // Constructors
    public ClassProducts(int id, String name, String type, double price, int quantity, int supplierId, String uuid) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.supplierId = supplierId;
        this.uuid = uuid;
    }

    public ClassProducts(String name, String type, double price, int quantity, int supplierId, String uuid) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.supplierId = supplierId;
        this.uuid = uuid;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public String getUuid() {
        return uuid;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", supplierId=" + supplierId +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
