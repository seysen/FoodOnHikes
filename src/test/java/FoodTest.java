import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FoodTest {
    private Food testFood;
    private Product product1;
    private Product product2;

    @Before
    public void init() {
        product1 = new ProductImpl("product1",3,4,5);
        product2 = new ProductImpl("product2",4,5,6);
        testFood = new FoodImpl("testFood");
        testFood.addProduct(product1,20.0);
        testFood.addProduct(product2,30.0);
        System.out.println(testFood);
    }

    @Test
    public void createFoodTest() {
        Assert.assertEquals(1.8, testFood.getProtein(),0.0001);
        Assert.assertEquals(2.3, testFood.getFat(),0.0001);
        Assert.assertEquals(2.8, testFood.getCarbohydrate(),0.0001);
        Assert.assertEquals(40.227, testFood.getEnergy(),0.0001);
    }

    @Test
    public void addFoodTest() {
        Product product3 = new ProductImpl("product3",  5, 6,7);
        testFood.addProduct(product2, 10.0);
        testFood.addProduct(product3, 60.0);
        Assert.assertEquals(5.2, testFood.getProtein(),0.0001);
        Assert.assertEquals(6.4, testFood.getFat(),0.0001);
        Assert.assertEquals(7.6, testFood.getCarbohydrate(),0.0001);
        Assert.assertEquals(111.936, testFood.getEnergy(),0.0001);
    }

    @Test
    public void removeFoodTest() {
        testFood.removeProduct(product2);
        Assert.assertEquals(0.6, testFood.getProtein(),0.0001);
        Assert.assertEquals(0.8, testFood.getFat(),0.0001);
        Assert.assertEquals(1.0, testFood.getCarbohydrate(),0.0001);
        Assert.assertEquals(13.992, testFood.getEnergy(),0.0001);
    }

    @Test
    public void changeFoodTest() {
        product2.setCarbohydrate(8);
        Assert.assertEquals(1.8, testFood.getProtein(),0.0001);
        Assert.assertEquals(2.3, testFood.getFat(),0.0001);
        Assert.assertEquals(3.4, testFood.getCarbohydrate(),0.0001);
        Assert.assertEquals(42.687, testFood.getEnergy(),0.0001);
    }

    @Test
    public void getProductWeightTest() {
        double foodWeight = testFood.getProductWeight(product1);
        Assert.assertEquals(20, foodWeight, 0.0001);
        Assert.assertEquals(0.0, testFood.getProductWeight(new ProductImpl("food3")), 0.0001);
    }

    @Test
    public void secondAddProductTest() {
        testFood.addProduct(product1, 10.0);
        Assert.assertEquals(2.1, testFood.getProtein(),0.0001);
        Assert.assertEquals(2.7, testFood.getFat(),0.0001);
        Assert.assertEquals(3.3, testFood.getCarbohydrate(),0.0001);
        Assert.assertEquals(47.223, testFood.getEnergy(),0.0001);
    }

    @Test
    public void getProductsTest() {
        List<ProductServing> expectedProducts = new ArrayList<>();
        expectedProducts.add(new ProductServingImpl(product2, 30.));
        expectedProducts.add(new ProductServingImpl(product1, 20.));
        List<ProductServing> foods = testFood.getProductServings();
        Assert.assertEquals(expectedProducts,foods);
        System.out.println("Expected:");
        System.out.println(expectedProducts.get(0).getProduct().toString());
        System.out.println(expectedProducts.get(1).getProduct().toString());
        System.out.println("Actual:");
        System.out.println(foods.get(0).getProduct().toString());
        System.out.println(foods.get(1).getProduct().toString());
    }

    @Test
    public void foodEqualsTest() {
        Food newFood = new FoodImpl("New Food");
        newFood.addProduct(product2, 30.0);
        newFood.addProduct(product1,20.0);
        Assert.assertEquals(newFood, testFood);
    }
}