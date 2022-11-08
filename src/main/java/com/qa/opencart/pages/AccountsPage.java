package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");

	private By searchIcon = By.cssSelector("div#search button");

	private By accountsectionHeaders = By.cssSelector("div#content h2");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		this.eleUtil = new ElementUtil(driver);
	}

	public String getaccountPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, AppConstants.ACCOUNT_PAGE_TITLE);
		// String title = driver.getTitle();
		System.out.println("Account Page Title:" + title);
		return title;
	}

	public boolean getAccountPageUrl() {
		String url = eleUtil.waitForUrlContains(AppConstants.DEFAULT_LARGE_TIME_OUT,
				AppConstants.ACCOUNT_PAGE_URL_PARAM);
		// String url = driver.getCurrentUrl();
		System.out.println("Account Page Url:" + url);
		if (url.contains(AppConstants.ACCOUNT_PAGE_URL_PARAM)) {
			return true;
		}
		return false;
	}

	public boolean isLogoutLinkExist() {
		return eleUtil.doEleIsDisplayed(logoutLink);
		// return driver.findElement(logoutLink).isDisplayed();
	}

	public boolean isSearchExist() {
		return eleUtil.doEleIsDisplayed(searchIcon);
		// return driver.findElement(searchIcon).isDisplayed();
	}

	public SearchResultPage performSearch(String productKey) {
		System.out.println("Product Name is:" + productKey);
		if (isSearchExist()) {
			eleUtil.doSendKeys(search, productKey);
			eleUtil.doClick(searchIcon);
			return new SearchResultPage(driver);
		} else {
			System.out.println("search field is not present on the page...");
			return null;
		}

	}

	public List<String> getAccountSectionHeadersList() {
		// List<WebElement> sectionList =
		// driver.findElements(accountsectionHeaders);
		List<WebElement> sectionList = eleUtil.waitForElementsToBeVisible(accountsectionHeaders,
				AppConstants.DEFAULT_TIME_OUT);
		List<String> actSecTextList = new ArrayList<>();
		System.out.println("Total Section Headers:" + sectionList.size());

		for (WebElement e : sectionList) {
			String text = e.getText();
			actSecTextList.add(text);
		}
		return actSecTextList;
	}

}
