package com.simbirsoft.way2automation.tests;

import com.simbirsoft.way2automation.helpers.ScreenshotHelper;
import com.simbirsoft.way2automation.helpers.ConfHelper;
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

    protected WebDriver driver;

    @BeforeClass
    public void setup() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", ConfHelper.getProperty("chromedriver"));

        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void makeScreenshot(ITestResult tr) throws IOException {
        if (!tr.isSuccess()) {
            ScreenshotHelper.makeScreenshotToByte(driver);
        }
    }

    @AfterClass
    public void exit() {
        driver.quit();
    }
}