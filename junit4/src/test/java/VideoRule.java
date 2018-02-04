import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class VideoRule extends TestWatcher {
    @Override
    protected void failed(Throwable e, Description description) {
        String sessionId = BaseTestClass.getSessionId();
        closeWebDriver();

        System.out.println("video.enabled2: " + System.getProperty("video.enabled"));
        if ("true".equals(System.getProperty("video.enabled"))) {
            BaseTestClass.attachAllureVideo(sessionId);
        }
    }
}
