import io.qameta.allure.Link;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class SimpleTest extends BaseTestClass {

    //@Rule
    //public BrowserStrategy perTest = new BrowserStrategy();

    @Link("http://ya.ru")
    @Step
    @Test
    public void test_1() {
        open("http://ya.ru");
        $("#text").shouldBe(visible).setValue("selenoid");
        $(byText("Найти")).parent().shouldBe(visible).click();
        $("div.content").shouldBe(visible);
        AllureHelpers.takeScreenshot("selenoid");
        sleep(5000);
        AllureHelpers.takeScreenshot("selenoid");
        sleep(5000);
    }

    @Step
    @Test
    public void test_2() {
        open("http://ya.ru");
        $("#text").shouldBe(visible).setValue("selenoid");
        $(byText("Найти")).shouldBe(visible).click();
        $("div.content").shouldBe(visible);
    }

}
