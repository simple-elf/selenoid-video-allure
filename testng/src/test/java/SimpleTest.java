import io.qameta.allure.Link;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class SimpleTest extends BaseTestClass {

    //@Rule
    //public BrowserStrategy perTest = new BrowserStrategy();

    //@Rule
    //public VideoRule video = new VideoRule();

    @Link("http://ya.ru")
    //@Step
    @Test
    public void test_1() {
        open("http://ya.ru");
        sleep(5000);
        $("#text").shouldBe(visible).setValue("selenoid");
        sleep(5000);
        $(byText("Найти")).parent().shouldBe(visible).click();
        sleep(5000);
        $("div.content").shouldBe(visible);
        sleep(5000);
    }

    //@Step
    @Test
    public void test_2() {
        open("http://ya.ru");
        sleep(5000);
        $("#text").shouldBe(visible).setValue("selenoid");
        sleep(5000);
        $(byText("Найти")).shouldBe(visible).click();
        sleep(5000);
        $("div.content").shouldBe(visible);
        sleep(5000);
    }

}
