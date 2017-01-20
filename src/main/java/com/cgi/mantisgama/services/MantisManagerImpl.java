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

	@Autowired
	private MantisConfig mantisConfig;
	
	@Autowired
	private SeleniumConfig seleniumConfig;

	public void openMantisAppli () {
		seleniumConfig.getDriver().get(mantisConfig.getUrlMantis());
	}
	
	public void openMantisAppli (Credentials oktaCredentials) {
		
		WebDriver driver = seleniumConfig.getDriver();
		driver.get(mantisConfig.getUrlMantis());
		
		(new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().startsWith("ENGIE - Se connecter");
            }
        });
		
		WebElement user = driver.findElement(By.id("user-signin"));
		user.sendKeys(oktaCredentials.getLogin());
		WebElement pass = driver.findElement(By.id("pass-signin"));
		pass.sendKeys(oktaCredentials.getPassword());
		WebElement submitButton = driver.findElement(By.id("signin-button"));
		submitButton.click();	
	}

	public MantisManager getInstance(SeleniumConfig seleniumConfig, MantisConfig mantisConfig) {
		// TODO Auto-generated method stub
		return null;
	}

	public MantisManager getInstance(SeleniumConfig seleniumConfig, MantisConfig mantisConfig,
			Credentials oktaCredentials) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	
	
	
}
