package com.qa.testingelementswithwaits;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class WaitTests {
	
	public WebDriver driver = null;
	public static int countInt = 1;
	public static int totalTestCountInt = 1;
	public static boolean replaceExistingBoolean = true;
	static ExtentReports extentReportRef;
	static ExtentTest extentTestRef;
	
	@BeforeClass
	public static void beforeClass() {
		// Initialise ExtentReports with a file path 
		extentReportRef = new ExtentReports(Constant.testReportFilePathString, replaceExistingBoolean);	
		// Initialise start the test
		extentTestRef = extentReportRef.startTest("Verify Create User And Login");
	}
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", Constant.chromeDriverFilePathString);
		System.out.println("Setted up a chrome web driver: chromedriver.exe");
		
		driver = new ChromeDriver();
		System.out.println("Created a new ChromeDriver");
		// add a note to the test
		extentTestRef.log(LogStatus.INFO, "Browser started");		
		System.out.println("Test: " + countInt + "/" + totalTestCountInt + " Starting");
	}
	
	@Test
	public void createNewUserThenLoginTest() throws InterruptedException {	
		
		driver.manage().window().maximize();
		driver.get(Constant.iHateYouShafeqqSiteURL);	
		
		System.out.println("Opened the site");		
		extentTestRef.log(LogStatus.INFO, "Site Opened");
		
		String s = driver.getTitle();	
		
		if(s.equals("Wait test")) {
			// report the test as a pass
			extentTestRef.log(LogStatus.PASS, "Correct Page Was Opened");
	    }
	    else {
	    	extentTestRef.log(LogStatus.FAIL, "Correct Page Was Not Loaded");
	    }		
		assertEquals("matching title with what's expected", "Wait test", s);
		
		
		//IHateYouPage iHateYouPage = PageFactory.initElements(driver, IHateYouPage.class);	
		
		
		extentTestRef.log(LogStatus.INFO, "Checking for I Hate You Shafeeq Text");
	    
		IHateYouPage iHateYouPage = PageFactory.initElements(driver, IHateYouPage.class);		
		
		
		if(iHateYouPage.getDynamicElement(driver).isDisplayed()) {
	    	// report the test as a pass
	    	extentTestRef.log(LogStatus.PASS, "The Shafeeq Text Appeared");
	    }
	    else {
	    	extentTestRef.log(LogStatus.FAIL, "The Shafeeq Text Appeared Never Came");
	    }
	    
	    assertTrue("Checking if The Shafeeq text appeared",iHateYouPage.getDynamicElement(driver).isDisplayed());
	} 
	
	
	@After
	public void tearDown() {	
		totalTestCountInt++;
		System.out.println("Test: " + countInt + "/" + totalTestCountInt + " Finished");
		driver.quit();
		System.out.println("Closed chrome web driver");
	}
	
	@AfterClass
	public static void afterClass() {
		extentReportRef.endTest(extentTestRef);
		extentReportRef.flush();
	}
	
	
	
	
}




