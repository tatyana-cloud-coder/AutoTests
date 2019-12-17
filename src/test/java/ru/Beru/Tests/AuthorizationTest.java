package ru.Beru.Tests;

import io.qameta.allure.Description;
import junit.runner.Version;
import org.testng.annotations.Test;
import ru.Beru.Pages.HomePage;
import ru.Beru.Pages.AuthPage;
import ru.Beru.Settings.WebDriverSettings;
public class AuthorizationTest extends WebDriverSettings {
    /*Авторизоваться на сайте, после авторизации проверить что на главной странице отображается логин.
    Убедиться что кнопка “Войти в аккаунт” сменилась на “Мой профиль” */
    @Test
    @Description("Проверка корректности авторизации на сайте интернет-магазина")
    public void authorization () {
        String login = "git config --global user.email \"bala5howatatwork@yandex.ru\"git commit -a";
        String password = "TatyanaEvgenij";

       HomePage homePage = new HomePage(driver);
       homePage.openShop();
       homePage.checkText("Войти в аккаунт");
       homePage.doAuthorization();
       AuthPage authPage = new AuthPage(driver);
       authPage.fillLogin(login);
       authPage.fillPassword(password);
       homePage.checkText("Мой профиль");
       System.out.println(Version.id());
    }
}
