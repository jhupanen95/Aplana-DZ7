package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    @FindBy(id = "aa-search-input")
    private WebElement searchForm;

    @FindBy(xpath = "//input[@class='button-1 search-box-button at__searchbutton']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//div[@class='c-pl__main__hits js-pl__main__hits at__product-display ']")
    private WebElement products;

    @FindBy(xpath = "//span[@class='cart-label']")
    private WebElement cart;

    @FindBy(xpath = "//select[@class='ais-HitsPerPage-select']")
    private WebElement countProduct;

    private Wait<WebDriver> wait;

    public MainPage (WebDriver driver){
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5, 1000);
    }

    public void search (String request){
        searchForm.clear();
        searchForm.sendKeys(request);
        buttonSearch.click();
        wait.until(ExpectedConditions.visibilityOf(countProduct));
    }

    public void clickOnProduct(String id){
        WebElement product = products.findElement(By.xpath("//div[@class='c-productItem js-productItem-" + id + " at__productitem c-productItem--inited']"));
        product.findElement(By.xpath("//div[@class='c-productItem__content__desc__title']/a")).click();
    }

    public double getPriceProduct(String id){
        WebElement product = products.findElement(By.xpath("//div[@class='c-productItem js-productItem-" + id + " at__productitem c-productItem--inited']"));
        String price = product.findElement(By.xpath("//div[@class='c-productItem__price c-productItem__price--current at__product-price']")).getText().replaceAll("\\.", "").replaceAll(",- €", "");
        return Double.parseDouble(price);
    }

    public void addToCart(String id){
        WebElement product = products.findElement(By.xpath("//div[@class='c-productItem js-productItem-" + id + " at__productitem c-productItem--inited']"));
        product.findElement(By.xpath("//div[@class='c-productItem__priceBox__lower__button c-productItem__priceBox__lower__button--addToCart']/a")).click();
    }

    public void inToCart(){
        cart.click();
    }

}
