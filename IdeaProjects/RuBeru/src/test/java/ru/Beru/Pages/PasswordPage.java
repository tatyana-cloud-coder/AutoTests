package ru.Beru.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasswordPage {
    WebDriver driver;
    private WebElement FieldForPassword;
    private String password = "Tatyana2018";
    private WebDriverWait wait;
    public PasswordPage (WebDriver driver) {
        this.driver =  driver;
        wait = new WebDriverWait(driver, 10000);
    }
    public void fillPassword () {
        FieldForPassword = driver.findElement(By.id("passp-field-passwd"));
        FieldForPassword.sendKeys(password);
        FieldForPassword.submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mvd1wAJzjc")));
    }
}
