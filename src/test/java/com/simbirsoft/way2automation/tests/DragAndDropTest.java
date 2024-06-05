package com.simbirsoft.way2automation.tests;

import com.simbirsoft.way2automation.config.ConfProperties;
import com.simbirsoft.way2automation.helpers.Constants;
import com.simbirsoft.way2automation.pages.DroppablePage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DragAndDropTest extends BaseTest {
    protected static DroppablePage droppablePage;

    @BeforeClass
    public static void setup() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));

        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL(Constants.URL_GRID), options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfProperties.getProperty("dragDropPage"));

        droppablePage = new DroppablePage(driver);
    }

    @Test
    public void dragAndDropTest() throws InterruptedException {
        driver.switchTo().frame(droppablePage.getFrame());
        String firstText = droppablePage.getDropSectionText();
        WebElement target = droppablePage.getTarget();
        WebElement destination = droppablePage.getDestination();
        new Actions(driver).dragAndDrop(target, destination).build().perform();
        String secondText = droppablePage.getDropSectionText();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(firstText.equals(secondText)).isFalse();
    }

}
