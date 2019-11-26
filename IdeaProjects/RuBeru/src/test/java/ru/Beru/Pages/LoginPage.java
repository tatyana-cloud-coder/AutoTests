package ru.Beru.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
    private WebElement FieldForLogin;
    private String login = "bala5howatatwork@yandex.ru";
    public LoginPage (WebDriver driver) {
        this.driver = driver;
    }
    public void fillLogin() {
        FieldForLogin = driver.findElement(By.id("passp-field-login"));
        FieldForLogin.sendKeys(login);
        FieldForLogin.submit();
        WebDriverWait wait = new WebDriverWait(driver, 10000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passp-field-passwd")));
    }
}
