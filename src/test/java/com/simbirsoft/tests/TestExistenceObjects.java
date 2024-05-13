package com.simbirsoft.tests;

import com.simbirsoft.TestApplication;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class TestExistenceObjects extends TestApplication {

    @Severity(SeverityLevel.MINOR)
    @Epic("Проверка сайта Way2Automation")
    @Feature("Наличие элементов")
    @Story("Проверка наличия элементов")
    @Test
    public void testElements() {
        WebElement resultHeader = mainPage.getHeader();
        WebElement resultMenu = mainPage.getMenu();
        WebElement resultSertif = mainPage.getSertification();
        WebElement resultSlider = mainPage.getSlider();
        WebElement slBut = mainPage.getSlBut();
        WebElement footer = mainPage.getFooter();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(resultHeader).isNotNull();
        softAssertions.assertThat(resultMenu).isNotNull();
        softAssertions.assertThat(resultSertif).isNotNull();
        softAssertions.assertThat(resultSlider).isNotNull();

        slBut.click();
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
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,10000)");

        WebElement mainMenu = mainPage.getMainMenu();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(mainMenu).isNotNull();
    }

    @Severity(SeverityLevel.NORMAL)
    @Epic("Проверка сайта Way2Automation")
    @Feature("Переход на другую страницу")
    @Story("Переход на страницу \"Robot Framework\"")
    @Test
    public void testSwOver(){
        WebElement videoTutorial = mainPage.getVideoMenu();
        WebElement robotFramework = mainPage.getVideoRobotFramework();

        Actions action = new Actions(driver);
        action.moveToElement(videoTutorial).perform();

        robotFramework.click();
    }

}
