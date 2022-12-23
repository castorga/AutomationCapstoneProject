import Constants.ConstantsClass;
import Steps.CartPageSteps;
import Steps.CommonSteps;
import Steps.MainPageSteps;
import Steps.ProductPageSteps;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.Reporter;

public class PageTests extends BaseTest {
    CommonSteps commonSteps = new CommonSteps(webDriver);
    MainPageSteps mainPageSteps = new MainPageSteps(webDriver);
    ProductPageSteps productPageSteps = new ProductPageSteps(webDriver);
    CartPageSteps cartPageSteps = new CartPageSteps(webDriver);

    //private static Logger logger = LoggerFactory.getLogger(DebugTests.class);

    @Test(
            testName ="verifyHomePageElements",
            description = "Verify Home page elements",
            groups = "Main",
            priority = 1
    )
    public void verifyHomePageElements() throws InterruptedException {
        Reporter.log("<br> • Test case 1: Verify Home page elements");

        Reporter.log("<br> • Ensure Home page displayed");
        commonSteps.click_Home();
        mainPageSteps.wait_until_products_displayed();

        for(int i = 0; i < ConstantsClass.CATEGORIES_AMOUNT; i++) {
            Reporter.log("<br> • Verify category " + ConstantsClass.CATEGORIES[i]);
            CommonAssertions.requirement_verifyEquals(
                    ConstantsClass.CATEGORIES[i],
                    mainPageSteps.getCategoryText(i),
                    "SR-12111"
            );
        }

        Reporter.log("<br> • Check amount of articles displayed by category");

        for(int i = 0; i < ConstantsClass.CATEGORIES_AMOUNT; i++) {
            Reporter.log("<br> • Click category: " + ConstantsClass.CATEGORIES[i]);
            mainPageSteps.clickCategory(i);

            Reporter.log("<br> • Wait for page to update");
            Thread.sleep(2000);

            Reporter.log("<br> • Verify number of " + ConstantsClass.CATEGORIES[i]);
            CommonAssertions.verifyEquals(
                    ConstantsClass.CATEGORY_AMOUNTS[i],
                    mainPageSteps.getNumberOfProductsDisplayed()
            );
        }

        Reporter.log("<br> • Reload the page");
        commonSteps.refreshPage();
        mainPageSteps.wait_until_products_displayed();
    }

    @Test(
            testName ="addAndDeleteProductFromCart",
            description = "Add and delete product from cart",
            groups = "Main",
            priority = 2
    )
    public void addAndDeleteProductFromCart() throws InterruptedException {
        Reporter.log("<br> • Test case 2: Add and delete a product from the cart");

        Random rn = new Random();

        Reporter.log("<br> • Ensure Home page displayed");
        commonSteps.click_Home();
        mainPageSteps.wait_until_products_displayed();

        Reporter.log("<br> • Select a product on the main page");
        mainPageSteps.clickProductLink(rn.nextInt(9) + 1);
        productPageSteps.wait_until_product_page_displayed();

        Reporter.log("<br> • Verify product details are shown");
        String productName = productPageSteps.getProductName();
        CommonAssertions.requirement_verifyNotEmpty(
                productName,
                "SR-12120"
        );
        Reporter.log("<br> • Product name: " + productName);

        String productDescription = productPageSteps.getProductDescription();
        CommonAssertions.requirement_verifyNotEmpty(
                productDescription,
                "SR-12120"
        );
        Reporter.log("<br> • Product description: " + productDescription);

        String productPrice = productPageSteps.getProductPriceText();
        CommonAssertions.requirement_verifyNotEmpty(
                productPrice,
                "SR-12120"
        );
        Reporter.log("<br> • Product price: " + productPrice);

        boolean productImageShown = productPageSteps.isProductImageAvailable();
        CommonAssertions.requirement_verifyTrue(
                productImageShown,
                "SR-12120"
        );
        Reporter.log("<br> • Product image shown: " + productImageShown);

        String addToCartButtonText = productPageSteps.getAddToCartButtonText();
        CommonAssertions.requirement_verifyEquals(
                ConstantsClass.ADD_TO_CART_TEXT,
                addToCartButtonText,
                "SR-12120"
        );
        Reporter.log("<br> • Button text: " + addToCartButtonText);

        Reporter.log("<br> • Click the Add to cart button");
        productPageSteps.clickAddToCartButton();

        Reporter.log("<br> • Wait for and acknowledge alert");
        productPageSteps.wait_until_alert_displayed();
        CommonAssertions.requirement_verifyEquals(
                ConstantsClass.ALERT_MESSAGE,
                productPageSteps.getAlertMessage(),
                "SR-12121");
        productPageSteps.acknowledge_alert();

        Reporter.log("<br> • Ensure Home page displayed");
        commonSteps.click_Cart();

        Reporter.log("<br> • Go to Cart page");
        commonSteps.click_Cart();
        cartPageSteps.wait_until_products_displayed();

        Reporter.log("<br> • Ensure product is added on cart page");
        CommonAssertions.verifyEquals(
                1,
                cartPageSteps.getNumberOfProducts()
        );

        Reporter.log("<br> • Delete product");
        cartPageSteps.deleteProductOnCart(0);

        Reporter.log("<br> • Wait for page to reload and verify there are no products in the cart");
        Thread.sleep(2000);
        CommonAssertions.verifyEquals(
                0,
                cartPageSteps.getNumberOfProducts()
        );
    }

