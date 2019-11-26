package ru.Beru.Tests;

import com.sun.org.glassfish.gmbal.Description;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import ru.Beru.Pages.HomePage;
import ru.Beru.Settings.WebDriverSettings;

public class AuthorizationTest extends WebDriverSettings {
    /*Авторизоваться на сайте, после авторизации проверить что на главной странице отображается логин.
    Убедиться что кнопка “Войти в аккаунт” сменилась на “Мой профиль” */
    @Test
    @Description("Проверка корректности авторизации на сайте интернет-магазина")
    public void authorization () {
       HomePage homepage = new HomePage(chromeDriver);
       homepage.OpenShop();
       homepage.checkTextProfileButton("Войти в аккаунт");
       homepage.doAuthorization();






    }

}
