package com.simbirsoft.way2automation.tests;

import com.simbirsoft.config.ConfHelpers;
import com.simbirsoft.way2automation.pages.RegistrationPage;
import com.simbirsoft.way2automation.pages.SuccessfulRegistrationPage;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class LoginPracticeSiteTest extends BaseTest {

    protected RegistrationPage registrationPage;
    protected SuccessfulRegistrationPage successfulRegPage;

    @BeforeClass
    public void setup() throws MalformedURLException {
        super.setup();
        driver.get(ConfHelpers.getProperty("registrationPage"));

        registrationPage = new RegistrationPage(driver);
        successfulRegPage = new SuccessfulRegistrationPage(driver);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Epic("Проверка сайта \"Way2Automation\"")
    @Feature("Авторизация")
    @Story("Проверка авторизации пользователя \"userName\"")
    @Test
    public void test() {
        registrationPage.fillForm(
                ConfHelpers.getProperty("userName"),
                ConfHelpers.getProperty("password"),
                ConfHelpers.getProperty("description")
        ).clickLoginButton();
        WebElement resultLogged = successfulRegPage.getLoggedIn();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(resultLogged).isNotNull();

        successfulRegPage.clickLogoutButton();
    }
}