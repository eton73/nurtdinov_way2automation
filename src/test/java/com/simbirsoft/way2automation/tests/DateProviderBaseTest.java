package com.simbirsoft.way2automation.tests;

import com.simbirsoft.config.ConfHelpers;
import com.simbirsoft.way2automation.pages.RegistrationPage;
import com.simbirsoft.way2automation.pages.SuccessfulRegistrationPage;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;


public class DateProviderBaseTest extends BaseTest {

    private RegistrationPage registrationPage;
    private SuccessfulRegistrationPage successfulRegistrationPage;

    @BeforeClass
    public void setup() throws MalformedURLException {
        super.setup();
        driver.get(ConfHelpers.getProperty("registrationPage"));

        registrationPage = new RegistrationPage(driver);
        successfulRegistrationPage = new SuccessfulRegistrationPage(driver);
    }

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][] { { "angular", "password", "description"},
                { "UsernameWrong_1", "password", "Another_1" },
                {"angular", "passwordWrong_2", "Another_2"},
                {"UsernameWrong_3", "passwordWrong_3", "Another_3"}};
    }

    @Severity(SeverityLevel.CRITICAL)
    @Epic("Проверка сайта \"Way2Automation\"")
    @Feature("Авторизация")
    @Story("Проверка авторизации пользователя \"userName\"")
    @Test(dataProvider = "data-provider")
    public void test(String name, String password, String description) {
        SoftAssertions softAssertions = new SoftAssertions();
        registrationPage.fillForm(
                name,
                password,
                description
        ).clickLoginButton();

        if (name.equals(ConfHelpers.getProperty("userName")) && password.equals(ConfHelpers.getProperty("password"))) {
            WebElement resultLogged = successfulRegistrationPage.getLoggedIn();

            softAssertions.assertThat(resultLogged).isNotNull();

            successfulRegistrationPage.clickLogoutButton();
        } else {
            WebElement incorrectLoginOrPassword = registrationPage.getIncorrectLoginOrPassword();
            softAssertions.assertThat(incorrectLoginOrPassword).isNotNull();
        }
    }

    @DataProvider(name = "failure-data-provider")
    public Object[][] dataProviderFailureMethod() {
        return new Object[][] { { "", "", "description"},
                { "angular", "password", "" }};
    }

    @Severity(SeverityLevel.CRITICAL)
    @Epic("Проверка сайта \"Way2Automation\"")
    @Feature("Авторизация")
    @Story("Проверка авторизации пользователя без ввода данных")
    @Test(dataProvider = "failure-data-provider")
    public void testFailed(String name, String password, String description) {
        SoftAssertions softAssertions = new SoftAssertions();
        registrationPage.fillForm(name, password, description).clickLoginButton();

        WebElement resultLogged = successfulRegistrationPage.getLoggedIn();
        softAssertions.assertThat(resultLogged).isNotNull();
        successfulRegistrationPage.clickLogoutButton();
    }
}