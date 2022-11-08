package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private Select select;
	private Actions act;
	private JavaScriptUtil jsUtil;

	// doSearch("Summer Dresses > Printed Chiffon ");

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
		jsUtil = new JavaScriptUtil(driver);
	}

	public void doSendKeys(By locator, String value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);

	}

	public WebElement getElement(By locator) {
		WebElement ele =  driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight))
		{
			jsUtil.flash(ele);
		}
		return ele;
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public String doGetAttribute(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
	}

	public boolean doEleIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	public List<String> getElementsTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getText();
			if (text.length() != 0) {
				eleTextList.add(text);
			}

		}
		return eleTextList;
	}

	public void doSelectDropDownByIndex(By locator, int index) {
		select = new Select(getElement(locator));
		select.selectByIndex(index);

	}

	public void doSelectDropDownByVisibleText(By locator, String value) {
		select = new Select(getElement(locator));
		select.selectByVisibleText(value);

	}

	public void doSelectDropDownByValue(By locator, String value) {
		select = new Select(getElement(locator));
		select.selectByValue(value);

	}

	public void doSelectValueFronDropDown(By locator, String value) {
		List<WebElement> optionList = getElements(locator);
		for (WebElement e : optionList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	public void doSearch(String text) throws InterruptedException {
		Thread.sleep(9000);
		By printedDress = By.xpath("//li[text()='" + text + "']");
		getElement(printedDress).click();
	}

	public boolean isSingleElementPresent(By locator) {
		List<WebElement> list = getElements(locator);
		if (list.size() == 1) {
			System.out.println("Single Search Element is present");
			return true;
		} else {
			System.out.println("No Search or Multiple search element present on the page");
			return false;
		}
	}

	// ***************************JavaScript Utils
	// ********************************
	// To check which fields are mandatory

	public boolean checkElementIsMandatory(String jsScript) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String man_text = js.executeScript(jsScript).toString();
		System.out.println(man_text);
		if (man_text.contains("*")) {
			System.out.println("First Name is Mandatory Field");
			return true;
		} else {
			System.out.println("First Name is not Mandatory");
			return false;
		}

	}

	// ***************************************Actions
	// Utils****************************************************************

	public void handleLevel1MenuItems(By parentMenu, By childMenu) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenu)).build().perform();
		Thread.sleep(3000);
		getElement(childMenu).click();
	}

	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}

	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform();
	}

	// ***************************************WAIT
	// Utils*********************************************

	public WebElement waitForElementPresent(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void doSendKeysWithWait(By locator, int timeOut, String value) {
		waitForElementPresent(locator, timeOut).sendKeys(value);
	}

	public void doClickWithWait(By locator, int timeOut) {
		waitForElementPresent(locator, timeOut).click();
	}

	public String getElementTextWithWit(By locator, int timeOut) {
		return waitForElementPresent(locator, timeOut).getText();
	}

	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that all elements present on the web page
	 * that match the locator are visible. Visibility means that elements are
	 * not only displayed but also have a height and width that is greater than
	 * 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return List of WebElements
	 */
	public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	/**
	 * FluentWait
	 * 
	 * @param locator
	 * @param timeOut
	 * @param pollingTime
	 * @return
	 */
	public WebElement waitForElementToBeVisibleWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
				.withMessage("element is not available on the pageeeeeesss" + locator);

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// ************************************ALERT +
	// WAIT******************************************
	public Alert waitForALert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(int timeout) {
		return waitForALert(timeout).getText();
	}

	public void acceptAlert(int timeOut) {
		waitForALert(timeOut).accept();
	}

	public void dismissAlert(int timeOut) {
		waitForALert(timeOut).dismiss();
	}

	public void alertSendKeys(int timeOut, String value) {
		waitForALert(timeOut).sendKeys(value);
	}

	// *****************************TITLE +
	// WAIT******************************************************

	public String waitForTitleContains(int timeOut, String titleFraction) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
			return driver.getTitle();
		} else {
			return null;
		}
	}

	public String waitForTitleIs(int timeOut, String titleValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleContains(titleValue))) {
			return driver.getTitle();
		} else {
			return null;
		}
	}

	// **************************************URL + WAIT*************************
	public String waitForUrlContains(int timeOut, String urlFraction) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
			return driver.getCurrentUrl();
		} else {
			return null;
		}
	}

	public String waitForUrl(int timeOut, String urlValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlToBe(urlValue))) {
			return driver.getCurrentUrl();
		} else {
			return null;
		}
	}

	// ***************************************FRAME
	// WAIT**********************************
	public void waitForFrame(int timeOut, int frameIndex) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void waitForFrame(int timeOut, String frameName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
	}

	public void waitForFrame(int timeOut, WebElement frameElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	public void waitForFrame(int timeOut, By Framelocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(Framelocator));
	}

}
