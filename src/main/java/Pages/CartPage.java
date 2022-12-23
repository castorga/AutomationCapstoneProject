package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class CartPage extends BasePage {
    public CartPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.CLASS_NAME, using = "btn-success")
    private WebElement placeOrderButton;

    @FindBy(how = How.ID, using = "totalp")
    private WebElement totalPrice;

    //@FindAll(@FindBy(how = How.CLASS_NAME, using = "success"))
    //private List<WebElement> cartProducts;

    public WebElement getPlaceOrderButton() {
        return placeOrderButton;
    }

    public WebElement getTotalPriceElement() {
        return totalPrice;
    }

    public List<WebElement> getCartProducts() {
        // return cartProducts;
        return webDriver.findElements(By.className("success"));
    }

    public WebElement getProductImage(int i) {
        return this.getCartProducts()
                .get(i)
                .findElements(new By.ByTagName("td"))
                .get(0)
                .findElement(By.tagName("img"));
    }

    public WebElement getProductNameElement(int i) {
        return this.getCartProducts()
                .get(i)
                .findElements(By.tagName("td"))
                .get(1);
    }

    public WebElement getProductPriceElement(int i) {
        return this.getCartProducts()
                .get(i)
                .findElements(By.tagName("td"))
                .get(2);
    }

    public WebElement getProductDeleteButton(int i) {
        return this.getCartProducts()
                .get(i)
                .findElements(By.tagName("td"))
                .get(3)
                .findElement(
                        By.xpath("//a[contains(text(),'Delete')]"
                        )
                );
    }

}
