package ru.Beru.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.Beru.Pages.AuthPage;
import ru.Beru.Pages.HomePage;
import ru.Beru.Pages.SettingsPage;
import ru.Beru.Settings.WebDriverSettings;


public class ChangeCityTest extends WebDriverSettings {

    @Test(dataProvider = "Cities", dataProviderClass = ProviderCities.class)
    public void changeCity(String cityName) {
        String login = "bala5howatatwork";
        String password = "TatyanaEvgenij";

        HomePage homePage = new HomePage(driver);
        homePage.openShop();
        homePage.checkCity("Саратов");
        homePage.changeCity(cityName);
        homePage.checkCity(cityName);
        homePage.doAuthorization();

        AuthPage authPage = new AuthPage(driver);
        authPage.fillLogin(login);
        authPage.fillPassword(password);

        homePage.checkText("Мой профиль");
        homePage.openSettings();

        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.checkCityCorrect();
    }

    public static class ProviderCities {
        @DataProvider(name = "Cities")
        public static Object[][] dataProviderMethod() {
            return new Object[][]{ {"Хвалынск"}, {"Хабаровск"}};
        }
    }
}
