package com.cgi.mantisgama.services;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.cgi.mantisgama.models.MantisGeneralInfo;

public interface MantisShowMantis {

	void selectProject(String nameProject);
	int getNumberTickets();
	List<String> getListIdMantis();
	List<MantisGeneralInfo> getMantisAfterlastUpdate(Date lastUpdateSearch) throws ParseException;
	List<String> getListIdMantisAfterlastUpdate(Date lastUpdateSearch) throws ParseException;
	
}
