package com.simbirsoft.sqlTest.tests;

import com.simbirsoft.way2automation.helpers.ConfHelpers;
import io.qameta.allure.*;
import com.simbirsoft.sqlTest.helpers.CookiesHelper;
import com.simbirsoft.sqlTest.pages.BaseSQLPage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class BaseTest {

    private static final String PHPSESSID_COOKIE_NAME = "PHPSESSID";

    protected static WebDriver driver;
    protected static BaseSQLPage baseSQLPage;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", ConfHelpers.getProperty("chromedriver"));

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfHelpers.getProperty("sqlTestPage"));

        baseSQLPage = new BaseSQLPage(driver);
    }

    @Severity(SeverityLevel.MINOR)
    @Epic("Проверка сайта sql-ex.ru")
    @Feature("Cookies")
    @Story("Сохранить Cookies и войти по сохранённым кукам на сайт")
    @Test
    public void testASave() {
        baseSQLPage.setLogin(ConfHelpers.getProperty("userNameCookies"));
        baseSQLPage.setPassword(ConfHelpers.getProperty("passwordCookies"));
        baseSQLPage.clickInputButton();

        CookiesHelper.writerReaderCookies(driver);

        driver.manage().deleteCookieNamed(PHPSESSID_COOKIE_NAME);
        driver.get(ConfHelpers.getProperty("sqlTestPage"));
        driver.manage().deleteCookieNamed(PHPSESSID_COOKIE_NAME);
        CookiesHelper.readerReaderCookies(driver);
        driver.get(ConfHelpers.getProperty("sqlTestPage"));
        driver.get("https://www.sql-ex.ru/personal.php");

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(baseSQLPage.getNickname()).isNotNull();
        baseSQLPage.clickExitButton();
    }

    @AfterClass
    public static void exit() {
        driver.quit();
    }
}