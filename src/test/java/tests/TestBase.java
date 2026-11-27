package tests;

import com.codeborne.selenide.Configuration;
import components.ResultRegistration;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.RegistrationPage_DemoQa;

import java.util.Map;

public class TestBase {

    protected RegistrationPage_DemoQa registrationPageDemoQa = new RegistrationPage_DemoQa();
    protected ResultRegistration resultRegistration = new ResultRegistration();

    // JUnit вызывает этот метод перед тестами каждого класса, который наследует TestBase.
    @BeforeAll
    static void SetupBrowser() {
        Configuration.browserSize = "1920x1080";   // Размер окна Chrome под размер видеозаписи.
        Configuration.timeout = 10000;            // До 10 секунд на ожидание условий Selenide.
        Configuration.baseUrl = "https://demoqa.com"; // Базовый адрес для open("/путь").
        Configuration.pageLoadStrategy = "eager"; // Продолжаем после загрузки DOM страницы.

        // TODO: разобраться с проверками автоматизации Google и ролью настройки Blink.
        ChromeOptions options = new ChromeOptions(); // Собираем настройки для запуска Chrome.
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setCapability("webSocketUrl", false); // Не запрашиваем WebSocket для WebDriver BiDi.

        // В Jenkins URL задаётся в environment этапа run-test.
        // Для запуска в обычном Chrome на Windows оставь переменную в IDE незаданной.
        String remoteUrl = System.getenv("SELENOID_URL");

        if (remoteUrl != null && !remoteUrl.isBlank()) {
            // Адрес задан: запускаем браузер через Selenoid.
            Configuration.remote = remoteUrl;
            options.setCapability("selenoid:options", Map.of("enableVideo", true));

            // Закрываем сессию после прогона, чтобы Selenoid завершил запись MP4.
            Configuration.holdBrowserOpen = false;
        } else {
            // Адрес не задан: запускаем Chrome на компьютере, где выполняется тест.
            Configuration.remote = null;
            Configuration.holdBrowserOpen = true; // Оставляем окно открытым для отладки.
        }

        // Передаём Selenide все собранные настройки до первого вызова open().
        Configuration.browserCapabilities = options;
    }
}