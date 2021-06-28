import java.util.Set;

public interface ShoppingCart {
    Set<ShoppingCartImpl.ShoppingCartItem> getShoppingCart();

    void addShoppingCartItem(Product product, double productWeight);

    Set<Product> getSetOfProducts();

    ShoppingCartImpl.ShoppingCartItem getShoppingCartItem(Product product);

    double getProductWeight(Product product);

    void addProductWeight(Product product, double productWeight);

    boolean isProductBought(Product product);

    void setProductBought(Product product, boolean isProductBought);
}
