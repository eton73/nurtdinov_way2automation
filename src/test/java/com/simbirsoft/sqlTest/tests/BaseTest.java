package com.simbirsoft.sqlTest.tests;

import com.simbirsoft.way2automation.helpers.ConfHelpers;
import com.simbirsoft.way2automation.helpers.Constants;
import io.qameta.allure.*;
import com.simbirsoft.sqlTest.helpers.CookiesHelper;
import com.simbirsoft.sqlTest.pages.BaseSQLPage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    private static final String PHPSESSID_COOKIE_NAME = "PHPSESSID";

    protected ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    protected BaseSQLPage baseSQLPage;

    protected WebDriver getWebDriver() {
        return driver.get();
    }

    @BeforeClass
    public void setup() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", ConfHelpers.getProperty("chromedriver"));

        ChromeOptions options = new ChromeOptions();
        driver.set(new RemoteWebDriver(new URL(Constants.URL_GRID), options));
        getWebDriver().manage().window().maximize();
        getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getWebDriver().get(ConfHelpers.getProperty("sqlTestPage"));

        baseSQLPage = new BaseSQLPage(getWebDriver());
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

        CookiesHelper.writerReaderCookies(getWebDriver());

        getWebDriver().manage().deleteCookieNamed(PHPSESSID_COOKIE_NAME);
        getWebDriver().get(ConfHelpers.getProperty("sqlTestPage"));
        getWebDriver().manage().deleteCookieNamed(PHPSESSID_COOKIE_NAME);
        CookiesHelper.readerReaderCookies(getWebDriver());
        getWebDriver().get(ConfHelpers.getProperty("sqlTestPage"));
        getWebDriver().get("https://www.sql-ex.ru/personal.php");

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(baseSQLPage.getNickname()).isNotNull();
        baseSQLPage.clickExitButton();
    }

    @AfterClass
    public void exit() {
        getWebDriver().quit();
    }
}