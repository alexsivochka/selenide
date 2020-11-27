import com.codeborne.selenide.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.*;

public class SimpleExample {

    @Test
    public void simpleTest(){
        open("https://privatbank.ua/");
        $(".standart-logo").waitUntil(Condition.visible, 8000);
        $$("ul.divide a")
                .shouldHaveSize(4)
                .shouldHave(CollectionCondition.textsInAnyOrder(
                        " Приватним особам", " Бізнесу",
                        " VIP-обслуговування", "English"));
        Assert.assertEquals(title(), "ПриватБанк – беремо i робимо");
    }

}
