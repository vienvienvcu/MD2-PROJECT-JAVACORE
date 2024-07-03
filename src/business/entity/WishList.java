package business.entity;

public class WishList {
    private int wishListId;
    private int userId;
    private int productId;
//      =====================CONTRACTOR=============================

    public WishList() {
    }

    public WishList(int productId, int userId, int wishListId) {
        this.productId = productId;
        this.userId = userId;
        this.wishListId = wishListId;
    }

//      =====================METHOD GET/SET=========================

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWishListId() {
        return wishListId;
    }

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
    }


//      ==================== VALIDATION==============================
//      =====================INPUT CART==============================
//      =====================SHOW CART===============================
}