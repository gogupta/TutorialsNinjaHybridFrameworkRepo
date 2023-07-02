package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement passwordConfirmField;
	
	@FindBy(xpath="//input[@name='agree']")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	public RegisterPage(WebDriver driver)
	{
		this.driver=driver; 
		PageFactory.initElements(driver, this);
	}

	public void enterFirstName(String firstNameText)
	{
		firstNameField.sendKeys(firstNameText);
	}
	
	public void enterLastName(String LastNameText)
	{
		lastNameField.sendKeys(LastNameText);
	}
	
	public void enterEmailAddressField(String emailText)
	{
		emailAddressField.sendKeys(emailText);
	}

	public void enterTelephoneField(String telephoneText)
	{
		telephoneField.sendKeys(telephoneText);
	}
	
	public void enterPasswordField(String passwordText)
	{
		passwordField.sendKeys(passwordText);
	}
	
	public void enterConfirmPasswordField(String passwordText)
	{
		passwordConfirmField.sendKeys(passwordText);
	}
	
	public void selectPrivacyPolicy()
	{
		privacyPolicyField.click();
	}

	public AccountSuccessPage clickOnContinueButton()
	{
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
		
	public AccountSuccessPage register(String firstNameText,String LastNameText,String emailText,String telephoneText,String passwordText)
	{
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(LastNameText);
		emailAddressField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		passwordConfirmField.sendKeys(passwordText);
		privacyPolicyField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
}
 