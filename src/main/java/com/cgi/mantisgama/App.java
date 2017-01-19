package com.cgi.mantisgama;

import java.awt.AWTException;
import java.util.ArrayList;
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
		
		class MantisData {
			private String id;
			private String resume;
			private String statut;
			private String responsable;
			private String rapporteur;
			private String type;
			private String projet;
			private String severite;
			private String dateDebut;
			private String dateCloture;
			private String heureDebut;
			private String heureFin;
			
			public MantisData() {
			}
		}
		
		//Id;Résumé;Statut;Responsable;Crér par;Type;Projet;Sévérité;Date Début;Date clôture;Heure Début;Heure Fin
		
		List<MantisData> listMantisData = new ArrayList<MantisData>();
		
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
			
			//ID MANTIS
			WebElement idmantis = webElement.findElement(By.xpath("td[4]/a[@href]"));
			final MantisData mantisData = new MantisData();
			mantisData.id = idmantis.getText();
			System.out.println(idmantis.getText());
			
			//RESUME
			WebElement resume = webElement.findElement(By.xpath("td[10]"));
			mantisData.resume = resume.getText();
			System.out.println(resume.getText());
		
			//Statut
			WebElement statut = webElement.findElement(By.xpath("td[8]/span[@class=\"issue-status\"]"));
			mantisData.statut = statut.getText();
			System.out.println(statut.getText());
			
			
			
			//Type
			WebElement type = webElement.findElement(By.xpath("td[6]"));
			mantisData.type = type.getText();
			System.out.println(type.getText());
			
			//Projet
			
			
			//severite
			WebElement severerite = webElement.findElement(By.xpath("td[7]"));
			mantisData.type = severerite.getText();
			System.out.println(severerite.getText());
			
			listMantisData.add(mantisData);
			
			
		}
		
		for (MantisData mantisData : listMantisData) {
			driver.get("https://mantis.engie.com/di/view.php?id="+mantisData.id);
			//rapporteur
			List<WebElement> rapporteurs = driver.findElements(By.className("row-2"));
			List<WebElement> rapporteurs2 = rapporteurs.get(0).findElements(By.tagName("td"));
			System.out.println(rapporteurs2.get(1).getText());
			
			//dateDebut
			List<WebElement> datedebuts = driver.findElements(By.className("row-1"));
			List<WebElement> datedebut = datedebuts.get(0).findElements(By.tagName("td"));
			System.out.println(datedebut.get(4).getText());
			
			//datcloture
			WebElement datclotures = driver.findElement(By.id("history_open"));
			List<WebElement> trs = datclotures.findElements(By.tagName("tr"));
		
			for (WebElement tr : trs) {
				List<WebElement> tds = tr.findElements(By.tagName("td"));
				for (WebElement td : tds) {
					//System.out.println(td.getText());

					if (td.getText() != null && td.getText().contains("Fermé")) {
						System.out.println("YES");
						System.out.println(tds.get(0).getText());
						break;
					}
					
				}	
				
			}
		}
			
			
			
			//responsable
			

			
			
			
			//dateDebut
			
			//dateCloture
			
			//heureDebut
			
			//heureFin
			
		
		
		
	}
}	
