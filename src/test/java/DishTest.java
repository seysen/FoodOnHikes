import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DishTest {
    private Dish testDish;
    private Food food1;
    private Food food2;

    @Before
    public void init() {
        food1 = new FoodImpl("food1",3,4,5);
        food2 = new FoodImpl("food2",4,5,6);
        testDish = new DishImpl("testDish");
        testDish.addFood(food1,20.0);
        testDish.addFood(food2,30.0);
        System.out.println(testDish);
    }

    @Test
    public void createDishTest() {
        Assert.assertEquals(1.8, testDish.getProtein(),0.0001);
        Assert.assertEquals(2.3,testDish.getFat(),0.0001);
        Assert.assertEquals(2.8,testDish.getCarbohydrate(),0.0001);
        Assert.assertEquals(40.227,testDish.getEnergy(),0.0001);
    }

    @Test(expected = IllegalStateException.class)
    public void setProteinTest() {
        testDish.setProtein(10.0);
        Dish newDish = new DishImpl("New Dish");
        newDish.setProtein(10.0);
        Assert.assertEquals(10.0, newDish.getProtein(), 0.0001);
    }

    @Test
    public void removeFoodTest() {
        testDish.removeFood(food2);
        Assert.assertEquals(0.6, testDish.getProtein(),0.0001);
        Assert.assertEquals(0.8,testDish.getFat(),0.0001);
        Assert.assertEquals(1.0,testDish.getCarbohydrate(),0.0001);
        Assert.assertEquals(13.992,testDish.getEnergy(),0.0001);
    }

    @Test
    public void changeFoodTest() {
        food2.setCarbohydrate(8);
        testDish.updateDish();
        Assert.assertEquals(1.8, testDish.getProtein(),0.0001);
        Assert.assertEquals(2.3,testDish.getFat(),0.0001);
        Assert.assertEquals(3.4,testDish.getCarbohydrate(),0.0001);
        Assert.assertEquals(42.687,testDish.getEnergy(),0.0001);
    }

    @Test
    public void getFoodWeightTest() {
        double foodWeight = testDish.getFoodWeight(food1);
        Assert.assertEquals(20, foodWeight, 0.0001);
        Assert.assertEquals(0.0,testDish.getFoodWeight(new FoodImpl("food3")), 0.0001);
    }

    @Test
    public void setFoodWeightTest() {
        testDish.setFoodWeight(food1, 30.0);
        Assert.assertEquals(2.1, testDish.getProtein(),0.0001);
        Assert.assertEquals(2.7,testDish.getFat(),0.0001);
        Assert.assertEquals(3.3,testDish.getCarbohydrate(),0.0001);
        Assert.assertEquals(47.223,testDish.getEnergy(),0.0001);
    }

    @Test
    public void getFoodsTest() {
        Map<Food, Double> expectedFoods = new HashMap<>();
        expectedFoods.put(food1, 20.);
        expectedFoods.put(food2, 30.);
        Map<Food, Double> foods = testDish.getFoods();
        Assert.assertEquals(expectedFoods,foods);
    }
}