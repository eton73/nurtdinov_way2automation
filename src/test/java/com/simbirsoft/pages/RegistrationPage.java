package com.simbirsoft.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

    private static final String USER_NAME_XPATH = "//*[@id=\"username\"]";
    private static final String PASSWORD_XPATH = "//*[@id=\"password\"]";
    private static final String USER_NAME_DESCRIPTION_XPATH = "//*[@id=\"formly_1_input_username_0\"]";
    private static final String LOGIN_BUTTON_XPATH = "/html/body/div[1]/div/div/div/form/div[3]/button";

    private final WebDriver driver;
    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Step("Найти строку \"UserName\"")
    public WebElement userName() {
        return driver.findElement(By.xpath(USER_NAME_XPATH));
    }

    @Step("Найти строку \"Password\"")
    public WebElement password() {
        return driver.findElement(By.xpath(PASSWORD_XPATH));
    }

    @Step("Найти строку \"UserNameDescription\"")
    public WebElement userNameDescription() {
        return driver.findElement(By.xpath(USER_NAME_DESCRIPTION_XPATH));
    }

    @Step("Найти кнопку входа")
    public WebElement loginButton() {return driver.findElement(By.xpath(LOGIN_BUTTON_XPATH));
    }

    @Step("Ввести имя пользователя")
    public void setUserName(String value) {
        userName().sendKeys(value);
    }

    @Step("Ввести пароль пользователя")
    public void setPassword(String value) {
        password().sendKeys(value);
    }

    @Step("Ввести описание имени пользователя")
    public void setUserNameDescription(String value) {
        userNameDescription().sendKeys(value);
    }

    @Step("Кликнуть кнопку входа")
    public void clickLoginButton(){
        loginButton().click();
    }

}
