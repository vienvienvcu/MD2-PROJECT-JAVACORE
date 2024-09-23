package presentation.userShow;


import business.constants.OrderStatus;
import business.entity.*;
import business.feature.IOrdersFeature;

import business.feature.Impl.CartFeatureImpl;
import business.feature.Impl.OrdersFeatureImpl;
import business.feature.Impl.ProductFeatureImpl;
import business.utils.IOFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static business.constants.OrderStatus.SUCCESS;
import static business.feature.Impl.UserFeatureImpl.userLogin;
import static business.utils.Colors.BLUE;
import static business.utils.Colors.GREEN;
import static java.io.File.separator;

public class OrderManagement {
    public static IOrdersFeature ordersFeature = new OrdersFeatureImpl();

    public static void showMenuOrder(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println(BLUE + "┏━━━━━━━━━━━━━━━━━━━━━━ MENU ORDER ━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃                                                      ┃");
            System.out.println("┃      " + GREEN + "1. SHOW ALL PRODUCTS IN CART   " + BLUE + "                 ┃");
            System.out.println("┃      " + GREEN + "2. ADD CART IN ORDER " + BLUE + "                           ┃");
            System.out.println("┃      " + GREEN + "3. SHOW DETAIL ORDER " + BLUE + "                           ┃");
            System.out.println("┃      " + GREEN + "4. SHOW HISTORY ODER BY ORDER STATUS   " + BLUE + "         ┃");
            System.out.println("┃      " + GREEN + "5. SHOW ALL HISTORY ORDERED    " + BLUE + "                 ┃");
            System.out.println("┃      " + GREEN + "6. BACK               " + BLUE + "                          ┃");
            System.out.println("┃                                                      ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.println("Your choice to 1 from 6: ");
            int choice = inputNumber(scanner);
            switch (choice) {
                case 1:
                   CartManagement.showCart();
                    break;
                case 2:
                    CartManagement.showCart();
                    addCartToOrder(scanner);
                    break;
                case 3:
                    showAllOrders(scanner);
                    break;
                case 4:
                    showOrdersByStatus(scanner);
                    break;
                case 5:
                    showOrdersByStatusSuccess();
                    break;
                case 6:
                    isExit = false;
                    break;
                default:
                    System.err.println("Enter a valid choice 1-5");
            }
        } while (isExit);
    }

    public static void showOrdersByStatusSuccess() {
        String currentUserEmail = getCurrentUserEmail();
        boolean hasOrders = false;

        for (Orders order : ordersFeature.getAll()) {
            if (order.getUser() != null &&
                    order.getUser().getEmail().equals(currentUserEmail) &&
                    order.getStatus().equals(OrderStatus.SUCCESS)) {
                order.displayOrderData();
                hasOrders = true;
            }
        }

        if (!hasOrders) {
            System.out.println("No successful orders found.");
        }
    }

    public static void addCartToOrder(Scanner scanner) {
        String currentUserEmail = getCurrentUserEmail();
        List<CartItem> userCarts = getUserCarts(currentUserEmail);

        if (userCarts.isEmpty()) {
            System.out.println("Bạn không có cart nào để thêm vào đơn hàng.");
            return;
        }

        System.out.println("Chọn giỏ hàng để thêm vào đơn hàng:");
        int index = 1;
        for (CartItem cart : userCarts) {
            System.out.println(index + ". Cart ID: " + cart.getCartItemId());
            index++;
        }

        System.out.print("Nhập số giỏ hàng bạn muốn thêm hoặc 0 để hủy: ");
        int cartChoice = inputNumber(scanner);

        if (cartChoice == 0 || cartChoice > userCarts.size()) {
            System.out.println("Hủy thêm giỏ hàng vào đơn hàngs.");
            return;
        }

        CartItem chosenCart = userCarts.get(cartChoice - 1);

        Orders newOrder = createOrderFromCart(chosenCart, scanner);
        if (newOrder != null && !newOrder.getOrderDetails().isEmpty()) {
            ordersFeature.save(newOrder);
            System.out.println("Đơn hàng đã được tạo thành công!");
            // Xóa cart vừa thêm vào order khỏi danh sách CartItem
            CartFeatureImpl.cartItemList.remove(chosenCart);
            System.out.println("Giỏ hàng đã bị xóa khỏi danh sách giỏ hàng của người dùng");
        } else {
            System.out.println("No items were added to the order.");
        }
    }


    private static Orders createOrderFromCart(CartItem chosenCart, Scanner scanner) {
        Orders newOrder = new Orders();
        newOrder.inputOrderData(scanner);
        newOrder.setUser(userLogin); // Set the user for the order

        double totalPrice = 0;
        Product product = ProductFeatureImpl.findProductById(chosenCart.getProductId());
        if (product != null) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductId(chosenCart.getProductId());
            orderDetail.setProductName(product.getProductName());
            orderDetail.setPrice(product.getUniPrice());
            orderDetail.setOrderQuantity(chosenCart.getQuantity());
            orderDetail.setOrderId(newOrder.getOrderId());

            newOrder.getOrderDetails().add(orderDetail);
            totalPrice += orderDetail.getPrice() * orderDetail.getOrderQuantity();

            ProductFeatureImpl.updateStockQuantity(chosenCart.getProductId(), -chosenCart.getQuantity());
        }

        newOrder.setTotalPrice(totalPrice);
        return newOrder;
    }

    public static List<CartItem> getUserCarts(String userEmail) {
        List<CartItem> userCarts = new ArrayList<>();
        for (CartItem cart : CartFeatureImpl.cartItemList) {
            if (cart.getUser().getEmail().equals(userEmail)) {
                userCarts.add(cart);
            }
        }
        return userCarts;
    }

    public static void showAllOrders(Scanner scanner) {
        String currentUserEmail = getCurrentUserEmail();
        boolean hasOrders = false;

        for (Orders order : ordersFeature.getAll()) {
            if (order.getUser().getEmail().equals(currentUserEmail)) {
                order.displayOrderData();
                hasOrders = true;
            }
        }

        if (!hasOrders) {
            System.out.println("No orders found for the current user.");
        }
    }

    public static void showOrdersByStatus(Scanner scanner) {
        Orders.updateAllOrdersStatus(OrdersFeatureImpl.orderlist);

        System.out.print("Enter the order status (e.g., WAITING, CONFIRM, DELIVERY, SUCCESS, CANCEL): ");
        String statusInput = scanner.nextLine().toUpperCase();

        OrderStatus status;
        try {
            status = OrderStatus.valueOf(statusInput);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid order status. Please enter a valid order status.");
            return;
        }

        String currentUserEmail = getCurrentUserEmail();
        boolean hasOrders = false;

        for (Orders order : ordersFeature.getAll()) {
            if (order.getUser().getEmail().equals(currentUserEmail) && order.getStatus() == status) {
                order.displayOrderData();
                hasOrders = true;
            }
        }

        if (!hasOrders) {
            System.out.println("No orders found for the current user with the specified status.");
        }
    }

    public static int inputNumber(Scanner scanner) {
        do {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Please enter a valid number.");
            }
        } while (true);
    }

    public static String getCurrentUserEmail() {
        if (userLogin != null) {
            return userLogin.getEmail();
        } else {
            return null;
        }
    }
}
