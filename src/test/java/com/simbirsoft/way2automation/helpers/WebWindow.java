package com.simbirsoft.way2automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.util.Set;

public class WebWindow {

    private static int INSTANCE_COUNT = 0;

    private final WebDriver driver;
    private final String name;
    private String handle;
    private String parentHandle;

    public WebWindow(WebDriver parent, String url) {
        this.driver = parent;
        parentHandle = parent.getWindowHandle();
        name = createUniqueName();
        handle = createWindow(url);
        switchToWindow().get(url);
    }

    private static String createUniqueName() {
        return "a_Web_Window_" + INSTANCE_COUNT++;
    }

    public WebDriver switchToWindow() {
        checkForClosed();
        return driver.switchTo().window(handle);
    }

    private String createWindow(String url) {
        Set<String> oldHandles = driver.getWindowHandles();
        parentHandle = driver.getWindowHandle();

        ((JavascriptExecutor) driver).executeScript(injectAnchorTag(name, url));

        driver.findElement(By.id(name)).click();
        handle = getNewHandle(oldHandles);
        return handle;
    }

    private String getNewHandle(Set<String> oldHandles) {
        Set<String> newHandles = driver.getWindowHandles();
        newHandles.removeAll(oldHandles);
        //Найти новое окно
        for (String handle: newHandles) {
            return handle;
        }
        return null;
    }

    private void checkForClosed() {
        if (handle == null || handle.equals("")) {
            throw new WebDriverException("Web Window closed or not initialized");
        }
    }

    private String injectAnchorTag(String id, String url) {
        return String.format("var anchorTag = document.createElement('a'); " +
                        "anchorTag.appendChild(document.createTextNode('nwh'));" +
                        "anchorTag.setAttribute('id', '%s');" +
                        "anchorTag.setAttribute('href', '%s');" +
                        "anchorTag.setAttribute('target', '_blank');" +
                        "anchorTag.setAttribute('style', 'display:block;');" +
                        "document.getElementsByTagName('body')[0].appendChild(anchorTag);",
                id, url
        );
    }
}
