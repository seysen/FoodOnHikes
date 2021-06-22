import java.util.Map;

public interface Food extends NutritionFacts {

    String getFoodName();

    void setFoodName(String dishName);

    void addProduct(Product product, double massOfFood);

    void removeFood(Product product);

    void updateFood();

    double getProductWeight(Product product);

    void setFoodWeight(Product product, double massOfFood);

    Map<Product, Double> getProducts();
}