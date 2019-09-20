[![CircleCI](https://circleci.com/gh/simple-elf/selenoid-video-allure.svg?style=svg)](https://circleci.com/gh/simple-elf/selenoid-video-allure)

# selenoid-video-allure
This is example of using selenoide video recording feature with Allure.

How to add link to video on Selenoid host:
```
@Attachment(value = "Video HTML", type = "text/html", fileExtension = ".html")
public static String videoInHtml(String sessionId) {
    return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
           + "http://127.0.0.1:4444/video/" + sessionId + ".mp4"
           +"' type='video/mp4'></video></body></html>";
}
```
This works if you don't delete video on selenoid, and selenoid run forever =)

If we want to download video file to Jenkins, and store it in Allure report - there is two ways.
First not so good, because we get file from http server to temp dir, and then return it like byte[] array:
```
@Attachment(value = "Видео", type = "video/mp4", fileExtension = ".mp4")
public static byte[] attachVideo(String sessionId) {
    try {
        File mp4 = new File(System.getProperty("java.io.tmpdir") + "temp.mp4");
        mp4.deleteOnExit();
        FileUtils.copyURLToFile("http://127.0.0.1:4444/video/" + sessionId + ".mp4", mp4);
        return Files.toByteArray(mp4);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return new byte[0];
}
```

Second is better, because we get InputStream from http server, and pass it to native Allure method:
```
private static String selenoidUrl = "http://127.0.0.1:4444";
public void attachAllureVideo(String sessionId) {
    try {
        URL videoUrl = new URL(selenoidUrl + "/video/" + sessionId + ".mp4");
        InputStream is = null;
        Thread.sleep(1000);
        for (int i = 0; i < 10; i++) {
            try {
                is = videoUrl.openStream();
                i = 10;
            } catch (FileNotFoundException e) {
                Thread.sleep(1000);
            }
        }
        Allure.addAttachment("Video", "video/mp4", is, "mp4");
    } catch (Exception e) {
        System.out.println("attachAllureVideo");
        e.printStackTrace();
    }
}
```

But there is big problem. Video file renaming after finishing to sessionId. And some time I get file with ~28Kb.
So we need to add some method for checking http head for file size, until it be unchangeable.
And also we can add http request for deleting file on Selenide side.