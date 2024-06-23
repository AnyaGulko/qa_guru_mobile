package configs;

import com.codeborne.selenide.WebDriverProvider;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriverProvider implements WebDriverProvider {

    protected static PlatformsConfig platformsConfig = ConfigFactory.create(PlatformsConfig.class, System.getProperties());
    protected static AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {

        MutableCapabilities caps = new MutableCapabilities();

        // Set your access credentials
        caps.setCapability("browserstack.user", authConfig.getUser());
        caps.setCapability("browserstack.key", authConfig.getKey());

        // Set URL of the application under test
        caps.setCapability("app", platformsConfig.getApp());


        // Specify device and os_version for testing
        caps.setCapability("device", platformsConfig.getDevice());
        caps.setCapability("os_version", platformsConfig.getOS());

        // Set other BrowserStack capabilities
        caps.setCapability("project", platformsConfig.getProject());
        caps.setCapability("build", platformsConfig.getBuild());
        caps.setCapability("name", platformsConfig.getName());

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        try {
            return new RemoteWebDriver(
                    new URL(authConfig.getRemoteUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
