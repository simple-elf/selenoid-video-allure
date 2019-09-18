import io.qameta.allure.Link;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class EasyTest extends BaseTestClass {

    //@Rule
    //public BrowserStrategy perTest = new BrowserStrategy();

    //@Rule
    //public VideoRule video = new VideoRule();

    @Link("http://ya.ru")
    //@Step
    @Test
    public void test_3() {
        test_do();
    }

    //@Step
    @Test
    public void test_4() {
        test_do();
    }


    public static void test_do() {
        open("http://ya.ru");
        sleep(1000);
        $("#text").shouldBe(visible).setValue("selenoid");
        sleep(1000);
        $(byText("Найти")).parent().shouldBe(visible).click();
        sleep(1000);
        $("div.content").shouldBe(visible);
        sleep(1000);
        AllureHelpers.takeScreenshot();
        close();
    }

}
