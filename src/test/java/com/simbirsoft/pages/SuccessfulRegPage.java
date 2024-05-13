package com.simbirsoft.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SuccessfulRegPage {

    private static final String USER_LOGGED_IN = "/html/body/div[1]/div/div/div/p[1]";

    private final WebDriver driver;

    public SuccessfulRegPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Step("Найти label успешной регистрации")
    public WebElement loggedIn() {
        return driver.findElement(By.xpath(USER_LOGGED_IN));
    }

}
