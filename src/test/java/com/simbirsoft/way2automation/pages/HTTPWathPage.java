package com.simbirsoft.way2automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HTTPWathPage {
    private final WebDriver driver;
    public HTTPWathPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id = "displayImage")
    private WebElement inputDisplayImage;

    @FindBy(css = "body > img")
    private WebElement imageResultLogged;

    @Step("Найти кнопку \"Display Image\"")
    public WebElement getInputDisplayImage(){
        return inputDisplayImage;
    }

    @Step("Кликнуть по кнопке \"Display Image\"")
    public void inputDisplayImageClick() {
        getInputDisplayImage().click();
    }

    @Step("Найти изображение с успешным входом")
    public WebElement getImageResultLogged() {
        return imageResultLogged;
    }
}