package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CartPage {

    @FindBy(xpath = "//table[@class='cart']")
    private WebElement cart;

    private Wait<WebDriver> wait;

    public CartPage (WebDriver driver){
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5, 1000);
    }

    public double getPriceProduct(String id) {
        WebElement product = cart.findElement(By.xpath("//tr[@class='at__cartproducts' and @data-productid='"+ id + "']"));
        String price = product.findElement(By.xpath("//td[@class='subtotal at__subtotalprice']")).getAttribute("data-totalpricevalue").replaceAll(",", "");
        return Double.parseDouble(price);
    }

    public void addCountProduct(String id, int count){
        WebElement product = cart.findElement(By.xpath("//tr[@class='at__cartproducts' and @data-productid='"+ id + "']"));
        WebElement selectElem = product.findElement(By.xpath("//select"));
        Select select = new Select(selectElem);
        String price = product.findElement(By.xpath("//td[@class='subtotal at__subtotalprice']")).getAttribute("data-totalpricevalue");
        select.selectByIndex(count-1);
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(product.findElement(By.xpath("//td[@class='subtotal at__subtotalprice']")), "data-totalpricevalue", price)));
    }
}
