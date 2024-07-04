package presentation.userShow;

import business.entity.Product;
import business.entity.Users;
import business.feature.Impl.UserFeatureImpl;

import java.util.Scanner;

import static business.utils.Colors.BLUE;
import static business.utils.Colors.GREEN;
import static presentation.adminShow.ManagementProduct.productFeature;

public class UserManagement {
    public static UserFeatureImpl userFeature = new UserFeatureImpl();
    public static void showMenuUser(Scanner scanner) {
        boolean isExit = true;
        do {
                System.out.println(BLUE + "┏━━━━━━━━━━━━━━━━━━ MENU USERS ━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("┃                                                 ┃");
                System.out.println("┃      " + GREEN + "1. SHOW PRODUCT LIST     " + BLUE + "                  ┃");
                System.out.println("┃      " + GREEN + "2. MANAGEMENT PERSONAL INFORMATION    " + BLUE + "     ┃");
                System.out.println("┃      " + GREEN + "3. CART MANAGEMENT       " + BLUE + "                  ┃");
                System.out.println("┃      " + GREEN + "4. ORDER MANAGEMENT      " + BLUE + "                  ┃");
                System.out.println("┃      " + GREEN + "5. MANAGE FAVORITE LIST  " + BLUE + "                  ┃");
                System.out.println("┃      " + GREEN + "6. LOG OUT               " + BLUE + "                  ┃");
                System.out.println("┃                                                 ┃");
                System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
                System.out.println("Your choice to 1 from 4: ");
                int choice = inputNumber(scanner);
                switch (choice) {
                    case 1:
                        displayProduct();
                        break;
                    case 2:
                        PersonalManagement.showMenuPersonal(scanner);
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Enter a valid choice 1-6");
                }
        } while (isExit);
    }

    public static void displayProduct() {
        if (productFeature.getAll().isEmpty()){
            System.err.println("product now is empty");
        }else {
            for (Product product : productFeature.getAll()) {
                product.displayProductData();
            }
        }
    }
    public static void showProductDetail(Scanner scanner) {

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
