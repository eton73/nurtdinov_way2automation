package sqlTest.page;

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

    @FindBy(xpath = "/html/body/table[2]/tbody/tr/td[1]/form/table/tbody/tr[1]/td/input[2]")
    private WebElement password;

    @FindBy(xpath = "/html/body/table[2]/tbody/tr/td[1]/form/table/tbody/tr[2]/td[1]/input")
    private WebElement input;

    @FindBy(xpath = "/html/body/table[1]/tbody/tr/td[3]/a")
    private WebElement logOut;

    @FindBy(xpath = "/html/body/table[1]/tbody/tr/td[3]/b/a")
    private WebElement nickName;

    @Step("Ввести имя пользователя")
    public void setLogin(CharSequence value) {
        login.sendKeys(value);
    }

    @Step("Ввести пароль пользователя")
    public void setPassword(CharSequence value) {
        password.sendKeys(value);
    }

    @Step("Кликнуть кнопку входа")
    public void clickInputButton(){
        input.click();
    }

    @Step("Найти псевдоним")
    public WebElement getNickname() {
        return nickName;
    }

    @Step("Убрать фокус")
    public Boolean dropFocus() {
        login.sendKeys("future");
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        jse.executeScript("document.querySelector(\"body > table:nth-child(4) > tbody > tr > td:nth-child(1) > form > table > tbody > tr:nth-child(1) > td > input[type=text]:nth-child(2)\").blur()");
        return (Boolean) jse.executeScript("return document.activeElement == document.body");
    }

    @Step("Проверить наличие скролла")
    public Boolean isScrollHeight() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        WebElement container = driver.findElement(By.xpath("/html/body"));
        return (Boolean) jse.executeScript("return arguments[0].scrollHeight > arguments[0].offsetHeight;", container);
    }
}