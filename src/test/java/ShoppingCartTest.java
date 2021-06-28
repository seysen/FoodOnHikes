import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class ShoppingCartTest {
    @Test
    public void getShoppingCartItemTest() {
        ShoppingCart cart = new ShoppingCartImpl();
        Product testProduct1 = new ProductImpl("Test Product1", 10);
        Product testProduct2 = new ProductImpl("Test Product2", 20);
        cart.addShoppingCartItem(testProduct1, 10);
        Assert.assertNull(cart.getShoppingCartItem(testProduct2));
        Assert.assertEquals(testProduct1, cart.getShoppingCartItem(testProduct1).getProduct());
    }

    @Test
    public void getListOfProductsTest() {
        ShoppingCart cart = new ShoppingCartImpl();
        Set<Product> productSet = new HashSet<>();
        Product testProduct1 = new ProductImpl("Test Product1", 10);
        Product testProduct2 = new ProductImpl("Test Product2", 20);
        cart.addShoppingCartItem(testProduct1, 10);
        cart.addShoppingCartItem(testProduct2, 20);
        productSet.add(testProduct2);
        productSet.add(testProduct1);
        Assert.assertEquals(productSet, cart.getSetOfProducts());
    }

    @Test
    public void getProductWeightTest() {
        ShoppingCart cart = new ShoppingCartImpl();
        Product testProduct1 = new ProductImpl("Test Product1", 10);
        Product testProduct2 = new ProductImpl("Test Product2", 20);
        cart.addShoppingCartItem(testProduct2, 20);
        cart.addShoppingCartItem(testProduct1, 10);
        Assert.assertEquals(10.0, cart.getProductWeight(testProduct1), 0.0001);
        Assert.assertEquals(20.0, cart.getProductWeight(testProduct2), 0.0001);
    }

    @Test
    public void setProductWeightTest() {
        ShoppingCart cart = new ShoppingCartImpl();
        Product testProduct1 = new ProductImpl("Test Product1", 10);
        Product testProduct2 = new ProductImpl("Test Product2", 20);
        Product testProduct3 = new ProductImpl("Test Product3", 30);
        cart.addShoppingCartItem(testProduct2, 20);
        cart.addShoppingCartItem(testProduct1, 10);
        cart.addProductWeight(testProduct2, 40);
        cart.addProductWeight(testProduct3, 30);
        Assert.assertEquals(10.0, cart.getProductWeight(testProduct1), 0.0001);
        Assert.assertEquals(60.0, cart.getProductWeight(testProduct2), 0.0001);
        Assert.assertEquals(30.0, cart.getProductWeight(testProduct3), 0.0001);
    }

    @Test(expected = NullPointerException.class)
    public void setProductBoughtTest() {
        ShoppingCart cart = new ShoppingCartImpl();
        Product testProduct1 = new ProductImpl("Test Product1", 10);
        Product testProduct2 = new ProductImpl("Test Product2", 20);
        Product testProduct3 = new ProductImpl("Test Product3", 30);
        cart.addShoppingCartItem(testProduct2, 20);
        cart.addShoppingCartItem(testProduct1, 10);
        Assert.assertEquals(false,cart.isProductBought(testProduct1));
        Assert.assertEquals(false,cart.isProductBought(testProduct2));
        cart.setProductBought(testProduct2,false);
        cart.setProductBought(testProduct2,true);
        Assert.assertEquals(false,cart.isProductBought(testProduct1));
        Assert.assertEquals(true,cart.isProductBought(testProduct2));
        Assert.assertEquals(false,cart.isProductBought(testProduct3)); //NullPointerException
    }

    @Test
    public void equalsAndHashCodeTest() {
        ShoppingCart cart1 = new ShoppingCartImpl();
        ShoppingCart cart2 = new ShoppingCartImpl();
        ShoppingCart cart3 = new ShoppingCartImpl();
        Product testProduct1 = new ProductImpl("Test Product1", 10);
        Product testProduct2 = new ProductImpl("Test Product2", 20);
        Product testProduct3 = new ProductImpl("Test Product3", 30);
        cart1.addShoppingCartItem(testProduct2, 20);
        cart1.addShoppingCartItem(testProduct1, 10);
        cart2.addShoppingCartItem(testProduct1, 10);
        cart2.addShoppingCartItem(testProduct2, 20);
        cart3.addShoppingCartItem(testProduct1, 10);
        cart3.addShoppingCartItem(testProduct3, 30);
        Assert.assertEquals(cart1,cart2);
        Assert.assertNotSame(cart1,cart3);
        ShoppingCartImpl.ShoppingCartItem shoppingCartItem1 = cart1.getShoppingCartItem(testProduct1);
        ShoppingCartImpl.ShoppingCartItem shoppingCartItem2 = cart2.getShoppingCartItem(testProduct1);
        ShoppingCartImpl.ShoppingCartItem shoppingCartItem3 = cart3.getShoppingCartItem(testProduct3);
        Assert.assertEquals(shoppingCartItem1,shoppingCartItem2);
        Assert.assertNotSame(shoppingCartItem1,shoppingCartItem3);
    }
}
