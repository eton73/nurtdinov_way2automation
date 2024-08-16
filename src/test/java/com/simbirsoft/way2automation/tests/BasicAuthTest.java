package com.simbirsoft.way2automation.tests;

import com.simbirsoft.config.ConfHelpers;
import com.simbirsoft.way2automation.pages.HTTPWathPage;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/**
 * Выполнение U13
 */
public class BasicAuthTest extends BaseTest {
    private HTTPWathPage httpWathPage;

    @BeforeClass
    public void setup() throws MalformedURLException {
        super.setup();
        driver.get(ConfHelpers.getProperty("wathPage"));

        httpWathPage = new HTTPWathPage(driver);
    }

    @Severity(SeverityLevel.MINOR)
    @Epic("BaseAuth")
    @Feature("Проверка авторизации")
    @Story("Прохождение авторизации по login и password")
    @Test
    public void basicAuthTest() {
        httpWathPage.inputDisplayImageClick();
        String authUrl = ConfHelpers.getProperty("wathRegistrationPage");
        String url = authUrl.replaceAll("https://", "");
        String username = ConfHelpers.getProperty("wathLogin");
        String password = ConfHelpers.getProperty("wathPassword");
        String newURL = "https://" + username  + ":" + password + "@" + url;
        driver.get(newURL);
        WebElement imageResultLogged = httpWathPage.getImageResultLogged();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(imageResultLogged).isNotNull();
    }
}