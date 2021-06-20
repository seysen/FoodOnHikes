import java.util.*;

public class DishImpl implements Dish {
    private String dishName;
    private double massOfDish;
    private double energy;
    private double protein;
    private double fat;
    private double carbohydrate;
    private List<FoodServing> foods = new ArrayList<>();

    @Override
    public String getDishName() {
        return dishName;
    }

    @Override
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    @Override
    public double getEnergy() {
        return energy;
    }

    @Override
    public void setEnergy(double energy) {
        calculateNutritionFacts();
        if (protein == 0.0 && fat == 0.0 && carbohydrate == 0.0) {
            this.energy = energy;
        }
    }

    private void calculateNutritionFacts() {
        protein = 0;
        fat = 0;
        carbohydrate = 0;
        for (FoodServing foodServing:
                foods) {
            protein += foodServing.getProtein() * foodServing.massOfFood / 100;
            fat += foodServing.getFat() * foodServing.massOfFood / 100;
            carbohydrate += foodServing.getCarbohydrate() * foodServing.massOfFood / 100;
        }
    }

    @Override
    public double getProtein() {
        return protein;
    }

    @Override
    public void setProtein(double protein) {
        for (FoodServing foodServing :
                foods) {
            if (foodServing.getProtein() != 0.0) {
                throw new IllegalStateException("The dish calculates protein from included foods");
            }
        }
        this.protein = protein;
    }

    @Override
    public void updateDish() {
        calculateNutritionFacts();
        if (protein != 0.0 || fat != 0.0 || carbohydrate != 0.0) {
            massOfDish = protein + fat + carbohydrate;
            energy = protein * PROTEINENERGY + fat * FATENERGY + carbohydrate * CARBOHYDRATEENERGY;
        }
    }

    @Override
    public double getFat() {
        return fat;
    }

    @Override
    public void setFat(double fat) {
        for (FoodServing foodServing :
                foods) {
            if (foodServing.getFat() != 0.0) {
                throw new IllegalStateException("The dish calculates fat from included foods");
            }
        }
        this.fat = fat;
    }

    @Override
    public double getCarbohydrate() {
        return carbohydrate;
    }

    @Override
    public void setCarbohydrate(double carbohydrate) {
        for (FoodServing foodServing :
                foods) {
            if (foodServing.getCarbohydrate() != 0.0) {
                throw new IllegalStateException("The dish calculates carbohydrate from included foods");
            }
        }
        this.carbohydrate = carbohydrate;
    }

    @Override
    public void addFood(Food food, double massOfFood) {
        foods.add(new FoodServing(food, massOfFood));
        updateDish();
    }

    @Override
    public void removeFood(Food food) {
        foods.removeIf(foodServing -> foodServing.food.equals(food));
        updateDish();
    }

    @Override
    public double getFoodWeight(Food food) {
        for (FoodServing foodServing :
                foods) {
            if (foodServing.food.equals(food)) {
                return foodServing.massOfFood;
            }
        }
        return 0.0;
    }

    @Override
    public void setFoodWeight(Food food, double massOfFood) {
        for (FoodServing foodServing :
                foods) {
            if (foodServing.food.equals(food)) {
                foodServing.massOfFood = massOfFood;
            }
        }
        updateDish();
    }

    @Override
    public Map<Food, Double> getFoods() {
        Map<Food, Double> foodMap = new HashMap<>();
        for (FoodServing foodServing :
                foods) {
            foodMap.put(foodServing.food, foodServing.massOfFood);
        }
        return foodMap;
    }

    @Override
    public String toString() {
        return "Dish: " + dishName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishImpl dish = (DishImpl) o;
        return Objects.equals(foods, dish.foods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foods);
    }

    public DishImpl(String dishName) {
        this.dishName = dishName;
    }

    public DishImpl(String dishName, double energy) {
        this.dishName = dishName;
        this.energy = energy;
    }

    public DishImpl(String dishName, double protein, double fat, double carbohydrate) {
        this.dishName = dishName;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        updateDish();
    }

    private class FoodServing {
        private Food food;
        private double massOfFood;

        public FoodServing(Food food, double massOfFood) {
            this.food = food;
            this.massOfFood = massOfFood;
        }

        public double getProtein() {
            return food.getProtein();
        }

        public double getFat() {
            return food.getFat();
        }

        public double getCarbohydrate() {
            return food.getCarbohydrate();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FoodServing that = (FoodServing) o;
            return Double.compare(that.massOfFood, massOfFood) == 0 &&
                    Objects.equals(food, that.food);
        }

        @Override
        public int hashCode() {
            return Objects.hash(food, massOfFood);
        }
    }
}