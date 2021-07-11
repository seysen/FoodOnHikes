import java.util.*;

public class FoodImpl implements Food {
    private final List<ProductServing> productServings = new ArrayList<>();
    private String foodName;
    private double massOfFood;
    private double energy;
    private double protein;
    private double fat;
    private double carbohydrate;

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

    public void updateFood() {
        calculateNutritionFacts();
        if (protein != 0.0 || fat != 0.0 || carbohydrate != 0.0) {
            massOfFood = protein + fat + carbohydrate;
            energy = protein * NutritionFacts.PROTEIN_ENERGY
                    + fat * NutritionFacts.FAT_ENERGY
                    + carbohydrate * NutritionFacts.CARBOHYDRATE_ENERGY;
        }
    }

    private void calculateNutritionFacts() {
        protein = 0;
        fat = 0;
        carbohydrate = 0;
        for (ProductServing productServing : productServings) {
            protein += productServing.getProtein();
            fat += productServing.getFat();
            carbohydrate += productServing.getCarbohydrate();
        }
    }

    @Override
    public List<ProductServing> getProductServings() {
        sort();
        return productServings;
    }

    private void sort() {
        productServings.sort(Comparator.comparingDouble(ProductServing::getMassOfProduct).reversed()
                .thenComparing((productServing -> productServing.getProduct().toString())));
    }

    @Override
    public String getFoodName() {
        return foodName;
    }

    @Override
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    @Override
    public double getMassOfFood() {
        return massOfFood;
    }

    @Override
    public void addProduct(Product product, double massOfProduct) {
        if (foodContains(product)) {
           int index = indexOf(product);
           double oldMass = productServings.get(index).getMassOfProduct();
           productServings.get(index).setMassOfProduct(oldMass + massOfProduct);
        } else {
           productServings.add(new ProductServingImpl(product, massOfProduct));
        }
        updateFood();
    }

    private boolean foodContains (Product product) {
        return indexOf(product) >= 0;
    }

    private int indexOf(Product product) {
        int index = -1;
        for (int i = 0; i < productServings.size(); i++) {
            if (productServings.get(i).getProduct().equals(product)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public void removeProduct(Product product) {
        productServings.removeIf(productServing -> productServing.getProduct().equals(product));
        updateFood();
    }

    @Override
    public double getProductWeight(Product product) {
        int index = indexOf(product);
        if (index >= 0) {
            return productServings.get(index).getMassOfProduct();
        } else {
            return 0.0;
        }
    }

    @Override
    public double getEnergy() {
        updateFood();
        return energy;
    }

    @Override
    public double getProtein() {
        calculateNutritionFacts();
        return protein;
    }

    @Override
    public double getFat() {
        calculateNutritionFacts();
        return fat;
    }

    @Override
    public double getCarbohydrate() {
        calculateNutritionFacts();
        return carbohydrate;
    }

    @Override
    public void fillShoppingCart(ShoppingCart shoppingCart, int numberOfParticipants) {
        for (ProductServing productServing: productServings) {
            shoppingCart.addProduct(productServing.getProduct(), productServing.getMassOfProduct() * numberOfParticipants);
        }
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
        return Objects.equals(this.getProductServings(), food.getProductServings());
    }

    @Override
    public int hashCode() {
        return Objects.hash(protein, fat, carbohydrate);
    }
}