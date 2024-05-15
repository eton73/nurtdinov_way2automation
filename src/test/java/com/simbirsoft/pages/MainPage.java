package com.simbirsoft.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private static final String HEADER_CSS = "ul.elementor-icon-list-items.elementor-inline-items";
    private static final String MENU_ID = "ast-hf-menu-1";
    private static final String SERTIFICATION_CLASS_NAME = "elementor-container.elementor-column-gap-default";
    private static final String SLIDER_XPATH = "//*[@id=\"post-17\"]/div/div/section[4]/div[2]/div/div/div/div/div/div[1]/div/div[5]";
    private static final String SLIDER_BUTTON_CSS = "div.pp-slider-arrow.swiper-button-next";
    private static final String FOOTER_CLASS_NAME = "elementor-widget-container";
    private static final String MAIN_MENU_CLASS_NAME = "ast-primary-header-bar.ast-primary-header.main-header-bar.site-header-focus-item.ast-sticky-active.ast-sticky-shrunk.ast-header-sticked";
    private static final String VIDEO_XPATH = "//*[@id=\"menu-item-27597\"]";
    private static final String VIDEO_ROBOT_FRAMEWORK_XPATH = "//*[@id=\"menu-item-27603\"]/a/span[2]";

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = HEADER_CSS)
    private WebElement header;

    @FindBy(id = MENU_ID)
    private WebElement menu;

    @FindBy(className = SERTIFICATION_CLASS_NAME)
    private WebElement certificationBlock;

    @FindBy(xpath = SLIDER_XPATH)
    private WebElement slider;

    @FindBy(css = SLIDER_BUTTON_CSS)
    private WebElement sliderButton;

    @FindBy(className = FOOTER_CLASS_NAME)
    private WebElement footer;

    @FindBy(className = MAIN_MENU_CLASS_NAME)
    private WebElement mainMenu;

    @FindBy(xpath = VIDEO_XPATH)
    private WebElement videoTutorial;

    @FindBy(xpath = VIDEO_ROBOT_FRAMEWORK_XPATH)
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

}
