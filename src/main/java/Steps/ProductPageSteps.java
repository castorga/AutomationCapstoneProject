package Steps;

import Pages.MainPage;
import Pages.ProductPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPageSteps extends BaseSteps {
    ProductPage productPage = PageFactory.initElements(webDriver, ProductPage.class);

    public ProductPageSteps(WebDriver webDriver) {
        super(webDriver);
    }

    public void wait_until_product_page_displayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("btn-success")));
    }

    public void clickAddToCartButton(){
        productPage.getAddToCartButton().click();
    }

    public String getAddToCartButtonText() {
        return productPage.getAddToCartButton().getText();
    }

    public void wait_until_alert_displayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void acknowledge_alert() {
        webDriver.switchTo().alert().accept();
    }

    public String getAlertMessage() {
        Alert alert = webDriver.switchTo().alert();
        return alert.getText();
    }

    public String getProductName() {
        return productPage.getProductName().getText();
    }

    public String getProductPriceText() {
        return productPage.getProductPriceElement().getText();
    }

    public int getProductPrice() {
        int tmp_index = productPage.getProductPriceElement().getText().indexOf(' ');
        return Integer.parseInt(
                productPage
                        .getProductPriceElement()
                        .getText()
                        .strip()
                        .substring(1, tmp_index)
        );
    }

    public String getProductDescription() {
        return productPage.getProductDescription().getText();
    }

    public boolean isProductImageAvailable() {
        return !productPage.getProductImage().getAttribute("src").equals("");
    }

    public String getImageSource() {
        return productPage.getProductImage().getAttribute("src");
    }
}
