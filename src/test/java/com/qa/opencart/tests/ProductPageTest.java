package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class ProductPageTest extends BaseTest {

	@BeforeClass
	public void productInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	
	@DataProvider
	public Object[][] getProductHeaderData()
	{
		return new Object[][] {
			{"Macbook" , "MacBook Pro"},
			{"Macbook" , "MacBook Air"},
			{"iMac" , "iMac"},
			{"Samsung" , "Samsung Galaxy Tab 10.1"}};
		
	}
	
	@Test(dataProvider="getProductHeaderData")
	public void productHeaderTest(String searchKey, String mainProductName) {
		searchResultPage = accPage.performSearch(searchKey);
		productInfoPage = searchResultPage.selectProduct(mainProductName);
		String actualproductheader = productInfoPage.getProductheader(mainProductName);
		Assert.assertEquals(actualproductheader, mainProductName);
	}

	@DataProvider
	public Object[][] getProductInfoData() {
		return new Object[][] { { "Macbook", "MacBook Pro", AppConstants.MACBOOK_PRO_IMAGES_COUNT },
				{ "Macbook", "MacBook Air", AppConstants.MACBOOK_AIR_IMAGES_COUNT},
				{ "iMac", "iMac", AppConstants.IMAC_IMAGES_COUNT},
				 };

	}

	@Test(dataProvider = "getProductInfoData")
	public void productImagesCountTest(String serachKey, String mainProductName , int ImagesCount) {
		searchResultPage = accPage.performSearch(serachKey);
		productInfoPage = searchResultPage.selectProduct(mainProductName);
		int actProductImages = productInfoPage.getProductImagesCount();
		System.out.println("Actual product Images:" + actProductImages);
		Assert.assertEquals(actProductImages, ImagesCount);
	}
	
	@Test
	public void productMetaDataTest()
	{
		searchResultPage = accPage.performSearch("Macbook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Map<String, String> actMetaData = productInfoPage.getProductMetadata();
		Assert.assertEquals(actMetaData.get("Brand"), "Apple");
	}

}
