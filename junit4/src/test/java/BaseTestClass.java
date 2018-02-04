import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.common.io.Files;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTestClass {

    @BeforeClass
    public static void beforeClass() {
        //Configuration.browser = MyChromeBrowserClass.class.getName();
        Configuration.browser = MyRemoteWebDriverClass.class.getName();
        Configuration.browserSize = "1920x1080";

        Configuration.startMaximized = true;
        Configuration.reportsFolder = "target/reports";
        Configuration.screenshots = false;

        SelenideLogger.addListener("Allure Selenide", new AllureSelenide());
    }

    //@Step
    @After
    public void saveVideo() {
        String sessionId = getSessionId();
        closeWebDriver();

        System.out.println("video.enabled: " + System.getProperty("video.enabled"));
        if ("true".equals(System.getProperty("video.enabled"))) {
            //sleep(5000);
            attachAllureVideo(sessionId);
        }

    }

    public static URL getVideoUrl() {
        return getVideoUrl(getSessionId());
    }

    public static URL getVideoUrl(String sessionId) {
        URL url = null;
        try {
            url = new URL(selenoidUrl + "/video/" + sessionId + ".mp4");
        } catch (Exception e) {
            System.out.println("getVideoUrl");
            e.printStackTrace();
        }
        return url;
    }

    private static String selenoidUrl = "http://127.0.0.1:4444"; //127.0.0.1
    //@Step
    public void attachAllureVideo(String sessionId) {
        try {
            URL videoUrl = new URL(selenoidUrl + "/video/" + sessionId + ".mp4");
            InputStream is = getSelenoidVideo(videoUrl);
            Allure.addAttachment("Video", "video/mp4", is, "mp4");
            deleteSelenoidVideo(videoUrl);
        } catch (Exception e) {
            System.out.println("attachAllureVideo");
            e.printStackTrace();
        }
    }

    public InputStream getSelenoidVideo(URL url) {
        int lastSize = 0;
        int exit = 2;
        for (int i = 0; i < 20; i++) {
            try {
                int size = Integer.parseInt(url.openConnection().getHeaderField("Content-Length"));
                System.out.println("Content-Length: " + size);
                System.out.println(i);
                if (size > lastSize) {
                    lastSize = size;
                    Thread.sleep(500);
                } else if (size == lastSize) {
                    exit--;
                    Thread.sleep(200);
                }
                if (exit < 0) {
                    return url.openStream();
                }
            } catch (Exception e) {
                System.out.println("checkSelenoidVideo");
                e.printStackTrace();
            }
        }

        return null;
    }

    public void deleteSelenoidVideo(URL url) {
        try {
            HttpURLConnection deleteConn = (HttpURLConnection) url.openConnection();
            deleteConn.setRequestMethod("DELETE");
            deleteConn.connect();
            deleteConn.disconnect();
        } catch (IOException e) {
            System.out.println("deleteSelenoidVideo");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Video HTML", type = "text/html", fileExtension = ".html")
    public static String videoInHtml(String sessionId) {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + getVideoUrl(sessionId)
                +"' type='video/mp4'></video></body></html>";
    }

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Видео", type = "video/mp4", fileExtension = ".mp4")
    public static byte[] attachVideo(String sessionId) {
        try {
            File mp4 = new File(System.getProperty("java.io.tmpdir") + "temp.mp4");
            mp4.deleteOnExit();
            FileUtils.copyURLToFile(getVideoUrl(sessionId), mp4);
            return Files.toByteArray(mp4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static String getSessionId() {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }

}
