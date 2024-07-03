package business.entity;

public class OrderDetail {
    private int orderId;
    private int productId;
    private String productName;
    private Double price;
    private int orderQuantity;

//    =====================CONTRACTOR=============================

    public OrderDetail(){

    }

    public OrderDetail(int orderId, int orderQuantity, Double price, int productId, String productName) {
        this.orderId = orderId;
        this.orderQuantity = orderQuantity;
        this.price = price;
        this.productId = productId;
        this.productName = productName;
    }

//      =====================METHOD GET/SET=========================

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

//      ==================== VALIDATION==============================

//      =====================INPUT CART==============================
//      =====================SHOW CART===============================
}
