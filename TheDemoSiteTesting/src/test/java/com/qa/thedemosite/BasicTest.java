package com.qa.thedemosite;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BasicTest {
	public WebDriver driver = null;
	public static int iCount = 1;
	public static int iTotalTestCount = 1;
	public static boolean replaceExisting = true;
	static String filePath = "TestReports\\LoginTest.html";
	static ExtentReports extent;
	static ExtentTest test;
	
	@BeforeClass
	public static void beforeClass() {
		// Initialise ExtentReports with a file path 
		extent = new ExtentReports(filePath, replaceExisting);	
		// Initialise start the test
		test = extent.startTest("Verify create user and login");
	}
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		System.out.println("Setted up a chrome web driver from C:\\Users\\\\Admin\\Desktop\\chromedriver_win32\\chromedriver.exe");
		
		driver = new ChromeDriver();
		System.out.println("Created a new ChromeDriver");
		// add a note to the test
		test.log(LogStatus.INFO, "Browser started");
		
		System.out.println("Test: " + iCount + "/" + iTotalTestCount + " Starting");
			}
	
	@Test
	public void createNewUserTest() {	
		
		driver.manage().window().maximize();
		driver.get("http://thedemosite.co.uk/addauser.php");		
		System.out.println("Opened the site");		
		test.log(LogStatus.INFO, "Site Opened");
		
		String s = driver.getTitle();	
		
		if(s.equals("Add a user - FREE PHP code and SQL")) {
			// report the test as a pass
			test.log(LogStatus.PASS, "Correct Page Was Opened");
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Correct Page Was Not Loaded");
	    }
		
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
	    
	    test.log(LogStatus.INFO, "New User Details Entered And submitted");
	    
	    WebElement loginStatus = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b"));
	    
	    if(loginStatus.getText().equals("**Successful Login**")) {
	    	// report the test as a pass
			test.log(LogStatus.PASS, "Login Was Successful");
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Login Failed");
	    }
	    assertEquals("","**Successful Login**", loginStatus.getText());
	} 
	
	@After
	public void tearDown() {	
		iTotalTestCount++;
		System.out.println("Test: " + iCount + "/" + iTotalTestCount + " Finished");
		driver.quit();
		System.out.println("Closed chrome web driver");
	}
	
	@AfterClass
	public static void afterClass() {
		extent.endTest(test);
		extent.flush();
	}
}
