package com.simbirsoft.way2automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DroppablePage {

    private final WebDriver driver;

    public DroppablePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id=\"example-1-tab-1\"]/div/iframe")
    private WebElement frame;

    @FindBy(xpath = "//*[@id=\"draggable\"]")
    private WebElement target;

    @FindBy(xpath = "//*[@id=\"droppable\"]")
    private WebElement destination;

    @FindBy(css = "#droppable > p")
    private WebElement dropSection;

    @Step("Найти элемент frame")
    public WebElement getFrame() {
        return frame;
    }

    @Step("Найти элемент target")
    public WebElement getTarget() {
        return target;
    }

    @Step("Найти элемент destination")
    public WebElement getDestination() {
        return destination;
    }

    @Step("Найти элемент drop секции")
    public WebElement getDropSection() {
        return dropSection;
    }

    @Step("Найти текст drop секции")
    public String getDropSectionText() {
        return dropSection.getText();
    }
 }
