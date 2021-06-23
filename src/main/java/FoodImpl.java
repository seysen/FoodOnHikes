import java.util.*;

public class FoodImpl implements Food {
    private String foodName;
    private double foodWeight;
    private double energy;
    private double protein;
    private double fat;
    private double carbohydrate;
    private List<ProductServing> foods = new ArrayList<>();

    @Override
    public String getFoodName() {
        return foodName;
    }

    @Override
    public void setFoodName(String dishName) {
        this.foodName = dishName;
    }

    public double getFoodWeight() {
        return foodWeight;
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
        for (ProductServing productServing :
                foods) {
            protein += productServing.getProtein();
            fat += productServing.getFat();
            carbohydrate += productServing.getCarbohydrate();
        }
    }

    @Override
    public double getProtein() {
        return protein;
    }

    @Override
    public void setProtein(double protein) {
        for (ProductServing productServing :
                foods) {
            if (productServing.getProtein() != 0.0) {
                throw new IllegalStateException("The dish calculates protein from included foods");
            }
        }
        this.protein = protein;
    }

    @Override
    public void updateFood() {
        calculateNutritionFacts();
        if (protein != 0.0 || fat != 0.0 || carbohydrate != 0.0) {
            foodWeight = protein + fat + carbohydrate;
            energy = protein * PROTEIN_ENERGY + fat * FAT_ENERGY + carbohydrate * CARBOHYDRATE_ENERGY;
        }
    }

    @Override
    public double getFat() {
        return fat;
    }

    @Override
    public void setFat(double fat) {
        for (ProductServing productServing :
                foods) {
            if (productServing.getFat() != 0.0) {
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
        for (ProductServing productServing :
                foods) {
            if (productServing.getCarbohydrate() != 0.0) {
                throw new IllegalStateException("The dish calculates carbohydrate from included foods");
            }
        }
        this.carbohydrate = carbohydrate;
    }

    @Override
    public void addProduct(Product product, double massOfFood) {
        ProductServing productServing = new ProductServing(product, massOfFood);
        if (!foods.contains(productServing)) {
            foods.add(productServing);
        } else {
            ProductServing oldProductServing = foods.get(foods.indexOf(productServing));
            oldProductServing.setProductWeight(oldProductServing.getProductWeight() + productServing.getProductWeight());
        }
        updateFood();
    }

    @Override
    public void removeFood(Product product) {
        foods.removeIf(productServing -> productServing.getProduct().equals(product));
        updateFood();
    }

    @Override
    public double getProductWeight(Product product) {
        for (ProductServing productServing :
                foods) {
            if (productServing.getProduct().equals(product)) {
                return productServing.getProductWeight();
            }
        }
        return 0.0;
    }

    @Override
    public void setProductWeight(Product product, double massOfFood) {
        for (ProductServing productServing :
                foods) {
            if (productServing.getProduct().equals(product)) {
                productServing.setProductWeight(massOfFood);
            }
        }
        updateFood();
    }

    @Override
    public Map<Product, Double> getFoodProducts() {
        Map<Product, Double> productMap = new HashMap<>();
        for (ProductServing productServing :
                foods) {
            productMap.put(productServing.getProduct(), productServing.getProductWeight());
        }
        return productMap;
    }

    @Override
    public String toString() {
        return "Food: " + foodName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodImpl food = (FoodImpl) o;
        return Objects.equals(this.getFoodProducts(), food.getFoodProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(protein, fat, carbohydrate);
    }

    public FoodImpl(String foodName) {
        this.foodName = foodName;
    }

    public FoodImpl(String foodName, double energy) {
        this.foodName = foodName;
        this.energy = energy;
    }

    public FoodImpl(String foodName, double protein, double fat, double carbohydrate) {
        this.foodName = foodName;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        updateFood();
    }

}