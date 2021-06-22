import java.util.*;

public class FoodImpl implements Food {
    private String foodName;
    private double massOfFood;
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

    public double getMassOfFood() {
        return massOfFood;
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
            protein += productServing.getProtein() * productServing.massOfProduct / 100;
            fat += productServing.getFat() * productServing.massOfProduct / 100;
            carbohydrate += productServing.getCarbohydrate() * productServing.massOfProduct / 100;
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
            massOfFood = protein + fat + carbohydrate;
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
    public void addFood(Product product, double massOfFood) {
        ProductServing productServing = new ProductServing(product, massOfFood);
        if (!foods.contains(productServing)) {
            foods.add(productServing);
        } else {
            ProductServing oldProductServing = foods.get(foods.indexOf(productServing));
            oldProductServing.massOfProduct += productServing.massOfProduct;
        }
        updateFood();
    }

    @Override
    public void removeFood(Product product) {
        foods.removeIf(productServing -> productServing.product.equals(product));
        updateFood();
    }

    @Override
    public double getProductWeight(Product product) {
        for (ProductServing productServing :
                foods) {
            if (productServing.product.equals(product)) {
                return productServing.massOfProduct;
            }
        }
        return 0.0;
    }

    @Override
    public void setFoodWeight(Product product, double massOfFood) {
        for (ProductServing productServing :
                foods) {
            if (productServing.product.equals(product)) {
                productServing.massOfProduct = massOfFood;
            }
        }
        updateFood();
    }

    @Override
    public Map<Product, Double> getProducts() {
        Map<Product, Double> foodMap = new HashMap<>();
        for (ProductServing productServing :
                foods) {
            foodMap.put(productServing.product, productServing.massOfProduct);
        }
        return foodMap;
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
        return Objects.equals(this.getProducts(), food.getProducts());
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

    private class ProductServing {
        private final Product product;
        private double massOfProduct;

        public ProductServing(Product product, double massOfProduct) {
            this.product = product;
            this.massOfProduct = massOfProduct;
        }

        public double getProtein() {
            return product.getProtein();
        }

        public double getFat() {
            return product.getFat();
        }

        public double getCarbohydrate() {
            return product.getCarbohydrate();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ProductServing that = (ProductServing) o;
            return Double.compare(that.massOfProduct, massOfProduct) == 0 &&
                    Objects.equals(product, that.product);
        }

        @Override
        public int hashCode() {
            return Objects.hash(product, massOfProduct);
        }
    }
}