package com.qa.thedemosite;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicTest {
	public WebDriver driver = null;
	public static int iCount = 1;
	public static int iTotalTestCount = 1;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		System.out.println("Setted up a chrome web driver from C:\\Users\\\\Admin\\Desktop\\chromedriver_win32\\chromedriver.exe");
		
		driver = new ChromeDriver();
		System.out.println("Created a new ChromeDriver");
		
		System.out.println("Test: " + iCount + "/" + iTotalTestCount + " Starting");
	}
	
	@Test
	public void createNewUserTest() {
		
		driver.manage().window().maximize();
		driver.get("http://thedemosite.co.uk/addauser.php");		
		System.out.println("Opened the site");		
		
			
		String s = driver.getTitle();
		assertEquals("matching title with what's expected", "Add a user - FREE PHP code and SQL", s);
		
		driver.findElement(By.name("username")).click();
	    driver.findElement(By.name("username")).clear();
	    driver.findElement(By.name("username")).sendKeys("test123");
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("test");
	    driver.findElement(By.name("FormsButton2")).click();
	    driver.findElement(By.linkText("4. Login")).click();
	    driver.findElement(By.name("username")).click();
	    driver.findElement(By.name("username")).clear();
	    driver.findElement(By.name("username")).sendKeys("test123");
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("test");
	    driver.findElement(By.name("FormsButton2")).click();
	    
	    WebElement loginStatus = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b"));
	    
	    assertEquals("","**Successful Login**", loginStatus.getText());
	} 
	
	@After
	public void tearDown() {	
		iTotalTestCount++;
		System.out.println("Test: " + iCount + "/" + iTotalTestCount + " Finished");
		driver.quit();
		System.out.println("Closed chrome web driver");
	}
}
