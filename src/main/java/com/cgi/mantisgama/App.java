package com.cgi.mantisgama;

import java.awt.AWTException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cgi.mantisgama.models.MantisAllData;
import com.cgi.mantisgama.models.MantisHistory;
import com.cgi.mantisgama.services.MantisManager;
import com.cgi.mantisgama.services.MantisShowDetails;
import com.cgi.mantisgama.services.MantisShowMantis;

/**
 * Hello world!
 *
 */
public class App {

	private static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	
	private static List<String> categories = Arrays.asList(new String[]{"Demande de support","Anomalie", "Evolution"});
	private static List<String> usersDeclic = Arrays.asList(new String[]{"Anas KHALLAAYOUN","Bouacha Majda", "ElMARSLI Nezha"});
	
	public static void main(String[] args) throws AWTException, ParseException {
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		MantisManager mantisManager = (MantisManager) context.getBean("mantisManager");
		
		mantisManager.connectMantisAppliWithOkta();
		
		MantisShowMantis mantisShowMantis = mantisManager.accessMantisShowTickets();
		mantisShowMantis.selectProject("ESPACE CLIENT");
		
		
		
		List<String> listIdsMantis = mantisShowMantis.getListIdMantisAfterlastUpdate(format.parse("23-01-2017"));
		
		for (String idMantis : listIdsMantis) {
			
			MantisShowDetails MantisShowDetails = mantisManager.accessMantisDetailsTicket(idMantis);
			MantisAllData mantisAllData = MantisShowDetails.getMantisAllData();
			
			List<MantisHistory> listHistory = MantisShowDetails.getHistoryMantisByField("Assigné à");
			List<String> listAssignedUser = new ArrayList<String>();
			for (MantisHistory mantisHistory : listHistory) {
				listAssignedUser.add(mantisHistory.getUser());
			}
			listAssignedUser.retainAll(usersDeclic);
			
			if (categories.contains(mantisAllData.getCategorie()) && !listAssignedUser.isEmpty()) {
				System.out.println(mantisAllData);
			}
		}
			
		
		mantisManager.closeMantisAppli();
		
		
		
	}
}	
