package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By productSearchLayout = By.xpath("//div[contains(@class,'product-layout')]");
	
	

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public boolean isSearchSuccessful() {
		List<WebElement> searchList = eleUtil.waitForElementsToBeVisible(productSearchLayout, AppConstants.DEFAULT_TIME_OUT);
		if(searchList.size()>0)
		{
			System.out.println("Search is Successfully Done......");
			return true;
		}
		return false;
	}
	
	
	public ProductInfoPage selectProduct(String mainProductName)
	{
		By mainPrName = By.linkText(mainProductName);
		eleUtil.doClick(mainPrName);
		return new ProductInfoPage(driver);
	}
	

}
