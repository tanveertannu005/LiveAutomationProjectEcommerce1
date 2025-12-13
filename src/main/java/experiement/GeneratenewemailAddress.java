package experiement;

import java.util.Date;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.awt.image.BufferedImage;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;


import java.util.Date;

public class GeneratenewemailAddress {
    public static void main(String[] args) {
        System.out.println(generatebrandnewEmailadress());
    }
    
    public static String generatebrandnewEmailadress() {
        Date date = new Date();
        String datesString = date.toString();
        String dateStringwithoutSpaces = datesString.replaceAll("\\s", "");
String datestringwithoutspacesAndcolons=        dateStringwithoutSpaces.replaceAll("\\:","");
 String brandNewEmail=    datestringwithoutspacesAndcolons+"@gmail.com";
 return brandNewEmail;
 }
    
    public static boolean compareTwoScreenshots(String actualImagePath, String expectedImagePath) {
        BufferedImage bufferedActualImage = null;
        BufferedImage bufferedExpectedImage = null;

        try {
            bufferedActualImage = ImageIO.read(new File(System.getProperty("user.dir") + actualImagePath));
            bufferedExpectedImage = ImageIO.read(new File(System.getProperty("user.dir") + expectedImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageDiff diff = new ImageDiffer().makeDiff(bufferedExpectedImage, bufferedActualImage);

        return !diff.hasDiff();
    }
    
    public static void scrennshots(WebDriver driver, String ScreenshotPath) {
    	TakesScreenshot ts = (TakesScreenshot) driver;
        File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
       
        try {
            FileHandler.copy(srcScreenshot,
                new File(System.getProperty("ScreenshotPath")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}    
