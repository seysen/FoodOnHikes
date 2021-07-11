import java.util.List;

public interface ShoppingCart {
    List<ShoppingCartItem> getShoppingCartItems();

    void addProduct(Product product, double productWeight);

    void removeProduct(Product product);

    ShoppingCartItem getShoppingCartItem(Product product);

    double getProductWeight(Product product);

    void setProductWeight(Product product, double weight);

    boolean isProductBought(Product product);

    void setProductBought(Product product, boolean isProductBought);

    boolean containsItem(ShoppingCartItem shoppingCartItem);

    void setShoppingCartItem(ShoppingCartItem shoppingCartItem);
}
