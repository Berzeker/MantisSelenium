package com.cgi.mantisgama.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MantisShowMantisImpl {
	
	private WebDriver driver;
	
	private static final int NBRETICKETSPERPAGE = 50;
	
	public void selectProject() {
		Select select = new Select(driver.findElement(By.name("project_id")));
		select.selectByVisibleText("ESPACE CLIENT");
	}
	
	public int getNumberTickets () {
		
		int nbreTickets = -1;
		List<WebElement> formtitles = driver.findElements(By.className("form-title"));
		if (formtitles != null && !formtitles.isEmpty()) {
			for (WebElement formtitle : formtitles) {
				if (formtitle.getText().contains("Liste des fiches")) {
					String nbreTicketsStr = formtitle.getText();
					int indexSlash = nbreTicketsStr.indexOf("/");
					int indexEnd = nbreTicketsStr.indexOf(")");
					if (indexSlash != -1 && indexEnd != -1) {
						nbreTicketsStr = nbreTicketsStr.substring(indexSlash+1, indexEnd).trim();
						try {
							nbreTickets = Integer.valueOf(nbreTicketsStr);
						} catch (NumberFormatException e) {
							nbreTickets = -1;
						}
					}
					break;
				}
			}
		}
		return nbreTickets;
	}
	
	private int getNumberPages () {
		int nbreTickets = getNumberTickets();
		return new BigDecimal(nbreTickets).divide(new BigDecimal(NBRETICKETSPERPAGE), RoundingMode.UP).intValue();
	}
	
	
	public List<String> listIdMantis() {
		
		List<String> listIdsMantis = new ArrayList<String>();
		int nbrePages =  getNumberPages();
		
		for (int i = 1; i <= nbrePages; i++) {
			
			driver.get("https://mantis.engie.com/di/view_all_bug_page.php?page_number=" + i);
			
			List<WebElement> listTrsMantis = driver.findElements(By.xpath("//table[@id=\"buglist\"]//tr[@bgcolor]"));
			for (WebElement trMantis : listTrsMantis) {
				WebElement tdMantis = trMantis.findElement(By.xpath("td[4]/a[@href]"));
				listIdsMantis.add(tdMantis.getText());
			}
		}
		return listIdsMantis;
	}
	
}
