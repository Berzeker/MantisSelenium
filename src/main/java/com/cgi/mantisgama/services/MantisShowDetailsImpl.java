package com.cgi.mantisgama.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import com.cgi.mantisgama.config.MantisConfig;
import com.cgi.mantisgama.config.SeleniumConfig;
import com.cgi.mantisgama.models.MantisAllData;
import com.cgi.mantisgama.models.MantisHistory;

class MantisShowDetailsImpl implements MantisShowDetails {

	private static final SimpleDateFormat FORMATDATE = new SimpleDateFormat("dd-MM-yy hh:mm");
	
	private static MantisShowDetails INSTANCE = null;
	
	@Autowired
	private MantisConfig mantisConfig;

	@Autowired
	private SeleniumConfig seleniumConfig;
	
	
	private MantisShowDetailsImpl(SeleniumConfig seleniumConfig, MantisConfig mantisConfig) {
		this.mantisConfig = mantisConfig;
		this.seleniumConfig = seleniumConfig;
	}
	
	public static MantisShowDetails getInstance(SeleniumConfig seleniumConfig, MantisConfig mantisConfig) {
		if (INSTANCE == null)
			INSTANCE = new MantisShowDetailsImpl(seleniumConfig, mantisConfig);
		return INSTANCE;
	}

	public MantisAllData getMantisAllData() {
		
		MantisAllData mantisAllData = new MantisAllData();
		
		List<WebElement> rows2tr = seleniumConfig.getDriver().findElements(By.className("row-2"));
		List<WebElement> rows1tr = seleniumConfig.getDriver().findElements(By.className("row-1"));
		
		String id = getvalueTdsRow(rows1tr, 0, 0);
		String categorie = getvalueTdsRow(rows1tr, 0, 1);
		String severite = getvalueTdsRow(rows1tr, 0, 2);
		String reproductibilite = getvalueTdsRow(rows1tr, 0, 3);
		String dateStart = getvalueTdsRow(rows1tr, 0, 4);
		String dateLastUpdate= getvalueTdsRow(rows1tr, 0, 5);		
		String reporter = getvalueTdsRow(rows2tr, 0, 1);
		String assigned = getvalueTdsRow(rows1tr, 1, 1);
		String priorite = getvalueTdsRow(rows2tr, 1, 1);
		String etat = getvalueTdsRow(rows1tr, 2, 1);
		String brique = getvalueTdsRow(rows1tr, 5, 1);
		String bu = getvalueTdsRow(rows2tr, 5, 1);
		String dateCloture = getDateCloture();	
		
		mantisAllData.setId(id);
		mantisAllData.setCategorie(categorie);
		
		if (categorie.indexOf("[") >= 0 && categorie.indexOf("]") >= 0) {
			mantisAllData.setProject(categorie.substring(categorie.indexOf("[") + 1, categorie.indexOf("]")));
			mantisAllData.setCategorie(categorie.substring(categorie.indexOf("]")+1).trim());
		}
		
		mantisAllData.setSeverite(severite);
		mantisAllData.setReproductibilite(reproductibilite);
		mantisAllData.setDateStart(dateStart.substring(0, 8));
		mantisAllData.setHeureStart(dateStart.substring(9));
		mantisAllData.setDateLastUpdate(dateLastUpdate);
		mantisAllData.setReporter(reporter);
		mantisAllData.setAssigned(assigned);
		mantisAllData.setPriorite(priorite);
		mantisAllData.setEtat(etat);
		mantisAllData.setBrique(brique);
		mantisAllData.setBu(bu);
		
		if(!StringUtils.isBlank(dateCloture)) {
			mantisAllData.setDateCloture(dateCloture.substring(0, 8));
			mantisAllData.setHeureCloture(dateCloture.substring(0, 8));
		}
		
		return mantisAllData;
	}
	
	public List<MantisHistory> getHistoryMantis() {
		
		List<MantisHistory> resultat = new ArrayList<MantisHistory>();
		
		WebElement divHistory = seleniumConfig.getDriver().findElement(By.id("history_open"));
		List<WebElement> trs = divHistory.findElements(By.tagName("tr"));
		for (WebElement tr : trs) {
			if ("row-1".equals(tr.getAttribute("class")) || "row-2".equals(tr.getAttribute("class"))) {
				MantisHistory mantisHistory = new MantisHistory();
				List<WebElement> tds = tr.findElements(By.tagName("td"));
				if (tds != null && tds.size() == 4) {
					mantisHistory.setDateUpdate(tds.get(0).getText());
					mantisHistory.setUser(tds.get(1).getText());
					mantisHistory.setField(tds.get(2).getText());
					mantisHistory.setUpdate(tds.get(3).getText());
					resultat.add(mantisHistory);
				}
			}
		}
		return resultat;
	}
	
	public List<MantisHistory> getHistoryMantisByField(String field) {
		
		List<MantisHistory> resultat = new ArrayList<MantisHistory>();
		List<MantisHistory> histories = getHistoryMantis();
		
		if (!histories.isEmpty()) {
			for (MantisHistory history : histories) {
				if(field.equals(history.getField())) {
					resultat.add(history);
				}		
			}
		}		
		return resultat;
	}

	private String getvalueTdsRow(List<WebElement> rowstr, int indexTr, int indexTd) {
		String result = "";
		if (rowstr != null && !rowstr.isEmpty()) {
			List<WebElement> tds = rowstr.get(indexTr).findElements(By.tagName("td"));
			if (tds != null && indexTd < tds.size()) {
				result = tds.get(indexTd).getText();
			}
		}
		return result;
	}
	
	private String getDateCloture() {
		String dateCloture = "";
		List<MantisHistory> histories = getHistoryMantis();
		if (!histories.isEmpty()) {
			for (MantisHistory history : histories) {
				if(!StringUtils.isBlank(history.getUpdate()) && history.getUpdate().contains("Fermé")) {
					dateCloture = history.getDateUpdate();
					break;
				}
			}
		}
		return dateCloture;
	}

}
