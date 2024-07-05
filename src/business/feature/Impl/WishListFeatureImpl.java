package business.feature.Impl;

import business.entity.WishList;
import business.feature.IWishListFeature;
import business.utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class WishListFeatureImpl implements IWishListFeature {
    public static List<WishList> wishLists;


    static {
        wishLists = IOFile.readFromFile(IOFile.PATH_WISH);
        if (wishLists == null) {
            wishLists = new ArrayList<>();
        }
    }

    public WishListFeatureImpl() {
        if (wishLists == null) {
            wishLists = IOFile.readFromFile(IOFile.PATH_WISH);
            if (wishLists == null) {
                wishLists = new ArrayList<>();
            }
        }
    }


    @Override
    public void delete(Integer id) {
        int indexDeleted = findById(id);
        if (indexDeleted >=0) {
            wishLists.remove(indexDeleted);
            IOFile.writeToFile(IOFile.PATH_WISH, wishLists);
       }else {
            System.err.println("Not found wish");
        }
    }

    @Override
    public List<WishList> getAll() {
        return wishLists;
    }

    @Override
    public void save(WishList wishList) {
        int indexCheck = findById(wishList.getWishListId());
        if (indexCheck < 0) {
            wishLists.add(wishList);
        }else {
            wishLists.set(indexCheck, wishList);
        }
        IOFile.writeToFile(IOFile.PATH_WISH, wishLists);

    }

    public static void allDeleteProductWish() {
        wishLists.clear();
        System.out.println("Delete all wish list successfully");
        IOFile.writeToFile(IOFile.PATH_WISH, wishLists );
    }
    @Override
    public Integer findById(Integer id) {
        for (int i = 0; i < wishLists.size(); i++) {
            if (wishLists.get(i).getWishListId() == id){
                return i;
            }
        }
        return -1;
    }
}
