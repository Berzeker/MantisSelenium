package com.cgi.moulinette.mantisgama;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Hello world!
 *
 */
public class App {


	public static void main(String[] args) throws AWTException {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://mantis.engie.com/di/view_all_bug_page.php");
		
		System.out.println(driver.getTitle());
		
		(new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().startsWith("ENGIE - Se connecter");
            }
        });
		
		WebElement user = driver.findElement(By.id("user-signin"));
		user.sendKeys("LN5282");
		WebElement pass = driver.findElement(By.id("pass-signin"));
		pass.sendKeys("Pendragon$120787");
		WebElement submitButton = driver.findElement(By.id("signin-button"));
		submitButton.click();

		System.out.println(driver.getTitle());

		
		Select select = new Select(driver.findElement(By.name("project_id")));
		select.selectByValue("105");
		System.out.println(driver.getTitle());
		
		
		List<WebElement> listmantis = driver.findElements(By.xpath("//table[@id=\"buglist\"]//tr[@bgcolor]"));
		
		System.out.println(listmantis.size());
		
		for (WebElement webElement : listmantis) {
			WebElement idmantis = webElement.findElement(By.xpath("td[4]/a[@href]"));
			
		}
		
		
	}
}	
