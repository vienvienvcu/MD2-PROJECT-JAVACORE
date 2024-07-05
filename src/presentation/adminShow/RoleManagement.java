package presentation.adminShow;

import business.entity.RoLe;
import business.feature.Impl.CategoryFeatureImpl;
import business.feature.Impl.RoleFeatureImpl;

import java.util.Scanner;

import static business.utils.Colors.BLUE;
import static business.utils.Colors.GREEN;


public class RoleManagement {
    static public RoleFeatureImpl roleFeature = new RoleFeatureImpl();

    public static void showMenuRole(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println(BLUE + "┏━━━━━━━━━━━━━━━━━━ MENU ROLE ━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃                                                ┃");
            System.out.println("┃      " + GREEN + "1. SHOW ALL ROLE   " + BLUE + "                       ┃");
            System.out.println("┃      " + GREEN + "2. ADD NEW ROLE          " + BLUE + "                 ┃");
            System.out.println("┃      " + GREEN + "3. DELETE ROLE    " + BLUE + "                        ┃");
            System.out.println("┃      " + GREEN + "4. UPDATE ROLE    " + BLUE + "                        ┃");
            System.out.println("┃      " + GREEN + "5. BACK               " + BLUE + "                    ┃");
            System.out.println("┃                                                ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.println("Your choice to 1 from 5: ");
            int choice = inputNumber(scanner);
            switch (choice) {
                case 1:
                  showRole();
                    break;
                case 2:
                  addRole(scanner);
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

    public static void addRole(Scanner scanner) {
        System.out.println("Enter number of roles you want to add: ");
        int input = inputNumber(scanner);
        for (int i = 0; i < input; i++) {
            System.out.println("Role name " + (i + 1) + ": ");
            RoLe roLes = new RoLe();
            roLes.inputRoleData(scanner);
            roleFeature.save(roLes);
        }
    }
    public static void showRole() {
        if (roleFeature.getAll().isEmpty()){
            System.out.println("There are no roles");
        }else {
            for (RoLe roLe: RoleFeatureImpl.roLeList){
                roLe.displayRoleData();
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
