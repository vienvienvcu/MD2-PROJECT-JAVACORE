package business.constants;


public enum OrderStatus {
    WAITING("Đơn hàng mới chờ xác nhận"),
    CONFIRM("Đã xác nhận"),
    DELIVERY("Đang giao hàng"),
    SUCCESS("Đã giao hàng"),
    CANCEL("Đã hủy đơn"),
    DENIED("Bị từ chối");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
