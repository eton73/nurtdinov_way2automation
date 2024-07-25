package com.simbirsoft.way2automation.tests;

import com.simbirsoft.config.ConfHelpers;
import com.simbirsoft.config.Constants;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class DriverFactoryTest {

    private static final Logger logger = LoggerFactory.getLogger(DriverFactoryTest.class);
    private WebDriver driver;

    @Test
    @Parameters({"browserType"})
    public void testExamplePageOnMultipleBrowsers(String browserType) {
        switch (browserType.toUpperCase(Locale.ROOT)) {
            case "CHROME": {
                System.setProperty("webdriver.chrome.driver", ConfHelpers.getProperty("chromedriver"));
                driver = new ChromeDriver();
                break;
            }
            case "EDGE": {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            }
            case "FIREFOX": {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            }
            case "IE": {
                WebDriverManager.firefoxdriver().setup();
                driver = new InternetExplorerDriver();
                break;
            }
            default: {
                logger.warn("Такой браузер не поддерживается");
            }
        }
        driver.manage().window().maximize();
        driver.get(ConfHelpers.getProperty("startPage"));
        logger.info("{}: {}", browserType, driver.getTitle());
    }

    @Test
    @Parameters({"browserType"})
    public void testExamplePageOnMultipleBrowsersWithGRID(String browserType) throws MalformedURLException {
        if (browserType.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", ConfHelpers.getProperty("chromedriver"));
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

        driver.get(ConfHelpers.getProperty("startPage"));
        logger.info("{}: {}", browserType, driver.getTitle());
    }

    @AfterClass
    public void exit() {
        driver.quit();
    }
}