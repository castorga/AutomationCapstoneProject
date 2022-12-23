package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage extends BasePage {
    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getProductLink(int id) {
        WebElement link = webDriver.findElement(
                By.xpath("//a[@href='prod.html?idp_=" + id + "']")
        );
        return link;
    }

    public WebElement getCategory(int i) {
        return webDriver.findElement(By.className("list-group"))
                .findElements(By.id("itemc"))
                .get(i);
    }

    public List<WebElement> getProductBlocks() {
        return webDriver.findElements(By.className("h-100"));
    }
}

