import java.util.Objects;

public class ProductServingImpl implements ProductServing {
    private final Product product;
    private double massOfProduct;

    public ProductServingImpl(Product product, double massOfProduct) {
        this.product = product;
        this.massOfProduct = massOfProduct;
    }

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public double getMassOfProduct() {
        return massOfProduct;
    }

    @Override
    public void setMassOfProduct(double massOfProduct) {
        this.massOfProduct = massOfProduct;
    }

    @Override
    public double getEnergy() {
        double energy;
        if (product.getProtein() != 0.0 || product.getFat() != 0.0 || product.getCarbohydrate() != 0.0) {
            energy = product.getProtein() * NutritionFacts.PROTEIN_ENERGY
                    + product.getFat() * NutritionFacts.FAT_ENERGY
                    + product.getCarbohydrate() * NutritionFacts.CARBOHYDRATE_ENERGY;
        } else {
            energy = product.getEnergy() * massOfProduct / 100;
        }
        return energy;
    }

    @Override
    public double getProtein() {
        return product.getProtein() * massOfProduct / 100;
    }

    @Override
    public double getFat() {
        return product.getFat() * massOfProduct / 100;
    }

    @Override
    public double getCarbohydrate() {
        return product.getCarbohydrate() * massOfProduct / 100;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductServingImpl that = (ProductServingImpl) o;
        return Double.compare(that.massOfProduct, massOfProduct) == 0 &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, massOfProduct);
    }
}
