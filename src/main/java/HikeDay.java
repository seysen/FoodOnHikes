import java.util.List;

public interface HikeDay {
    List<Food> getFoods();

    Food getFood(int i);

    void addFood(Food food);

    void addFood(Food food, int position);

    void removeFood(Food food);

    void removeFood(int position);
}
