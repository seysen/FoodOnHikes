import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FoodTest {
    private Food food;
    private Food newFood;

    @Before
    public void init() {
        food = new FoodImpl("test", 5,6,7);
        System.out.println(food);
        newFood = new FoodImpl("new");
        System.out.println(newFood);
    }
    @Test
    public void createFoodTest() {
        Assert.assertEquals(104.94, food.getEnergy(), 0.001);
        Assert.assertEquals(0.0, newFood.getEnergy(), 0.001);
    }
    @Test
    public void changeProteinTest() {
        food.setProtein(4);
        Assert.assertEquals(100.84, food.getEnergy(),0.001);
    }
    @Test
    public void setEnergyTest() {
        food.setEnergy(100.0);
        Assert.assertEquals(104.94, food.getEnergy(),0.001);
        newFood.setEnergy(100.0);
        Assert.assertEquals(100.0, newFood.getEnergy(),0.001);
    }
}