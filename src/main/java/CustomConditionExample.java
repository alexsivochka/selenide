import com.codeborne.selenide.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class CustomConditionExample {

    public static Condition css(String propName, String propValue) {
        return new Condition("css") {
            @Override
            public boolean apply(Driver driver, WebElement webElement) {
                return propValue.equals(webElement.getCssValue(propName));
            }
        };
    }

    @BeforeClass
    public void setUp(){
        Configuration.startMaximized = true;
        Configuration.timeout = 8000;
        Configuration.browser = ChromeDriverProvider.class.getName();
    }

    @Test
    public void customConditionTest(){
        open("http://the-internet.herokuapp.com");
        SelenideElement block = $x("//h1");
        block.shouldHave(css("font-size", "44px"));
        block.shouldHave(css("font-style", "normal"));
    }

}
