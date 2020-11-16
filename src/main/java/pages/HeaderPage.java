package pages;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class HeaderPage {

    public LoginPage openLoginForm(){
        $("button[ng-click*='showLoginPage']")
                .shouldBe(visible, enabled).click();
        return new LoginPage();
    }

    public void checkUserName(String expectedName){
        $("span.company-name")
                .waitUntil(visible, 15000)
                .shouldHave(text(expectedName));
    }

}
