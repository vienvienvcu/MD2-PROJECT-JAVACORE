package presentation.adminShow;

import business.entity.Users;
import business.feature.Impl.UserFeatureImpl;

import java.util.Collections;
import java.util.Scanner;

import static business.feature.Impl.UserFeatureImpl.sortUserById;
import static business.utils.Colors.BLUE;
import static business.utils.Colors.GREEN;

public class ManagementUsers {
    public static UserFeatureImpl userFeature = new UserFeatureImpl();
    public static void showMenuUsersAdmin(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println(BLUE + "┏━━━━━━━━━━━━━━━━━━━ MENU USERS ━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃                                                  ┃");
            System.out.println("┃      " + GREEN + "1. SHOW LIST USERS INFORMATION    " + BLUE + "          ┃");
            System.out.println("┃      " + GREEN + "2. ADD USER        " + BLUE + "                         ┃");
            System.out.println("┃      " + GREEN + "3. DELETE USER   " + BLUE + "                           ┃");
            System.out.println("┃      " + GREEN + "4. SEARCH USER BY ID  " + BLUE + "                      ┃");
            System.out.println("┃      " + GREEN + "5. SORT USER NAME       " + BLUE + "                    ┃");
            System.out.println("┃      " + GREEN + "6. BACK   " + BLUE + "                                  ┃");
            System.out.println("┃                                                  ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.println("Your choice to 1 from 5: ");
            int choice = inputNumber(scanner);
            switch (choice) {
                case 1:
                    showUsersAdmin();
                    break;
                case 2:
                    addUser(scanner);
                    break;
                case 3:
                    deleteUser(scanner);
                    break;
                case 4:
                    searchUser(scanner);
                    break;
                case 5:
                   UserFeatureImpl.sortUserById();
                    break;
                case 6:
                    isExit = false;
                    break;
                default:
                    System.err.println("Enter a valid choice 1-6");
            }
        } while (isExit);
    }



    public static void searchUser(Scanner scanner) {
        System.out.println("Enter user ID you want to search: ");
        int userIdSearch = inputNumber(scanner);
        for (Users user: UserFeatureImpl.usersList){
            if (user.getUsersId() == userIdSearch){
                user.displayUserData();
            }
        }
        System.out.println("You have found the user with ID " + userIdSearch);
    }

    public static void addUser(Scanner scanner) {
        System.out.println("Enter number of users you want to add: ");
        int numberOfUsers = inputNumber(scanner);
        for (int i = 0; i < numberOfUsers; i++) {
            System.out.println("User information" +(i+1) + ": ");
            Users user = new Users();
            user.inputUserData(scanner);
            userFeature.save(user);
        }
        System.out.println("You have successfully added " + numberOfUsers + " users");
    }

    public static void showUsersAdmin() {
        for (Users user : UserFeatureImpl.usersList) {
            user.displayUserData();
        }
        System.out.println("You have successfully added " + UserFeatureImpl.usersList.size() + " users");
    }

    public static void deleteUser(Scanner scanner) {
        System.out.println("Enter ID of the user you want to delete: ");
        int userIdDelete = inputNumber(scanner);
        userFeature.delete(userIdDelete);
        System.out.println("You have successfully deleted " + userIdDelete + " users");
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
