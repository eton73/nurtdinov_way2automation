package com.simbirsoft;

import com.simbirsoft.config.ConfProperties;
import com.simbirsoft.pages.MainPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver;
    protected static MainPage mainPage;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfProperties.getProperty("startPage"));

        mainPage = new MainPage(driver);
    }

    @AfterClass
    public static void exit() {
        driver.quit();
    }

}
