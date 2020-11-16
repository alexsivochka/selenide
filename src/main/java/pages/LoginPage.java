package pages;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public LoginPage fillLoginPhone(String phone){
        $("input[data-id='enter-login']")
                .shouldBe(visible, enabled)
                .shouldHave(attribute("placeholder", "+380"))
                .setValue(phone);
        return this;
    }

    public LoginPage fillPassword(String password){
        $("input[data-id='enter-pwd']")
                .shouldBe(visible, enabled)
                .shouldHave(attribute("placeholder", "Пароль"))
                .setValue(password);
        return this;
    }

    public void pressSubmitButton(){
        $("button[data-id='enter-submit']")
                .shouldNotBe(disabled).click();
    }

}
