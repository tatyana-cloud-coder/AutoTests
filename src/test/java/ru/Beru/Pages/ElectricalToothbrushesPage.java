package ru.Beru.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ElectricalToothbrushesPage {
    WebDriver driver;
    WebDriverWait wait;

    public ElectricalToothbrushesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    @FindBy(xpath = "//div[@data-auto=\"filter-range-glprice\"]//span[@data-auto=\"filter-range-min\"]//input")
    private WebElement minPrice;

    @FindBy(xpath = "//div[@data-auto=\"filter-range-glprice\"]//span[@data-auto=\"filter-range-max\"]//input")
    private WebElement maxPrice;

    @FindBy(css = "[class=\"_3GNV1gy3cc\"]")
    private WebElement priceRange;

    @FindBy(xpath = "//span[contains(@class, '_3ioN70chUh _3XRVQbB83A')]")
    private WebElement popupFounded;

    @FindBy(xpath = "//div[@class=\"_1uhsh_io8o\"]//div[@class=\"_3rWYRsam78\"][last()]/div[last()]//div[@class=\"_1RjY7YIluf _1zYszmgEzn\"][last()-1]//span[@class=\"_2w0qPDYwej\"]")
    private WebElement penultimateToothbrush;

    @FindBy(css = "[class=\"_1LEwf9X1Gy\"]")
    private WebElement gotoBAsket;

    private By locatorFoundedGoods = By.className("_3rWu3-6RDl qpgDgmh6Hn _11QbuC0gtX _1zxBwSfbGK _1mXFu6EZpv >wrItvb7JRv");
    private By locatorCartPage = By.className("wn9mZbgWbv");


    @Step("Открыть каталог электрических зубных щеток")
    public void open() {
        driver.get("https://beru.ru/catalog/elektricheskie-zubnye-shchetki-v-saratove/80961/list?hid=278374&track=fr_ctlg");
        wait.until(ExpectedConditions.visibilityOf(minPrice));
    }

    @Step("Поставить минимальную цену")
    public void setMinPrice(int price) {
        minPrice.sendKeys(Integer.toString(price));
    }

    @Step("Поставить максимальную цену")
    public void setMaxPrice(int price) {
        maxPrice.sendKeys(Integer.toString(price));
    }

    @Step("Проверка правильности диапазона")
    public void checkPriceRangeCorrect(int low, int max) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(priceRange));
        String priceRange = low + " — " + max + " ₽";
        Assert.assertEquals(priceRange, this.priceRange.getText());

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return popupFounded.isDisplayed();
            }
        });
    }

    @Step("Покупка предпоследней зубной щетки")
    public void purchaseLast() {
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(penultimateToothbrush));
        penultimateToothbrush.click();
        wait.until(ExpectedConditions.elementToBeClickable(penultimateToothbrush));
    }

    @Step("Перейти в корзину")
    public void gotoCart() {
        gotoBAsket.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorCartPage));
    }


}
