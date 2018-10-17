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
	public static FileInputStream fileInputStream = null;
	public static XSSFWorkbook workBook = null;
	public static XSSFSheet sheet = null;
	
	@BeforeClass
	public static void beforeClass() {
		// Initialise ExtentReports with a file path 
		extentReportRef = new ExtentReports(Constant.testReportFilePathString, replaceExistingBoolean);	
		try {
			fileInputStream = new FileInputStream(Constant.Path_TestData + Constant.FileName_TestData);
			workBook = new XSSFWorkbook(fileInputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet = workBook.getSheetAt(0);
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
		
		// Initialise start the test
		extentTestRef = extentReportRef.startTest("Verify Create User And Login");
		// add a note to the test
		extentTestRef.log(LogStatus.INFO, "Browser started");			
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);	
		for(int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			
			Cell username = sheet.getRow(i).getCell(0);
			Cell password = sheet.getRow(i).getCell(1);
			Cell expectedResult = sheet.getRow(i).getCell(2);
			
			String userString = username.getStringCellValue();
			String passString = password.getStringCellValue();
			String expectedResultString = expectedResult.getStringCellValue();
			
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
			
			addUserPage.addUser(userString, passString);
			
		    extentTestRef.log(LogStatus.INFO, "New User Details Entered And submitted - User: " + userString + ", Password: " + passString);
		    
		    addUserPage.goToLoginPage();
		    
		    extentTestRef.log(LogStatus.INFO, "Opened Login Page");
		    
		    loginPage.login(userString, passString);
		    
		    extentTestRef.log(LogStatus.INFO, "User Details Entered And Login Attempted - User: " + userString + ", Password: " + passString);
		    
		    if(loginPage.getLoginStatus().equals(expectedResultString)) {
		    	// report the test as a pass
		    	extentTestRef.log(LogStatus.PASS, "Login Was Successful");
		    }
		    else {
		    	extentTestRef.log(LogStatus.FAIL, "Login Failed");
		    }
		    extentReportRef.endTest(extentTestRef);
		    assertEquals("Tested Creating New User And  Then Logging In",expectedResultString, loginPage.getLoginStatus());	    
			
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
