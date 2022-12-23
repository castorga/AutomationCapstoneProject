import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;

public class BaseTest {
    WebDriver webDriver = get_driver();
    public WebDriver get_driver()
    {
        if(this.webDriver == null) {
            setupDriver("firefox");
            webDriver.get("https://www.demoblaze.com/");
            return webDriver;
        }
        return this.webDriver;
    }

    private void setupDriver(String browserName)
    {
        switch(browserName)
        {
            case "chrome":
                System.setProperty(
                        "webdriver.chrome.driver",
                        System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver");
                this.webDriver = new ChromeDriver();
                break;
            case "firefox":
                FirefoxOptions options = new FirefoxOptions();
                options.setBinary("C:/Program Files/Mozilla Firefox/firefox.exe");

                // Initialize the web driver
                System.setProperty(
                        "webdriver.gecko.driver",
                        System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "geckodriver.exe"
                );
                this.webDriver = new FirefoxDriver(options);
                break;
            default:
                System.out.println("Please enter chrome or firefox in the setupDriver method string parameter");
        }
    }

    @BeforeTest(groups = "Main")
    public void beforeTest()
    {
        webDriver.manage().window().maximize();
    }

    @AfterTest(groups = {"SmokeSuite", "Main"})
    public void afterTest()
    {
        //.
    }

    @AfterSuite(groups = "Main")
    public void afterSuite() {
        CommonAssertions.assertAll();
        webDriver.quit();
    }
}
