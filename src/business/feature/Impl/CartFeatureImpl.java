package business.feature.Impl;

import business.entity.CartItem;
import business.feature.ICartItemFeature;

import java.util.ArrayList;
import java.util.List;

public class CartFeatureImpl implements ICartItemFeature {
    public static List<CartItem> cartItemList = new ArrayList<>();
    @Override
    public List<CartItem> getAll() {
        return cartItemList;
    }

    @Override
    public void save(CartItem cartItem) {

    }

    @Override
    public Integer findById(Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
