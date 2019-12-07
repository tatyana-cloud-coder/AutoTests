package ru.Beru.Tests;

import com.sun.org.glassfish.gmbal.Description;
import junit.runner.Version;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import ru.Beru.Pages.HomePage;
import ru.Beru.Pages.LoginPage;
import ru.Beru.Pages.PasswordPage;
import ru.Beru.Settings.WebDriverSettings;
public class AuthorizationTest extends WebDriverSettings {
    /*Авторизоваться на сайте, после авторизации проверить что на главной странице отображается логин.
    Убедиться что кнопка “Войти в аккаунт” сменилась на “Мой профиль” */
    @Test
    @Description("Проверка корректности авторизации на сайте интернет-магазина")
    public void authorization () {
       HomePage homePage = new HomePage(chromeDriver);
       homePage.OpenShop();
       homePage.checkTextProfileButton("Войти в аккаунт");
       homePage.doAuthorization();
       LoginPage loginPage = new LoginPage(chromeDriver);
       loginPage.fillLogin();
       PasswordPage passwordPage = new PasswordPage(chromeDriver);
       passwordPage.fillPassword();
       homePage.checkTextProfileButton("Мой профиль");
       System.out.println(Version.id());
    }
}
