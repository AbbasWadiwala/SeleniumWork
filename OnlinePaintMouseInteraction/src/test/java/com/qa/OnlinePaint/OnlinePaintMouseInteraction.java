package com.qa.OnlinePaint;




import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class OnlinePaintMouseInteraction {

	public WebDriver driver = null;
	public static int iCount = 1;
	public static int iTotalTestCount = 1;
	Actions action = null;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		System.out.println("Setted up a chrome web driver from C:\\\\Users\\\\Admin\\\\Desktop\\\\chromedriver_win32\\\\chromedriver.exe");
		
		driver = new ChromeDriver();
		System.out.println("Created a new ChromeDriver");
		action = new Actions(driver);
		System.out.println("Created a new Action");
		
		System.out.println("Test: " + iCount + "/" + iTotalTestCount + " Starting");
	}
	
	@Test
	public void mouseInteraction() throws InterruptedException {
		
		driver.manage().window().maximize();
		
		System.out.println("Opened the site");		
		
			
		  	driver.get("https://www.google.com/");
		    driver.findElement(By.id("lst-ib")).click();
		    driver.findElement(By.id("lst-ib")).clear();
		    driver.findElement(By.id("lst-ib")).sendKeys("online paint");
		    driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
		    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Web results'])[1]/following::h3[1]")).click();
		    
		    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);	
		    
		    driver.findElement(By.id("brush")).click();
		       
		    driver.findElement(By.id("color")).click();
		    
		    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Pattern'])[1]/following::div[6]")).click();
		    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Pattern'])[1]/following::div[4]")).click();
		    
		    
		    WebElement paintingSurface = driver.findElement(By.id("catch"));
		    
		    Point paintingSurfaceLocation = paintingSurface.getLocation();
		    int paintingSurfaceLocationXPos = paintingSurfaceLocation.x;
		    int paintingSurfaceLocationYPos = paintingSurfaceLocation.y;
		    
		    Dimension paintingSurfaceDimensions = paintingSurface.getSize();
		    
		    int paintingSurfaceWidth = paintingSurfaceDimensions.width;		    
		    int paintingSurfaceHeight = paintingSurfaceDimensions.height;
		    
		    
		    System.out.println("Xpos: " + paintingSurfaceLocationXPos + ", Ypos: " + + paintingSurfaceLocationYPos);
		    System.out.println("Width: " + paintingSurfaceWidth + ", Height: " + + paintingSurfaceHeight);
		    action.moveToElement(paintingSurface);
		    action.moveByOffset(-(paintingSurfaceWidth/2)+20, -(paintingSurfaceHeight/2)+20);
		   
		    action.clickAndHold().moveByOffset(paintingSurfaceWidth-40, 0).moveByOffset(0, paintingSurfaceHeight-40).
		    moveByOffset(-paintingSurfaceWidth+40, 0).moveByOffset(0, -paintingSurfaceHeight+40)
		    .release().perform();
		    
		    
		    
		    
		    
		    /*driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Sign In'])[1]/following::div[77]")).click();		    
		    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='â–¼'])[1]/following::span[3]")).click();		    
		    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='â–¼'])[1]/following::span[3]")).click();*/    
		    
		    
		    driver.findElement(By.id("btnBrush")).click();
		    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);	
		    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Sign In'])[1]/following::div[77]")).click();
		    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='▼'])[1]/following::span[3]")).click();
		    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='▼'])[1]/following::span[3]")).click();
		    
		    
		    action.moveToElement(paintingSurface);
		    Thread.sleep(1000);
		    action.moveByOffset(-(paintingSurfaceWidth/2)+300, -(paintingSurfaceHeight/2)+250).clickAndHold().moveByOffset(0, 100).release().perform();
		    action.moveByOffset(0,-100).clickAndHold().moveByOffset(50, 0).release().perform();
		    action.clickAndHold().moveByOffset(0, 100).release().perform();
		    action.moveByOffset(0, -60).clickAndHold().moveByOffset(-50, 0).release().perform();
		    
		    action.moveByOffset(80, -40).clickAndHold().moveByOffset(50, 0).release().perform();
		    action.moveByOffset(-50, 0).clickAndHold().moveByOffset(0, 100).release().perform();
		    action.clickAndHold().moveByOffset(50, 0).release().perform();
		    action.clickAndHold().moveByOffset(0, -45).release().perform();
		    action.clickAndHold().moveByOffset(-50, 0).release().perform();
		    action.moveByOffset(0, -10).clickAndHold().moveByOffset(50, 0).release().perform();
		    action.clickAndHold().moveByOffset(0, -45).release().perform();
		    
		    action.moveByOffset(30, 0).clickAndHold().moveByOffset(50, 0).release().perform();
		    action.moveByOffset(-50, 0).clickAndHold().moveByOffset(0, 100).release().perform();
		    action.clickAndHold().moveByOffset(50, 0).release().perform();
		    action.clickAndHold().moveByOffset(0, -45).release().perform();
		    action.clickAndHold().moveByOffset(-50, 0).release().perform();
		    action.moveByOffset(0, -10).clickAndHold().moveByOffset(50, 0).release().perform();
		    action.clickAndHold().moveByOffset(0, -45).release().perform();
		    
		    action.moveByOffset(30, 0).clickAndHold().moveByOffset(0, 100).release().perform();
		    action.moveByOffset(0,-100).clickAndHold().moveByOffset(50, 0).release().perform();
		    action.clickAndHold().moveByOffset(0, 100).release().perform();
		    action.moveByOffset(0, -60).clickAndHold().moveByOffset(-50, 0).release().perform();
		    
		    action.moveByOffset(80, -40).clickAndHold().moveByOffset(50, 0).release().perform();
		    action.moveByOffset(-50,0).clickAndHold().moveByOffset(0, 50).release().perform();
		    action.clickAndHold().moveByOffset(50, 0).release().perform();
		    action.clickAndHold().moveByOffset(0, 50).release().perform();
		    action.clickAndHold().moveByOffset(-50, 0).release().perform();
		       
	} 
	
	@After
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		iTotalTestCount++;
		System.out.println("Test: " + iCount + "/" + iTotalTestCount + " Finished");
		driver.close();
		
		System.out.println("Closed chrome web driver");
	}
}
