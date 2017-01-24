package com.cgi.mantisgama;

import java.awt.AWTException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cgi.mantisgama.models.MantisGeneralInfo;
import com.cgi.mantisgama.services.MantisManager;
import com.cgi.mantisgama.services.MantisShowMantis;

/**
 * Hello world!
 *
 */
public class App {

	private static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	
	public static void main(String[] args) throws AWTException, ParseException {
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		MantisManager mantisManager = (MantisManager) context.getBean("mantisManager");
		
		mantisManager.connectMantisAppliWithOkta();
		
		MantisShowMantis mantisShowMantis = mantisManager.accessMantisShowTickets();
		mantisShowMantis.selectProject("ESPACE CLIENT");
		
		List<MantisGeneralInfo> listeMantis = mantisShowMantis.getMantisAfterlastUpdate(format.parse("23-01-2017"));
		System.out.println(listeMantis);
		
		
		
		
		
	}
}	
