package com.tutorialsninja.qa.testcases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {
	
	//LoginPage loginPage;
	
	public LoginTest(){
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		Thread.sleep(8000);
		HomePage homepage=new HomePage(driver);
		Thread.sleep(5000);
		homepage.navigateToLoginPage();
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	
	@Test(priority = 1,dataProvider = "validcredentialSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) throws InterruptedException
	{
		/*
		 * AccountPage accountPage=loginPage.login(email, password); Thread.sleep(3000);
		 * 	Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformation(),"Edit you account page is not displayed");
		 */
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		loginPage.clickOnLoginButton();
		AccountPage accountPage=new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformation(),"Edit you account page is not displayed");

		
	}

	@DataProvider(name="validcredentialSupplier")
	public Object[][] supplydata() throws IOException{
		Object data[][]= Utilities.getTestDataExcel("Login");
		return data;
		/*
		 * Object data[][]= {
		 * {"govindgupta2209@gmail.com","12345"},{"g@gmail.com","12345"},{
		 * "scorpionknight22@gmail.com","12345"} }; return data;
		 */	
	}
	
	
	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() throws InterruptedException
	{
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateTimestamp());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		
		String actualmsg=loginPage.retriveEMailNotMatchingWarningMsg();
		String expectedwarningmsg=dataProp.getProperty("emailPasswordnotMatch");
		Assert.assertTrue(actualmsg.contains(expectedwarningmsg),"Msg was not correct");
	}
	

}
