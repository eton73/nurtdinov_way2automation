package com.simbirsoft.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

    private static final String USER_NAME_ID = "username";
    private static final String PASSWORD_ID = "password";
    private static final String USER_NAME_DESCRIPTION_CSS =
            "input.form-control.ng-pristine.ng-untouched.ng-invalid.ng-invalid-required.ng-valid-maxlength.ng-valid-minlength";
    private static final String USER_NAME_DESCRIPTION_WRONG_CSS =
            "input.form-control.ng-valid-maxlength.ng-dirty.ng-valid-parse.ng-valid-required.ng-valid.ng-valid-minlength.ng-touched";
    private static final String LOGIN_BUTTON_CSS = "button.btn.btn-danger";
    private static final String INCORRECT_USERNAME_OR_PASSWORD_XPATH = "/html/body/div[1]/div/div/div/div[2]";

    private final WebDriver driver;
    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id = USER_NAME_ID)
    private WebElement userName;

    @FindBy(id = PASSWORD_ID)
    private WebElement password;

    @FindBy(css = LOGIN_BUTTON_CSS)
    private WebElement loginButton;

    @FindBy(xpath = INCORRECT_USERNAME_OR_PASSWORD_XPATH)
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
    public void setUserName(CharSequence value) {
        getUserName().sendKeys(value);
    }

    @Step("Ввести пароль пользователя")
    public void setPassword(CharSequence value) {
        getPassword().sendKeys(value);
    }

    @Step("Ввести описание имени пользователя")
    public void setUserNameDescription(CharSequence value) {
        getUserNameDescription().sendKeys(value);
    }

    @Step("Ввести форму ввода")
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
