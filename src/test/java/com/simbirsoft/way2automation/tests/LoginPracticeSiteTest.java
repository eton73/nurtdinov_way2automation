package com.simbirsoft.way2automation.tests;

import com.simbirsoft.way2automation.helpers.ConfHelper;
import com.simbirsoft.way2automation.pages.RegistrationPage;
import com.simbirsoft.way2automation.pages.SuccessfulRegistrationPage;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class LoginPracticeSiteTest extends BaseTest {

    private RegistrationPage registrationPage;
    private SuccessfulRegistrationPage successfulRegPage;

    @BeforeClass
    public void setup() throws MalformedURLException {
        super.setup();
        driver.get(ConfHelper.getProperty("registrationPage"));

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
            ConfHelper.getProperty("userName"),
            ConfHelper.getProperty("password"),
            ConfHelper.getProperty("description")
        ).clickLoginButton();
        WebElement resultLogged = successfulRegPage.getLoggedIn();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(resultLogged).isNotNull();

        successfulRegPage.clickLogoutButton();
    }
}