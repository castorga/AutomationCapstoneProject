package Steps;

import Pages.CommonPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonSteps extends BaseSteps
{
    CommonPage commonPage = PageFactory.initElements(webDriver, CommonPage.class);

    public CommonSteps(WebDriver webDriver) {
        super(webDriver);
    }

    public String getLogoTitle() {
        return commonPage.getLogo().getText();
    }

    public void click_Home() {
        commonPage.getHomeLink().click();
    }

    public void click_Cart() {
        commonPage.getCartLink().click();
    }

    public void wait_until_empty_page_loaded() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState")
                            .equals("complete"));
    }

    public void refreshPage() {
        webDriver.navigate().refresh();
    }

}
