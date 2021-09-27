package qaguru;

import com.codeborne.selenide.Configuration;
import qaguru.config.AndroidConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static qaguru.Attach.getSessionId;

public class TestBase {

    public static AndroidConfig config;

    @BeforeAll
    public static void setup(){
        addListener("allure", new AllureSelenide());
        Configuration.browser = BrowserstackMobileDriver.class.getName();
        Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.timeout = 10000;
        config = ConfigFactory.create(AndroidConfig.class, System.getProperties());
    }

    @BeforeEach
    public void startDriver(){
        open();
    }

    @AfterEach
    public void closeDriver(){
        String sessionId = getSessionId();
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();

        closeWebDriver();

        Attach.attachVideo(sessionId);
    }
}
