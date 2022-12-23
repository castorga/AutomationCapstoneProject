package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

// Class for the Page sharing all common elements
public class CommonPage extends BasePage {

    public CommonPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'PRODUCT STORE')]")
    private WebElement logo;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Home')]")
    private WebElement linkHome;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Contact')]")
    private WebElement linkContact;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'About us')]")
    private WebElement linkAboutUs;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Cart')]")
    private WebElement linkCart;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Log in')]")
    private WebElement linkLogIn;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Sign up')]")
    private WebElement linkSignUp;

    public WebElement getLogo() {
        return logo;
    }

    public WebElement getHomeLink() {
        return linkHome;
    }

    public WebElement getContactLink() {
        return linkContact;
    }

    public WebElement getCartLink() {
        return linkCart;
    }

    public WebElement getLoginLink() {
        return linkLogIn;
    }

    public WebElement getSignUpLink() {
        return linkSignUp;
    }
}
