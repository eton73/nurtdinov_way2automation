package com.simbirsoft.way2automation.tests;

import com.simbirsoft.config.ConfHelpers;
import com.simbirsoft.way2automation.pages.AlertBoxPage;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.Alert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/**
 * Выполнение U12
 */
public class AlertTest extends BaseTest {
    private static final String HELLO_TEXT = "Ivan";
    private AlertBoxPage alertBoxPage;

    @BeforeClass
    public void setup() throws MalformedURLException {
        super.setup();
        driver.get(ConfHelpers.getProperty("alertPage"));

        alertBoxPage = new AlertBoxPage(driver);
    }

    @Severity(SeverityLevel.MINOR)
    @Epic("Проверка Alert")
    @Feature("Текст окна Alert")
    @Story("Ввод и вывод кастомного текста в окно Alert")
    @Test
    public void alertTest() {
        alertBoxPage.inputAlertClick();
        driver.switchTo().frame(alertBoxPage.getIFrame());
        alertBoxPage.demonstrationInputBoxClick();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(HELLO_TEXT);
        alert.accept();
        String helloText = alertBoxPage.getTextFromHelloText();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(helloText.contains(HELLO_TEXT)).isTrue();
    }

}