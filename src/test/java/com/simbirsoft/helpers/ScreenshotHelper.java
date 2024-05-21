package com.simbirsoft.helpers;

import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ScreenshotHelper {

    @Attachment(value = "Attachment Screenshot", type = "image/png")
    public static byte[] makeScreenshotToByte(WebDriver driver) throws IOException {
        Screenshot screenshot = new AShot()
            .shootingStrategy(ShootingStrategies.viewportPasting(100))
            .takeScreenshot(driver);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(screenshot.getImage(), "jpg", baos);
        return baos.toByteArray();
    }

}
