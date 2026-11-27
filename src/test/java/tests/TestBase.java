package tests;

import com.codeborne.selenide.Configuration;
import components.ResultRegistration;
import pages.RegistrationPage_DemoQa;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestBase {

    protected RegistrationPage_DemoQa registrationPageDemoQa = new RegistrationPage_DemoQa();
    protected ResultRegistration resultRegistration = new ResultRegistration();

    @BeforeAll
    static void SetupBrowser() {
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false; // для отладки
        Configuration.timeout = 10000;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = System.getenv().getOrDefault(
                "SELENOID_URL", "http://localhost:4444/wd/hub"
        );


//todo разобраться с обходом автоматизации гугла, не достаточно blink, похоже надо задавать браузер итд
        ChromeOptions options = new ChromeOptions(); // создаем новый объект для обхода ботов
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setCapability("webSocketUrl", false);
        options.setCapability("selenoid:options", java.util.Map.of("enableVideo", true)); // запись видео в selenoid
        Configuration.browserCapabilities = options;

    }
}
