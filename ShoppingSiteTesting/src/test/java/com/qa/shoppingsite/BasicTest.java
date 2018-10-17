package com.qa.shoppingsite;

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
		System.out.println("Setted up a chrome web driver chromedriver.exe");
		
		driver = new ChromeDriver();
		System.out.println("Created a new ChromeDriver");
		
		System.out.println("Test: " + iCount + "/" + iTotalTestCount + " Starting");
	}
	
	@Test
	public void createNewUserTest() {
		
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");		
		System.out.println("Opened the site");		
		
			
		String stringPlaceHolder = driver.getTitle();
		assertEquals("matching title with what's expected", "My Store", stringPlaceHolder);
		
		driver.get("http://automationpractice.com/index.php");
		
		WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"search_query_top\"]"));
		searchBar.click();
		searchBar.clear();
		searchBar.sendKeys("dress");
	    driver.findElement(By.name("submit_search")).click();
	    
	    stringPlaceHolder = driver.getTitle();
		assertEquals("matching title with what's expected", "Search - My Store", stringPlaceHolder);
		
		
	    	    
	    WebElement linkTextDress = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul"));
	    linkTextDress = linkTextDress.findElement(By.linkText("Printed Summer Dress"));
	    stringPlaceHolder = linkTextDress.getText();
	    boolean bContainsDress = stringPlaceHolder.equals("Printed Summer Dress");
	  
	    assertTrue("Checking if the Printed Summer Dress was found in a linkText Element's text", bContainsDress);
	    
	   /* WebElement spanCounterForSearches = driver.findElement(By.className("heading-counter"));
	    stringPlaceHolder = spanCounterForSearches.getText();
	    Boolean bContainsZero = stringPlaceHolder.contains("0");
	    
	    assertFalse("Checking if the text inside span under class=\"heading-counter\" doesn't contain 0, meaning there were results found for dress", bContainsZero);*/
	    		
	    		
	} 
	
	@After
	public void tearDown() {	
		iTotalTestCount++;
		System.out.println("Test: " + iCount + "/" + iTotalTestCount + " Finished");
		driver.quit();
		System.out.println("Closed chrome web driver");
	}
}
