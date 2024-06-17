package com.simbirsoft.way2automation.tests;

import com.simbirsoft.way2automation.helpers.ConfHelper;
import com.simbirsoft.way2automation.pages.MainPage;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class ExistenceObjectsTest extends BaseTest {
    protected MainPage mainPage;

    @BeforeClass
    public void setup() throws MalformedURLException {
        super.setup();
        getWebDriver().get(ConfHelper.getProperty("startPage"));

        mainPage = new MainPage(getWebDriver());
    }

    @Severity(SeverityLevel.MINOR)
    @Epic("Проверка сайта Way2Automation")
    @Feature("Наличие элементов")
    @Story("Проверка наличия элементов")
    @Test
    public void testElements() {
        WebElement resultHeader = mainPage.getHeader();
        WebElement resultMenu = mainPage.getMenu();
        WebElement resultCertification = mainPage.getCertification();
        WebElement resultSlider = mainPage.getSlider();
        WebElement footer = mainPage.getFooter();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(resultHeader).isNotNull();
        softAssertions.assertThat(resultMenu).isNotNull();
        softAssertions.assertThat(resultCertification).isNotNull();
        softAssertions.assertThat(resultSlider).isNotNull();

        mainPage.sliderButtonCLick();
        WebElement resultSlider1 = mainPage.getSlider();
        softAssertions.assertThat(resultSlider1).isNull();
        softAssertions.assertThat(footer).isNotNull();
    }

    @Severity(SeverityLevel.MINOR)
    @Epic("Проверка сайта Way2Automation")
    @Feature("Основное меню")
    @Story("Наличие основного меню при скроллинге")
    @Test
    public void testScroll() {
        mainPage.scrollBy();

        WebElement mainMenu = mainPage.getMainMenu();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(mainMenu).isNotNull();
    }

    @Severity(SeverityLevel.NORMAL)
    @Epic("Проверка сайта Way2Automation")
    @Feature("Переход на другую страницу")
    @Story("Переход на страницу \"Robot Framework\"")
    @Test
    public void testChangePage(){
        WebElement videoTutorial = mainPage.getVideoMenu();
        WebElement robotFramework = mainPage.getVideoRobotFramework();

        Actions action = new Actions(getWebDriver());
        action.moveToElement(videoTutorial).perform();

        robotFramework.click();

        getWebDriver().get(ConfHelper.getProperty("startPage"));
    }
}