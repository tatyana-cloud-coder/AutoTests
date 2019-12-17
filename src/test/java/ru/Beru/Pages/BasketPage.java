package ru.Beru.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.Beru.Settings.WebDriverSettings;


public class BasketPage extends WebDriverSettings {
    WebDriver driver;
    WebDriverWait wait;

    public BasketPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    @FindBy(css="[class=\"_1oBlNqVHPq\"]")
    private WebElement labelTotal;

    @FindBy(css="[class=\"_4qhIn2-ESi Pjv3h3YbYr THqSbzx07u _39B7yXQbvm _2W4X8tX6r0\"]")
    private WebElement buyButton;

    @FindBy(xpath = "//div[@class=\"_3MqS53YE3Q\"]//div[@class=\"_1u3j_pk1db\"]")
    private WebElement price;

    @FindBy(xpath = "//div[@data-auto=\"CartOffer\"]//input[@value]")
    private WebElement fieldCount;

    @FindBy(css = "[class=\"_2YHTmhZmt4\"]")
    private WebElement freeShipment;

    @FindBy(css = "[class=\"_2TbI0lRCD8\"]")
    private WebElement deleteButton;

    @FindBy(css="[class=\"_2TFWzc3clT\"]")
    private WebElement clear;

    By checkoutPage = By.className("_1e2FY_93Ro");
    By deleteBut = By.className("uL4H6qKhvR");
    By deleteX = By.className("_45y2-1v_xT");

    @Step("Открыть корзину")
    public void open() {
        driver.get("https://beru.ru/my/cart");
    }

    @Step("Проверка бесплатной доставки")
    public void checkFreeShipment() {
        wait.until(ExpectedConditions.visibilityOf(freeShipment));
        String[] priceStr = freeShipment.getText().split(" ");
        int leftForFree = Integer.parseInt(priceStr[0]);
        if (leftForFree < 10) {
            leftForFree *= 100;
            leftForFree += Integer.parseInt(priceStr[1]);
        }

        System.out.println(leftForFree);
    }

    @Step("Перейти к оформлению")
    public void openBuy() {
        buyButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutPage));
    }

    @Step("Увеличение колличества щеток")
    public void increaseToothbrushes(double price) {
        String[] nums = this.price.getText().split("\\D+");
        int toothbrushPrice = Integer.parseInt(nums[0]);
        if (nums.length > 1) {
            toothbrushPrice *= 1000;
            toothbrushPrice += Integer.parseInt(nums[1]);
        }

        int k = (int) Math.ceil(price / toothbrushPrice);
        fieldCount.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        fieldCount.sendKeys(Integer.toString(k));
        wait.until(ExpectedConditions.visibilityOf(labelTotal));
        wait.until(ExpectedConditions.elementToBeClickable(buyButton));
    }

    @Step("Очистка корзины")
    public void cleanCart() {
        open();
        if (driver.findElements(deleteBut).size() != 0) {
            deleteButton.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(deleteX));
            open();
        }
        wait.until(ExpectedConditions.visibilityOf(clear));
    }
}
