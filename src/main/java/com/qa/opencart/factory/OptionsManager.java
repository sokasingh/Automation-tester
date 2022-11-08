package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

	//ChromeOptions co = new ChromeOptions();
	
	public OptionsManager(Properties prop)
	{
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions()
	{
		co = new ChromeOptions();
	     if(Boolean.parseBoolean(prop.getProperty("headless")))
	     {
	    	 co.setHeadless(true);
	     }
	     
	     if(Boolean.parseBoolean(prop.getProperty("incognito")))
	     {
	    	 co.addArguments("--incognito");
	     }
	     
	     return co;
	     
	     
	}
	
	public FirefoxOptions getFirefoxoptions()
	{
		fo = new FirefoxOptions();
	     if(Boolean.parseBoolean(prop.getProperty("headless")))
	     {
	    	 co.setHeadless(true);
	     }
	     
	     if(Boolean.parseBoolean(prop.getProperty("incognito")))
	     {
	    	 co.addArguments("--incognito");
	     }
	     
	     return fo;
	     
	     
	}
	
	public EdgeOptions getEdgeoptions()
	{
		eo = new EdgeOptions();
	     if(Boolean.parseBoolean(prop.getProperty("headless")))
	     {
	    	 co.setHeadless(true);
	     }
	     
	     if(Boolean.parseBoolean(prop.getProperty("incognito")))
	     {
	    	 co.addArguments("--incognito");
	     }
	     
	     return eo;
	     
	     
	}
	
}
