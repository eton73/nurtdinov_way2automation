package com.simbirsoft.way2automation.tests;

import com.simbirsoft.config.Constants;
import com.simbirsoft.way2automation.helpers.RunTestAgain;
import com.simbirsoft.way2automation.helpers.ScreenshotHelper;
import com.simbirsoft.config.ConfHelpers;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeSuite
    public void setUpTestsSuite(ITestContext testsContext) {
        for (ITestNGMethod testMethod : testsContext.getAllTestMethods()) {
            if (testMethod.getRetryAnalyzer() == null) {
                testMethod.setRetryAnalyzer(new RunTestAgain());
            }
        }
    }

    @BeforeClass
    public void setup() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", ConfHelpers.getProperty("chromedriver"));

        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL(Constants.URL_GRID), options);
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