package com.simbirsoft.way2automation.tests;

import com.simbirsoft.way2automation.helpers.ConfHelper;
import com.simbirsoft.way2automation.helpers.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactoryTesting {

    private WebDriver driver;

    @Test
    @Parameters({"browserType"})
    public void testExamplePageOnMultipleBrowsers(String browserType) {
        if (browserType.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", ConfHelper.getProperty("chromedriver"));
            driver = new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browserType.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserType.equalsIgnoreCase("IE")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new InternetExplorerDriver();
        }
        driver.manage().window().maximize();
        driver.get(ConfHelper.getProperty("startPage"));
        System.out.println(browserType + ": " + driver.getTitle());
    }

    @Test
    @Parameters({"browserType"})
    public void testExamplePageOnMultipleBrowsersWithGRID(String browserType) throws MalformedURLException {
        if (browserType.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", ConfHelper.getProperty("chromedriver"));
            ChromeOptions options = new ChromeOptions();
            driver = new RemoteWebDriver(new URL(Constants.URL_GRID), options);
            driver.manage().window().maximize();
        }
        else if (browserType.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            driver = new RemoteWebDriver(new URL(Constants.URL_GRID), options);
        }
        else if (browserType.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            driver = new RemoteWebDriver(new URL(Constants.URL_GRID), options);
        }

        driver.get(ConfHelper.getProperty("startPage"));
        System.out.println(browserType + ": " + driver.getTitle());
    }

    @AfterClass
    public void exit() {
        driver.quit();
    }
}
