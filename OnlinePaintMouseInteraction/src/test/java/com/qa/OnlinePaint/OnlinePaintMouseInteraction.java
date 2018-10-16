package com.qa.OnlinePaint;



import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class OnlinePaintMouseInteraction {

	public WebDriver driver = null;
	public static int iCount = 1;
	public static int iTotalTestCount = 1;
	Actions action = null;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\chromedriver_win32\\chromedriver.exe");
		System.out.println("Setted up a chrome web driver from C:\\\\Users\\\\Admin\\\\Desktop\\\\chromedriver_win32\\\\chromedriver.exe");
		
		driver = new ChromeDriver();
		System.out.println("Created a new ChromeDriver");
		action = new Actions(driver);
		System.out.println("Created a new Action");
		
		System.out.println("Test: " + iCount + "/" + iTotalTestCount + " Starting");
	}
	
	@Test
	public void mouseInteraction() {
		
		driver.manage().window().maximize();
		
		System.out.println("Opened the site");		
		
			
		  	driver.get("https://www.google.com/");
		    driver.findElement(By.id("lst-ib")).click();
		    driver.findElement(By.id("lst-ib")).clear();
		    driver.findElement(By.id("lst-ib")).sendKeys("online paint");
		    driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
		    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Web results'])[1]/following::h3[1]")).click();
		    
		    
		    
		    driver.findElement(By.id("brush")).click();
		    action.moveByOffset(550, 300).clickAndHold().moveByOffset(0, 100).release().perform();
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
