package com.cgi.mantisgama.services;

public interface MantisManager {
	
	void connectMantisAppli();
	void connectMantisAppliWithOkta();
	MantisShowMantis accessMantisShowTickets();
	MantisShowDetails accessMantisDetailsTicket(String idMantis) ;
	void closeMantisAppli();
}
