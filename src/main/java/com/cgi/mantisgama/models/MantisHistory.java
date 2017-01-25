package com.cgi.mantisgama.models;

public class MantisHistory {

	String dateUpdate;
	String user;
	String field;
	String update;
	
	public String getDateUpdate() {
		return dateUpdate;
	}
	
	
	
	@Override
	public String toString() {
		return "MantisHistory [dateUpdate=" + dateUpdate + ", user=" + user + ", field=" + field + ", update=" + update
				+ "]";
	}



	public void setDateUpdate(String dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	
	
}
