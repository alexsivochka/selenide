import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.partialLinkText;

public class UploadDownloadExample {

    @BeforeClass
    public void setUp(){
        Configuration.startMaximized = true;
        Configuration.timeout = 8000;
        Configuration.downloadsFolder = "src/main/resources";
        Configuration.browser = ChromeDriverProvider.class.getName();
    }

    @Test
    public void uploadFileTest(){
        File file = new File("src/main/resources/upload.txt");
        open("http://the-internet.herokuapp.com/upload");
        $("#file-upload").should(exist).uploadFile(file);
        $("#file-submit").shouldBe(visible, enabled).click();
        $x("//h3").should(appear).shouldHave(text("File Uploaded!"));
        $("div#uploaded-files").shouldBe(visible).shouldHave(text(file.getName()));
    }

    @Test
    public void downloadFileTest() throws IOException {
        open("https://www.google.com/");
        String query = "Пример PDF файла";
        $(By.name("q")).setValue(query).pressEnter();
        $$x("//*[text=('')]").filterBy(visible).first().click();
        File file = $("a[href$='People.txt']").download();
        Stream<String> stream = Files.lines(Paths.get(file.getPath()));
        stream.forEach(System.out::println);
    }
}
