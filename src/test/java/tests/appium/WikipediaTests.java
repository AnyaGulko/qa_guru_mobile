package tests.appium;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tests.TestBase;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class WikipediaTests extends TestBase {
    @ValueSource(strings = {
            "QA", "Appium"
    })
    @ParameterizedTest(name = "Проверка наличия элементов в результатах поиска {0}")
    @Tag("emulator")
    void successfulSearchTest(String searchQuery) {
        back();
        step("Ввести в строку поиска значение {0}", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys(searchQuery);
        });
        step("Проверить, что контент найден", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

}
