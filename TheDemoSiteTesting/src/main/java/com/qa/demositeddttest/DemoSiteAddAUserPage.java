package com.qa.demositeddttest;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoSiteAddAUserPage {
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input")
	private WebElement userNameTextBoxWebElement;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input")
	private WebElement passwordTextBoxWebElement;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input")
	private WebElement saveButtonWebElement;	
	
	@FindBy(xpath = "/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]")
	private WebElement loginWebElement;
	
	public void goToLoginPage() {
		loginWebElement.click();
	}	
	
	public void addUser(String userNameString, String passwordString) {
		userNameTextBoxWebElement.clear();
		userNameTextBoxWebElement.sendKeys(userNameString);
		passwordTextBoxWebElement.clear();
		passwordTextBoxWebElement.sendKeys(passwordString);
		saveButtonWebElement.click();		
	}
	
}
