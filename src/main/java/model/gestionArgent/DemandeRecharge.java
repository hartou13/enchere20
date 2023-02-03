package model.gestionArgent;

import gdao.genericdao.ColumnName;
import model.EnchereEntity;

public class DemandeRecharge extends EnchereEntity<DemandeRecharge>{
    @ColumnName
    String refDemande;
    @ColumnName
    Double somme;
    @ColumnName
    Integer utilisateurid;
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
    public Integer getUtilisateurid() {
        return utilisateurid;
    }
    public void setUtilisateurid(Integer utilisateurid) {
        this.utilisateurid = utilisateurid;
    }
    
    
    
}
