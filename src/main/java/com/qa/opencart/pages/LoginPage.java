package com.qa.opencart.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// By locator

	private By emailId = By.id("input-email");

	private By password = By.id("input-password");

	private By loginBtn = By.xpath("//input[@value='Login']");

	private By logoImage = By.cssSelector("img[title='naveenopencart']");

	private By forgotPwdlink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	private static final Logger LOG = Logger.getLogger(DriverFactory.class);

	// Page constructor

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// Page Actions

	public String getLoginPageTitle() {

		String title = eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE);

		// String title = driver.getTitle();
		System.out.println("Login Page Title:" + title);
		LOG.info("Login Page Title:" + title);
		return title;
	}

	public boolean getLoginPageUrl() {

		String url = eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_PARAM);

		// String url = driver.getCurrentUrl();
		System.out.println("Login Page Url:" + url);
		if (url.contains(AppConstants.LOGIN_PAGE_URL_PARAM)) {
			return true;
		}
		return false;
	}

	public boolean isForgotPwdLinkExist() {

		return eleUtil.doEleIsDisplayed(forgotPwdlink);
		// return driver.findElement(forgotPwdlink).isDisplayed();
	}

	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("User Creds are:" + username + ":" + pwd);
		eleUtil.doSendKeysWithWait(emailId, AppConstants.DEFAULT_TIME_OUT, username);
		// driver.findElement(emailId).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		// driver.findElement(this.password).sendKeys(pwd);
		eleUtil.doClick(loginBtn);
		// driver.findElement(loginBtn).click();
		return new AccountsPage(driver);
	}
	
	public RegisterPage navigatetoResgierPage()
	{
		System.out.println("Navigating to Register Page...........");
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}

}
