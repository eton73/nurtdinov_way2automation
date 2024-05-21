package com.simbirsoft.tests;

import com.simbirsoft.BaseTest;
import com.simbirsoft.config.ConfProperties;
import com.simbirsoft.pages.RegistrationPage;
import com.simbirsoft.pages.SuccessfulRegPage;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Duration;


public class DateProviderBaseTest extends BaseTest {

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
    public void test(String name, String pass, String desc) {
        SoftAssertions softAssertions = new SoftAssertions();
        registrationPage.fillForm(
                name,
                pass,
                desc
        ).clickLoginButton();

        if (name.equals(ConfProperties.getProperty("userName")) && pass.equals(ConfProperties.getProperty("password"))) {
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
