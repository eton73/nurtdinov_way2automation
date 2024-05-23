package com.simbirsoft.way2automation.tests.tests;

import com.simbirsoft.way2automation.tests.config.ConfProperties;
import com.simbirsoft.way2automation.tests.helpers.ScreenshotHelper;
import com.simbirsoft.way2automation.tests.pages.MainPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver;
    protected static MainPage mainPage;

    @BeforeClass
    public static void setup() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));

        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfProperties.getProperty("startPage"));

        mainPage = new MainPage(driver);
    }

    @AfterMethod
    public void makeScreenshot(ITestResult tr) throws IOException {
        if (!tr.isSuccess()) {
            ScreenshotHelper.makeScreenshotToByte(driver);
        }
    }

    @AfterClass
    public static void exit() {
        driver.quit();
    }
}