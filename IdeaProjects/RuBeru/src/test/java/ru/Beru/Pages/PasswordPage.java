package ru.Beru.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasswordPage {
    WebDriver driver;
    private WebElement FieldForPassword;
    private String password = "TatyanaEvgenij";
    public PasswordPage (WebDriver driver) {
        this.driver =  driver;
    }
    public void fillPassword () {
        FieldForPassword = driver.findElement(By.id("passp-field-passwd"));
        FieldForPassword.sendKeys(password);
        FieldForPassword.submit();
        WebDriverWait wait = new WebDriverWait(driver, 10000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mvd1wAJzjc")));

    }

}
