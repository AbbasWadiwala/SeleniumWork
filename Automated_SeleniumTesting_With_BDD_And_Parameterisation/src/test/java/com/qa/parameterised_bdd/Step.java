package com.qa.parameterised_bdd;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Step {

WebDriver driver = null;
public String searchTermArgs = null;
	
@Before
public void setUp() {
	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	driver = new ChromeDriver();
}

@After
public void tearDown() {
	driver.close();
}
	
@Given("^I go to \"([^\"]*)\" website$")
public void i_go_to_website(String arg1) {
    driver.get(arg1);
}

@When("^I search for \"([^\"]*)\" searchTerm$")
public void i_search_for(String arg1) {
   searchTermArgs = arg1;
   WebElement element = driver.findElement(By.id("sb_form_q"));
   element.sendKeys(arg1);
   element.submit();
}

@Then("^I am taken to a list of data for that search$")
public void i_am_taken_to_a_list_of_data_for_that_search() throws Throwable {	
		
		Boolean searchStringFoundBoolean = false;
		String toLowerCaseString = searchTermArgs.toLowerCase();
		
		//List<WebElement> elementsOfSearchString = driver.findElements(By.partialLinkText(toLowerCaseString));
		List<WebElement> allElementsOfTagNamea = driver.findElements(By.tagName("a"));
		
		System.out.println("Element Count by tag name a: " + allElementsOfTagNamea.size());
		
		if(!allElementsOfTagNamea.isEmpty()) {
			for(int i = 0; i < allElementsOfTagNamea.size(); i++) {
				String attributeString = ""; 
				attributeString = allElementsOfTagNamea.get(i).getAttribute("href");
				
				 if(attributeString.contains(toLowerCaseString)) {
					 searchStringFoundBoolean = true;
					 break;
				 }
			}
		}	
		
		/*f(!elementsOfSearchString.isEmpty()) {
			for(int i = 0; i < elementsOfSearchString.size(); i++) {
				Point location = elementsOfSearchString.get(i).getLocation();
				System.out.println("Element: " + i + " is at PosX: " + location.x + ", PosY: " + location.y);
			}
		}*/
		
		 
		assertEquals("Checking if elementsofSearchString is empty", true, searchStringFoundBoolean);		
	
	
	Thread.sleep(2000);
}

	
}
