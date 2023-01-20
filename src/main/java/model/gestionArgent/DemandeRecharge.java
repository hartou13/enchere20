package model.gestionArgent;

import gdao.genericdao.ColumnName;
import model.EnchereEntity;

public class DemandeRecharge extends EnchereEntity<DemandeRecharge>{
    @ColumnName
    String refDemande;
    @ColumnName
    Double somme;
    @ColumnName("utilisateurid")
    String idUtilisateur;
    public String getRefDemande() {
        return refDemande;
    }
    public void setRefDemande(String refDemande) {
        this.refDemande = refDemande;
    }
    public Double getSomme() {
        return somme;
    }
    public void setSomme(Double somme) {
        this.somme = somme;
    }
    public String getIdUtilisateur() {
        return idUtilisateur;
    }
    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    
}
