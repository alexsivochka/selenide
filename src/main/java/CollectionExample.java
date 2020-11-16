import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class CollectionExample {

    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

    @BeforeClass
    public void setUp(){
        Configuration.startMaximized = true;
        Configuration.browser = ChromeDriverProvider.class.getName();
    }

    @Test
    public void collectionExample(){

        open("https://zakupivli24.pb.ua/prz-at/tender?mode=clear");

        ElementsCollection dates = $$("div.date-col");
        ElementsCollection todayTenders = dates
                .shouldHave(sizeGreaterThanOrEqual(10), 10000)
                .filterBy(text(date));

        todayTenders.forEach(t -> {
            t.parent().shouldHave(attribute("id"))
                        .$("div.status-common-info")
                            .shouldHave(
                                    or("Tender status",
                                            text("Період прийому пропозицій"),
                                            text("Період уточнень")));
        });

    }

}
