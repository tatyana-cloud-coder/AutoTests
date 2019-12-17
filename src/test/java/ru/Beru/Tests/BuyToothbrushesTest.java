package ru.Beru.Tests;

import org.testng.annotations.Test;
import ru.Beru.Pages.BasketPage;
import ru.Beru.Pages.BuyPage;
import ru.Beru.Pages.ElectricalToothbrushesPage;
import ru.Beru.Pages.HomePage;
import ru.Beru.Settings.WebDriverSettings;


public class BuyToothbrushesTest extends WebDriverSettings {
    @Test
    public void buyToothBrushes() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        homePage.openShop();
        homePage.openElectricalToothbrushesPage();

        ElectricalToothbrushesPage electricalToothbrushesPage = new ElectricalToothbrushesPage(driver);
        electricalToothbrushesPage.setMinPrice(999);
        electricalToothbrushesPage.setMaxPrice(1999);
        electricalToothbrushesPage.checkPriceRangeCorrect(999, 1999);
        electricalToothbrushesPage.purchaseLast();
        electricalToothbrushesPage.gotoCart();

        BasketPage basketPage = new BasketPage(driver);
        basketPage.checkFreeShipment();
        basketPage.openBuy();

        BuyPage buyPage = new BuyPage(driver);
        buyPage.costCorrection();
        buyPage.openBasket();

        basketPage.increaseToothbrushes(2999);
        basketPage.openBuy();

        buyPage.costCorrection();
        basketPage.cleanCart();
    }
}
