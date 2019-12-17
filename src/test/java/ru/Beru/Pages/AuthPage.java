package ru.Beru.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage {
    WebDriver driver;
    private WebDriverWait wait;

    public AuthPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    @FindBy(id = "passp-field-login")
    private WebElement fieldLogin;

    @FindBy(id = "passp-field-passwd")
    private WebElement fieldPassword;

    @FindBy(css = "[class=\"passp-button passp-sign-in-button\"]")
    private WebElement buttonEnter;

    @Step("Открыть страницу")
    public void open() {
        driver.get("https://passport.yandex.ru/auth?origin=beru_sber&retpath=https%3A%2F%2Fberu.ru%2Fauth-redir%3Fretpath%3D%252F%253Floggedin%253D1");
        wait.until(ExpectedConditions.visibilityOf(fieldLogin));
    }

    By locatorHomepage = By.className("_1r1GkezLi0");

    @Step("Ввод логина")
    public void fillLogin(String login) {
        fieldLogin.sendKeys(login);
        buttonEnter.click();
        wait.until(ExpectedConditions.visibilityOf(fieldPassword));
    }

    @Step("Ввод пароля")
    public void fillPassword(String password) {
        fieldPassword.sendKeys(password);
        buttonEnter.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorHomepage));
    }
}
