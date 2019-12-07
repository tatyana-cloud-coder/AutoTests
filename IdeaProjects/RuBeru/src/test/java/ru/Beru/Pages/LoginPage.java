package ru.Beru.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
    private WebElement FieldForLogin;
    private WebDriverWait wait;
    private String login = "bala5howatatwork@yandex.ru";

    public LoginPage (WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10000);
    }
    public void fillLogin() {
        FieldForLogin = driver.findElement(By.id("passp-field-login"));
        FieldForLogin.sendKeys(login);
        FieldForLogin.submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passp-field-passwd")));
    }
}
