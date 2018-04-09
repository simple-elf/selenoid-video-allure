import org.testng.ITestResult;
import org.testng.reporters.ExitCodeListener;

/**
 * Selenide screenshots to Allure report.
 *
 * Annotate your test class with <code>@Listeners({AllureScreenShooter.class})</code>.
 *
 * You can configure capture screenshots after success tests by <code>AllureScreenShooter.captureSuccessfulTests = false;</code>.
 *
 * Link {@link com.codeborne.selenide.testng.ScreenShooter}
 */
public class AllureScreenShooter extends ExitCodeListener {
    public static boolean captureSuccessfulTests = true;

    @Override
    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
        AllureHelpers.takeScreenshot();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        super.onTestSuccess(result);
        if (captureSuccessfulTests) {
            AllureHelpers.takeScreenshot();
        }
    }
}
