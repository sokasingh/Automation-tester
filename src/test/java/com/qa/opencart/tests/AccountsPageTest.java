package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void accPageTitleTest() {
		String actaccPageTitle = accPage.getaccountPageTitle();
		Assert.assertEquals(actaccPageTitle, AppConstants.ACCOUNT_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void accPageUrlTest() {
		Assert.assertTrue(accPage.getAccountPageUrl());
	}

	@Test(priority = 3)
	public void searchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}

	@Test(priority = 4)
	public void logoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@Test(priority = 5)
	public void accPageHeadersTest() {
		List<String> actHeadersList = accPage.getAccountSectionHeadersList();
		System.out.println("Actual Account headers:" + actHeadersList);
		Assert.assertEquals(actHeadersList, AppConstants.ACC_PAGE_SECTIONS_HEADERS);
	}
	
	
	@DataProvider
	public Object[][] getProductKey()
	{
		return new Object[][] {
			{"Macbook"},
			{"Macbook"},
			{"iMac" },
			{"Samsung"}};
		
	}
	
	
	@Test(dataProvider="getProductKey",priority=6)
	public void searchCheckTest(String productKey) {
		searchResultPage = accPage.performSearch(productKey);
		Assert.assertTrue(searchResultPage.isSearchSuccessful());
	}
	
	
	@DataProvider
	public Object[][] getProductData()
	{
		return new Object[][] {
			{"Macbook" , "MacBook Pro"},
			{"Macbook" , "MacBook Air"},
			{"iMac" , "iMac"},
			{"Samsung" , "Samsung Galaxy Tab 10.1"}};
		
	}
	

	@Test(dataProvider="getProductData",priority=7)
	public void searchTest(String searchKey, String mainProductname)
	{
		searchResultPage = accPage.performSearch(searchKey);
	if(searchResultPage.isSearchSuccessful())
	{
		productInfoPage = searchResultPage.selectProduct(mainProductname);
		String actualproductheader = productInfoPage.getProductheader(mainProductname);
		Assert.assertEquals(actualproductheader,mainProductname);
	}
	}


}
