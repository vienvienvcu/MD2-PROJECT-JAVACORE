package presentation.userShow;

import business.entity.Address;
import business.entity.Users;
import business.feature.Impl.AddressFeatureImpl;
import business.feature.Impl.UserFeatureImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static business.feature.Impl.UserFeatureImpl.userLogin;
import static business.utils.Colors.BLUE;
import static business.utils.Colors.GREEN;

public class AddressManagement {
    public static AddressFeatureImpl addressFeature = new AddressFeatureImpl();

    public static void showMenuAddress(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println(BLUE + "┏━━━━━━━━━━━━━━━━━━━ MENU ADDRESS ━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃                                                    ┃");
            System.out.println("┃      " + GREEN + "1. SHOW ADDRESS OF USER BY ID    " + BLUE + "             ┃");
            System.out.println("┃      " + GREEN + "2. SHOW ALL LIST ADDRESS OF USER        " + BLUE + "      ┃");
            System.out.println("┃      " + GREEN + "3. ADD NEW ADDRESS OF USER   " + BLUE + "                 ┃");
            System.out.println("┃      " + GREEN + "4. DELETE ADDRESS OF USER BY ID      " + BLUE + "         ┃");
            System.out.println("┃      " + GREEN + "5. BACK   " + BLUE + "                                    ┃");
            System.out.println("┃                                                    ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.println("Your choice to 1 from 5: ");
            int choice = inputNumber(scanner);
            switch (choice) {
                case 1:
                    showAddressById(scanner);
                    break;
                case 2:
                    showAddressesForCurrentUser();
                    break;
                case 3:
                    addNewAddress(scanner);
                    break;
                case 4:
                    deleteAddress(scanner);
                    break;
                case 5:
                    isExit = false;
                    break;
                default:
                    System.err.println("Enter a valid choice 1-6");
            }
        } while (isExit);
    }


    public static void addNewAddress(Scanner scanner) {
        System.out.println("Enter number address you want to add: ");
        int number = inputNumber(scanner);
            for (int i = 0; i < number; i++) {
                System.out.println("Add address " + (i + 1) + ":");
                Address address = new Address();

                // Nhập lại tên người dùng cho đến khi tìm thấy hoặc người dùng dừng lại
                Users user = null;
                while (user == null) {
                    user = findUser(scanner);
                    if (user == null) {
                        System.out.println("User not found. Please try again.");
                    }
                }

                address.setUser(user);
                address.inputAddressData(scanner);
                addressFeature.save(address);
            }
            System.out.println("You have successfully added address");
    }

    public static void showAddressesForCurrentUser() {
        String currentUserEmail = getCurrentUserEmail(); // Hàm này trả về email của người dùng hiện tại

        if (currentUserEmail == null) {
            System.err.println("Current user not authenticated.");
            return;
        }

        List<Address> currentUserAddresses = new ArrayList<>();

        // Lặp qua danh sách địa chỉ và lọc ra các địa chỉ của người dùng hiện tại
        for (Address address : AddressFeatureImpl.addressList) {
            if (address.getUser().getEmail().equals(currentUserEmail)) {
                currentUserAddresses.add(address);
            }
        }

        // Kiểm tra nếu danh sách rỗng thì thông báo và thoát
        if (currentUserAddresses.isEmpty()) {
            System.err.println("No addresses found for current user.");
            return;
        }

        // Hiển thị danh sách các địa chỉ của người dùng hiện tại
        System.out.println("Addresses for current user:");
        for (Address address : currentUserAddresses) {
            address.displayAddressData();
        }
    }

    public static String getCurrentUserEmail() {
        if (userLogin != null) {
            return userLogin.getEmail();
        } else {
            return null;
        }
    }

    public static Users findUser(Scanner scanner) {
        System.out.println("Enter user name: ");
        String userName = scanner.nextLine().trim(); // Nhập tên người dùng và xóa khoảng trắng
        for (Users user : UserFeatureImpl.usersList) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        System.err.println("User not found.");
        return null;
    }

    public static void showAddressById(Scanner scanner) {
        System.out.println("Enter address id you want to show: ");
        boolean isExit = false;
        int id = inputNumber(scanner);
        for (Address address : addressFeature.getAll()) {
            if (address.getAddressId() == id) {
                isExit = true;
                address.displayAddressData();
            }
        }
        if (!isExit) {
            System.err.println("Not found address with id " + id);
        }
        System.out.println("You have successfully added address");
    }

    public static void deleteAddress(Scanner scanner) {
        System.out.println("Enter address id you want to delete: ");
        int id = inputNumber(scanner);
        addressFeature.delete(id);
        System.out.println("You have successfully deleted address with id " + id);
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

