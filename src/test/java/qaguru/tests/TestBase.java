package qaguru.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import qaguru.drivers.AndriodMobileDriver;
import qaguru.drivers.BrowserstackMobileDriver;
import qaguru.drivers.SelenoidMobileDriver;
import qaguru.helpers.Attach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static qaguru.helpers.Attach.getSessionId;

public class TestBase {

    public static String deviceHost;
    public static boolean onboardingSettingPlaced = false;

    @BeforeAll
    public static void setup(){
        addListener("allure", new AllureSelenide());
        Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.timeout = 10000;
        deviceHost = System.getProperty("deviceHost");
        switch (deviceHost){
            case "browserstack": Configuration.browser = BrowserstackMobileDriver.class.getName();
                break;
            case "selenoid": Configuration.browser = SelenoidMobileDriver.class.getName();
                break;
            case "real":
            case "emulator": Configuration.browser = AndriodMobileDriver.class.getName();
                break;
            default: new RuntimeException("no such deviceHost" + deviceHost);
        }
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
        switch (deviceHost){
            case "browserstack": Attach.attachVideo(sessionId);
        }

    }
}
