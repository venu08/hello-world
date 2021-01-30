package com.example.demo;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotEx {
	
	@Test
	public void takesScreenshotEx() throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "C:\\Apache\\chrome-driver-87.0.4280.88\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
	    driver.get("http://demo.automationtesting.in/Alerts.html");
	    TakesScreenshot screen = (TakesScreenshot) driver;
	    File source =screen.getScreenshotAs(OutputType.FILE);
	    File destination = new File("C:\\CVM\\Test-file\\screen.png");
	    FileHandler.copy(source, destination);
	}
	
	@Test
	public void robotScreenshotEx() throws InterruptedException, IOException, AWTException {
		System.setProperty("webdriver.chrome.driver", "C:\\Apache\\chrome-driver-87.0.4280.88\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
	    driver.get("http://demo.automationtesting.in/Alerts.html");
	    driver.findElement(By.xpath("//button[@onclick='alertbox()']")).click();
	    Thread.sleep(5000);  
	    Robot robot = new Robot();
	    Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
	    Rectangle rec = new Rectangle(di);
	    BufferedImage source = robot.createScreenCapture(rec);
	    File destination = new File("C:\\CVM\\Test-file\\screen1.png");
	    
	    ImageIO.write(source, "png", destination);
	}

}
