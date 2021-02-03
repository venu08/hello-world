package com.example.demo;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class TakeScreenshots {

    public static void main(String[] args) {
        try {
         String dirPath = "C:/Test/" ;
         
         //Create folder/directory if not exist.
         File file = new File(dirPath);
            if (!file.exists()) {
                if (file.mkdir()) {
                    System.out.println("Directory  is created!");
                } else {
                    System.out.println("Failed to create directory!");
                }
            }

            XWPFDocument docx = new XWPFDocument();
            XWPFRun run = docx.createParagraph().createRun();
            FileOutputStream out = new FileOutputStream(dirPath+"Test2.docx");
             String Transaction_score = "https://fedex1-test.saas.appdynamics.com/controller/#/location=APP_BT_TX_SCORE&timeRange=Custom_Time_Range.BETWEEN_TIMES.1608301800000.1608283800000.300&application=43337&businessTransaction=13222003";
             String urlString = "https://fedex1-test.saas.appdynamics.com/controller/#/location=APP_BT_DETAIL&timeRange=Custom_Time_Range.BETWEEN_TIMES.1608301800000.1608283800000.300&application=43337&businessTransaction=13222003&dashboardMode=force";
             
             // Add for loop for example, because here we are capturing 5 screenhots
         //   for (int counter = 1; counter <= 2; counter++) {
                run.setText("DashBoard");
                captureScreenShot(urlString, run, out, dirPath);
                TimeUnit.SECONDS.sleep(1);
                run.setText("Business Transaction");
                captureScreenShot(Transaction_score, run, out, dirPath);
                TimeUnit.SECONDS.sleep(1);
        //    }
            System.out.println("Write to doc file sucessfully...");

            docx.write(out);
            out.flush();
            out.close();
            docx.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void captureScreenShot(String urlString, XWPFRun run, FileOutputStream out, String dirPath) throws Exception {

    //	String urlString = "https://fedex1-test.saas.appdynamics.com/controller/#/location=APP_BT_DETAIL&timeRange=Custom_Time_Range.BETWEEN_TIMES.1608301800000.1608283800000.300&application=43337&businessTransaction=13222003&dashboardMode=force";
        Desktop desktop = java.awt.Desktop.getDesktop();
        URI oURL = new URI(urlString);
        desktop.browse(oURL);
        
        Thread.sleep(15000);
    	
		/*
		 * String screenshot_name = System.currentTimeMillis() + ".png"; BufferedImage
		 * image = new Robot().createScreenCapture(new
		 * Rectangle(Toolkit.getDefaultToolkit().getScreenSize())); File file = new
		 * File(dirPath + screenshot_name); ImageIO.write(image, "png", file);
		 * InputStream pic = new FileInputStream(dirPath + screenshot_name);
		 * run.addBreak(); run.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG,
		 * screenshot_name, Units.toEMU(500), Units.toEMU(350)); pic.close();
		 * file.delete();
		 */
        
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_DOWN);
        robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
        
        Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
     //   File file = new File("screen-capture.png");
  //      boolean status = ImageIO.write(bufferedImage, "png", file);
        
        
        String screenshot_name = System.currentTimeMillis() + ".png";
     //   BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        File file = new File(dirPath + screenshot_name);
        ImageIO.write(bufferedImage, "png", file);
        InputStream pic = new FileInputStream(dirPath + screenshot_name);
        run.addBreak();
        run.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG, screenshot_name, Units.toEMU(500), Units.toEMU(350));
        pic.close();
        file.delete();
    }

}