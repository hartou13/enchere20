package model.enchere;

import gdao.genericdao.ColumnName;

public class Full_v_enchere_statut extends Enchere{
    @ColumnName("utilisateurid")
    Integer idUtilisateur;
    @ColumnName
    String status, nomlot;
    @ColumnName
    Double maxmise, somme;
    
    public Double getMaxmise() {
        return maxmise;
    }

    public void setMaxmise(Double maxmise) {
        this.maxmise = maxmise;
    }

    public Double getSomme() {
        return somme;
    }

    public void setSomme(Double somme) {
        this.somme = somme;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNomlot() {
        return nomlot;
    }

    public void setNomlot(String nomlot) {
        this.nomlot = nomlot;
    }
    
}
