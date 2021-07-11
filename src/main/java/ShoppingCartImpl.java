import java.util.*;

public class ShoppingCartImpl implements ShoppingCart {
    private final List<ShoppingCartItem> shoppingCart = new ArrayList<>();

    @Override
    public List<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCart;
    }

    @Override
    public void addProduct(Product product, double productWeight) {
        if (containsProduct(product)) {
            int index = indexOfProduct(product);
            double oldMass = shoppingCart.get(index).getProductWeight();
            shoppingCart.get(index).setProductWeight(oldMass + productWeight);
        } else {
            shoppingCart.add(new ShoppingCartItemImpl(product, productWeight));
        }
        sort();
    }

    private void sort() {
        shoppingCart.sort(Comparator.comparing(ShoppingCartItem::getProductWeight).reversed()
                .thenComparing((ShoppingCartItem shoppingCartItem) -> shoppingCartItem.getProduct().toString()));
    }

    private boolean containsProduct(Product product) {
        return indexOfProduct(product) >= 0;
    }

    private int indexOfProduct(Product product) {
        sort();
        int index = -1;
        for (int i = 0; i < shoppingCart.size(); i++) {
            if (shoppingCart.get(i).getProduct().equals(product)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public boolean containsItem(ShoppingCartItem shoppingCartItem) {
        return indexOfItem(shoppingCartItem) >= 0;
    }

    private int indexOfItem(ShoppingCartItem shoppingCartItem) {
        sort();
        int index = -1;
        for (int i = 0; i < shoppingCart.size(); i++) {
            if (shoppingCart.get(i).equals(shoppingCartItem)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public void setShoppingCartItem(ShoppingCartItem shoppingCartItem) {
        Product product = shoppingCartItem.getProduct();
        if (containsProduct(product)) {
            setProductWeight(product, shoppingCartItem.getProductWeight());
            setProductBought(product, false);
        } else {
            shoppingCart.add(shoppingCartItem);
            sort();
        }
    }

    @Override
    public void removeProduct(Product product) {
        shoppingCart.removeIf(shoppingCartItem -> shoppingCartItem.getProduct().equals(product));
    }

    @Override
    public ShoppingCartItem getShoppingCartItem(Product product) {
        for (ShoppingCartItem item : shoppingCart) {
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
    public void setProductWeight(Product product, double weight) {
        int index = indexOfProduct(product);
        if (index >= 0) {
            shoppingCart.get(index).setProductWeight(weight);
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
}
