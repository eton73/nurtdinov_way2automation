package com.simbirsoft.tests;

import com.simbirsoft.BaseTest;
import com.simbirsoft.config.ConfProperties;
import com.simbirsoft.pages.RegistrationPage;
import com.simbirsoft.pages.SuccessfulRegPage;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginPracticeSiteTest extends BaseTest {

    protected static RegistrationPage registrationPage;
    protected static SuccessfulRegPage successfulRegPage;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));

        driver = new ChromeDriver();
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
    @Test
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