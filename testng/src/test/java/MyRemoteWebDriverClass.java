import com.codeborne.selenide.WebDriverProvider;
import io.qameta.allure.model.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static io.qameta.allure.Allure.step;

public class MyRemoteWebDriverClass implements WebDriverProvider {

    @SuppressWarnings("deprecation")
    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        capabilities.setBrowserName("chrome");
        capabilities.setCapability(ChromeOptions.CAPABILITY, MyChromeBrowserClass.getChromeOptions());
        capabilities.setCapability("screenResolution", "1920x1080x24");
        //capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("timeZone", "Europe/Moscow");

        //System.out.println("video.enabled: " + System.getProperty("video.enabled"));
        if ("true".equals(System.getProperty("video.enabled"))) {
            capabilities.setCapability("enableVideo", true);
            capabilities.setCapability("videoFrameRate", 24);
        }

        System.out.println(capabilities);
        try {
            return new RemoteWebDriver(getGridHubUrl(), capabilities);
        } catch (Exception e) {
            e.printStackTrace();
            step("ChromeDriver error: " + e.getMessage(), Status.FAILED);
            throw e;
            //return null;
        }
    }

    public static URL getGridHubUrl() {
        URL hostURL = null;
        try {
            hostURL = new URL(System.getProperty("selenoid.url", "http://127.0.0.1:4444/wd/hub"));
        } catch (MalformedURLException e) {
            //e.printStackTrace();
        }
        return hostURL;
    }

}
