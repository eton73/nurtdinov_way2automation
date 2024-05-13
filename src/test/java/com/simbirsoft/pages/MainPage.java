package com.simbirsoft.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private static final String HEADER_XPATH = "//*[@id=\"ast-desktop-header\"]/div[1]/div/div/div/div[1]/div";
    private static final String MENU_XPATH = "//*[@id=\"ast-hf-menu-1\"]";
    private static final String SERTIFICATION_XPATH = "//*[@id=\"post-17\"]/div/div/section[2]/div/div/div/section/div";
    private static final String SLIDER_XPATH = "//*[@id=\"post-17\"]/div/div/section[4]/div[2]/div/div/div/div/div/div[1]/div/div[5]";
    private static final String SL_BUT_XPATH = "//*[@id=\"post-17\"]/div/div/section[4]/div[2]/div/div/div/div/div/div[3]";
    private static final String FOOTER_XPATH = "//*[@id=\"page\"]/div[2]/div/section/div[2]/div/div/section[2]/div/div/div/div/div/h2";
    private static final String MAIN_MN_XPATH = "//*[@id=\"ast-desktop-header\"]/div[2]/div/div";
    private static final String VIDEO_XPATH = "//*[@id=\"menu-item-27597\"]";
    private static final String VIDEO_ROBOT_FRAMEWORK_XPATH = "//*[@id=\"menu-item-27603\"]/a/span[2]";

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Step("Найти хидер с реквизитами для связи")
    public WebElement getHeader() {
        return driver.findElement(By.xpath(HEADER_XPATH));
    }

    @Step("Найти горизонтальное меню")
    public WebElement getMenu() {
        return driver.findElement(By.xpath(MENU_XPATH));
    }

    @Step("Найти блок с сертификацией")
    public WebElement getSertification() {
        return driver.findElement(By.xpath(SERTIFICATION_XPATH));
    }

    @Step("Найти блок с курсами")
    public WebElement getSlider() {
        return driver.findElement(By.xpath(SLIDER_XPATH));
    }

    @Step("Кликнуть по кнопке блока с курсами")
    public WebElement getSlBut() {
        return driver.findElement(By.xpath(SL_BUT_XPATH));
    }

    @Step("Найти футер")
    public WebElement getFooter() {
        return driver.findElement(By.xpath(FOOTER_XPATH));
    }

    @Step("Найти главное меню")
    public WebElement getMainMenu(){
        return driver.findElement(By.xpath(MAIN_MN_XPATH));

    }

    @Step("Найти \"Video Tutorial\" в главном меню")
    public WebElement getVideoMenu() {
        return driver.findElement(By.xpath(VIDEO_XPATH));
    }

    @Step("Нажать кнопку \"Robot Framework\" в всплыающем окне")
    public WebElement getVideoRobotFramework() {
        return driver.findElement(By.xpath(VIDEO_ROBOT_FRAMEWORK_XPATH));
    }

}
