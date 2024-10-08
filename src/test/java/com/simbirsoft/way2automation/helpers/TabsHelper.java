package com.simbirsoft.way2automation.helpers;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class TabsHelper {

    private static final Logger logger = LoggerFactory.getLogger(TabsHelper.class);

    public static void createNewTab(WebDriver driver, String url)  {
        try {
            new WebWindow(driver, url);
        } catch (Exception e) {
            logger.error("Не может загрузиться вторая страница, e - {}", e.getMessage());
        }
    }

    public static void switchFromSecondTabToFirst(WebDriver driver, String handleHost) {
        try {
            driver.switchTo().window(handleHost);
            driver.switchTo().activeElement();
        } catch (Exception e) {
            logger.error("Не можем вернуться назад на первую страницу, e - {}", e.getMessage());
        }
    }

    public static void switchFromFirstPageToSecond(WebDriver driver, String handleHost) {
        try {
            for (String handle: driver.getWindowHandles()) {
                if (!Objects.equals(handle, handleHost)) {
                    driver.switchTo().window(handle);
                    driver.switchTo().activeElement();
                }
            }
        } catch (Exception e) {
            logger.error("Не можем вернуться на вторую страницу, e - {}", e.getMessage());
        }
    }
}
