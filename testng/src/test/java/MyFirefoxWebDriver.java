import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MyFirefoxWebDriver implements WebDriverProvider {

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        WebDriverManager.firefoxdriver().setup();

        return new FirefoxDriver(getFirefoxOptions().merge(capabilities));
    }

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--disable-web-security");
        firefoxOptions.addArguments("--allow-running-insecure-content");

        FirefoxProfile profile = new FirefoxProfile();
        //profile.setPreference("plugin.state.flash", 0);
        //profile.setPreference("plugin.state.flash", 2);
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(false);
        profile.setPreference("pageLoadStrategy", "normal");

        firefoxOptions.setCapability(FirefoxDriver.PROFILE, profile);
        //firefoxOptions.setCapability("version", "68.0"); //TODO set auto latest version

        return firefoxOptions;
    }
}
