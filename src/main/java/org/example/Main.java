package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Ignore
        // System.out.println("Hello world!");
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:/Program Files/Mozilla Firefox/firefox.exe");

        // Initialize web driver
        System.setProperty(
                "webdriver.gecko.driver",
                System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "geckodriver.exe"
        );
        WebDriver driver = new FirefoxDriver(options);

        // Get website
        driver.get("https://oscarvalerio.github.io/seleniumExercises/practice1.html");

        Thread.sleep(5000);

        driver.close();
    }
}