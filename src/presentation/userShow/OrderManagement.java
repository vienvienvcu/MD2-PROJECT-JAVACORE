package presentation.userShow;

import business.feature.ICartItemFeature;
import business.feature.IOderDetailFeature;
import business.feature.IOrdersFeature;
import business.feature.Impl.CartFeatureImpl;
import business.feature.Impl.OrderDetailFeatureImpl;
import business.feature.Impl.OrdersFeatureImpl;

import java.util.Scanner;

import static business.utils.Colors.BLUE;
import static business.utils.Colors.GREEN;

public class OrderManagement {
    public static IOrdersFeature ordersFeature = new OrdersFeatureImpl();
    public static void showMenuOrder(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println(BLUE + "┏━━━━━━━━━━━━━━━━━━━━━━ MENU ORDER ━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃                                                      ┃");
            System.out.println("┃      " + GREEN + "1. SHOW ALL PRODUCTS IN CART   " + BLUE + "                             ┃");
            System.out.println("┃      " + GREEN + "2. SHOW DETAIL ORDER " + BLUE + "                           ┃");
            System.out.println("┃      " + GREEN + "3. SHOW HISTORY ODER BY ORDER STATUS   " + BLUE + "         ┃");
            System.out.println("┃      " + GREEN + "4. SHOW ALL HISTORY ORDERED    " + BLUE + "                 ┃");
            System.out.println("┃      " + GREEN + "5. BACK               " + BLUE + "                          ┃");
            System.out.println("┃                                                      ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.println("Your choice to 1 from 5: ");
            int choice = inputNumber(scanner);
            switch (choice) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    isExit = false;
                    break;
                default:
                    System.err.println("Enter a valid choice 1-5");
            }
        } while (isExit);
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
}
