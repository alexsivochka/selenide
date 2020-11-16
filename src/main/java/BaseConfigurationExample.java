import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.SoftAsserts;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.title;

@Listeners({ SoftAsserts.class})
public class BaseConfigurationExample {

    @BeforeClass
    public void setUp(){
        browser = "opera";
        startMaximized = true;
        timeout = 8000;
        baseUrl = "https://privatbank.ua/";
        fastSetValue = true;
        assertionMode = AssertionMode.SOFT;
    }

    @Test
    public void simpleTest(){
        open(baseUrl);
        $(".standart-logo").shouldBe(Condition.visible);
        $$("ul.divide a")
                .shouldHaveSize(4)
                .shouldHave(CollectionCondition.textsInAnyOrder(
                        " Приватним особам", " Бізнесу",
                        " VIP-обслуговування", "English"));
        Assert.assertEquals(title(), "ПриватБанк – беремо i робимо");

    }

}
