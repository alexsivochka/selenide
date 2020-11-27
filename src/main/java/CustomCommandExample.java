import com.codeborne.selenide.Command;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementSource;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;

public class CustomCommandExample {

    public static Command<SelenideElement> setSliderValue(int value){
        return (selenideElement, webElementSource, objects) -> {
            executeJavaScript("hs=$(arguments[0]).slider();" +
                    "hs.slider('option', 'value',arguments[1]);" +
                    "hs.slider('option','slide').call(hs,null,{ handle: $('.ui-slider-handle', hs), value: arguments[1] });",selenideElement, value);
            return null;
        };
    }

    @BeforeClass
    public void setUp(){
        Configuration.startMaximized = true;
        Configuration.timeout = 8000;
        Configuration.proxyEnabled = true;
        Configuration.browser = ChromeDriverProvider.class.getName();
    }

    @Test
    public void customConditionTest(){
        open("https://jqueryui.com/slider/#slider-vertical");
        switchTo().frame(0);
        SelenideElement slider = $("#slider-vertical");
        slider
                .execute(setSliderValue(44));
        sleep(5000);

    }

}
