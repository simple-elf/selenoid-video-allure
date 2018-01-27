# selenoid-video-allure
This is example of using selenoide video recording feature with Allure.

How to add link to video on Selenoid host:
```
@Attachment(value = "Video HTML", type = "text/html", fileExtension = ".html")
public static String videoInHtml(String sessionId) {
    return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
           + "http://1.1.1.1:4444/video/" + sessionId + ".mp4"
           +"' type='video/mp4'></video></body></html>";
}
```
This works if you don't delete video on selenoid, and selenoid run forever =)
