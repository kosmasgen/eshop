public class ClassSuppliersProducts {
    private int id;
    private int supplierId;
    private int productId;
    private int quantity;

    public ClassSuppliersProducts(int supplierId, int productId, int quantity) {
        this.supplierId = supplierId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public ClassSuppliersProducts(int id, int supplierId, int productId, int quantity) {
        this.id = id;
        this.supplierId = supplierId;
        this.productId = productId;
        this.quantity = quantity;
    }

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
    }
}
