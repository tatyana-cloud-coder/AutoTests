package ru.Beru.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.Beru.Settings.WebDriverSettings;


public class BuyPage extends WebDriverSettings {
    WebDriver driver;
    WebDriverWait wait;

    public BuyPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }


    @FindBy(css="[class=\"_1oBlNqVHPq\"]")
    private WebElement totalPrice;

    @FindBy(xpath = "//div[@data-auto=\"DELIVERY\"]")
    private WebElement courier;

    @FindBy(css="[class=\"_1e2FY_93Ro\"]")
    private WebElement order;

    @FindBy(xpath = "//div[@data-auto=\"total-items\"]/span[@data-auto=\"value\"]")
    private WebElement itemsPrice;

    @FindBy(xpath = "//div[@data-auto=\"total-delivery\"]//span[@data-auto=\"value\"]")
    private WebElement courierPrice;

    @FindBy(xpath = "//span[@data-auto=\"change-link\"]")
    private WebElement changeOrder;

    private By discount = By.xpath("//div[@data-auto=\"total-discount\"]//span[@data-auto=\"value\"]");
    private By basketPage = By.className("wn9mZbgWbv");

    @Step("Проверка стоимости")
    public void costCorrection() {
        String[] nums = itemsPrice.getText().split("\\D+");
        int itemsPrice = Integer.parseInt(nums[0]);
        if (nums.length > 1) {
            itemsPrice *= 1000;
            itemsPrice += Integer.parseInt(nums[1]);
        }

        courier.click();

        int discount = 0;
        if (driver.findElements(this.discount).size() != 0) {
            nums = driver.findElement(this.discount).getText().split("\\D+");
            discount = Integer.parseInt(nums[0]);
            if (nums.length > 1) {
                discount *= 1000;
                discount += Integer.parseInt(nums[1]);
            }
        }

        int deliveryPrice;
        if (this.courierPrice.getText().equals("бесплатно")) {
            deliveryPrice = 0;
        } else {
            nums = this.courierPrice.getText().split("\\D+");
            deliveryPrice = Integer.parseInt(nums[0]);
            if (nums.length > 1) {
                deliveryPrice *= 1000;
                deliveryPrice += Integer.parseInt(nums[1]);
            }
        }

        nums = totalPrice.getText().split("\\D+");
        int total = Integer.parseInt(nums[0]);
        if (nums.length > 1) {
            total *= 1000;
            total += Integer.parseInt(nums[1]);
        }

        Assert.assertEquals(deliveryPrice + itemsPrice - discount, total);
    }

    @Step("Открыть корзину")
    public void openBasket() {
        changeOrder.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(basketPage));
    }
}
