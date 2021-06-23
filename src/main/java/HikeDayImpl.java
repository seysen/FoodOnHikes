import java.util.ArrayList;
import java.util.List;

public class HikeDayImpl implements HikeDay {
    private final Hike hike;
    private List<Food> foods = new ArrayList<>();

    public HikeDayImpl(HikeImpl hike) {
        this.hike = hike;
    }

    @Override
    public List<Food> getFoods() {
        return foods;
    }

    @Override
    public Food getFood(int i) {
        return foods.get(i);
    }

    @Override
    public void addFood(Food food) {
        foods.add(food);
    }

    @Override
    public void addFood(Food food, int position) {
        foods.add(position, food);
    }

    @Override
    public void removeFood(Food food) {
        foods.removeIf(hikeDayFood -> hikeDayFood.equals(food));
    }

    @Override
    public void removeFood(int position) {
        foods.remove(position);
    }

    @Override
    public String toString() {
        return "Day of hike " + hike.getHikeName() + " number " + (hike.getHikeDays().indexOf(this));
    }
}
