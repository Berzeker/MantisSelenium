package com.cgi.mantisgama.models;

import java.util.Date;

public class MantisGeneralInfo {

	private String id;
	private String priorite;
	private String projet;
	private String categorie;
	private String severite;
	private String statut;
	private String affected;
	private Date lastUpdate;
	private String resume;
	
	public MantisGeneralInfo() {
		// TODO Auto-generated constructor stub
	}
		
	@Override
	public String toString() {
		return "MantisGeneralInfo [id=" + id + ", priorite=" + priorite + ", projet=" + projet + ", categorie="
				+ categorie + ", severite=" + severite + ", statut=" + statut + ", affected=" + affected
				+ ", lastUpdate=" + lastUpdate + ", resume=" + resume + "]";
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPriorite() {
		return priorite;
	}
	public void setPriorite(String priorite) {
		this.priorite = priorite;
	}
	public String getProjet() {
		return projet;
	}
	public void setProjet(String projet) {
		this.projet = projet;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getSeverite() {
		return severite;
	}
	public void setSeverite(String severite) {
		this.severite = severite;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public String getAffected() {
		return affected;
	}
	public void setAffected(String affected) {
		this.affected = affected;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	
	
	
}
