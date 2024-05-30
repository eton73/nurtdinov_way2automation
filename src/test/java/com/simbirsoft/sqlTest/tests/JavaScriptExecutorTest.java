package com.simbirsoft.sqlTest.tests;

import com.simbirsoft.way2automation.config.ConfProperties;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.simbirsoft.sqlTest.pages.BaseSQLPage;

import java.time.Duration;

public class JavaScriptExecutorTest {
    protected static WebDriver driver;
    protected static BaseSQLPage baseSQLPage;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfProperties.getProperty("sqlTestPage"));

        baseSQLPage = new BaseSQLPage(driver);
    }

    @Severity(SeverityLevel.MINOR)
    @Epic("Проверка сайта sql-ex.ru")
    @Feature("Фокус")
    @Story("Убрать фокус из поля ввода")
    @Test(threadPoolSize = 2)
    public void testDropFocus() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(baseSQLPage.dropFocus()).isTrue();
    }

    @Severity(SeverityLevel.MINOR)
    @Epic("Проверка сайта sql-ex.ru")
    @Feature("Скролл")
    @Story("Наличие скролла")
    @Test(threadPoolSize = 2)
    public void testIsScrollHeight() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(baseSQLPage.isScrollHeight()).isTrue();
    }

    @AfterClass
    public static void exit() {
        driver.quit();
    }
}
