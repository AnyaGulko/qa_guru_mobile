package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;

import drivers.BrowserstackDriverProvider;
import drivers.EmulatorDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {
    public static String deviceHost = System.getProperty("deviceHost");
    @BeforeAll
    static void setUp() {
        switch (deviceHost) {
            case "browserstack" -> Configuration.browser = BrowserstackDriverProvider.class.getName();
            case "emulation" -> Configuration.browser = EmulatorDriver.class.getName();
        }
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void addAttachments() {
        String sessionId = Selenide.sessionId().toString();

        Attach.pageSource();
        closeWebDriver();

//        Attach.addVideo(sessionId);
    }

}
