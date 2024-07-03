package business.entity;

import business.feature.Impl.CartFeatureImpl;
import business.feature.Impl.ProductFeatureImpl;

import java.util.Scanner;

public class CartItem {
    private int cartItemId;
    private int productId;
    private int userId;
    private int quantity;

//    =====================CONTRACTOR=============================

    public CartItem() {
    }

    public CartItem(int cartItemId, int productId, int quantity, int userId) {
        this.cartItemId = cartItemId;
        this.productId = productId;
        this.quantity = quantity;
        this.userId = userId;
    }

//    =====================METHOD GET/SET=========================

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



//      =====================INPUT CART==============================
    public void displayCartItemData(Scanner scanner){
        this.cartItemId = inputCartId();
        this.productId = inputProductId(scanner);
        this.userId = inputUserId(scanner);
        this.quantity = inputQuantity(scanner);
    }
//      ==================== VALIDATION==============================

    public int inputCartId(){
        int cartItemIdMax = 0;
        for (CartItem cartItem: CartFeatureImpl.cartItemList){
            if(cartItem.getCartItemId() > cartItemIdMax){
                cartItemIdMax = cartItem.getCartItemId();
            }
        }
        return cartItemIdMax + 1;
    }

    public int inputProductId(Scanner scanner){
        do {
            for (int i = 0; i < ProductFeatureImpl.productList.size(); i++) {
                System.out.println((i + 1) + ". " + ProductFeatureImpl.productList.get(i).getProductName()
                        + "   price : " +( ProductFeatureImpl.productList.get(i).getUniPrice()
                        + "   Stock : " + ProductFeatureImpl.productList.get(i).getStockQuantity()));

            }
            System.out.println((ProductFeatureImpl.productList.size()+1) +". Back");
            System.out.println("Enter your choice : ");
            int choice = Integer.parseInt(scanner.nextLine());
            if ( choice >0 && choice <= ProductFeatureImpl.productList.size()) {
                return ProductFeatureImpl.productList.get(choice - 1).getProductId();
            }else if (choice == (ProductFeatureImpl.productList.size()+1))
                return -1;
        }while (true);
    }

    public int inputUserId(Scanner scanner) {
        do {
            System.out.println("Enter user ID: ");
            try {
                int userId = Integer.parseInt(scanner.nextLine());
                // Add any necessary validation here, for example checking if the user exists
                return userId;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid user ID.");
            }
        } while (true);
    }


    public int inputQuantity(Scanner scanner){
        System.out.println("Enter the quantity of the cart item");
        do {
            try {
                int quantity = Integer.parseInt(scanner.nextLine());
                if (quantity >= 0 && quantity <= ProductFeatureImpl.productList.get(productId).getStockQuantity()) {
                    return quantity;
                }else {
                    System.out.println("Invalid quantity entered,please try again");
                }
            }catch (NumberFormatException e) {
                System.out.println("Enter the quantity of the cart item");
            }

        }while (true);
    }

//      =====================SHOW CART===============================
    public void displayProductData(Scanner scanner){

   }


}
