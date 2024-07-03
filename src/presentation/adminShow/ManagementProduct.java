package presentation.adminShow;

import business.entity.Product;
import business.feature.Impl.ProductFeatureImpl;

import java.util.Collections;
import java.util.Scanner;

import static business.utils.Colors.BLUE;
import static business.utils.Colors.GREEN;

public class ManagementProduct {
    public static ProductFeatureImpl productFeature = new ProductFeatureImpl();
    public static void showMenuProduct(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println(BLUE + "┏━━━━━━━━━━━━━━━━━━━━━━━━━ MENU PRODUCT ━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃                                                                 ┃");
            System.out.println("┃      " + GREEN + "1. SHOW PRODUCT   " + BLUE + "                                         ┃");
            System.out.println("┃      " + GREEN + "2. ADD PRODUCT          " + BLUE + "                                   ┃");
            System.out.println("┃      " + GREEN + "3. UPDATE PRODUCT      " + BLUE + "                                    ┃");
            System.out.println("┃      " + GREEN + "4. DELETE PRODUCT     " + BLUE + "                                     ┃");
            System.out.println("┃      " + GREEN + "5. SEARCH PRODUCT BY ID   " + BLUE + "                                 ┃");
            System.out.println("┃      " + GREEN + "6. SORT BY PRICE OR NAME IN DESCENDING OR ASCENDING    " + BLUE + "    ┃");
            System.out.println("┃      " + GREEN + "7. BACK               " + BLUE + "                                     ┃");
            System.out.println("┃                                                                 ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.println("Your choice to 1 from 4: ");
            int choice = inputNumber(scanner);
            switch (choice) {
                case 1:
                    displayProduct();
                    break;
                case 2:
                    addProduct(scanner);
                    break;
                case 3:
                    updateProduct(scanner);
                    break;
                case 4:
                    deleteProduct(scanner);
                    break;
                case 5:
                   searchProductById(scanner);
                    break;
                case 6:
                    ProductFeatureImpl.sortProductByPrice();
                    break;
                case 7:
                    isExit = false;
                    break;
                default:
                    System.err.println("Enter a valid choice 1-6");
            }
        } while (isExit);
    }

    public static void displayProduct() {
        if (productFeature.getAll().isEmpty()){
            System.err.println("product now is empty");
        }else {
            for (Product product : productFeature.getAll()) {
                product.displayProductData();
            }
        }
    }

    public static void addProduct(Scanner scanner) {
        System.out.println("Enter number of products you want to add: ");
        int number = inputNumber(scanner);
        for (int i = 0; i < number; i++) {
            System.out.println("Product input information " + (i + 1) + ": ");
            Product product = new Product();
            product.inputProductData(scanner);
            productFeature.save(product);
        }
    }

    public static void updateProduct(Scanner scanner) {
      System.out.println("Enter id of products you want to update: ");
      int idUpdate = inputNumber(scanner);
      int indexUpdate = productFeature.findById(idUpdate);
      Product productUpdate = ProductFeatureImpl.productList.get(indexUpdate);
        boolean isExit = true;
        do {
            System.out.println("1. Update product name");
            System.out.println("3. Update product description");
            System.out.println("2. Update product price");
            System.out.println("4. Update product quantity");
            System.out.println("5. Update product product of category");
            System.out.println("6. Update product status");
            System.out.println("7. Back");
            System.out.println("Your choice to 1 from 7: ");
            int choice = inputNumber(scanner);
            switch (choice) {
                case 1:
                    productUpdate.setProductName(productUpdate.inputProductName(scanner));
                    break;
                case 2:
                    productUpdate.setDescription(productUpdate.inputProductDescription(scanner));
                    break;
                case 3:
                    productUpdate.setUniPrice(productUpdate.inputUnitPrice(scanner));
                    break;
                case 4:
                    productUpdate.setStockQuantity(productUpdate.inputProductQuantity(scanner));
                    break;
                case 5:
                    productUpdate.setCategoryId(productUpdate.inputCategoryId(scanner));
                    break;
                case 6:
                    productUpdate.setStatus(productUpdate.inputStatusProduct(scanner));
                    break;
                case 7:
                    isExit = false;
                    break;
                default: System.err.println("Enter a valid choice 1-7");

            }
        }while (isExit);
        System.out.println("You update successfully");
        productFeature.save(productUpdate);
    }

    public static void deleteProduct(Scanner scanner) {
        System.out.println("Product Id you want to delete: ");
        int idDelete = inputNumber(scanner);
        productFeature.delete(idDelete);
    }

    public static void searchProductById(Scanner scanner) {
        System.out.println("Enter id of product you want to search: ");
        int idSearch = inputNumber(scanner);
            productFeature.searchProductById(idSearch);
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
