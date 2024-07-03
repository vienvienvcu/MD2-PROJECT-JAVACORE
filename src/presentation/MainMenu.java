package presentation;

import business.constants.Role;
import business.entity.Users;
import business.feature.IUserFeature;
import business.feature.Impl.UserFeatureImpl;
import presentation.adminShow.AdminManagement;
import presentation.generalShow.GeneralManagement;
import presentation.userShow.UserManagement;

import java.util.Scanner;

import static business.utils.Colors.*;

public class MainMenu {
    public static IUserFeature userFeature = new UserFeatureImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(BLUE + "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━ WEB BÁN HÀNG ━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃                           ┃                             ┃                                  ┃                           ┃                           ┃");
            System.out.println("┃      " + PURPLE + "1. LOG IN        " + BLUE + "    ┃          " + YELLOW + "2. REGISTER       " + BLUE +" ┃     " + GREEN + "   3. FORGOT PASSWORD  " + BLUE + "      ┃     " + CYAN + "4. GENERAL            ┃     "+ RED +" 5. EXIT       " + BLUE +"       ┃");
            System.out.println("┃                           ┃                             ┃                                  ┃                           ┃                           ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.println("Your choice to 1 from 4: ");
            int choice = inputNumber(scanner);
            switch (choice) {
                case 1:
                    handleLogin(scanner);
                    break;
                case 2:
                    handleRegister(scanner);
                    break;
                case 3:
                    break;
                case 4:
                    GeneralManagement.showMenuGeneral(scanner);
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a valid choice 1-5");
            }
        }while (true);
    }

    private static void handleRegister(Scanner scanner) {
        userFeature.register();
        System.out.println("Your register successfully");;
    }

    private static void handleLogin(Scanner scanner) {
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Enter vào password: ");
        String password = scanner.nextLine();
        Users user = userFeature.login(email, password);
        if (user == null) {
            System.err.println("Email và password không đúng");
            return;
        }
        // check quyền
        if (user.getRoleName().equals(Role.ROLE_ADMIN)) {
            // điều hướng đến menu admin
            AdminManagement adminManagement = new AdminManagement();
            adminManagement.showMenuAdmin(scanner);
        } else {
            // điều hướng đến menu user
            UserManagement userManagement = new UserManagement();
            userManagement.showMenuUser(scanner);

        }
    }

    public static int inputNumber(Scanner scanner) {
        do {
            try {
                return Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e) {
                System.err.println("Please a input number choice");
            }
        }while (true);

    }
}
