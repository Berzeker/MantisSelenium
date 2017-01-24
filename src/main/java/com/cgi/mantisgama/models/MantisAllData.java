package com.cgi.mantisgama.models;

public class MantisAllData {

	private MantisGeneralInfo mantisgeneralInfo;
	
	private String responsable;
	private String rapporteur;
	private String dateDebut;
	private String dateCloture;
	private String heureDebut;
	private String heureFin;
	
	public MantisAllData() {}

	public String getId() {
		return mantisgeneralInfo.getId();
	}

	public void setId(String id) {
		mantisgeneralInfo.setId(id);
	}

	public String getResume() {
		return mantisgeneralInfo.getResume();
	}

	public void setResume(String resume) {
		mantisgeneralInfo.setResume(resume);
	}

	public String getStatut() {
		return mantisgeneralInfo.getStatut();
	}

	public void setStatut(String statut) {
		mantisgeneralInfo.setStatut(statut);
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getRapporteur() {
		return rapporteur;
	}

	public void setRapporteur(String rapporteur) {
		this.rapporteur = rapporteur;
	}

	public String getCategorie() {
		return mantisgeneralInfo.getCategorie();
	}

	public void setCategorie(String categorie) {
		mantisgeneralInfo.setCategorie(categorie);
	}

	public String getProjet() {
		return mantisgeneralInfo.getProjet();
	}

	public void setProjet(String projet) {
		mantisgeneralInfo.setProjet(projet);
	}

	public String getSeverite() {
		return mantisgeneralInfo.getSeverite();
	}

	public void setSeverite(String severite) {
		mantisgeneralInfo.setSeverite(severite);
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateCloture() {
		return dateCloture;
	}

	public void setDateCloture(String dateCloture) {
		this.dateCloture = dateCloture;
	}

	public String getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(String heureDebut) {
		this.heureDebut = heureDebut;
	}

	public String getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(String heureFin) {
		this.heureFin = heureFin;
	}

	public MantisGeneralInfo getMantisgeneralInfo() {
		return mantisgeneralInfo;
	}

	public void setMantisgeneralInfo(MantisGeneralInfo mantisgeneralInfo) {
		this.mantisgeneralInfo = mantisgeneralInfo;
	}
	
	
	
}
