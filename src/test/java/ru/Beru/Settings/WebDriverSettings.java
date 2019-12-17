package ru.Beru.Settings;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;

public class WebDriverSettings {
    public static ChromeDriver chromeDriver;
    public static EventFiringWebDriver driver;
    public static WebDriverWait wait;
    public static SeleniumListener listener;

    @BeforeMethod
    public void start() {
        System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\drivers\\chromedriver78.exe");
        chromeDriver = new ChromeDriver();
        driver = new EventFiringWebDriver(chromeDriver);
        listener = new SeleniumListener();
        wait = new WebDriverWait(chromeDriver, 10);
        driver.register(listener);
        driver.manage().window().fullscreen();
    }

    @AfterMethod
    protected void finish() {
        driver.quit();
    }

    @AfterMethod
    public void catchFailure(ITestResult result) {
        if (!result.isSuccess()) {
            takeScreenshot();
        }
    }

    @Attachment(value = "Screenshot")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