    @Test(
            testName ="addMultipleProductsAndCheckPrice",
            description = "Add multiple products to card and verify price",
            groups = "Main",
            priority = 3
    )
    public void addMultipleProductsAndCheckPrice() throws InterruptedException {
        Reporter.log("<br> • Test case 3: Add multiple products and verify cart price");

        class Product {
            public String Name;
            public String imageSrc;
            public int Price;
        }
        int totalPrice_expected = 0;
        int totalPrice_accumulated = 0;
        int numberOfProducts = 3;

        ArrayList<Product> products = new ArrayList<>();
        Random rn = new Random();

        for(int i = 0; i < numberOfProducts; i++) {

            // Temporary variable to store products into the ArrayList
            // for comparison later
            Product tmp_product = new Product();

            Reporter.log("<br> • Go to Home page");
            commonSteps.click_Home();
            mainPageSteps.wait_until_products_displayed();

            Reporter.log("<br> • Select a product on the main page");
            mainPageSteps.clickProductLink(rn.nextInt(9) + 1);
            productPageSteps.wait_until_product_page_displayed();

            Reporter.log("<br> • Click the Add to cart button");
            productPageSteps.clickAddToCartButton();

            Reporter.log("<br> • Wait for and acknowledge alert");
            productPageSteps.wait_until_alert_displayed();
            productPageSteps.acknowledge_alert();

            tmp_product.Name = productPageSteps.getProductName();
            tmp_product.Price = productPageSteps.getProductPrice();
            tmp_product.imageSrc = productPageSteps.getImageSource();

            totalPrice_expected = totalPrice_expected + tmp_product.Price;
            products.add(tmp_product);
        }
        Reporter.log("<br> • Go to Cart page");
        commonSteps.click_Cart();
        cartPageSteps.wait_until_products_displayed();

        for(int i = 0; i < numberOfProducts; i++) {
            Reporter.log("<br> • Check details for product number " + (i + 1));

            // We need another loop because products on cart
            // page are ordered at random it seems
            for(int j = 0; j < numberOfProducts; j++) {
                if(products.get(i).Name.equals(cartPageSteps.getProductName(j))) {
                    Reporter.log("<br> • Check product name");
                    CommonAssertions.requirement_verifyEquals(
                            products.get(i).Name,
                            cartPageSteps.getProductName(j),
                            "SR-12130"
                    );

                    Reporter.log("<br> • Check product image");
                    CommonAssertions.requirement_verifyEquals(
                            products.get(i).imageSrc,
                            cartPageSteps.getProductImageSource(j),
                            "SR-12130"
                    );

                    Reporter.log("<br> • Check product price");
                    CommonAssertions.requirement_verifyEquals(
                            products.get(i).Price,
                            cartPageSteps.getProductPrice(j),
                            "SR-12130"
                    );

                    Reporter.log("<br> • Verify product has delete button available");
                    CommonAssertions.requirement_verifyEquals(
                            ConstantsClass.DELETE_LINK_TEXT,
                            cartPageSteps.getDeleteButtonText(j),
                            "SR-12130"
                    );

                    totalPrice_accumulated = totalPrice_accumulated + cartPageSteps.getProductPrice(j);

                    break; // Exit to avoid duplicates
                } else; // Do nothing
            }
        }

        Reporter.log("<br> • Ensure expected total price matches with the accumulated price");
        CommonAssertions.verifyEquals(totalPrice_expected, totalPrice_accumulated);

        Reporter.log(" Verify expected total price matches with price shown at the right of the page");
        CommonAssertions.requirement_verifyEquals(
                totalPrice_expected,
                cartPageSteps.getTotalPrice(),
                "SR-12130"
        );

        Reporter.log("<br> • Verify place order button says 'Place order'");
        CommonAssertions.requirement_verifyEquals(
                ConstantsClass.PLACE_ORDER_TEXT,
                cartPageSteps.getPlaceOrderButtonText(),
                "SR-12130"
        );

        Reporter.log("<br> • Delete each product");
        for(int i = 0; i < numberOfProducts; i++) {
            cartPageSteps.deleteProductOnCart(0);
            Thread.sleep(2000);
        }
    }
}
