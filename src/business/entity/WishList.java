package business.entity;


import business.feature.Impl.ProductFeatureImpl;
import business.feature.Impl.WishListFeatureImpl;

import java.io.Serializable;
import java.util.Scanner;

import static business.feature.Impl.ProductFeatureImpl.productList;

public class WishList implements Serializable {
    private int wishListId;
    private Users user;
    private int productId;
//      =====================CONTRACTOR=============================

    public WishList() {
    }

    public WishList(int productId, Users user, int wishListId) {
        this.productId = productId;
        this.user = user;
        this.wishListId = wishListId;
    }

//      =====================METHOD GET/SET=========================

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getWishListId() {
        return wishListId;
    }

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
    }


//      ==================== VALIDATION==============================
    public int inputWishListId(){
        int idWishMax =0;
        for (WishList wishList : WishListFeatureImpl.wishLists ){
            if(wishList.getWishListId() > idWishMax){
                idWishMax = wishList.getWishListId();
            }
        }
        return idWishMax + 1;

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
//      =====================INPUT CART==============================

    public void inputWishList(Scanner scanner){
        this.wishListId = inputWishListId();
        this.productId = inputProductId(scanner);
    }
//      =====================SHOW CART===============================

    public void displayWishList(){
        String format = "| %-10s | %-20s | %-10s |\n";
        String separator = "+------------+----------------------+------------+\n";

        // Table header
        System.out.print(separator);
        System.out.format(format, "Wish ID", "User", "Product ID");
        System.out.print(separator);

        // Wish list data
        System.out.format(format, this.wishListId, this.user.getUserName(),this.productId);
        System.out.print(separator);
    }
}