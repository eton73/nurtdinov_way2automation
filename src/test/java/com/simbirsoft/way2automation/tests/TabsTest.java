package com.simbirsoft.way2automation.tests;

import com.simbirsoft.config.ConfHelpers;
import com.simbirsoft.way2automation.helpers.TabsHelper;
import com.simbirsoft.way2automation.pages.FirstTabsPage;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class TabsTest extends BaseTest {

    protected FirstTabsPage firstTabsPage;
    private String handleHost;

    @BeforeClass
    public void setup() throws MalformedURLException {
        super.setup();
        driver.get(ConfHelpers.getProperty("firstTabsPage"));

        firstTabsPage = new FirstTabsPage(driver);
        handleHost = driver.getWindowHandle();
    }

    @Severity(SeverityLevel.MINOR)
    @Epic("Проверка сайта Way2Automation")
    @Feature("Переключение вкладок")
    @Story("Открытие вкладок и переключение между ними")
    @Test
    public void tabsTest() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(firstTabsPage.getHeading()).isNotNull();

        driver.switchTo().frame(firstTabsPage.getFrame());
        String url2 = firstTabsPage.newBrowserTabsGetLink();
        TabsHelper.createNewTab(driver, url2);

        softAssertions.assertThat(firstTabsPage.getHeading()).isNull();

        String url3 = firstTabsPage.newBrowserTabsGetLink();
        TabsHelper.createNewTab(driver, url3);

        softAssertions.assertThat(firstTabsPage.getHeading()).isNull();

        TabsHelper.switchFromSecondTabToFirst(driver, handleHost);

        softAssertions.assertThat(firstTabsPage.getHeading()).isNotNull();

        TabsHelper.switchFromFirstPageToSecond(driver, handleHost);

        softAssertions.assertThat(firstTabsPage.getHeading()).isNull();
    }
}
