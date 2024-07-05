package business.entity;

import business.feature.Impl.CartFeatureImpl;
import business.feature.Impl.ProductFeatureImpl;
import presentation.userShow.UserManagement;

import java.io.Serializable;
import java.util.Scanner;

import static business.feature.Impl.ProductFeatureImpl.productList;

public class CartItem implements Serializable {
    private int cartItemId;
    private int productId;
    private Users user;
    private int quantity;

//    =====================CONTRACTOR=============================

    public CartItem() {
    }

    public CartItem(int cartItemId, int productId, int quantity, Users user) {
        this.cartItemId = cartItemId;
        this.productId = productId;
        this.quantity = quantity;
        this.user = user;
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    //      =====================INPUT CART==============================
    public void inputsCartItemData(Scanner scanner){
        this.cartItemId = inputCartId();
        this.productId = inputProductId(scanner);
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
        String format = "| %-5s | %-20s | %-10s | %-10s | %-30s | %-10s |\n";
        String separator = "+-------+----------------------+------------+------------+--------------------------------+------------+\n";

        do {
            // Table header
            System.out.print(separator);
            System.out.format(format, "ID", "Product Name", "Price", "Stock", "Description", "Status");
            System.out.print(separator);

            // Product data
            for (int i = 0; i < ProductFeatureImpl.productList.size(); i++) {
                Product product = ProductFeatureImpl.productList.get(i);
                System.out.format(format,
                        (i + 1),
                        product.getProductName(),
                        product.getUniPrice(),
                        product.getStockQuantity(),
                        product.getDescription(),
                        product.getStatus());
            }

            System.out.print(separator);
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice > 0 && choice <= productList.size()) {
                return ProductFeatureImpl.productList.get(choice - 1).getProductId();
            } else {
                System.err.println("You have entered an invalid choice. Try again.");
            }
        } while (true);
    }

    public int inputQuantity(Scanner scanner){
        System.out.println("Enter the quantity of the cart item");
        do {
            try {
                int quantity = Integer.parseInt(scanner.nextLine());
                boolean flag = false;
                for (int i = 0; i < productList.size(); i++) {
                    if (quantity > 0 && quantity <= productList.get(i).getStockQuantity()) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    return quantity;
                }else {
                    System.err.println("Invalid quantity entered,please try again");
                }
            }catch (NumberFormatException e) {
                System.out.println("Enter the quantity of the cart item");
            }

        }while (true);
    }

//      =====================SHOW CART===============================
    public void displayCartData(){
        String format = "| %-10s | %-20s | %-20s | %-10s |\n";
        String separator = "+------------+----------------------+----------------------+------------+\n";

        // Table header
        System.out.print(separator);
        System.out.format(format, "Cart ID", "Product", "Users", "Quantity");
        System.out.print(separator);

        // Product data
        System.out.format(format, this.cartItemId, this.productId, this.user.getUserName(), this.quantity);
        System.out.print(separator);
   }

}
