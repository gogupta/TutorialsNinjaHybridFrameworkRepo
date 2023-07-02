package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {
	
	SearchPage searchPage;
	HomePage homepage;
	
	public SearchTest() {
		super();
	}
	public WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		//homepage=new HomePage(driver);

	}
	
	@Test(priority=1)
	public void verifySearchwithValidProduct() throws InterruptedException
	{
		HomePage homepage=new HomePage(driver);
		homepage.enterProductIntoSearchBox(dataProp.getProperty("validProduct"));
		homepage.clickOnSearchButton();
		Thread.sleep(3000);
		SearchPage searchPage=new SearchPage(driver);
		Assert.assertTrue(searchPage.displayStatusofHPValidProduct(),"Valid HP Product is not displayed in search results");
	}
	
	@Test(priority=2)
	public void verifySearchwithInvalidProduct() throws InterruptedException
	{
		HomePage homepage=new HomePage(driver);
		homepage.enterProductIntoSearchBox(dataProp.getProperty("validProduct"));
		homepage.clickOnSearchButton();
		Thread.sleep(3000);
		SearchPage searchPage=new SearchPage(driver);
		Assert.assertEquals(searchPage.retriveNoProductMessageText(),dataProp.getProperty("noProductTxtSearch"),"No product in search results");
	}
	
	@AfterMethod
	public void teardown()
	{
	driver.quit();	
	}

}
