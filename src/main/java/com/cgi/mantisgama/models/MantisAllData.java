package com.cgi.mantisgama.models;

public class MantisAllData {

	private String id;
	private String project;
	private String categorie;
	private String severite;
	private String reproductibilite;
	private String dateStart;
	private String heureStart;
	private String dateCloture;
	private String heureCloture;
	private String dateLastUpdate;		
	private String reporter;
	private String assigned;
	private String priorite;
	private String etat;
	private String resume;
	private String brique;
	private String bu;
	
	@Override
	public String toString() {
		return "MantisAllData [id=" + id + ", project=" + project + ", categorie=" + categorie + ", severite="
				+ severite + ", reproductibilite=" + reproductibilite + ", dateStart=" + dateStart + ", heureStart="
				+ heureStart + ", dateCloture=" + dateCloture + ", heureCloture=" + heureCloture + ", dateLastUpdate="
				+ dateLastUpdate + ", reporter=" + reporter + ", assigned=" + assigned + ", priorite=" + priorite
				+ ", etat=" + etat + ", resume=" + resume + ", brique=" + brique + ", bu=" + bu + "]";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getReproductibilite() {
		return reproductibilite;
	}
	public void setReproductibilite(String reproductibilite) {
		this.reproductibilite = reproductibilite;
	}
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public String getDateLastUpdate() {
		return dateLastUpdate;
	}
	public void setDateLastUpdate(String dateLastUpdate) {
		this.dateLastUpdate = dateLastUpdate;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	public String getAssigned() {
		return assigned;
	}
	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}
	public String getPriorite() {
		return priorite;
	}
	public void setPriorite(String priorite) {
		this.priorite = priorite;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getBrique() {
		return brique;
	}
	public void setBrique(String brique) {
		this.brique = brique;
	}
	public String getBu() {
		return bu;
	}
	public void setBu(String bu) {
		this.bu = bu;
	}

	public String getHeureStart() {
		return heureStart;
	}

	public void setHeureStart(String heureStart) {
		this.heureStart = heureStart;
	}

	public String getDateCloture() {
		return dateCloture;
	}

	public void setDateCloture(String dateCloture) {
		this.dateCloture = dateCloture;
	}

	public String getHeureCloture() {
		return heureCloture;
	}

	public void setHeureCloture(String heureCloture) {
		this.heureCloture = heureCloture;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}

}
