import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.title;

public class CustomDriverExample {

    @BeforeClass
    public void setUp(){
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
        Configuration.browser = ChromeDriverProvider.class.getName();
    }

    @Test
    public void simpleTest(){
        open("https://privatbank.ua/");

        $(".standart-logo").shouldBe(visible);
        $$("ul.divide a")
                .shouldHaveSize(4)
                .shouldHave(CollectionCondition.textsInAnyOrder(
                        " Приватним особам", " Бізнесу",
                        " VIP-обслуговування", "English"));
        Assert.assertEquals(title(), "ПриватБанк – беремо i робимо");

    }

}
