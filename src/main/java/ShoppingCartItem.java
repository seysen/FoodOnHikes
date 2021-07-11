public interface ShoppingCartItem {
    Product getProduct();

    double getProductWeight();

    void setProductWeight(double productWeight);

    boolean isBought();

    void setBought(boolean bought);
}
