package com.simbirsoft.way2automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

    private static final String USER_NAME_DESCRIPTION_CSS =
            "input.form-control.ng-pristine.ng-untouched.ng-invalid";
    private static final String USER_NAME_DESCRIPTION_WRONG_CSS =
            "input.form-control.ng-valid-maxlength.ng-dirty.ng-valid-parse";

    private final WebDriver driver;
    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id = "username")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(css = "button.btn.btn-danger")
    private WebElement loginButton;

    @FindBy(className = "alert.alert-danger")
    private WebElement incorrectLoginOrPassword;

    @Step("Найти строку \"UserName\"")
    public WebElement getUserName() {
        return userName;
    }

    @Step("Найти строку \"Password\"")
    public WebElement getPassword() {
        return password;
    }

    @Step("Найти строку \"UserNameDescription\"")
    public WebElement getUserNameDescription() {
        try {
            return driver.findElement(By.cssSelector(USER_NAME_DESCRIPTION_CSS));
        } catch (NoSuchElementException exc) {
            return driver.findElement(By.cssSelector(USER_NAME_DESCRIPTION_WRONG_CSS));
        }
    }

    @Step("Найти кнопку входа")
    public WebElement getLoginButton() {
        return loginButton;
    }

    @Step("Ввести имя пользователя")
    public void setUserName(String value) {
        getUserName().sendKeys(value);
    }

    @Step("Ввести пароль пользователя")
    public void setPassword(String value) {
        getPassword().sendKeys(value);
    }

    @Step("Ввести описание имени пользователя")
    public void setUserNameDescription(String value) {
        getUserNameDescription().sendKeys(value);
    }

    @Step("Заполнить форму")
    public RegistrationPage fillForm(String username, String password, String description) {
        setUserName(Keys.chord(Keys.CONTROL, "a"));
        setUserName(username);
        setPassword(Keys.chord(Keys.CONTROL, "a"));
        setPassword(password);
        setUserNameDescription(Keys.chord(Keys.CONTROL, "a"));
        setUserNameDescription(description);
        return this;
    }

    @Step("Кликнуть кнопку входа")
    public void clickLoginButton(){
        getLoginButton().click();
    }

    @Step("Найти строку некорректного логина или пароля")
    public WebElement getIncorrectLoginOrPassword() {
        return incorrectLoginOrPassword;
    }
}