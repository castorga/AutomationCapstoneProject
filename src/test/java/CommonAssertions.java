import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class CommonAssertions extends Assert {
    static SoftAssert softAssert = new SoftAssert();
    public static void verifyEquals(String expected, String actual) {
        String errorMessage = "Strings do not match. [Expected: " + expected + ", Actual " + actual + "]";
        softAssert.assertEquals(expected, actual, errorMessage);
    }

    public static void verifyEquals(int expected, int actual) {
        String errorMessage = "Quantities do not match. [Expected: " + expected + ", Actual " + actual + "]";
        softAssert.assertEquals(expected, actual);
    }

    public static void requirement_verifyEquals(String expected, String actual, String requirement) {
        String errorMessage = "Strings do not match. [Expected: " + expected + ", Actual " + actual + "]" +
                "\nRequirement: " + requirement + " FAILED";
        softAssert.assertEquals(expected, actual, errorMessage);
    }

    public static void requirement_verifyEquals(int expected, int actual, String requirement) {
        String errorMessage = "Quantities do not match. [Expected: " + expected + ", Actual " + actual + "]" +
                "\nRequirement: " + requirement + " FAILED";
        softAssert.assertEquals(expected, actual, errorMessage);
    }


    public static void verifyNotEmpty(String value) {
        softAssert.assertTrue(!value.isEmpty(), "String is empty");
    }

    public static void requirement_verifyNotEmpty(String value, String requirement) {
        String errorMessage = "String is empty" +
                "\nRequirement: " + requirement + " FAILED";
        softAssert.assertTrue(!value.isEmpty(), errorMessage);
    }

    public static void requirement_verifyTrue(boolean value, String requirement) {
        String errorMessage = "Condition returned false" +
                "\nRequirement: " + requirement + " FAILED";
        softAssert.assertTrue(value, errorMessage);
    }

    public static void assertAll() {
        softAssert.assertAll();
    }
}
