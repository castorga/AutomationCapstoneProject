package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProductPage extends BasePage{

    public ProductPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Add to cart')]")
    private WebElement addToCartButton;

    @FindBy(how = How.CLASS_NAME, using = "name")
    private WebElement productName;

    @FindBy(how = How.CLASS_NAME, using = "price-container")
    private WebElement productPriceElement;

    @FindBy(how = How.ID, using = "more-information")
    private WebElement productDescription;

    public WebElement getAddToCartButton() {
        return addToCartButton;
    }

    public WebElement getProductName() {
        return productName;
    }

    public WebElement getProductPriceElement() {
        return productPriceElement;
    }

    public WebElement getProductDescription() {
        return productDescription.findElement(By.tagName("p"));
    }

    public WebElement getProductImage() {
        return webDriver.findElement(By.id("imgp"))
                .findElement(By.className("item"))
                .findElement(By.tagName("img"));
    }

}
