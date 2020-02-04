import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class SelenoidVideoListener implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            if (testResult.isSuccess())
                return;

            String sessionId = BaseTestClass.getSessionId();
            closeWebDriver();

            //System.out.println("video.enabled1: " + System.getProperty("video.enabled"));
            if ("true".equals(System.getProperty("video.enabled"))) {
                //sleep(5000);
                BaseTestClass.attachAllureVideo(sessionId);
            }
        }
    }
}
