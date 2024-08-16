package com.simbirsoft.way2automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AlertBoxPage {
    private final WebDriver driver;
    public AlertBoxPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = "div.internal_navi > ul > li:nth-child(2)")
    private WebElement inputAlert;

    @FindBy(css = "body > button")
    private WebElement demonstrationInputBox;

    @FindBy(css = "#example-1-tab-2 > div > iframe")
    private WebElement iFrame;

    @FindBy(css = "#demo")
    private WebElement helloText;

    @Step("Найти helloText")
    public WebElement getHelloText() {
        return helloText;
    }

    @Step("Найти iframe")
    public WebElement getIFrame(){
        return iFrame;
    }

    @Step("Получить элемент inputAlert")
    public WebElement getInputAlert() {
        return inputAlert;
    }

    @Step("Кликнуть по столбцу таблицы inputAlert")
    public void inputAlertClick() {
        getInputAlert().click();
    }

    @Step("Получить элемент inputAlert")
    public WebElement getDemonstrationInputBox() {
        return demonstrationInputBox;
    }

    @Step("Кликнуть на кнопку для получения текста")
    public void demonstrationInputBoxClick() {
        getDemonstrationInputBox().click();
    }

    @Step("Получить текст приветствия")
    public String getTextFromHelloText() {
        return getHelloText().getText();
    }
}
