package presentation.adminShow;

import business.entity.Category;
import business.feature.Impl.CategoryFeatureImpl;

import java.util.Collections;
import java.util.Scanner;

import static business.utils.Colors.BLUE;
import static business.utils.Colors.GREEN;

public class ManagementCategory {
    public static CategoryFeatureImpl categoryFeature = new CategoryFeatureImpl();

    public static void main(String[] args) {

    }
    public static void showMenuCategory(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println(BLUE + "┏━━━━━━━━━━━━━━━━━━ MENU CATEGORY ━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃                                                    ┃");
            System.out.println("┃      " + GREEN + "1. SHOW CATEGORY   " + BLUE + "                           ┃");
            System.out.println("┃      " + GREEN + "2. ADD CATEGORY          " + BLUE + "                     ┃");
            System.out.println("┃      " + GREEN + "3. UPDATE CATEGORY      " + BLUE + "                      ┃");
            System.out.println("┃      " + GREEN + "4. DELETE CATEGORY     " + BLUE + "                       ┃");
            System.out.println("┃      " + GREEN + "5. SEARCH CATEGORY BY ID   " + BLUE + "                   ┃");
            System.out.println("┃      " + GREEN + "6. SORT IN DESCENDING OR ASCENDING ORDER" + BLUE + "      ┃");
            System.out.println("┃      " + GREEN + "7. BACK   " + BLUE + "                                    ┃");
            System.out.println("┃                                                    ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.println("Your choice to 1 from 4: ");
            int choice = inputNumber(scanner);
            switch (choice) {
                case 1:
                    displayCategory();
                    break;
                case 2:
                    addCategory(scanner);
                    break;
                case 3:
                    updateCategory(scanner);
                    break;
                case 4:
                    deleteCategory(scanner);
                    break;
                case 5:
                     searchCategory(scanner);
                    break;
                case 6:
                    CategoryFeatureImpl.sortCategoryByName();
                    break;
                case 7:
                    isExit = false;
                    break;
                default:
                    System.err.println("Enter a valid choice 1-6");
            }
        } while (isExit);
    }
    public static void displayCategory() {
        if (CategoryFeatureImpl.categoryList.isEmpty()){
            System.err.println("There is no category in the system");
        }else {
            for (Category category : categoryFeature.getAll()){
                category.displayCategoryData();
            }
        }
    }

    public static void addCategory(Scanner scanner) {
        System.out.println("Enter number category you want to add: ");
        int number = inputNumber(scanner);
        for (int i = 0; i< number; i++ ){
            System.out.println("Category input information" + i + ": ");
            Category category = new Category();
            category.inputCategoryData(scanner);
            categoryFeature.save(category);
        }
    }

    public static void updateCategory(Scanner scanner) {
        System.out.println("Enter category id you want to update: ");
        int idUpdate = inputNumber(scanner);
        int indexUpdate = categoryFeature.findById(idUpdate);
        Category categoryUpdate =  CategoryFeatureImpl.categoryList.get(indexUpdate);
        if (indexUpdate >=0){
            boolean isExit = true;
            do {
                System.out.println("1. update category name");
                System.out.println("2. update category description");
                System.out.println("3. update category status");
                System.out.println("4. back ");
                System.out.println("Enter your choice: ");
                int choice = inputNumber(scanner);
                switch (choice) {
                    case 1:
                       categoryUpdate.setCategoryName(Category.inputCategoryName(scanner));
                        break;
                    case 2:
                        categoryUpdate.setDescription(Category.inputCategoryDescription(scanner));
                        break;
                    case 3:
                        categoryUpdate.setStatus(Category.inputCategoryStatus(scanner));
                        break;
                    case 4:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Enter a valid choice 1-4");
                }
            }while (isExit);
            System.out.println("Category updated successfully");
            categoryFeature.save(categoryUpdate);
        }else {
            System.err.println("Category id does not exist");
        }

    }

    public static void deleteCategory(Scanner scanner) {
        System.out.println("Enter category id you want to delete: ");
        int number = inputNumber(scanner);
        categoryFeature.delete(number);
    }

//    public static void sortCategoryByName() {
//        Collections.sort(categoryFeature.getAll());
//        for (Category category : categoryFeature.getAll()){
//            category.displayCategoryData();
//        }
//    }

    public static void searchCategory(Scanner scanner) {
        System.out.println("Enter category name you want to search: ");
        String categoryName = scanner.nextLine().toLowerCase();
        categoryFeature.searchCategoryByName(categoryName);
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
