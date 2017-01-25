package com.cgi.mantisgama.services;

import java.util.List;

import com.cgi.mantisgama.models.MantisAllData;
import com.cgi.mantisgama.models.MantisHistory;

public interface MantisShowDetails {
	
	MantisAllData getMantisAllData();
	List<MantisHistory> getHistoryMantis();
	List<MantisHistory> getHistoryMantisByField(String field);

}
