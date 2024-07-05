package presentation.userShow;

import business.entity.Address;
import business.entity.CartItem;
import business.feature.ICartItemFeature;
import business.feature.Impl.AddressFeatureImpl;
import business.feature.Impl.CartFeatureImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static business.feature.Impl.UserFeatureImpl.userLogin;
import static business.utils.Colors.BLUE;
import static business.utils.Colors.GREEN;
import static presentation.userShow.AddressManagement.getCurrentUserEmail;

public class CartManagement {

    public static ICartItemFeature cartItemFeature = new CartFeatureImpl();

    public static void showMenuCart(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println(BLUE + "┏━━━━━━━━━━━━━━━━━━ MENU CART ━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃                                                ┃");
            System.out.println("┃      " + GREEN + "1. SHOW ALL CART   " + BLUE + "                            ┃");
            System.out.println("┃      " + GREEN + "2. ADD NEW PRODUCT IN CART          " + BLUE + "           ┃");
            System.out.println("┃      " + GREEN + "3. DELETE ONE PRODUCT IN CART    " + BLUE + "              ┃");
            System.out.println("┃      " + GREEN + "4. DELETE ALL PRODUCTS IN CART     " + BLUE + "            ┃");
            System.out.println("┃      " + GREEN + "5. CHANGE THE ORDER QUANTITY OF A PRODUCTS    " + BLUE + " ┃");
            System.out.println("┃      " + GREEN + "6. BACK               " + BLUE + "                         ┃");
            System.out.println("┃                                                ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.println("Your choice to 1 from 6: ");
            int choice = inputNumber(scanner);
            switch (choice) {
                case 1:
                    showCart();
                    break;
                case 2:
                    addNewCart(scanner);
                    break;
                case 3:
                    deleteCartItem(scanner);
                    break;
                case 4:
                   CartFeatureImpl.allDeleteProductCart();
                    break;
                case 5:
                    updateCartItemQuantity(scanner);
                    break;
                case 6:
                    isExit = false;
                    break;
                default:
                    System.err.println("Enter a valid choice 1-5");
            }
        } while (isExit);
    }

    public static void addNewCart(Scanner scanner) {
        CartItem cartItem = new CartItem();
        cartItem.setUser(userLogin);
        cartItem.inputsCartItemData(scanner);
        cartItemFeature.save(cartItem);
        System.out.println("You have successfully added cart");

    }

    public static void showCart() {
        String currentUserEmail = getCurrentUserEmail();

        if (currentUserEmail == null) {
            System.err.println("Current user not exist.");
            return;
        }
        List<CartItem> currentCart = new ArrayList<>();
        // Lặp qua danh sách cart và lọc ra các cart của người dùng hiện tại
        for (CartItem cartItem : CartFeatureImpl.cartItemList) {
            if (cartItem.getUser().getEmail().equals(currentUserEmail)) {
                currentCart.add(cartItem);
            }
        }
        // Kiểm tra nếu danh sách rỗng thì thông báo và thoát
        if (currentCart.isEmpty()) {
            System.err.println("Cart is empty.");
            return;
        }
        // Hiển thị danh sách cart của người dùng hiện tại
        System.out.println("Cart for current user:");
        for (CartItem cartItem : currentCart) {
            cartItem.displayCartData();
        }
    }

    public static void deleteCartItem(Scanner scanner) {
        System.out.println("Enter cart id you want to delete: ");
        int cartId = Integer.parseInt(scanner.nextLine());
        cartItemFeature.delete(cartId);
        System.out.println("You have successfully deleted the cart item" + cartId);
    }

    public static void updateCartItemQuantity(Scanner scanner) {
        System.out.println("Enter the cart item id you want to update: ");
        int cartItemId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the new quantity: ");
        int newQuantity = Integer.parseInt(scanner.nextLine());
        ((CartFeatureImpl) cartItemFeature).updateQuantity(cartItemId, newQuantity);
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


