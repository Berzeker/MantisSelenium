package com.cgi.mantisgama.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;

import com.cgi.mantisgama.config.MantisConfig;
import com.cgi.mantisgama.config.SeleniumConfig;
import com.cgi.mantisgama.models.MantisGeneralInfo;

public class MantisShowMantisImpl implements MantisShowMantis {
	
	@Autowired
	private MantisConfig mantisConfig;
	
	@Autowired
	private SeleniumConfig seleniumConfig;
	
	private static final SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yy");
	
	private static final int NBRETICKETSPERPAGE = 50;
	
	public MantisShowMantisImpl(MantisConfig mantisConfig, SeleniumConfig seleniumConfig) {
		this.mantisConfig = mantisConfig;
		this.seleniumConfig = seleniumConfig;
		
	}

	public void selectProject(String nameProject) {
		Select select = new Select(seleniumConfig.getDriver().findElement(By.name("project_id")));
		select.selectByVisibleText(nameProject);
	}
	
	public int getNumberTickets() {
		
		int nbreTickets = -1;
		List<WebElement> formtitles = seleniumConfig.getDriver().findElements(By.className("form-title"));
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
	
	private int getNumberPages() {
		int nbreTickets = getNumberTickets();
		return new BigDecimal(nbreTickets).divide(new BigDecimal(NBRETICKETSPERPAGE), RoundingMode.UP).intValue();
	}
	
	
	public List<String> getListIdMantis() {
		
		List<String> listIdsMantis = new ArrayList<String>();
		int nbrePages =  getNumberPages();
		
		for (int i = 1; i <= nbrePages; i++) {
			
			seleniumConfig.getDriver().get(mantisConfig.getUrlMantisAllTicketsPagination() + i);
			
			List<WebElement> listTrsMantis = seleniumConfig.getDriver().findElements(By.xpath("//table[@id=\"buglist\"]//tr[@bgcolor]"));
			for (WebElement trMantis : listTrsMantis) {
				WebElement tdMantis = trMantis.findElement(By.xpath("td[4]/a[@href]"));
				listIdsMantis.add(tdMantis.getText());
			}
		}
		return listIdsMantis;
	}
	
	
	public List<MantisGeneralInfo> getMantisAfterlastUpdate(Date lastUpdateSearch) {
		
		List<MantisGeneralInfo> result = new ArrayList<MantisGeneralInfo>();
		boolean interuptWile = false;
			
		seleniumConfig.getDriver().get(mantisConfig.getUrlMantis() + "/view_all_set.php?sort=last_updated&dir=DESC&type=2");
		int nbrePages =  getNumberPages();
		
		int page = 1;
		while (page <= nbrePages && !interuptWile) {
			
			seleniumConfig.getDriver().get(mantisConfig.getUrlMantisAllTicketsPagination() + page);
			List<WebElement> listTrsMantis = seleniumConfig.getDriver().findElements(By.xpath("//table[@id=\"buglist\"]//tr[@bgcolor]"));
			
			for (WebElement trMantis : listTrsMantis) {
				
				MantisGeneralInfo mantisGeneralInfo = new MantisGeneralInfo();
				
				WebElement lastUpdate = null;
				try {
					lastUpdate = trMantis.findElement(By.xpath("td[9]/span"));
				} catch (NoSuchElementException e) {
					lastUpdate = trMantis.findElement(By.xpath("td[9]"));
				}
				
				
				try {
					mantisGeneralInfo.setLastUpdate(formatDate.parse(lastUpdate.getText()));
				} catch (ParseException e1) {
					e1.printStackTrace();
					interuptWile = true;
					break;
				}
				
				if (lastUpdateSearch.compareTo(mantisGeneralInfo.getLastUpdate()) > 0) {
					interuptWile = true;
					break;
				}
				
				//ID MANTIS
				WebElement idmantis = trMantis.findElement(By.xpath("td[4]/a[@href]"));
				mantisGeneralInfo.setId(idmantis.getText());
				
				//CATEGORIE
				WebElement categorie = trMantis.findElement(By.xpath("td[6]"));
				mantisGeneralInfo.setCategorie(categorie.getText());
				
				//PROJET
				try {
					WebElement projet = trMantis.findElement(By.xpath("td[6]/small/a"));
					mantisGeneralInfo.setProjet(projet.getText());
				} catch (NoSuchElementException e) {
					mantisGeneralInfo.setProjet("");
				}
				
				//SEVERITE
				WebElement severerite = trMantis.findElement(By.xpath("td[7]"));
				mantisGeneralInfo.setSeverite(severerite.getText());
						
				//STATUT
				WebElement statut = trMantis.findElement(By.xpath("td[8]/span[@class=\"issue-status\"]"));
				mantisGeneralInfo.setStatut(statut.getText());
				
				//AFFECTED
				WebElement affected = trMantis.findElement(By.xpath("td[8]"));
				String userAffected = affected.getText();
				if (!StringUtils.isBlank(userAffected) && userAffected.indexOf("(") > 0 && userAffected.indexOf(")") > 0)
					userAffected = userAffected.substring(userAffected.indexOf("(") + 1, userAffected.indexOf(")"));
				mantisGeneralInfo.setAffected(userAffected);
				
				//RESUME
				WebElement resume = trMantis.findElement(By.xpath("td[10]"));
				mantisGeneralInfo.setResume(resume.getText()); 
				
				result.add(mantisGeneralInfo);	
			}
			page++;
		}
		
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
