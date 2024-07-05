package presentation.adminShow;

import java.util.Scanner;

import static business.utils.Colors.BLUE;
import static business.utils.Colors.GREEN;

public class AdminManagement {
    public static void showMenuAdmin(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println(BLUE + "┏━━━━━━━━━━━━━━━━━━ MENU ADMIN ━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃                                                 ┃");
            System.out.println("┃      " + GREEN + "1. MANAGEMENT CATALOG    " + BLUE + "                  ┃");
            System.out.println("┃      " + GREEN + "2. MANAGEMENT PRODUCT          " + BLUE + "            ┃");
            System.out.println("┃      " + GREEN + "3. MANAGEMENT ORDER      " + BLUE + "                  ┃");
            System.out.println("┃      " + GREEN + "4. MANAGEMENT USERS     " + BLUE + "                   ┃");
            System.out.println("┃      " + GREEN + "5. MANAGEMENT STATISTICAL   " + BLUE + "               ┃");
            System.out.println("┃      " + GREEN + "6. SHOW LIST ROLE   " + BLUE + "                       ┃");
            System.out.println("┃      " + GREEN + "7. LOG OUT               " + BLUE + "                  ┃");
            System.out.println("┃                                                 ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.println("Your choice to 1 from 7: ");
            int choice = inputNumber(scanner);
            switch (choice) {
                case 1:
                    ManagementCategory.showMenuCategory(scanner);
                    break;
                case 2:
                    ManagementProduct.showMenuProduct(scanner);
                    break;
                case 3:
                    break;
                case 4:
                    ManagementUsers.showMenuUsersAdmin(scanner);
                    break;
                case 5:
                    break;
                case 6:
                    RoleManagement.showMenuRole(scanner);
                    break;
                case 7:
                    isExit = false;
                    break;
                default:
                    System.err.println("Enter a valid choice 1-6");
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