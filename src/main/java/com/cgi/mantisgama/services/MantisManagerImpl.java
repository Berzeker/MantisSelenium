package com.cgi.mantisgama.services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import com.cgi.mantisgama.config.MantisConfig;
import com.cgi.mantisgama.config.SeleniumConfig;
import com.cgi.mantisgama.credentials.Credentials;

public class MantisManagerImpl implements MantisManager {

	private static MantisManager INSTANCE;
	
	@Autowired
	private MantisConfig mantisConfig;
	
	@Autowired
	private SeleniumConfig seleniumConfig;
	
	@Autowired
	private Credentials oktaCredentials;
	
	
	private MantisManagerImpl(SeleniumConfig seleniumConfig, MantisConfig mantisConfig) {
		this.mantisConfig = mantisConfig;
		this.seleniumConfig = seleniumConfig;
	}
	
	private MantisManagerImpl(SeleniumConfig seleniumConfig, MantisConfig mantisConfig, Credentials oktaCredentials) {
		this.mantisConfig = mantisConfig;
		this.seleniumConfig = seleniumConfig;
		this.oktaCredentials = oktaCredentials;
	}
	
	public static MantisManager getInstance(SeleniumConfig seleniumConfig, MantisConfig mantisConfig) {
		if(INSTANCE == null)
			INSTANCE = new MantisManagerImpl(seleniumConfig, mantisConfig);
		return INSTANCE;
	}

	public static MantisManager getInstance(SeleniumConfig seleniumConfig, MantisConfig mantisConfig, Credentials oktaCredentials) {
		if(INSTANCE == null)
			INSTANCE = new MantisManagerImpl(seleniumConfig, mantisConfig, oktaCredentials);
		return INSTANCE;
	}

	public void connectMantisAppli() {
		seleniumConfig.getDriver().get(mantisConfig.getUrlMantis());
	}
	
	public void connectMantisAppliWithOkta() {
		
		WebDriver driver = seleniumConfig.getDriver();
		driver.get(oktaCredentials.getUrl());
		
		(new WebDriverWait(driver, 120)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().startsWith("ENGIE - Sign In");
            }
        });
		
		WebElement user = driver.findElement(By.name("username"));
		user.sendKeys(oktaCredentials.getLogin());
		WebElement pass = driver.findElement(By.name("password"));
		pass.sendKeys(oktaCredentials.getPassword());
		pass.submit();
		
		(new WebDriverWait(driver, 120)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().contains("UserHome");
            }
        });
		
		driver.get(mantisConfig.getUrlMantisAllTickets());
		
//		WebElement submitButton = driver.findElement(By.id("signin-button"));
//		submitButton.click();	
	}
	
	public MantisShowMantis accessMantisShowTickets() {
		seleniumConfig.getDriver().get(mantisConfig.getUrlMantisAllTickets());
		return new MantisShowMantisImpl(mantisConfig, seleniumConfig);
	}

	public MantisShowDetails accessMantisDetailsTicket(String idMantis) {
		seleniumConfig.getDriver().get(mantisConfig.getUrlMantisDetailTicket() + idMantis);
		return MantisShowDetailsImpl.getInstance(seleniumConfig, mantisConfig);
	}

	public void closeMantisAppli() {
		seleniumConfig.getDriver().close();		
	}
	
}
