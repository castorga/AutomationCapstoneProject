import Constants.ConstantsClass;
import Steps.CartPageSteps;
import Steps.CommonSteps;
import Steps.MainPageSteps;
import Steps.ProductPageSteps;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class DebugTests extends BaseTest {
    CommonSteps commonSteps = new CommonSteps(webDriver);
    MainPageSteps mainPageSteps = new MainPageSteps(webDriver);
    ProductPageSteps productPageSteps = new ProductPageSteps(webDriver);
    CartPageSteps cartPageSteps = new CartPageSteps(webDriver);

    private static Logger logger = LoggerFactory.getLogger(DebugTests.class);

    @Test(description = "Test some elements of home page", groups = "SmokeSuite", priority = 1)
    public void testHomePage() {
        commonSteps.click_Home();
        String title = commonSteps.getLogoTitle();
        CommonAssertions.verifyEquals(title, "PRODUCT STORE");

        mainPageSteps.wait_until_products_displayed();
        CommonAssertions.verifyEquals(
                ConstantsClass.CATEGORY_PHONES_TEXT,
                mainPageSteps.getCategoryText(0)
        );

        CommonAssertions.verifyEquals(
                ConstantsClass.CATEGORY_LAPTOPS_TEXT,
                mainPageSteps.getCategoryText(1)
        );

        CommonAssertions.verifyEquals(
                ConstantsClass.CATEGORY_MONITORS_TEXT,
                mainPageSteps.getCategoryText(2)
        );
    }

    @Test(description = "Verify page title is correct", groups = "SmokeSuite", priority = 1)
    public void verifyTitleIsCorrect(){
        commonSteps.click_Home();
        String title = commonSteps.getLogoTitle();
        CommonAssertions.verifyEquals(title, "PRODUCT STORE");
    }

    @Test(description = "Test the cart  pagee", groups = "SmokeSuite", priority = 1)
    void testCartPage() {
        for(int i = 0; i < 3; i++) {
            commonSteps.click_Home();
            mainPageSteps.wait_until_products_displayed();
            mainPageSteps.clickRandomLinkFromFirstPage();

            productPageSteps.wait_until_product_page_displayed();
            productPageSteps.clickAddToCartButton();
            productPageSteps.wait_until_alert_displayed();
            productPageSteps.acknowledge_alert();
        }

        commonSteps.click_Cart();
        cartPageSteps.wait_until_products_displayed();
        for(int i = 0; i < 3; i++) {
            logger.info(cartPageSteps.getProductName(i));
            logger.info(cartPageSteps.getProductPriceText(i));
        }
        logger.info(cartPageSteps.getTotalPriceText());
    }

    @Test(description = "Test product page", groups = "SmokeSuite", priority = 1)
    public void testProductPage() {
        commonSteps.click_Home();
        mainPageSteps.wait_until_products_displayed();
        mainPageSteps.clickRandomLinkFromFirstPage();

        productPageSteps.wait_until_product_page_displayed();
        CommonAssertions.verifyNotEmpty(productPageSteps.getProductName());
        logger.info(productPageSteps.getProductName());
        CommonAssertions.verifyNotEmpty(productPageSteps.getProductDescription());
        logger.info(productPageSteps.getProductDescription());
        CommonAssertions.verifyNotEmpty(productPageSteps.getProductPriceText());
        logger.info(productPageSteps.getProductPriceText());
        logger.info(Integer.toString(productPageSteps.getProductPrice()));
        CommonAssertions.verifyNotEmpty(productPageSteps.getAddToCartButtonText());
        logger.info(productPageSteps.getAddToCartButtonText());
        productPageSteps.clickAddToCartButton();
        productPageSteps.wait_until_alert_displayed();
        CommonAssertions.requirement_verifyEquals(
                "Product added",
                productPageSteps.getAlertMessage(),
                "SR-12130");
        productPageSteps.acknowledge_alert();

        commonSteps.click_Home();
        mainPageSteps.wait_until_products_displayed();
    }

    @Test(description = "Test going to cart page", groups = "SmokeSuite", priority = 1)
    public void goToCartPage() {
        logger.info("TC-1: Click on the cart page");

        commonSteps.click_Home();
        mainPageSteps.wait_until_products_displayed();
        commonSteps.click_Cart();
    }

    @Test(description = "Click some links", groups = "SmokeSuite", priority = 1)
    public void clickCoupleOfLinks() throws InterruptedException {
        logger.info("TC-2: Add two products to the shopping cart");

        commonSteps.click_Home();
        mainPageSteps.wait_until_products_displayed();
        String title = commonSteps.getLogoTitle();
        CommonAssertions.requirement_verifyEquals("PRODUCT STORE", title, "SR-0");

        logger.info("Wait until Home page is displayed");
        commonSteps.click_Home();
        mainPageSteps.wait_until_products_displayed();

        logger.info("Click the second product");
        //mainPageSteps.click_Product_Link(2);
        mainPageSteps.clickRandomLinkFromFirstPage();
        productPageSteps.wait_until_product_page_displayed();

        logger.info("Click the Add to cart button");
        productPageSteps.clickAddToCartButton();

        logger.info("Wait for and acknowledge alert");
        productPageSteps.wait_until_alert_displayed();
        CommonAssertions.requirement_verifyEquals(
                "Product added",
                productPageSteps.getAlertMessage(),
                "SR-12130");
        productPageSteps.acknowledge_alert();

        logger.info("Go to the Home page");
        commonSteps.click_Home();
        mainPageSteps.wait_until_products_displayed();

        logger.info("Click product 3");
        mainPageSteps.clickProductLink(3);
        productPageSteps.wait_until_product_page_displayed();

        logger.info("Click the Add to cart button");
        productPageSteps.clickAddToCartButton();

        logger.info("Wait for and acknowledge alert");
        logger.info("VERIFIES SR-12130");
        productPageSteps.wait_until_alert_displayed();
        CommonAssertions.requirement_verifyEquals(
                "Product added",
                productPageSteps.getAlertMessage(),
                "SR-12130");
        productPageSteps.acknowledge_alert();

        logger.info("Go to the Home page");
        commonSteps.click_Home();
        mainPageSteps.wait_until_products_displayed();
    }
}
