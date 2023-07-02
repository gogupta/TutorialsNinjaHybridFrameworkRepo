package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	@FindBy(id="input-email")
	private WebElement emailAddressField;

	@FindBy(id="input-password")
	private WebElement passwordField;

	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatching;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//actions
	public void enterEmailAddress(String emailText) {
	emailAddressField.sendKeys(emailText);	
	}
	
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);	
	}
	
	public AccountPage clickOnLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public AccountPage login(String emailText,String passwordText) throws InterruptedException
	{
		Thread.sleep(3000);
		emailAddressField.sendKeys(emailText);	
		passwordField.sendKeys(passwordText);
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public String retriveEMailNotMatchingWarningMsg() {
		String warningText=emailPasswordNotMatching.getText();
		return warningText;
	}
}
