package qaguru;

import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

@Tag("selenide_android")
public class BrowserStackAndroidSelenideTest extends TestBase {

    @Test
    void searchTest(){
        step("Type search", () -> {
            $(MobileBy.AccessibilityId("Search Wikipedia")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).val("BrowserStack");
        });
        step("Verify content found", () ->
                $$(MobileBy.id("org.wikipedia.alpha:id/page_list_item_container"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    void hideFeaturedArticleCard(){
        step("Hide feature article card", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/view_featured_article_card_header"))
                    .$(MobileBy.id("org.wikipedia.alpha:id/view_list_card_header_menu")).click();
            $(MobileBy.className("android.widget.LinearLayout")).click();
        });
        step("Verify feature article card was hidden", () -> {
            $$(MobileBy.id("org.wikipedia.alpha:id/view_featured_article_card_header"))
                    .shouldHaveSize(0);
        });
    }

}
