import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ConditionExample {

    String tenderId = "UA-2020-06-11-000207-a";

    @BeforeClass
    public void setUp(){
        Configuration.startMaximized = true;
        Configuration.browser = ChromeDriverProvider.class.getName();
    }

    @Test
    public void conditionClassExample(){
        open("https://zakupivli24.pb.ua/prz-at/tender?mode=clear");
        $("#search-query-input")
                .shouldBe(visible, enabled.because("Поле поиска должно быть активным"))
                .shouldHave(attribute("placeholder", "Пошук за назвою закупівлі, классифікатором, ЄДРПОУ ..."))
                .setValue(tenderId)
                .pressEnter();
        $(".spinner").waitUntil(disappear, 15000);
        $("div.tender-id").should(appear.because("Не удалось найти тендер по заданному ID."))
                .shouldHave(text(tenderId))
                .should(matchText("UA-\\d{4}-\\d{2}-\\d{2}-\\d{6}-\\w"));
        $("div.unsuccessful").shouldHave(exactText("Заблокировано"));
    }

}
