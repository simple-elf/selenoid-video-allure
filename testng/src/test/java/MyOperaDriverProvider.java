import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MyOperaDriverProvider implements WebDriverProvider {

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        WebDriverManager.operadriver().setup();

        //capabilities = DesiredCapabilities.operaBlink();
        return new OperaDriver(getOperaOptions().merge(capabilities));
        //return new OperaDriver();
    }

    public static OperaOptions getOperaOptions() {
        OperaOptions operaOptions = new OperaOptions();
        //operaOptions.addArguments("start-maximized"); // open Browser in maximized mode
        operaOptions.addArguments("disable-infobars"); // disabling infobars
        operaOptions.addArguments("--disable-extensions"); // disabling extensions
        operaOptions.addArguments("--disable-gpu"); // applicable to windows os only
        operaOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        operaOptions.addArguments("--no-sandbox"); // Bypass OS security model
        //operaOptions.addArguments("--headless"); // Bypass OS security model

        return operaOptions;
    }

}
