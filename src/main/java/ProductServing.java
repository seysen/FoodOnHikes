import java.util.Objects;

public class ProductServing {
    private final Product product;
    private double productWeight;

    public ProductServing(Product product, double productWeight) {
        this.product = product;
        this.productWeight = productWeight;
    }

    public Product getProduct() {
        return product;
    }

    public double getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(double productWeight) {
        this.productWeight = productWeight;
    }

    public double getProtein() {
        return product.getProtein() * productWeight / 100;
    }

    public double getFat() {
        return product.getFat() * productWeight / 100;
    }

    public double getCarbohydrate() {
        return product.getCarbohydrate() * productWeight / 100;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductServing that = (ProductServing) o;
        return Double.compare(that.productWeight, productWeight) == 0 &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, productWeight);
    }
}
