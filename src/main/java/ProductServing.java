import java.util.Objects;

public class ProductServing {
    private final Product product;
    private double massOfProduct;

    public ProductServing(Product product, double massOfProduct) {
        this.product = product;
        this.massOfProduct = massOfProduct;
    }

    public Product getProduct() {
        return product;
    }

    public double getMassOfProduct() {
        return massOfProduct;
    }

    public void setMassOfProduct(double massOfProduct) {
        this.massOfProduct = massOfProduct;
    }

    public double getProtein() {
        return product.getProtein() * massOfProduct / 100;
    }

    public double getFat() {
        return product.getFat() * massOfProduct / 100;
    }

    public double getCarbohydrate() {
        return product.getCarbohydrate() * massOfProduct / 100;
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
