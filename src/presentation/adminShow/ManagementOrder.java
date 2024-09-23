package presentation.adminShow;

import business.constants.OrderStatus;
import business.entity.Orders;
import business.feature.Impl.OrdersFeatureImpl;


import java.util.Scanner;

import static business.utils.Colors.BLUE;
import static business.utils.Colors.GREEN;
import static presentation.userShow.CartManagement.inputNumber;

public class ManagementOrder {
    public static OrdersFeatureImpl ordersFeature = new OrdersFeatureImpl();

    public static void showMenuOrders(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println(BLUE + "┏━━━━━━━━━━━━━━━━━━━ MENU ORDER━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃                                                  ┃");
            System.out.println("┃      " + GREEN + "1. SHOW DETAIL ORDERS " + BLUE + "                      ┃");
            System.out.println("┃      " + GREEN + "2. SHOW LIST ORDERS BY ORDER STATUS " + BLUE + "        ┃");
            System.out.println("┃      " + GREEN + "3. BACK  " + BLUE + "                                   ┃");
            System.out.println("┃                                                  ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.println("Your choice to 1 from 3: ");
            int choice = inputNumber(scanner);
            switch (choice) {
                case 1:
                    showMenuDetailOrders();
                    break;
                case 2:
                    showMenuDetailOrderByStatus(scanner);
                    break;
                case 3:
                    isExit = false;
                    break;
                default:
                    System.err.println("Enter a valid choice 1-3");
            }
        } while (isExit);
    }

    public static void showMenuDetailOrderByStatus(Scanner scanner) {
        // Cập nhật trạng thái của tất cả đơn hàng trước khi hiển thị
        Orders.updateAllOrdersStatus(OrdersFeatureImpl.orderlist);

        System.out.println("Nhập trạng thái đơn hàng để  (e.g., WAITING,CONFIRM,DELIVERY,SUCCESS,CANCEL): ");
        String statusInput = scanner.nextLine().toUpperCase();
        OrderStatus status;
        try {
            status = OrderStatus.valueOf(statusInput);
        } catch (IllegalArgumentException e) {
            System.err.println("Trạng thái không hợp lệ. Vui lòng nhập trạng thái đơn hàng hợp lệ.");
            return;
        }
        for (Orders order : ordersFeature.getAll()) {
            if (order.getStatus() == status) {
                order.displayOrderData();
            }
        }

    }

    public static void showMenuDetailOrders() {
        for (Orders order : ordersFeature.getAll()) {
            order.displayOrderData();
        }
    }
}
