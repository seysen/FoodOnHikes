import java.util.Map;

public interface Dish extends NutrilonFacts {

    String getDishName();

    void setDishName(String dishName);

    void addFood(Food food, double massOfFood);

    void removeFood(Food food);

    void updateDish();

    double getFoodWeight(Food food);

    void setFoodWeight(Food food, double massOfFood);

    Map<Food, Double> getFoods();
}