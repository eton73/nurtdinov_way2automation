package com.simbirsoft.way2automation.tests;

import com.simbirsoft.way2automation.pages.RegistrationPage;
import com.simbirsoft.way2automation.pages.SuccessfulRegPage;
import com.simbirsoft.way2automation.config.ConfProperties;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class DateProviderBaseTest extends BaseTest {

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

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][] { { "angular", "password", "descr"},
                { "UsernameWrong_1", "password", "descrAnother_1" },
                {"angular", "passwordWrong_2", "descrAnother_2"},
                {"UsernameWrong_3", "passwordWrong_3", "descrAnother_3"}};
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

        if (name.equals(ConfProperties.getProperty("userName")) && password.equals(ConfProperties.getProperty("password"))) {
            WebElement resultLogged = successfulRegPage.getLoggedIn();

            softAssertions.assertThat(resultLogged).isNotNull();

            successfulRegPage.clickLogoutButton();
        } else {
            WebElement incorrectLoginOrPassword = registrationPage.getIncorrectLoginOrPassword();
            softAssertions.assertThat(incorrectLoginOrPassword).isNotNull();
        }
    }

    @DataProvider(name = "failure-data-provider")
    public Object[][] dataProviderFailureMethod() {
        return new Object[][] { { "", "", "descr"},
                { "angular", "password", "" }};
    }

    @Severity(SeverityLevel.CRITICAL)
    @Epic("Проверка сайта \"Way2Automation\"")
    @Feature("Авторизация")
    @Story("Проверка авторизации пользователя без ввода данных")
    @Test(dataProvider = "failure-data-provider")
    public void testFailed(String name, String pass, String desc) {
        SoftAssertions softAssertions = new SoftAssertions();
        registrationPage.fillForm(name, pass, desc).clickLoginButton();

        WebElement resultLogged = successfulRegPage.getLoggedIn();
        softAssertions.assertThat(resultLogged).isNotNull();
        successfulRegPage.clickLogoutButton();
    }

    @AfterClass
    public static void exit() {
        driver.quit();
    }
}
