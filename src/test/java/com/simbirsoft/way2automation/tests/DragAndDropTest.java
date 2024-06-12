package com.simbirsoft.way2automation.tests;

import com.simbirsoft.way2automation.helpers.ConfHelpers;
import com.simbirsoft.way2automation.pages.DroppablePage;
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
        getWebDriver().get(ConfHelpers.getProperty("dragDropPage"));

        droppablePage = new DroppablePage(getWebDriver());
    }

    @Test
    public void dragAndDropTest() throws InterruptedException {
        getWebDriver().switchTo().frame(droppablePage.getFrame());
        String firstText = droppablePage.getDropSectionText();
        WebElement target = droppablePage.getTarget();
        WebElement destination = droppablePage.getDestination();
        new Actions(getWebDriver()).dragAndDrop(target, destination).build().perform();
        String secondText = droppablePage.getDropSectionText();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(firstText.equals(secondText)).isFalse();
    }

}
