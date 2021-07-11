import java.util.List;

public interface Food {
    List<ProductServing> getProductServings();

    String getFoodName();

    void setFoodName(String foodName);

    double getMassOfFood();

    void addProduct(Product product, double massOfFood);

    void removeProduct(Product product);

    double getProductWeight(Product product);

    double getEnergy();

    double getProtein();

    double getFat();

    double getCarbohydrate();

    void fillShoppingCart(ShoppingCart shoppingCart, int numberOfParticipants);
}