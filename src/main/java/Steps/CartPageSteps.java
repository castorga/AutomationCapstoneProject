package Steps;

import Pages.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPageSteps extends BaseSteps {

    CartPage cartPage = PageFactory.initElements(webDriver, CartPage.class);

    public CartPageSteps(WebDriver webDriver) {
        super(webDriver);
    }

    public void wait_until_products_displayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("success")));
    }

    public void clickPlaceOrderButton() {
        cartPage.getPlaceOrderButton().click();
    }

    public String getPlaceOrderButtonText() {
        return cartPage.getPlaceOrderButton().getText();
    }

    public String getTotalPriceText() {
        return cartPage.getTotalPriceElement().getText();
    }

    public int getTotalPrice() {
        return Integer.parseInt(cartPage.getTotalPriceElement().getText());
    }

    public int getNumberOfProducts() {
        return cartPage.getCartProducts().size();
    }

    public boolean isProductImagePresent(int i) {
        return cartPage.getProductImage(i).getAttribute("src") != "";
    }

    public String getProductImageSource(int i) {
        return cartPage.getProductImage(i).getAttribute("src");
    }

    public String getProductName(int i) {
        return cartPage.getProductNameElement(i).getText();
    }

    public int getProductPrice(int i) {
        return Integer.parseInt(cartPage.getProductPriceElement(i).getText());
    }

    public String getProductPriceText(int i) {
        return cartPage.getProductPriceElement(i).getText();
    }

    public void deleteProductOnCart(int i) {
        cartPage.getProductDeleteButton(i).click();
    }

    public String getDeleteButtonText(int i) {
        return cartPage.getProductDeleteButton(i).getText();
    }


}
