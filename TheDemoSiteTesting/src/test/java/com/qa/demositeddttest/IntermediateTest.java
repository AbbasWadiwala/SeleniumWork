package com.qa.demositeddttest;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

public class IntermediateTest {
	
	public static WebDriver driver = null;
	public static int countInt = 1;
	public static int totalTestCountInt = 2;
	public static boolean replaceExistingBoolean = true;
	static ExtentReports extentReportRef;
	static ExtentTest extentTestRef;
	static ExtentTest extentTestRef2;
	
	
	@BeforeClass
	public static void beforeClass() {
		// Initialise ExtentReports with a file path 
		extentReportRef = new ExtentReports(Constant.testReportFilePathString, replaceExistingBoolean);	
		ExcelUtils.setExcelFile(Constant.Path_TestData+Constant.FileName_TestData, 0);
		
		for(int i = 1; i < ExcelUtils.getExcelWSheet().getPhysicalNumberOfRows(); i++) {
			 ExcelUtils.setCellData(Constant.Path_TestData+Constant.FileName_TestData, "Test Not Completed", i, 3);
			 ExcelUtils.setCellData(Constant.Path_TestData+Constant.FileName_TestData, "Test Not Completed", i, 4);
		}
		
	}
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", Constant.chromeDriverFilePathString);
		System.out.println("Setted up a chrome web driver: chromedriver.exe");
		
		driver = new ChromeDriver();
		System.out.println("Created a new ChromeDriver");			
		System.out.println("Test: " + countInt + "/" + totalTestCountInt + " Starting");
	}
	
	@Test
	public void demoSiteCorrectLandingPageTest(){
		
		// Initialise start the test
		extentTestRef = extentReportRef.startTest("Check Landing Page Is Correct");		
		// add a note to the test
		extentTestRef.log(LogStatus.INFO, "Browser started");			
				
		driver.manage().window().maximize();		
		extentTestRef.log(LogStatus.INFO, "Browser Window Maximised");	
		
		driver.get(Constant.demoSiteLandingURL);
		extentTestRef.log(LogStatus.INFO, "Site Opened");
		
		String driverTitleString = driver.getTitle();
		
		if(driverTitleString.equals(Constant.demoSiteLandingTitle)) {
			// report the test as a pass
			extentTestRef.log(LogStatus.PASS, "Correct Page Was Opened");
	    }
	    else {
	    	extentTestRef.log(LogStatus.FAIL, "Correct Page Was Not Loaded");
	    }		
		assertEquals("matching title with what's expected", Constant.demoSiteLandingTitle, driverTitleString);
		
		extentReportRef.endTest(extentTestRef);		
	}
	
	@Test
	public void createNewUserThenLoginTest() throws InterruptedException {	
		
		
		for(int i = 1; i < ExcelUtils.getExcelWSheet().getPhysicalNumberOfRows(); i++) {
			
			// Initialise start the test
			extentTestRef = extentReportRef.startTest("Verify Create User And Login Test: " + i);
			// add a note to the test
			extentTestRef.log(LogStatus.INFO, "Browser started");			
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);	
			
			driver.manage().window().maximize();
						
			driver.get(Constant.demoSiteAddUserURL);	
			
			System.out.println("Opened the site");		
			extentTestRef.log(LogStatus.INFO, "Site Opened");
			
			String driverTitleString = driver.getTitle();	
			
			if(driverTitleString.equals(Constant.demoSiteAddUserTitle)) {
				// report the test as a pass
				extentTestRef.log(LogStatus.PASS, "Correct Page Was Opened");
		    }
		    else {
		    	extentTestRef.log(LogStatus.FAIL, "Correct Page Was Not Loaded");
		    }		
			assertEquals("matching title with what's expected", Constant.demoSiteAddUserTitle, driverTitleString);
			
			
			DemoSiteAddAUserPage addUserPage = PageFactory.initElements(driver, DemoSiteAddAUserPage.class);
			DemoSiteLoginPage  loginPage = PageFactory.initElements(driver, DemoSiteLoginPage.class);
			
			addUserPage.addUser(ExcelUtils.getCellData(i, 0), ExcelUtils.getCellData(i, 1));
			
		    extentTestRef.log(LogStatus.INFO, "New User Details Entered And submitted - User: " + ExcelUtils.getCellData(i, 0) + ", Password: " + ExcelUtils.getCellData(i, 1));
		    
		    addUserPage.goToLoginPage();
		    
		    extentTestRef.log(LogStatus.INFO, "Opened Login Page");
		    
		    loginPage.login(ExcelUtils.getCellData(i, 0), ExcelUtils.getCellData(i, 1));
		    
		    extentTestRef.log(LogStatus.INFO, "User Details Entered And Login Attempted - User: " + ExcelUtils.getCellData(i, 0) + ", Password: " + ExcelUtils.getCellData(i, 1));
		    
		    if(!loginPage.getLoginStatus().equals(ExcelUtils.getCellData(i, 2))) {		    
		    	extentTestRef.log(LogStatus.FAIL, "Login Failed");
		    	extentReportRef.endTest(extentTestRef);
		    	extentReportRef.flush();
		    	ExcelUtils.setCellData(Constant.Path_TestData+Constant.FileName_TestData, loginPage.getLoginStatus(), i, 3);
		    	ExcelUtils.setCellData(Constant.Path_TestData+Constant.FileName_TestData, "Failed", i, 4);
		    }
		    
		    assertEquals("Tested Creating New User And Then Logging In",ExcelUtils.getCellData(i, 2), loginPage.getLoginStatus());	
		    ExcelUtils.setCellData(Constant.Path_TestData+Constant.FileName_TestData, loginPage.getLoginStatus(), i, 3);
	    	ExcelUtils.setCellData(Constant.Path_TestData+Constant.FileName_TestData, "Passed", i, 4);
		    // report the test as a pass
	    	extentTestRef.log(LogStatus.PASS, "Login Was Successful");
		    extentReportRef.endTest(extentTestRef);
			
		}
		
	} 
	
	
	@After
	public void tearDown() {			
		System.out.println("Test: " + countInt + "/" + totalTestCountInt + " Finished");
		driver.quit();
		System.out.println("Closed chrome web driver");
		countInt++;
	}
	
	@AfterClass
	public static void afterClass() {
		extentReportRef.flush();	
	}
	
	
	
	
}
