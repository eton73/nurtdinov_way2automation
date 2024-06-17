package com.simbirsoft.way2automation.helpers;

import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class TabsHelper {
    public static void createNewTab(WebDriver driver, String url)  {
        try {
            new WebWindow(driver, url);
        } catch (Exception e) {
            System.err.println("Не может загрузиться вторая страница");
        }
    }

    public static void switchFromSecondTabToFirst(WebDriver driver, String handleHost) {
        try {
            driver.switchTo().window(handleHost);
            driver.switchTo().activeElement();
        } catch (Exception e) {
            System.err.println("Не можем вернуться назад на первую страницу");
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
            System.err.println("Не можем вернуться на вторую страницу");
        }
    }
}
