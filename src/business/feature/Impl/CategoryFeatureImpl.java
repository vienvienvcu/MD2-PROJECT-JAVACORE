package business.feature.Impl;

import business.entity.Category;
import business.entity.Product;
import business.feature.ICategoryFeature;
import business.utils.IOFile;
import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryFeatureImpl implements ICategoryFeature {

    public static List <Category> categoryList = new ArrayList<>();

    public CategoryFeatureImpl (){
        categoryList = IOFile.readFromFile(IOFile.PATH_CATEGORY);
    }
    @Override
    public void delete(Integer id) {
        int indexDeleted = findById(id);
        if (indexDeleted >= 0) {
            boolean isExist = false;
            for (Product product : ProductFeatureImpl.productList) {
                if (product.getCategoryId() == id){
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                System.err.println("Category cannot be deleted when there is a product");
            }else {
                categoryList.remove(indexDeleted);
            }
            IOFile.writeToFile(IOFile.PATH_CATEGORY, categoryList);
        }else {
            System.err.println("Delete Category Failed");

        }
    }

    @Override
    public List<Category> getAll() {
        return categoryList;
    }

    @Override
    public void save(Category category) {
        int indexCheck = categoryList.indexOf(category);
        if (indexCheck <0) {
            categoryList.add(category);
        }else {
            categoryList.set(indexCheck, category);
        }
        IOFile.writeToFile(IOFile.PATH_CATEGORY, categoryList);
    }

    @Override
    public Integer findById(Integer id) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getCategoryId() == id){
                return i;
            }
        }
        return -1;
    }

    public static void sortCategoryByName() {
        Collections.sort(categoryList);
        for (Category category : categoryList){
            category.displayCategoryData();
        }
        System.out.println();
        System.out.println("Sort Category Successfully");
        IOFile.writeToFile(IOFile.PATH_CATEGORY, categoryList);
    }

    public  void searchCategoryByName(String categoryName) {
            for (Category category : categoryList){
                if (category.getCategoryName().toLowerCase().equals(categoryName)){
                    category.displayCategoryData();
                }
            }
        System.out.println();
        System.out.println("Search Category Successfully");
        IOFile.writeToFile(IOFile.PATH_CATEGORY, categoryList);
    }
}
