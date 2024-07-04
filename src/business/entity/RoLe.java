package business.entity;

import business.constants.RoleName;
import business.feature.Impl.RoleFeatureImpl;

import java.util.Scanner;

public class RoLe {
    private int roleId;
    private RoleName roleName;
//
    public RoLe() {
    }

    public RoLe(int roleId, RoleName roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public void inputRoleData(Scanner scanner) {
        this.roleId = inputRoleId();
        this.roleName = inputRoleName(scanner).getRoleName();

    }

    public int inputRoleId() {
        int idRoleMax = 0;
        for (int i = 0; i < RoleFeatureImpl.roLeList.size(); i++) {
            if (RoleFeatureImpl.roLeList.get(i).getRoleId() > idRoleMax) {
                idRoleMax = RoleFeatureImpl.roLeList.get(i).getRoleId();
            }

        }
        return idRoleMax + 1;
    }

    public RoLe inputRoleName(Scanner scanner) {
        while (true) {
            System.out.println("Enter role name (ROLE_ADMIN, ROLE_USER):");
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                this.roleName = RoleName.valueOf(input);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid role name. Please enter either ROLE_ADMIN or ROLE_USER.");
            }
        }
        return this;
    }

    public void displayRoleData() {
        String format = "| %-20s | %-20s |\n";
        String separator = "+----------------------+----------------------+\n";

        // Print headers
        System.out.print(separator);
        System.out.printf(format, "Role ID", "Role Name");
        System.out.print(separator);

        // Print role data
        System.out.printf(format, this.roleId, this.roleName);
        System.out.print(separator);
    }

}
