package qaguru.tests;

import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

@Tag("selenide_android")
public class AndroidSelenideTest extends TestBase {

    @Test
    void searchTest(){
        step("Define version of Wiki", () ->{
            if ($$(MobileBy.AccessibilityId("Search Wikipedia")).size() > 0){
                onboardingSettingPlaced = true;
            }
        });

        if(!onboardingSettingPlaced){
            step("Click Continue for language", () -> {
                $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            });

            step("Click Continue for New ways to explore", () -> {
                $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            });

            step("Click Continue Reading lists with sync", () -> {
                $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            });

            step("Click get started", () -> {
                $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_done_button")).click();
            });
        }

        step("Type search", () -> {
            $(MobileBy.AccessibilityId("Search Wikipedia")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).val("BrowserStack");
        });

        step("Verify content found", () ->
                $$(MobileBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }
}
