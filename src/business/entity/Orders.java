package business.entity;

import java.util.Date;

public class Orders {
    private int orderId;
    private String serialNumber;
    private int userId;
    private Double totalPrice;
    private Enum status;
    private String note;
    private String receiveName;
    private String receiveAddress;
    private String receivePhone;
    private Date createdAt;
    private Date receivedAt;

//    =====================CONTRACTOR=============================
    public Orders() {}

    public Orders(Date createdAt, String note, int orderId, String
            receiveAddress, Date receivedAt, String receiveName, String receivePhone,
                  String serialNumber, Enum status, Double totalPrice, int userId) {
        this.createdAt = createdAt;
        this.note = note;
        this.orderId = orderId;
        this.receiveAddress = receiveAddress;
        this.receivedAt = receivedAt;
        this.receiveName = receiveName;
        this.receivePhone = receivePhone;
        this.serialNumber = serialNumber;
        this.status = status;
        this.totalPrice = totalPrice;
        this.userId = userId;
    }

//     =====================METHOD GET/SET=========================

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public Date getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(Date receivedAt) {
        this.receivedAt = receivedAt;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


//      ==================== VALIDATION==============================
//      =====================INPUT CART==============================
//      =====================SHOW CART===============================
}


