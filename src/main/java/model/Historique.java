package model;

import model.enchere.Enchere;
import model.utilisateur.Utilisateur;

public class Historique {

	Enchere enchere;
	Utilisateur gagnant;
	Double somme;
	public Enchere getEnchere() {
		return enchere;
	}
	public void setEnchere(Enchere enchere) {
		this.enchere = enchere;
	}
	public Utilisateur getGagnant() {
		return gagnant;
	}
	public void setGagnant(Utilisateur gagnant) {
		this.gagnant = gagnant;
	}
	public Double getSomme() {
		return somme;
	}
	public void setSomme(Double somme) {
		this.somme = somme;
	}
	public Historique(Enchere enchere, Utilisateur gagnant, Double somme) {
		super();
		this.enchere = enchere;
		this.gagnant = gagnant;
		this.somme = somme;
	}
	
	
	
}
