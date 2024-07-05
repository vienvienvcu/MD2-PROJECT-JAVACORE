package business.feature.Impl;

import business.entity.Product;
import business.feature.IProductFeature;
import business.utils.IOFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductFeatureImpl implements IProductFeature {

    public static List<Product> productList;

    static {
        productList = IOFile.readFromFile(IOFile.PATH_PRODUCT);
    }

    public ProductFeatureImpl() {
       productList = IOFile.readFromFile(IOFile.PATH_PRODUCT);
    }
    @Override
    public void delete(Integer id) {
        int indexDelete = findById(id);
        if (indexDelete >=0) {
            productList.remove(indexDelete);
        }else {
            System.err.println("product not found");
        }
        IOFile.writeToFile(IOFile.PATH_PRODUCT, productList);
    }

    @Override
    public List<Product> getAll() {
        return productList;
    }

    @Override
    public void save(Product product) {
       int indexCheck = findById(product.getProductId());
       if (indexCheck <0) {
           productList.add(product);
       }else {
           productList.set(indexCheck, product);
       }
       IOFile.writeToFile(IOFile.PATH_PRODUCT, productList);
    }

    @Override
    public Integer findById(Integer id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId() == id){
                return i;
            }
        }
        return -1;
    }

    public void searchProductById(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId() == id) {
                productList.get(i).displayProductData();
            }
        }
        System.out.println();
        System.out.println("Search Product By Id susses");
    }

    public static void sortProductByPrice() {
        Collections.sort(productList);
        for (int i = 0; i < productList.size(); i++) {
            productList.get(i).displayProductData();
        }
        System.out.println();
        System.out.println("Sort Product By Id susses");
        IOFile.writeToFile(IOFile.PATH_PRODUCT, productList);
    }

}
