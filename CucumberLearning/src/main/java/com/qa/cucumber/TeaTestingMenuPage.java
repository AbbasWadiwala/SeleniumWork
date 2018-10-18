package com.qa.cucumber;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TeaTestingMenuPage {
	
	@FindBy(xpath= "//*[@id=\"wsb-element-00000000-0000-0000-0000-000453230000\"]/div/p/span/span/strong")
	private WebElement productNameWebElement;
	
	public boolean productNameWebElementExists() {		
		try {
			productNameWebElement.isDisplayed();
			return true;
		
		} catch (NoSuchElementException e) {
				
		}			
		return false;
		
	}
}
