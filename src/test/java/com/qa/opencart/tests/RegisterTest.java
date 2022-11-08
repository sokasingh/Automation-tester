package com.qa.opencart.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterTest extends BaseTest {

	
	@BeforeClass
	public void regSetup()
	{
		registerPage = loginPage.navigatetoResgierPage();
	}
	
	@DataProvider
	public Object[][] getRegTestData()
	{
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	
	
	@Test(dataProvider="getRegTestData")
	public void registrationUserTest(String firstName,String lastName,String email,String telephone,String password,String subscribe)
	{
		String actSuccMesg = registerPage.userRegister(firstName,lastName,email,telephone,password,subscribe);
		Assert.assertEquals(actSuccMesg, AppConstants.ACC_CREATE_SUCC_MESSG);
	}
}
