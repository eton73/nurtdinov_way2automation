package com.simbirsoft.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

    private static final String USER_NAME_ID = "username";
    private static final String PASSWORD_ID = "password";
    private static final String USER_NAME_DESCRIPTION_ID = "formly_1_input_username_0";
    private static final String LOGIN_BUTTON_CSS = "button.btn.btn-danger";

    private final WebDriver driver;
    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id = USER_NAME_ID)
    private WebElement userName;

    @FindBy(id = PASSWORD_ID)
    private WebElement password;

    @FindBy(id = USER_NAME_DESCRIPTION_ID)
    private WebElement userNameDescription;

    @FindBy(css = LOGIN_BUTTON_CSS)
    private WebElement loginButton;

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
        return userNameDescription;
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

    @Step("Ввести форму ввода")
    public RegistrationPage fillForm(String username, String password, String description) {
        setUserName(username);
        setPassword(password);
        setUserNameDescription(description);
        return this;
    }

    @Step("Кликнуть кнопку входа")
    public void clickLoginButton(){
        getLoginButton().click();
    }

}
