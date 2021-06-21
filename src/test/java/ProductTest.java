import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductTest {
    private Product product;
    private Product newProduct;

    @Before
    public void init() {
        product = new ProductImpl("test", 5,6,7);
        System.out.println(product);
        newProduct = new ProductImpl("new");
        System.out.println(newProduct);
    }
    @Test
    public void createFoodTest() {
        Assert.assertEquals(104.94, product.getEnergy(), 0.001);
        Assert.assertEquals(0.0, newProduct.getEnergy(), 0.001);
    }
    @Test
    public void changeProteinTest() {
        product.setProtein(4);
        Assert.assertEquals(100.84, product.getEnergy(),0.001);
    }
    @Test
    public void setEnergyTest() {
        product.setEnergy(100.0);
        Assert.assertEquals(104.94, product.getEnergy(),0.001);
        newProduct.setEnergy(100.0);
        Assert.assertEquals(100.0, newProduct.getEnergy(),0.001);
    }
}