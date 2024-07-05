package business.feature.Impl;

import business.entity.CartItem;
import business.entity.Product;
import business.entity.Users;
import business.feature.ICartItemFeature;
import business.utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class CartFeatureImpl implements ICartItemFeature {
    public static List<CartItem> cartItemList = new ArrayList<>();
    public static List<Users> usersList;
    public static Users userLogin ;

    static {
        cartItemList = IOFile.readFromFile(IOFile.PATH_CART);
        if (cartItemList == null) {
            cartItemList = new ArrayList<>();
        }
    }

    public CartFeatureImpl() {
        if (cartItemList == null) {
            cartItemList = IOFile.readFromFile(IOFile.PATH_CART);
            if (cartItemList == null) {
                cartItemList = new ArrayList<>();
            }
        }
    }

    @Override
    public List<CartItem> getAll() {
        return cartItemList;
    }

    @Override
    public void save(CartItem cartItem) {
        CartItem existingCartItem = findByProductAndUser(cartItem.getProductId(), cartItem.getUser().getUsersId());
        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
        } else {
            cartItemList.add(cartItem);
        }
        IOFile.writeToFile(IOFile.PATH_CART, cartItemList);
         }

    public Product findProductById(int productId) {
            for (Product product : ProductFeatureImpl.productList) {
                if (product.getProductId() == productId) {
                    return product;
                }
            }
            return null;
       }

    public CartItem findByProductAndUser(int productId, int userId) {
        for (CartItem cartItem : cartItemList) {
            if (cartItem.getProductId() == productId && cartItem.getUser().getUsersId() == userId) {
                return cartItem;
            }
        }
        return null;
    }

    @Override
    public Integer findById(Integer id) {
        for (int i = 0; i < cartItemList.size(); i++) {
            if (cartItemList.get(i).getCartItemId() == id){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void delete(Integer id) {
      int indexDelete = findById(id);
      if (indexDelete >= 0) {
          cartItemList.remove(indexDelete);
          IOFile.writeToFile(IOFile.PATH_CART,cartItemList );
      }else {
          System.err.println("not found cart item");
      }
    }

    public static void allDeleteProductCart() {
        cartItemList.clear();
        System.out.println("Delete all cart successfully");
        IOFile.writeToFile(IOFile.PATH_CART,cartItemList );
    }

    public void updateQuantity(int cartItemId, int newQuantity) {
        for (CartItem cartItem : cartItemList) {
            if (cartItem.getCartItemId() == cartItemId) {
                Product product = findProductById(cartItem.getProductId());
                if (product == null) {
                    System.err.println("Product not found");
                    return;
                }

                if (newQuantity > (product.getStockQuantity() + cartItem.getQuantity())) {
                    System.err.println("Not enough stock available");
                    return;
                }

                // Update stock
                product.setStockQuantity(product.getStockQuantity() + cartItem.getQuantity() - newQuantity);
                cartItem.setQuantity(newQuantity);

                IOFile.writeToFile(IOFile.PATH_CART, cartItemList);
                IOFile.writeToFile(IOFile.PATH_PRODUCT, ProductFeatureImpl.productList);
                System.out.println("Updated the quantity successfully");
                return;
            }
        }
        System.err.println("Cart item not found");
    }

}
