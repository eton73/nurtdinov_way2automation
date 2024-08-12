package com.simbirsoft.way2automation.tests;

import com.simbirsoft.config.ConfHelpers;
import com.simbirsoft.way2automation.pages.DroppablePage;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class DragAndDropTest extends BaseTest {
    protected static DroppablePage droppablePage;

    @BeforeClass
    public void setup() throws MalformedURLException {
        super.setup();
        driver.get(ConfHelpers.getProperty("dragDropPage"));

        droppablePage = new DroppablePage(driver);
    }

    @Severity(SeverityLevel.MINOR)
    @Epic("Проверка сайта Way2Automation")
    @Feature("Drag and Drop")
    @Story("Проверка перетаскивания элемента")
    @Test
    public void dragAndDropTest() {
        driver.switchTo().frame(droppablePage.getIframe());
        String firstText = droppablePage.getDropSectionText();
        WebElement target = droppablePage.getTarget();
        WebElement destination = droppablePage.getDestination();
        new Actions(driver).dragAndDrop(target, destination).build().perform();
        String secondText = droppablePage.getDropSectionText();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(firstText.equals(secondText)).isFalse();
    }

}