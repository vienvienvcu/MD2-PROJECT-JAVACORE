package presentation.adminShow;

import business.entity.RoLe;
import business.feature.Impl.RoleFeatureImpl;

import java.util.Scanner;


public class RoleManagement {
    static public RoleFeatureImpl roleFeature = new RoleFeatureImpl();
    public static void showRole(Scanner scanner) {
        System.out.println("Enter number of roles you want to add: ");
        int input = inputNumber(scanner);
        for (int i = 0; i < input; i++) {
            System.out.println("Role name " + (i + 1) + ": ");
            RoLe roLes = new RoLe();
            roLes.inputRoleData(scanner);
            roleFeature.save(roLes);
        }
        for (RoLe roLe: RoleFeatureImpl.roLeList){
            roLe.displayRoleData();
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
