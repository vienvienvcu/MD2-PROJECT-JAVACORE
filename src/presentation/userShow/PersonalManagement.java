package presentation.userShow;

import business.entity.Product;
import business.entity.Users;
import business.feature.Impl.UserFeatureImpl;

import java.util.Scanner;

import static business.utils.Colors.BLUE;
import static business.utils.Colors.GREEN;

public class PersonalManagement {

    public static UserFeatureImpl userFeature = new UserFeatureImpl();

    public static void showMenuPersonal(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println(BLUE + "┏━━━━━━━━━━━━ MENU PERSONAL INFORMATION ━━━━━━━━━━━━━┓");
            System.out.println("┃                                                    ┃");
            System.out.println("┃      " + GREEN + "1. SHOW PERSONAL INFORMATION    " + BLUE + "              ┃");
            System.out.println("┃      " + GREEN + "2. ADD DETAIL PERSONAL INFORMATION         " + BLUE + "   ┃");
            System.out.println("┃      " + GREEN + "3. UPDATE PERSONAL INFORMATION         " + BLUE + "       ┃");
            System.out.println("┃      " + GREEN + "4. CHANGE PASSWORD     " + BLUE + "                       ┃");
            System.out.println("┃      " + GREEN + "5. ADDRESS MANAGEMENT     " + BLUE + "                    ┃");
            System.out.println("┃      " + GREEN + "6. BACK   " + BLUE + "                                    ┃");
            System.out.println("┃                                                    ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.println("Your choice to 1 from 6: ");
            int choice = inputNumber(scanner);
            switch (choice) {
                case 1:
                    showMenuPersonalInformation();
                    break;
                case 2:
                    addDetails(scanner);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    AddressManagement.showMenuAddress(scanner);
                    break;
                case 6:
                    isExit = false;
                    break;
                default:
                    System.err.println("Enter a valid choice 1-6");
            }
        } while (isExit);
    }

    public static void addDetails(Scanner scanner) {
        System.out.println("Enter detail information: ");
        UserFeatureImpl.userLogin.inputUser(scanner);
        userFeature.addDetailUser(UserFeatureImpl.userLogin);
//        userFeature.save(UserFeatureImpl.userLogin);
        System.out.println("You have successfully added details into the database");
    }

    public static void updateDetails(Scanner scanner) {

    }

    public static void showMenuPersonalInformation() {
       for (Users user : userFeature.getUsersList()){
           if (user.getUserName().equals(UserFeatureImpl.userLogin.getUserName())){
               user.displayUser();
           }
       }
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
