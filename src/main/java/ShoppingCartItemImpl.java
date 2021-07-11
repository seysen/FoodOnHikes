import java.util.Objects;

public class ShoppingCartItemImpl implements ShoppingCartItem {
    private final Product product;
    private double productWeight;
    private boolean isBought;

    public ShoppingCartItemImpl(Product product, double productWeight) {
        this.product = product;
        this.productWeight = productWeight;
    }

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public double getProductWeight() {
        return productWeight;
    }

    @Override
    public void setProductWeight(double productWeight) {
        this.productWeight = productWeight;
    }

    @Override
    public boolean isBought() {
        return isBought;
    }

    @Override
    public void setBought(boolean bought) {
        isBought = bought;
    }

    @Override
    public String toString() {
        return "Shopping cart item: " + product.getProductName() + " weight: " + productWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartItemImpl that = (ShoppingCartItemImpl) o;
        return Double.compare(that.productWeight, productWeight) == 0 &&
                product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, productWeight);
    }
}
