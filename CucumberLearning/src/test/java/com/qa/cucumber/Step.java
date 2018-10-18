package com.qa.cucumber;

import static org.junit.Assert.assertEquals;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Step {
	
	public static WebDriver driver = null;	
	// Initialise ExtentReports with a file path 
			
	static ExtentReports extentReportRef = new ExtentReports(Constants.Path_TestReportString + Constants.FileName_TestReportString, Constants.ReplaceExistingBoolean);
	ExtentTest extentTestRef;
	
	public static int iTestCounter = 1;	
	
	@Before
	public void setUp() {		
		System.getProperty("webdriver.chrome.driver", Constants.Path_ChromeDriverString + Constants.FileName_ChromeDriverFileNameString);
		if(iTestCounter == 1) {
			// Initialise start the test
			extentTestRef = extentReportRef.startTest("Browse The Items Avaiable On The Website");
		}
		else if (iTestCounter == 2)
		{
			extentTestRef = extentReportRef.startTest("Add an item to the checkout");
		}
	}
	
	@After
	public void tearDown() {		
		extentReportRef.flush();	
	}
	
	
	@Given("^the correct web address$")
	public void the_correct_web_address()  {				
		
		driver = new ChromeDriver();	
		// add a note to the test
		extentTestRef.log(LogStatus.INFO, "Browser started");	
		
		driver.manage().window().maximize();		
		extentTestRef.log(LogStatus.INFO, "Browser Window Maximised");	
		
		driver.get(Constants.TeaTestingWebSiteURL);
		extentTestRef.log(LogStatus.INFO, "Site Opened URL: " + Constants.TeaTestingWebSiteURL);
		
		String driverTitleString = driver.getTitle();
		extentTestRef.log(LogStatus.INFO, "Retrieved PageTitle: " + driverTitleString );	
		
		extentTestRef.log(LogStatus.INFO, "Comparing Page Title To Expected Page Title: " + Constants.TeaTestWebTitle);	
		if(!driverTitleString.equals(Constants.TeaTestWebTitle)) {		
	    	extentTestRef.log(LogStatus.FAIL, "Correct Page Was Not Loaded");
	    	extentReportRef.endTest(extentTestRef);
	    	extentReportRef.flush();	    	
	    }		
		assertEquals("Matching page title with what's expected", Constants.TeaTestWebTitle, driverTitleString);
		// report the test as a pass
		extentTestRef.log(LogStatus.PASS, "Correct Page Was Opened");
		       
	}

	@When("^I navigate to the 'Menu' page$")
	public void i_navigate_to_the_Menu_page() throws Throwable {		
	
		TeaTestingWebLandingPage landingPage = PageFactory.initElements(driver, TeaTestingWebLandingPage.class);
		landingPage.goToMenuPage();
		extentTestRef.log(LogStatus.INFO, "Clicked Menu Button: ");	
		
		String driverTitleString = driver.getTitle();
		extentTestRef.log(LogStatus.INFO, "Retrieved PageTitle: " + driverTitleString );	
		
		extentTestRef.log(LogStatus.INFO, "Comparing Page Title To Expected Page Title: " + Constants.TeaTestWebMenuPageTitle);	
		if(!driverTitleString.equals(Constants.TeaTestWebMenuPageTitle)) {		
	    	extentTestRef.log(LogStatus.FAIL, "Correct Page Was Not Loaded");
	    	extentReportRef.endTest(extentTestRef);
	    	extentReportRef.flush();	    	
	    }		
		assertEquals("Matching page title with what's expected", Constants.TeaTestWebMenuPageTitle, driverTitleString);
		// report the test as a pass
		extentTestRef.log(LogStatus.PASS, "Correct Page Was Opened");
		
	}

	@Then("^I can browse a list of the available products\\.$")
	public void i_can_browse_a_list_of_the_available_products() throws Throwable {
		
		TeaTestingMenuPage menuPage = null;
		
		menuPage = PageFactory.initElements(driver, TeaTestingMenuPage.class);
		
		Boolean productExists = menuPage.productNameWebElementExists();
		
		if(!productExists) {
			extentTestRef.log(LogStatus.FAIL, "Product Element Was Not Found");
			extentReportRef.endTest(extentTestRef);
	    	extentReportRef.flush();	
	    	driver.close();
	    }			
		
		assertEquals("Checking If A Product Is Available To Browse", true, productExists);
		// report the test as a pass
		extentTestRef.log(LogStatus.PASS, "Product Element Was Found");
		extentReportRef.endTest(extentTestRef);		
		driver.close();
	    
	   iTestCounter++;
	}

	@When("^I click the checkout button$")
	public void i_click_the_checkout_button() throws Throwable {
							
		try {
			TeaTestingWebLandingPage landingPage = PageFactory.initElements(driver, TeaTestingWebLandingPage.class);
			extentTestRef.log(LogStatus.INFO, "Click On Check Out Button Attempted: ");	
			landingPage.goToCheckOutpage();
			extentTestRef.log(LogStatus.PASS, "Button Was Clicked Succesfully");
		} catch(NoSuchElementException e){
			extentTestRef.log(LogStatus.FAIL, "Button Was Not Found");
			extentReportRef.endTest(extentTestRef);
	    	extentReportRef.flush();	 
	    	driver.close();
		}					
		
	}

	@Then("^I am taken to the checkout page$")
	public void i_am_taken_to_the_checkout_page() throws Throwable {
		
		String driverTitleString = driver.getTitle();
		extentTestRef.log(LogStatus.INFO, "Retrieved PageTitle: " + driverTitleString );	
		
		extentTestRef.log(LogStatus.INFO, "Comparing Page Title To Expected Page Title: " + Constants.TeaTestWebCheckOutPageTitle);	
		if(!driverTitleString.equals(Constants.TeaTestWebCheckOutPageTitle)) {		
	    	extentTestRef.log(LogStatus.FAIL, "Correct Page Was Not Loaded");
	    	extentReportRef.endTest(extentTestRef);
	    	extentReportRef.flush();	    	
	    }		
		assertEquals("Matching page title with what's expected", Constants.TeaTestWebCheckOutPageTitle, driverTitleString);
		// report the test as a pass
		extentTestRef.log(LogStatus.PASS, "Correct Page Was Opened");
		extentReportRef.endTest(extentTestRef);
	    
		driver.close();
	}
	
}
