import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;

public class HikeTest {
    private static Hike hike;
    private static Product product1;
    private static Product product2;
    private static Product product3;

    @BeforeClass
    public static void init() {
        hike = new HikeImpl("TestHike", 2, 4);
        product1 = new ProductImpl("Product 1", 1, 2, 3);
        product2 = new ProductImpl("Product 2", 2, 3, 4);
        product3 = new ProductImpl("Product 3", 3, 4, 5);
        Food food1 = new FoodImpl("Food 1");
        Food food2 = new FoodImpl("Food 2");
        food1.addProduct(product1, 10);
        food1.addProduct(product2, 20);
        food2.addProduct(product1, 10);
        food2.addProduct(product3, 30);
        for (int i = 0; i < hike.getDuration(); i++) {
            HikeDay hikeDay = hike.getHikeDay(i);
            hikeDay.addFood(food1);
            hikeDay.addFood(food2);
        }
    }

    @Test
    public void setDurationTest() {
        hike.setDuration(3);
        Assert.assertEquals(3, hike.getDuration());
        hike.setDuration(2);
        Assert.assertEquals(2, hike.getDuration());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setIllegalDurationTest() {
        hike.setDuration(0);
    }

    @Test
    public void setParticipantsTest() {
        hike.setNumberOfParticipants(6);
        Assert.assertEquals(6, hike.getNumberOfParticipants());
        hike.setNumberOfParticipants(4);
        Assert.assertEquals(4, hike.getNumberOfParticipants());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setIllegalParticipantsTest() {
        hike.setNumberOfParticipants(0);
    }

    @Test
    public void getHikeDayTest() {
        Assert.assertEquals("Day of hike TestHike number 1", hike.getHikeDay(1).toString());
    }

    @Test
    public void setHikeDurationTest() {
        hike.setDuration(3);
        Assert.assertEquals(3, hike.getDuration());
        hike.setDuration(2);
    }

    @Test
    public void moveHikeDay() {
        hike.setDuration(3);
        hike.moveHikeDay(2, 1);
        Assert.assertEquals(Collections.EMPTY_LIST, hike.getHikeDay(1).getFoods());
        hike.removeHikeDay(1);
        Assert.assertEquals(2,hike.getDuration());
    }

    @Test
    public void addHikeDayFood() {
        hike.setDuration(3);
        FoodImpl test_food = new FoodImpl("Test Food", 1, 1, 1);
        FoodImpl next_test_food = new FoodImpl("Next Test Food", 1, 1, 1);
        hike.getHikeDay(2).addFood(test_food);
        hike.getHikeDay(2).addFood(next_test_food,0);
        Assert.assertEquals(next_test_food, hike.getHikeDay(2).getFood(0));
        Assert.assertEquals(test_food, hike.getHikeDay(2).getFood(1));
        hike.removeHikeDay(2);
    }

    @Test
    public void getShoppingCartTest() {
        ShoppingCart expectedShoppingCart = new ShoppingCartImpl();
        expectedShoppingCart.addProduct(product1, 160.0);
        expectedShoppingCart.addProduct(product2, 160.0);
        expectedShoppingCart.addProduct(product3, 240.0);
        Assert.assertEquals(expectedShoppingCart, hike.getShoppingCart());
        hike.setNumberOfParticipants(5);
        expectedShoppingCart.addProduct(product1, 40.0);
        expectedShoppingCart.addProduct(product2, 40.0);
        expectedShoppingCart.addProduct(product3, 60.0);
        Assert.assertEquals(expectedShoppingCart, hike.getShoppingCart());
    }
}
