package com.simbirsoft.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = "ul.elementor-icon-list-items.elementor-inline-items")
    private WebElement header;

    @FindBy(id = "ast-hf-menu-1")
    private WebElement menu;

    @FindBy(className = "elementor-container.elementor-column-gap-default")
    private WebElement certificationBlock;

    @FindBy(xpath = "//*[@id=\"post-17\"]/div/div/section[4]/div[2]/div/div/div/div/div/div[1]/div/div[5]")
    private WebElement slider;

    @FindBy(css = "div.pp-slider-arrow.swiper-button-next")
    private WebElement sliderButton;

    @FindBy(className = "elementor-widget-container")
    private WebElement footer;

    @FindBy(className = "ast-primary-header-bar.ast-primary-header.main-header-bar.site-header-focus-item.ast-sticky-active.ast-sticky-shrunk.ast-header-sticked")
    private WebElement mainMenu;

    @FindBy(xpath = "//*[@id=\"menu-item-27597\"]")
    private WebElement videoTutorial;

    @FindBy(xpath = "//*[@id=\"menu-item-27603\"]/a/span[2]")
    private WebElement robotFramework;

    @Step("Найти хидер с реквизитами для связи")
    public WebElement getHeader() {
        return header;
    }

    @Step("Найти горизонтальное меню")
    public WebElement getMenu() {
        return menu;
    }

    @Step("Найти блок с сертификацией")
    public WebElement getCertification() {
        return certificationBlock;
    }

    @Step("Найти блок с курсами")
    public WebElement getSlider() {
        return slider;
    }

    @Step("Найти кнопку блока с курсами")
    public WebElement getSliderButton() {
        return sliderButton;
    }

    @Step("Кликнуть по кнопке блока с курсами")
    public void sliderButtonCLick() {
        getSliderButton().click();
    }

    @Step("Найти футер")
    public WebElement getFooter() {
        return footer;
    }

    @Step("Найти главное меню")
    public WebElement getMainMenu(){
        return mainMenu;
    }

    @Step("Найти \"Video Tutorial\" в главном меню")
    public WebElement getVideoMenu() {
        return videoTutorial;
    }

    @Step("Нажать кнопку \"Robot Framework\" в всплывающем окне")
    public WebElement getVideoRobotFramework() {
        return robotFramework;
    }

    @Step("Скроллить страницу вниз")
    public void scrollBy() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,10000)");
    }
}