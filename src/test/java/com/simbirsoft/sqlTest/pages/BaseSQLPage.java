package com.simbirsoft.sqlTest.pages;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BaseSQLPage {

    private final WebDriver driver;

    public BaseSQLPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(name = "login")
    private WebElement login;

    @FindBy(name = "psw")
    private WebElement password;

    @FindBy(name = "subm1")
    private WebElement input;

    @FindBy(css = "a[href='/logout.php']")
    private WebElement logout;

    @FindBy(className = "none")
    private WebElement nickName;

    @And("Ввести в поле \"Логин\" свой логин")
    @Step("Ввести имя пользователя")
    public void setLogin(String value) {
        login.sendKeys(value);
    }

    @And("Ввести в поле \"Пароль\" свой пароль")
    @Step("Ввести пароль пользователя")
    public void setPassword(String value) {
        password.sendKeys(value);
    }

    @And("Нажать кнопку входа \"Вход\"")
    @Step("Кликнуть кнопку входа")
    public void clickInputButton(){
        input.click();
    }

    @Then("Фиксируем, что Nick Name найден")
    @Step("Найти псевдоним")
    public WebElement getNickname() {
        return nickName;
    }

    @Step("Кликнуть кнопку выхода")
    public void clickExitButton() {
        logout.click();
    }

    @Step("Убрать фокус")
    public Boolean dropFocus() {

        login.sendKeys("future");
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        jse.executeScript("document.querySelector(\"[name=\\\"login\\\"]\").blur()");
        return (Boolean) jse.executeScript("return document.activeElement == document.body");
    }

    @Step("Проверить наличие скролла")
    public Boolean isScrollHeight() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        WebElement container = driver.findElement(By.xpath("/html/body"));
        return (Boolean) jse.executeScript("return document.documentElement.scrollHeight " +
                "> document.documentElement.clientHeight;", container);
    }
}