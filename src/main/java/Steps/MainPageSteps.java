package Steps;

import Pages.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.Random;

public class MainPageSteps extends BaseSteps {

    MainPage mainPage = PageFactory.initElements(webDriver, MainPage.class);

    public MainPageSteps(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickProductLink(int id)
    {
        mainPage.getProductLink(id).click();
    }

    //Non-functional
    public void clickRandomLinkFromFirstPage() {
        Random rn = new Random();
        mainPage.getProductLink(rn.nextInt(9) + 1).click();
    }

    public void wait_until_products_displayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("card-block")));
    }

    public String getCategoryText(int i) {
        return mainPage.getCategory(i).getText();
    }

    public void clickCategory(int i) {
        mainPage.getCategory(i).click();
    }

    public int getNumberOfProductsDisplayed() {
        return mainPage.getProductBlocks().size();
    }
}
