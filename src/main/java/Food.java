import java.util.Map;

public interface Food extends NutritionFacts {

    String getFoodName();

    void setFoodName(String dishName);

    void addProduct(Product product, double massOfFood);

    void removeFood(Product product);

    void updateFood();

    double getProductWeight(Product product);

    void setProductWeight(Product product, double massOfFood);

    Map<Product, Double> getFoodProducts();
}