package com.simbirsoft.way2automation.tests;

import com.simbirsoft.way2automation.config.ConfProperties;
import com.simbirsoft.way2automation.helpers.Constants;
import com.simbirsoft.way2automation.pages.RegistrationPage;
import com.simbirsoft.way2automation.pages.SuccessfulRegPage;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class LoginPracticeSiteTest extends BaseTest {

    protected static RegistrationPage registrationPage;
    protected static SuccessfulRegPage successfulRegPage;

    @BeforeClass
    public static void setup() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));

        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL(Constants.URL_GRID), options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfProperties.getProperty("registrationPage"));

        registrationPage = new RegistrationPage(driver);
        successfulRegPage = new SuccessfulRegPage(driver);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Epic("Проверка сайта \"Way2Automation\"")
    @Feature("Авторизация")
    @Story("Проверка авторизации пользователя \"userName\"")
    @Test(threadPoolSize = 3)
    public void test() {
        registrationPage.fillForm(
            ConfProperties.getProperty("userName"),
            ConfProperties.getProperty("password"),
            ConfProperties.getProperty("description")
        ).clickLoginButton();
        WebElement resultLogged = successfulRegPage.getLoggedIn();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(resultLogged).isNotNull();

        successfulRegPage.clickLogoutButton();
    }

    @AfterClass
    public static void exit() {
        driver.quit();
    }
}