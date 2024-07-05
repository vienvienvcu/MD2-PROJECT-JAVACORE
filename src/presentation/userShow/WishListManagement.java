package presentation.userShow;

import business.entity.WishList;
import business.feature.IOrdersFeature;
import business.feature.IWishListFeature;
import business.feature.Impl.OrdersFeatureImpl;
import business.feature.Impl.WishListFeatureImpl;

import java.util.Scanner;

import static business.feature.Impl.UserFeatureImpl.userLogin;
import static business.utils.Colors.BLUE;
import static business.utils.Colors.GREEN;

public class WishListManagement {
    public static IWishListFeature wishListFeature = new WishListFeatureImpl();
    public static void showMenuWishList(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println(BLUE + "┏━━━━━━━━━━━━━━━━━━━ MENU WISH LIST━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃                                                      ┃");
            System.out.println("┃      " + GREEN + "1. SHOW ALL PRODUCTS IN WISH LIST  " + BLUE + "             ┃");
            System.out.println("┃      " + GREEN + "2. DELETE ONE PRODUCT IN WISH LIST " + BLUE + "             ┃");
            System.out.println("┃      " + GREEN + "3. DELETE ALL PRODUCTS IN WISH LIST   " + BLUE + "          ┃");
            System.out.println("┃      " + GREEN + "4. ADD PRODUCT IN WISH LIST " + BLUE + "                    ┃");
            System.out.println("┃      " + GREEN + "5. BACK               " + BLUE + "                          ┃");
            System.out.println("┃                                                      ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.println("Your choice to 1 from 5: ");
            int choice = inputNumber(scanner);
            switch (choice) {
                case 1:
                    showWishList();
                    break;
                case 2:
                    deleteProductInWishList(scanner);
                    break;
                case 3:
                    WishListFeatureImpl.allDeleteProductWish();
                    break;
                case 4:
                    addProductInWishList(scanner);
                    break;
                case 5:
                    isExit = false;
                    break;
                default:
                    System.err.println("Enter a valid choice 1-5");
            }
        } while (isExit);
    }
    public static void addProductInWishList(Scanner scanner) {
        WishList wishList = new WishList();
        wishList.inputWishList(scanner);
        wishList.setUser(userLogin);
        wishListFeature.save(wishList);

    }
    public static void showWishList() {
        if (wishListFeature.getAll().isEmpty()){
            System.out.println("No wish list found");
        }
        for (WishList wishList: WishListFeatureImpl.wishLists){
            wishList.displayWishList();
        }
        System.out.println("All Wish Lists have been added to the database");
    }
    public static void deleteProductInWishList(Scanner scanner) {
        System.out.println("Enter wish ID to you want delete: ");
        int wishID = inputNumber(scanner);
        wishListFeature.delete(wishID);
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
