package model.enchere;

import gdao.genericdao.ColumnName;

public class V_enchere_statut_lot extends Enchere {
    @ColumnName
    String reflot, nomlot,descriptionlot, status;
    @ColumnName
    Double valeur, nombre;
    @ColumnName("utilisateurid")
    Integer utilisateur;
    
    public String getReflot() {
        return reflot;
    }
    public void setReflot(String reflot) {
        this.reflot = reflot;
    }
    public String getNomlot() {
        return nomlot;
    }
    public void setNomlot(String nomlot) {
        this.nomlot = nomlot;
    }
    public String getDescriptionlot() {
        return descriptionlot;
    }
    public void setDescriptionlot(String descriptionlot) {
        this.descriptionlot = descriptionlot;
    }
    public Double getValeur() {
        return valeur;
    }
    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }
    public Double getNombre() {
        return nombre;
    }
    public void setNombre(Double nombre) {
        this.nombre = nombre;
    }
    public Integer getUtilisateur() {
        return utilisateur;
    }
    public void setUtilisateur(Integer utilisateur) {
        this.utilisateur = utilisateur;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
}
