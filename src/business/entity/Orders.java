package business.entity;

import business.constants.OrderStatus;
import business.feature.Impl.AddressFeatureImpl;
import business.feature.Impl.OrdersFeatureImpl;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static business.feature.Impl.UserFeatureImpl.userLogin;

public class Orders implements Serializable {
    private int orderId;
    private String serialNumber;
    private Users user;
    private Double totalPrice;
    private OrderStatus status;
    private String note;
    private String receiveName;
    private String receiveAddress;
    private String receivePhone;
    private Date createdAt;
    private Date receivedAt;
    private List<OrderDetail> orderDetails;
    private int addressId;

//    =====================CONTRACTOR=============================
    public Orders() {
        this.status = OrderStatus.WAITING;//Đặt trạng thái mặc định
        this.orderDetails = new ArrayList<>();
    }

    public Orders(Date createdAt, String note, int orderId, String
            receiveAddress, Date receivedAt, String receiveName, String receivePhone,
                  String serialNumber, OrderStatus status, Double totalPrice,Users user,int addressId) {
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
        this.user = user;
        this.addressId = addressId;
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    //      ==================== VALIDATION==============================
    public static int inputOrderId(){
        int orderIdMax = 0;
        for (Orders orders : OrdersFeatureImpl.orderlist){
            if (orders.getOrderId() > orderIdMax){
                orderIdMax = orders.getOrderId();
            }
        }
        return orderIdMax +1;
    }

    public static String inputSerialNumber(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String inputNot(Scanner scanner){
        System.out.println("Enter you want note:");
        do {
            String note = scanner.nextLine();
            if (note.isEmpty()){
                System.err.println("Please input note, because cannot empty");
            }else {
                return note;
            }

        }while (true);
    }


//      =====================INPUT ORDER==============================

    public void inputOrderData(Scanner scanner){
        this.orderId = inputOrderId();
        this.serialNumber = inputSerialNumber();
        this.status = OrderStatus.WAITING;
        this.note = inputNot(scanner);
        // Chọn địa chỉ giao hàng từ danh sách
        List<Address> addressList = new ArrayList<>();
        for (Address address : AddressFeatureImpl.addressList) {
            if (address.getUser() != null && address.getUser().getUserName().equals(userLogin.getUserName())) {
                addressList.add(address);
            }
        }

        if (addressList.isEmpty()) {

            this.addressId = AddressFeatureImpl.generateAddressId();
            System.out.println("Enter receive name");
            this.receiveName = scanner.nextLine();
            System.out.println("Enter receive address");
            this.receiveAddress = scanner.nextLine();
            System.out.println("Enter receive phone");
            this.receivePhone = scanner.nextLine();


            // Tạo một đối tượng Address mới từ thông tin nhập vào và id tự tăng
            int newAddressId = AddressFeatureImpl.generateAddressId();
            Address newAddress = new Address(newAddressId, this.receiveName, this.receiveAddress, this.receivePhone, userLogin);

            // Thêm đối tượng Address mới vào danh sách addressList tạm thời
            addressList.add(newAddress);

            // Thêm đối tượng Address mới vào danh sách AddressFeatureImpl.addressList
            AddressFeatureImpl.addressList.add(newAddress);

            // Lưu danh sách addressList vào file
            AddressFeatureImpl.saveAddressesToFile();
            // Lấy addressId của địa chỉ mới tạo
            this.addressId = newAddressId;
        } else {
            // Hiển thị danh sách địa chỉ cho người dùng chọn
            System.out.println("Choose a delivery address:");
            String format = "| %-20s | %-40s | %-30s | %-20s |\n";
            String separator = "+----------------------+------------------------------------------+--------------------------------+----------------------+\n";

            // In tiêu đề
            System.out.print(separator);
            System.out.printf(format, "Address ID", "Receive Name", "Full Address", "Phone");
            System.out.print(separator);

            Address address = null;
            // Print address data
            for (int i = 0; i < addressList.size(); i++) {
               address = addressList.get(i);
                System.out.printf(format,
                        address.getAddressId(),
                        address.getReceiveName(),
                        address.getFullAddress(),
                        address.getPhone());
            }
            System.out.println(" "+(address.getAddressId() + 1) +  ". " + "Back.");

            System.out.print(separator);

            System.out.println("Chọn địa chỉ nhận hàng,neu ban khong muon chon," +
                    "'Back' de nhap lai dia chi: ");
            int addressChoice = inputNumber(scanner);

            if (addressChoice > 0 && addressChoice <= addressList.size()) {
                Address selectedAddress = addressList.get(addressChoice - 1);
                this.addressId = selectedAddress.getAddressId();
                this.receiveName = selectedAddress.getReceiveName();
                this.receiveAddress = selectedAddress.getFullAddress();
                this.receivePhone = selectedAddress.getPhone();
                this.user = selectedAddress.getUser();
            } else if (addressChoice == (address.getAddressId() + 1)){
                this.addressId = AddressFeatureImpl.generateAddressId();
                System.out.println("Enter receive name");
                this.receiveName = scanner.nextLine();
                System.out.println("Enter receive address");
                this.receiveAddress = scanner.nextLine();
                System.out.println("Enter receive phone");
                this.receivePhone = scanner.nextLine();

                // Tạo một đối tượng Address mới từ thông tin nhập vào và id tự tăng
                int newAddressId = AddressFeatureImpl.generateAddressId();
                Address newAddress = new Address(newAddressId, this.receiveName, this.receiveAddress, this.receivePhone, userLogin);

                // Thêm đối tượng Address mới vào danh sách addressList tạm thời
                addressList.add(newAddress);

                // Thêm đối tượng Address mới vào danh sách AddressFeatureImpl.addressList
                AddressFeatureImpl.addressList.add(newAddress);

                // Lưu danh sách addressList vào file
                AddressFeatureImpl.saveAddressesToFile();
                // Lấy addressId của địa chỉ mới tạo
                this.addressId = newAddressId;
            }
        }

        this.createdAt = new Date();
        // Thiết lập ngày nhận hàng là 4 ngày sau ngày tạo
        this.receivedAt = new Date(this.createdAt.getTime() + 4L * 24 * 60 * 60 * 1000); // 4 ngày sau
    }



    public static int inputNumber(Scanner scanner) {
        do {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Please a input number choice");
            }
        } while (true);

    }

    public void updateStatus() {
        Date now = new Date(); // Lấy thời gian hiện tại
        // Nếu trạng thái hiện tại là WAITING và đã qua thời gian tạo đơn hàng, chuyển sang trạng thái CONFIRM
        if (this.status == OrderStatus.WAITING && now.after(this.createdAt)) {
            this.status = OrderStatus.CONFIRM;
        // Nếu trạng thái hiện tại là CONFIRM và đã qua thời gian tạo đơn hàng, chuyển sang trạng thái DELIVERY
        } else if (this.status == OrderStatus.CONFIRM && now.after(this.createdAt)) {
            this.status = OrderStatus.DELIVERY;
        // Nếu trạng thái hiện tại là DELIVERY và đã qua thời gian nhận hàng, chuyển sang trạng thái SUCCESS
        } else if (this.status == OrderStatus.DELIVERY && now.after(this.receivedAt)) {
            this.status = OrderStatus.SUCCESS;
        }else if (this.status != OrderStatus.CANCEL && now.after(this.receivedAt)) {
            this.status = OrderStatus.CANCEL;
        }
    }
    // Phương thức kiểm tra và cập nhật trạng thái đơn hàng cho tất cả đơn hàng
    public static void updateAllOrdersStatus(List<Orders> ordersList) {
        for (Orders order : ordersList) {
            order.updateStatus();
        }
    }



    //      =====================SHOW CART===============================
    public void displayOrderData() {
        // Currency formatter for VND
        NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi","VN"));
        // Date formatter
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Header format
        String format = "| %-15s | %-40s |\n";
        String separator = "+-----------------+------------------------------------------+\n";

        // Display order details
        System.out.printf(separator);
        System.out.format(format, "Order ID", this.orderId);
        System.out.format(format, "Serial Number", this.serialNumber);
        System.out.format(format, "User Name", this.user.getUserName());
        System.out.format(format, "Total Price", vndFormat.format(this.totalPrice));
        System.out.format(format, "Status", this.status);
        System.out.format(format, "Note", this.note);
        System.out.format(format, "Receive Name", this.receiveName);
        System.out.format(format, "Receive Address", this.receiveAddress);
        System.out.format(format, "Receive Phone", this.receivePhone);
        System.out.format(format, "Created At", sdf.format(this.createdAt));
        System.out.format(format, "Received At", sdf.format(this.receivedAt));
        System.out.printf(separator);

        // Display each order detail
        String detailFormat = "| %-10s | %-10s | %-20s | %-10s | %-10s |\n";
        String detailSeparator = "+------------+------------+----------------------+------------+------------+\n";
        System.out.println("Order Details:");
        System.out.print(detailSeparator);
        System.out.format(detailFormat, "Order ID", "Product ID", "Product Name", "Quantity", "Price");
        System.out.print(detailSeparator);
        for (OrderDetail detail : this.orderDetails) {
            System.out.format(detailFormat, detail.getOrderId(), detail.getProductId(), detail.getProductName(), detail.getOrderQuantity(), vndFormat.format(detail.getPrice()));
        }
        System.out.print(detailSeparator);
    }
}


