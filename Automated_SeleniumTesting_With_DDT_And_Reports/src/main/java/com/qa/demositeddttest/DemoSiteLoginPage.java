package com.qa.demositeddttest;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class DemoSiteLoginPage {
	
	public WebDriver driver = null;
					
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input")
	private WebElement userNameTextBoxWebElement;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input")
	private WebElement passwordTextBoxWebElement;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input")
	private WebElement testLoginWebElement;	
	
	@FindBy(xpath ="/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")
	private WebElement loginStatusWebElement;
	
	
	public void login(String userNameString, String passwordString) {
		userNameTextBoxWebElement.clear();
		userNameTextBoxWebElement.sendKeys(userNameString);
		passwordTextBoxWebElement.clear();
		passwordTextBoxWebElement.sendKeys(passwordString);
		testLoginWebElement.click();		
	}
	
	public String getLoginStatus() {
		return loginStatusWebElement.getText();
	}
	
}