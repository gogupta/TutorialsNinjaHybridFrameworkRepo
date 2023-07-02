package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base{

	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	public RegisterTest()
	{
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		Thread.sleep(8000);
		HomePage homepage=new HomePage(driver);
		Thread.sleep(2000);
		registerPage=homepage.navigateToRegisterPage();
	}

	@Test
	public void verifyRegisterAccountMandatoryFields()
	{
		accountSuccessPage=registerPage.register(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),Utilities.generateTimestamp(),dataProp.getProperty("telephoneno"),prop.getProperty("validPassword"));		
		Assert.assertEquals(accountSuccessPage.retriveAccounrSuccesspageHeading(), dataProp.getProperty("accountsuccesscreated"),"Account is not created");
	}
	@AfterMethod
	public void teardown()
	{
		driver.close();
	}

}
