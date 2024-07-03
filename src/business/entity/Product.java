package business.entity;

import business.feature.Impl.CategoryFeatureImpl;
import business.feature.Impl.ProductFeatureImpl;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static business.feature.Impl.CategoryFeatureImpl.*;

public class Product implements Serializable,Comparable<Product>{
    private int productId;
    private String sku;
    private String productName;
    private String description;
    private double unitPrice;
    private int stockQuantity;
    private int categoryId;
    private Boolean status;
    private Date createdAt;
    private Date updatedAt;

//      =====================CONTRACTOR=============================

    public Product() {
    }

    public Product(int categoryId, Date createdAt, String description,
                   int productId, String productName, String sku, int stockQuantity,
                   double unitPrice, Date updatedAt,Boolean status) {
        this.categoryId = categoryId;
        this.createdAt = createdAt;
        this.description = description;
        this.productId = productId;
        this.productName = productName;
        this.sku = sku;
        this.stockQuantity = stockQuantity;
        this.unitPrice = unitPrice;
        this.updatedAt = updatedAt;
        this.status = status;
    }

//      =====================METHOD GET/SET=========================


    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public double getUniPrice() {
        return unitPrice;
    }

    public void setUniPrice(double uniPrice) {
        this.unitPrice= uniPrice;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

//      =====================INPUT PRODUCT==============================

    public void inputProductData(Scanner scanner){
        this.productId = inputProductId();
        this.sku = inputSkuId(scanner);
        this.productName = inputProductName(scanner);
        this.description = inputProductDescription(scanner);
        this.unitPrice = inputUnitPrice(scanner);
        this.stockQuantity = inputProductQuantity(scanner);
        this.categoryId = inputCategoryId(scanner);
        this.createdAt = new Date();
        this.updatedAt = this.createdAt;
        this.status = inputStatusProduct(scanner);
    }
// ==================== VALIDATION==============================
    public int inputProductId(){
        int idMax = 0;
        for (Product product: ProductFeatureImpl.productList){
            if (product.getProductId() > idMax){
                idMax = product.getProductId();
            }
        }
        return idMax + 1;
    }

    public String inputSkuId(Scanner scanner){
            UUID uuid = UUID.randomUUID();
            return uuid.toString();

    }

    public String inputProductName(Scanner scanner){
        System.out.println("Enter product name: ");
        do {
           String productName = scanner.nextLine();
           if (productName.isEmpty()){
               System.err.println("Product name cannot be empty");
           }else {
               boolean isExist = false;
               for (Product product: ProductFeatureImpl.productList){
                   if (product.getProductName().equals(productName)){
                       isExist = true;
                       break;
                   }
               }
               if (isExist){
                   System.err.println("Product name already exist");
               }else {
                   return productName;
               }
           }

        }while (true);
    }

    public String inputProductDescription(Scanner scanner){
        System.out.println("Enter product description: ");
        do {
            String productDescription = scanner.nextLine();
            if (productDescription.isEmpty()){
                System.err.println("Product description cannot be empty");
            }else {
                return productDescription;
            }

        }while (true);
    }

    public Double inputUnitPrice(Scanner scanner){
        System.out.println("Enter the unit price: ");
        do {
            try {
                double unitPrice = Double.parseDouble(scanner.nextLine());
                if (unitPrice < 0){
                    System.err.println("Unit price cannot be negative");
                }else {
                    return unitPrice;
                }
            }catch (NumberFormatException ex){
                System.err.println("price is not a number, please enter a number");
            }

        }while (true);
    }

    public int inputProductQuantity(Scanner scanner){
        System.out.println("Enter the product quantity: ");
        do {
            try {
                int quantity = Integer.parseInt(scanner.nextLine());
                if (quantity < 0){
                    System.err.println("quantity cannot be negative");
                }else {
                    return quantity;
                }
            }catch (NumberFormatException ex){
                System.err.println("product quantity is not a number, please enter a number");
            }
        }while (true);
    }

    public int inputCategoryId(Scanner scanner){
        System.out.println("Enter product category : ");
        do {
            try {
                for (int i = 0; i<CategoryFeatureImpl.categoryList.size(); i++){
                    System.out.println((i+1) + ". " + CategoryFeatureImpl.categoryList.get(i).getCategoryName());
                }
                System.out.println("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice > 0 && choice <= CategoryFeatureImpl.categoryList.size()){
                    return this.categoryId = CategoryFeatureImpl.categoryList.get(choice - 1).getCategoryId();
                }else {
                    System.err.println("categoryId cannot be negative");
                }
            }catch (NumberFormatException ex){
                System.err.println("categoryId is not a number, please enter a number");
            }
        }while (true);

    }

    public boolean inputStatusProduct(Scanner scanner){
        System.out.println("Enter product status: ");
        do {
            String status = scanner.nextLine();
            if (status.equals("true") || status.equals("false")){
                return Boolean.parseBoolean(status);
            }else {
                System.err.println("product status only 2 values are true or false");
            }

        }while (true);
    }

//      =====================SHOW PRODUCT===============================
    public void displayProductData(){
        // Currency formatter for VND
        NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        //date format
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String format = "| %-12s | %-40s | %-20s | %-20s | %-12s |\n";
        String separator = "+--------------+------------------------------------------+----------------------+----------------------+--------------+\n";

        System.out.printf(separator);
        System.out.format(format, "Product ID", "SKU", "Product Name", "Price", "Stock Qty");
        System.out.format(format, productId, sku, productName, vndFormat.format(unitPrice), stockQuantity);

        System.out.format(format, "Category ID", "Description", "Date Created", "Date Updated","Status");
        System.out.format(format, categoryId, description, sdf.format(this.createdAt), sdf.format(this.updatedAt),
                this.status?"Active":"Inactive");
        System.out.println();
    }
    @Override
    public int compareTo(Product o) {
        return Double.compare(this.unitPrice, o.unitPrice);
    }

}