import java.util.*;

public class ShoppingCartImpl implements ShoppingCart {
    Set<ShoppingCartItem> shoppingCart = new HashSet<>();

    @Override
    public Set<ShoppingCartItem> getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public void addShoppingCartItem(Product product, double productWeight) {
        shoppingCart.add(new ShoppingCartItem(product, productWeight));
    }

    @Override
    public Set<Product> getSetOfProducts() {
        Set<Product> productSet = new HashSet<>();
        Iterator<ShoppingCartItem> iterator = shoppingCart.iterator();
        while (iterator.hasNext()) {
            ShoppingCartItem item = iterator.next();
            productSet.add(item.getProduct());
        }
        return productSet;
    }

    @Override
    public ShoppingCartItem getShoppingCartItem(Product product) {
        Iterator<ShoppingCartItem> iterator = shoppingCart.iterator();
        while (iterator.hasNext()) {
            ShoppingCartItem item = iterator.next();
            if (product.equals(item.getProduct())) {
                return item;
            }
        }
        return null;
    }

    @Override
    public double getProductWeight(Product product) {
        return getShoppingCartItem(product).getProductWeight();
    }

    @Override
    public void addProductWeight(Product product, double productWeight) {
        ShoppingCartItem shoppingCartItem = getShoppingCartItem(product);
        if (shoppingCartItem != null) {
            shoppingCartItem.setProductWeight(shoppingCartItem.getProductWeight() + productWeight);
        } else {
            shoppingCart.add(new ShoppingCartItem(product, productWeight));
        }
    }

    @Override
    public boolean isProductBought(Product product) {
        return getShoppingCartItem(product).isBought();
    }

    @Override
    public void setProductBought(Product product, boolean isProductBought) {
        ShoppingCartItem shoppingCartItem = getShoppingCartItem(product);
        shoppingCartItem.setBought(isProductBought);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartImpl cart = (ShoppingCartImpl) o;
        return Objects.equals(shoppingCart, cart.shoppingCart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoppingCart);
    }

    public class ShoppingCartItem {
        private final Product product;
        private double productWeight;
        private boolean isBought;

        public ShoppingCartItem(Product product, double productWeight) {
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

        public boolean isBought() {
            return isBought;
        }

        public void setBought(boolean bought) {
            isBought = bought;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ShoppingCartItem that = (ShoppingCartItem) o;
            return Double.compare(that.productWeight, productWeight) == 0 &&
                    product.equals(that.product);
        }

        @Override
        public int hashCode() {
            return Objects.hash(product, productWeight);
        }
    }
}
