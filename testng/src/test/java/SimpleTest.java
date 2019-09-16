import io.qameta.allure.Link;
import org.testng.annotations.Test;

public class SimpleTest extends BaseTestClass {

    //@Rule
    //public BrowserStrategy perTest = new BrowserStrategy();

    //@Rule
    //public VideoRule video = new VideoRule();

    @Link("http://ya.ru")
    //@Step
    @Test
    public void test_1() {
        SimpleTest2.test_do();
    }

    //@Step
    @Test
    public void test_2() {
        SimpleTest2.test_do();
    }

}
