package business.entity;

import business.feature.Impl.CategoryFeatureImpl;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.io.Serializable;
import java.util.Scanner;

public class Category implements Serializable,Comparable<Category>{
    private int categoryId;
    private String categoryName;
    private String description;
    private Boolean status;

//      =====================CONTRACTOR=============================

    public Category() {
    }

    public Category(int categoryId, String categoryName, String description, Boolean status) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.status = status;
    }
//      =====================METHOD GET/SET=========================

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


//   =====================INPUT CATEGORY==============================

    public void inputCategoryData(Scanner scanner) {
        this.categoryId = inputCategoryId();
        this.categoryName = inputCategoryName(scanner);
        this.description = inputCategoryDescription(scanner);
        this.status = inputCategoryStatus(scanner);

    }

//      ==================== VALIDATION==============================

    public int inputCategoryId(){
        int idMax = 0;
        for (Category category: CategoryFeatureImpl.categoryList){
            if(category.getCategoryId() > idMax){
                idMax = category.getCategoryId();
            }
        }
        return idMax + 1;
    }

    public static String inputCategoryName(Scanner scanner){
        System.out.println("Enter category name: ");
        do {
          String categoryName = scanner.nextLine();
          if(categoryName.isEmpty()){
              System.err.println("Category name cannot be empty");
          }else {
              boolean isExist = false;
              for (Category category: CategoryFeatureImpl.categoryList){
                  if(category.getCategoryName().equals(categoryName)){
                      isExist = true;
                      break;
                  }
              }
              if(isExist){
                  System.err.println("Category name already exist");
              }else {
                  return categoryName;
              }
          }
        }while (true);
    }

    public static String inputCategoryDescription(Scanner scanner){
        System.out.println("Enter category description: ");
        do {
            String categoryDescription = scanner.nextLine();
            if(categoryDescription.isEmpty()){
                System.err.println("Category description cannot be empty");
            }else {
                return categoryDescription;
            }
        }while (true);
    }

    public static Boolean inputCategoryStatus(Scanner scanner) {
        System.out.println("Enter category status: ");
        do {
            String categoryStatus = scanner.nextLine();
            if (categoryStatus.isEmpty()){
                System.err.println("Category status cannot be empty");
            }else {
                if (categoryStatus.equals("true")||categoryStatus.equals("false")){
                    return Boolean.parseBoolean(categoryStatus);
                }else {
                    System.err.println("Invalid status. Please enter true or false.");
                }
            }
        }while (true);
    }

//      =====================SHOW CART===============================

    public  void displayCategoryData() {
        String format = "| %-12s | %-40s | %-20s | %-20s |\n";
        String separator = "+--------------+------------------------------------------+----------------------+----------------------+\n";

        // Print headers
        System.out.print(separator);
        System.out.printf(format, "Category Id", "Category Name", "Description", "Category Status");
        System.out.print(separator);

        // Print category data
        System.out.printf(format, this.categoryId, this.categoryName, this.description,
                this.status ? "Active" : "Inactive");
        System.out.println();
    }

    @Override
    public int compareTo(Category o) {
        return this.categoryName.compareTo(o.categoryName);
    }
}