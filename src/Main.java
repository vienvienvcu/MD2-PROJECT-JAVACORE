import java.text.SimpleDateFormat;
import java.util.Date;

import static business.utils.Colors.BLUE;
import static business.utils.Colors.GREEN;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main {


    public static class User {
        private int usersId;
        private String userName;
        private String fullName;
        private boolean gender; // true for male, false for female
        private String address;
        private String email;
        private String phone;
        private String password;
        private String confirmPassword;
        private Date creationDate;
        private Date updatedDate;
        private boolean statusUser; // true for active, false for inactive
        private boolean isDeleted; // true for deleted, false for not deleted
        private String roleName;

        // Constructor, getters, and setters not shown for brevity

        public void displayUserData() {
            // Date format
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String format = "| %-12s | %-20s | %-20s | %-12s | %-20s | %-30s | %-15s | %-12s |\n";
            String separator = "+--------------+----------------------+----------------------+--------------+----------------------+--------------------------------+-----------------+--------------+\n";

            // Table header
            System.out.printf(separator);
            System.out.format(format, "User ID", "User Name", "Full Name", "Gender", "Address", "Email", "Phone", "Role Name");
            System.out.printf(separator);

            // User data
            System.out.format(format, usersId, userName, fullName, gender ? "Male" : "Female", address, email, phone, roleName);
            System.out.printf(separator);

            System.out.format(format, "Password", "Confirm Password", "Creation Date", "Update Date", "Status", "Deleted", "", "");
            System.out.printf(separator);

            System.out.format(format, password, confirmPassword, sdf.format(creationDate), sdf.format(updatedDate),
                    statusUser ? "Active" : "Inactive", isDeleted ? "Deleted" : "Not Deleted", "", "");
            System.out.printf(separator);
            System.out.println();
        }

    }

    public static void main(String[] args) {
        // Sample data for testing
        User user = new User();
        user.usersId = 1;
        user.userName = "johndoe";
        user.fullName = "John Doe";
        user.gender = true;
        user.address = "123 Main St";
        user.email = "johndoe@example.com";
        user.phone = "1234567890";
        user.password = "password123";
        user.confirmPassword = "password123";
        user.creationDate = new Date();
        user.updatedDate = new Date();
        user.statusUser = true;
        user.isDeleted = false;
        user.roleName = "Admin";

        user.displayUserData();
    }

}