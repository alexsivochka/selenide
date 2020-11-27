import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class JavaScriptExecutingExample {

    @BeforeClass
    public void setUp(){
        Configuration.startMaximized = true;
        Configuration.browser = ChromeDriverProvider.class.getName();
    }

    public void setDate(int year, int month, int day){
        executeJavaScript(String.format(
                "$('#datepicker').datepicker().datepicker('setDate', new Date(%d, %d, %d));",
                year, month, day));
    }

    @Test
    public void jsExecTest(){
        open("https://jqueryui.com/datepicker/");
        switchTo().frame(0);
        $("input#datepicker").shouldBe(visible, enabled).click();
        setDate(2020, 9, 3); // нумерация месяца начинается с 0, т.е. фактически мы устанавливаем октябрь
        sleep(8000);
    }


}
