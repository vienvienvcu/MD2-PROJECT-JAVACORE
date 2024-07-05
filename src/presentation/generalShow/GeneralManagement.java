package presentation.generalShow;

import business.entity.Category;
import business.entity.Product;
import business.feature.Impl.CategoryFeatureImpl;
import business.feature.Impl.ProductFeatureImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import static business.utils.Colors.BLUE;
import static business.utils.Colors.GREEN;

public class GeneralManagement {
    public static void showMenuGeneral(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println(BLUE + "┏━━━━━━━━━━━━━━━━━━ MENU GENERAL ━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃                                                   ┃");
            System.out.println("┃      " + GREEN + "1. SEARCH PRODUCT DETAIL BY ID    " + BLUE + "           ┃");
            System.out.println("┃      " + GREEN + "2. SHOW PRODUCTS BY CATEGORY          " + BLUE + "       ┃");
            System.out.println("┃      " + GREEN + "3. SHOW ALL NEW PRODUCTS      " + BLUE + "               ┃");
            System.out.println("┃      " + GREEN + "4. SHOW ALL PRODUCTS ON SALES     " + BLUE + "           ┃");
            System.out.println("┃      " + GREEN + "5. SEARCH PRODUCT BY NAME OR DESCRIPTION  " + BLUE + "   ┃");
            System.out.println("┃      " + GREEN + "6. BACK               " + BLUE + "                       ┃");
            System.out.println("┃                                                   ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.println("Your choice to 1 from 6: ");
            int choice = inputNumber(scanner);
            switch (choice) {
                case 1:
                    displayProductById(scanner);
                    break;
                case 2:
                    displayProductByCategory(scanner);
                    break;
                case 3:
                    displayAllNewProducts();
                    break;
                case 4:
                    displayAllProductsOnSales();
                    break;
                case 5:
                    searchProductByNameOrDescription(scanner);
                    break;
                case 6:
                    isExit = false;
                    break;
                default:
                    System.err.println("Enter a valid choice 1-6");
            }
        } while (isExit);
    }

    public static void displayProductById(Scanner scanner) {
        System.out.println("Enter product ID you want to search: ");
        int id = inputNumber(scanner);
        for (Product product: ProductFeatureImpl.productList){
            if (product.getProductId() == id){
                product.displayProductData();
            }
        }

    }

    public static void displayProductByCategory(Scanner scanner) {
        System.out.println("Enter category ID you want to display products for: ");
        int categoryId = inputNumber(scanner);
        for (Category category : CategoryFeatureImpl.categoryList) {
            if (category.getCategoryId() == categoryId) {
                System.out.println("Category: " + category.getCategoryName());
                for (Product product : ProductFeatureImpl.productList) {
                    if (product.getCategoryId() == categoryId) {
                        product.displayProductData();
                    }
                }
                return;
            }
        }
        System.out.println("Category not found.");
    }

    public static void displayAllNewProducts() {
        Date today = new Date();
        for (Product product : ProductFeatureImpl.productList) {
            Date addNewProduct = product.getCreatedAt();
            if (isSameDay(addNewProduct, today)) {
                product.displayProductData();
            }
        }
        System.out.println("All new Products in today : " + today);
    }

    public static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    public static void displayAllProductsOnSales() {
        for (Product product: ProductFeatureImpl.productList){
            if (product.getStatus()){
                product.displayProductData();
            }
        }
        System.out.println("All products on sales");
    }

    public static void searchProductByNameOrDescription(Scanner scanner) {
        System.out.println("Enter product name or description: ");
        String search = scanner.nextLine().toLowerCase();
        for (Product product: ProductFeatureImpl.productList){
            if (product.getProductName().toLowerCase().contains(search)||product.getDescription().toLowerCase().contains(search)){
                product.displayProductData();
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
