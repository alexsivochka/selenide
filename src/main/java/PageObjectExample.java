import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HeaderPage;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class PageObjectExample {

    HeaderPage headerPage = new HeaderPage();
    LoginPage loginPage = new LoginPage();

    @BeforeClass
    public void setUp(){
        Configuration.startMaximized = true;
        Configuration.timeout = 8000;
        Configuration.browser = ChromeDriverProvider.class.getName();
        open("https://zakupivli24.pb.ua/prz-at/tender?mode=clear");
    }

    @Test
    public void loginTest(){
        headerPage.openLoginForm()
                .fillLoginPhone("+380270010001")
                .fillPassword("123456")
                .pressSubmitButton();
        headerPage.checkUserName("ФЕРМЕРСЬКЕ ГОСПОДАРСТВО \"ВІРА-АГРО 2014\"");
    }


}
