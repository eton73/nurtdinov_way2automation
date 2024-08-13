package com.simbirsoft.way2automation.tests;

import com.simbirsoft.config.ConfHelpers;
import com.simbirsoft.way2automation.helpers.TabsHelper;
import com.simbirsoft.way2automation.pages.FirstTabsPage;
import io.qameta.allure.*;
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
        driver.switchTo().frame(firstTabsPage.getFrame());
        String url2 = firstTabsPage.newBrowserTabsGetLink();
        TabsHelper.createNewTab(driver, url2);
        String url3 = firstTabsPage.newBrowserTabsGetLink();
        TabsHelper.createNewTab(driver, url3);
        TabsHelper.switchFromSecondTabToFirst(driver, handleHost);
        TabsHelper.switchFromFirstPageToSecond(driver, handleHost);
    }
}
