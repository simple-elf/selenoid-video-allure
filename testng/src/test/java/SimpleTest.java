import io.qameta.allure.Link;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

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
        $("#text").shouldBe(visible).setValue("selenoid");
        $(byText("Найти")).parent().shouldBe(visible).click();
        $("div.content").shouldBe(visible);
    }

    //@Step
    @Test
    public void test_2() {
        open("http://ya.ru");
        $("#text").shouldBe(visible).setValue("selenoid");
        $(byText("Найти")).shouldBe(visible).click();
        $("div.content").shouldBe(visible);
    }

}
