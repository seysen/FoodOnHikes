import java.util.Objects;

public class ProductImpl implements Product {
    private String foodName;
    private double energy;
    private double protein;
    private double fat;
    private double carbohydrate;

    @Override
    public String getProductName() {
        return foodName;
    }

    @Override
    public void setProductName(String productName) {
        this.foodName = productName;
    }

    @Override
    public double getEnergy() {
        return energy;
    }

    @Override
    public void setEnergy(double energy) {
        if (protein == 0.0 && fat == 0.0 && carbohydrate == 0.0) {
            this.energy = energy;
        }
    }

    @Override
    public double getProtein() {
        return protein;
    }

    @Override
    public void setProtein(double protein) {
        this.protein = protein;
        calculateEnergy();
    }

    @Override
    public double getFat() {
        return fat;
    }

    @Override
    public void setFat(double fat) {
        this.fat = fat;
        calculateEnergy();
    }

    @Override
    public double getCarbohydrate() {
        return carbohydrate;
    }

    @Override
    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
        calculateEnergy();
    }

    private void calculateEnergy() {
        if (protein != 0.0 || fat != 0.0 || carbohydrate != 0.0) {
            energy = protein * PROTEIN_ENERGY + fat * FAT_ENERGY + carbohydrate * CARBOHYDRATE_ENERGY;
        }
    }

    @Override
    public String toString() {
        return "Product: " + foodName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductImpl food = (ProductImpl) o;
        return Double.compare(food.energy, energy) == 0 &&
                Double.compare(food.protein, protein) == 0 &&
                Double.compare(food.fat, fat) == 0 &&
                Double.compare(food.carbohydrate, carbohydrate) == 0 &&
                Objects.equals(foodName, food.foodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodName, energy, protein, fat, carbohydrate);
    }

    public ProductImpl(String foodName) {
        this.foodName = foodName;
    }

    public ProductImpl(String foodName, double energy) {
        this.foodName = foodName;
        this.energy = energy;
    }

    public ProductImpl(String foodName, double protein, double fat, double carbohydrate) {
        this.foodName = foodName;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        calculateEnergy();
    }
}