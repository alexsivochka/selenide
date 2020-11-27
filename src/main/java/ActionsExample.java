import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class ActionsExample {

    @BeforeClass
    public void setUp(){
        Configuration.startMaximized = true;
        Configuration.timeout = 8000;
        Configuration.browser = ChromeDriverProvider.class.getName();
    }

    @Test
    public void radioSelectExample(){
        open("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_radio");
        switchTo().frame(0);
        $("[name='gender']").selectRadio("female");
        sleep(10000);

    }

    @Test
    public void dragAndDropExample(){
        open("https://jqueryui.com/droppable/");
        switchTo().frame(0);
        SelenideElement elementToDrag = $("div#draggable");
        SelenideElement elementToDrop = $("div#droppable");
        elementToDrag.dragAndDropTo(elementToDrop);
        elementToDrop.shouldHave(Condition.text("Dropped!"));
        sleep(10000);
    }

}
