package qaguru;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {
    public static URL getBrowserstackUrl() {
        try {
            return new URL("http://hub.browserstack.com/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        // Set your access credentials
        desiredCapabilities.setCapability("browserstack.user", TestBase.config.getUser());
        desiredCapabilities.setCapability("browserstack.key", TestBase.config.getPassword());

        // Set URL of the application under test
        desiredCapabilities.setCapability("app", TestBase.config.getApp());

        // Specify device and os_version for testing
        desiredCapabilities.setCapability("device", TestBase.config.getDevice());
        desiredCapabilities.setCapability("os_version", TestBase.config.getOsVersion());

        // Set other BrowserStack capabilities
        desiredCapabilities.setCapability("project", TestBase.config.getProject());
        desiredCapabilities.setCapability("build", TestBase.config.getBuild());
        desiredCapabilities.setCapability("name", TestBase.config.getName());

        return new AndroidDriver(getBrowserstackUrl(), desiredCapabilities);
    }
}

