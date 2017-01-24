package com.cgi.mantisgama.config;

public class MantisConfig {

	private static MantisConfig INSTANCE;
	
	private String version;
	private String urlMantis;
	private String urlMantisHome;
	private String urlMantisMyView;
	private String urlMantisAllTickets;
	private String urlMantisAllTicketsPagination;
	private String urlMantisCreateTicket;
	private String urlMantisDetailTicket;
	private String urlMantisMyAccount;
	
	private MantisConfig(String version, String urlMantis) {
		this.version = version;
		this.urlMantis = urlMantis;
		
	}
	
	
	public static MantisConfig getInstance(String version, String urlMantis) {
		if (INSTANCE == null) {
			INSTANCE = new MantisConfig(version, urlMantis);
		}
		return INSTANCE;
	}


	public String getUrlMantisHome() {
		return urlMantisHome;
	}


	public void setUrlMantisHome(String urlMantisHome) {
		this.urlMantisHome = urlMantisHome;
	}


	public String getUrlMantisMyView() {
		return urlMantisMyView;
	}


	public void setUrlMantisMyView(String urlMantisMyView) {
		this.urlMantisMyView = urlMantisMyView;
	}


	public String getUrlMantisAllTickets() {
		return urlMantisAllTickets;
	}


	public void setUrlMantisAllTickets(String urlMantisAllTickets) {
		this.urlMantisAllTickets = urlMantisAllTickets;
	}


	public String getUrlMantisCreateTicket() {
		return urlMantisCreateTicket;
	}


	public void setUrlMantisCreateTicket(String urlMantisCreateTicket) {
		this.urlMantisCreateTicket = urlMantisCreateTicket;
	}


	public String getUrlMantisDetailTicket() {
		return urlMantisDetailTicket;
	}


	public void setUrlMantisDetailTicket(String urlMantisDetailTicket) {
		this.urlMantisDetailTicket = urlMantisDetailTicket;
	}


	public String getUrlMantisMyAccount() {
		return urlMantisMyAccount;
	}


	public void setUrlMantisMyAccount(String urlMantisMyAccount) {
		this.urlMantisMyAccount = urlMantisMyAccount;
	}


	public String getVersion() {
		return version;
	}


	public String getUrlMantis() {
		return urlMantis;
	}


	public String getUrlMantisAllTicketsPagination() {
		return urlMantisAllTicketsPagination;
	}


	public void setUrlMantisAllTicketsPagination(String urlMantisAllTicketsPagination) {
		this.urlMantisAllTicketsPagination = urlMantisAllTicketsPagination;
	}
	
	

	
}
