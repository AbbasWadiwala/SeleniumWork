package com.qa.cucumber;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TeaTestingWebLandingPage {
	
	@FindBy(xpath = "//*[@id=\"wsb-nav-00000000-0000-0000-0000-000450914915\"]/ul/li[3]/a")
	private WebElement menuButtonWebElement;
	
	@FindBy(xpath = "//*[@id=\"wsb-nav-00000000-0000-0000-0000-000450914915\"]/ul/li[5]/a")
	private WebElement checkOutButtonWebElement;
		
	public void goToMenuPage()
	{
		menuButtonWebElement.click();
	}
	
	public void goToCheckOutpage() {
		checkOutButtonWebElement.click();
	}
}
