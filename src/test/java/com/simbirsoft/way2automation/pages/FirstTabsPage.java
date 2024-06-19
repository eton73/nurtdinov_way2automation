package com.simbirsoft.way2automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FirstTabsPage {
    private final WebDriver driver;

    public FirstTabsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(className = "demo-frame")
    private WebElement frame;

    @FindBy(xpath = "/html/body/div")
    private WebElement newBrowserTabs;

    @Step("Найти элемент frame")
    public WebElement getFrame() {
        return frame;
    }

    @Step("Получить ссылку для открытия нового окна")
    public String newBrowserTabsGetLink() {
        return newBrowserTabs.findElement(By.cssSelector("body > div > p > a")).getAttribute("href");
    }
}
