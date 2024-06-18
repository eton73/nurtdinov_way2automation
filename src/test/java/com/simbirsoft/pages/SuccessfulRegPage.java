package com.simbirsoft.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessfulRegPage {

    private final WebDriver driver;

    public SuccessfulRegPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "/html/body/div[1]/div/div/div/p[1]")
    private WebElement userLoggedIn;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/p[2]/a")
    private WebElement userLogoutButton;

    @Step("Найти label успешной регистрации")
    public WebElement getLoggedIn() {
        return userLoggedIn;
    }

    @Step("Найти кнопку выхода после успешной регистрации")
    public WebElement getLogoutButton() {
        return userLogoutButton;
    }
    @Step("Нажать на кнопку выхода")
    public void clickLogoutButton() {
        getLogoutButton().click();
    }
}