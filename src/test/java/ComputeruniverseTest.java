import org.junit.Assert;
import org.junit.Test;
import pages.CartPage;
import pages.MainPage;

public class ComputeruniverseTest extends BaseTest{

    @Test
    public void newTest(){

        MainPage mainPage = new MainPage(driver);
        CartPage cartPage = new CartPage(driver);

        String idProduct = "730904";
        double priceProduct;
        int count = 2;

        mainPage.search("macbook pro");
        priceProduct = mainPage.getPriceProduct(idProduct);
        mainPage.addToCart(idProduct);
        mainPage.inToCart();
        Assert.assertTrue(cartPage.getPriceProduct(idProduct) == priceProduct);
        cartPage.addCountProduct(idProduct, count);
        Assert.assertTrue(cartPage.getPriceProduct(idProduct) == priceProduct * count);
    }

}
