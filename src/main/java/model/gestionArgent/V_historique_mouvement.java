package model.gestionArgent;

import java.util.Date;

import gdao.genericdao.ColumnName;
import gdao.inherit.DBModel;
import model.EnchereEntity;

public class V_historique_mouvement extends EnchereEntity<V_historique_mouvement> {
    @ColumnName
    String refMouvement;
    @ColumnName
    String motif;
    @ColumnName
    Integer idMotif;
    @ColumnName
    Date daty;
    @ColumnName
    Double somme;
    @ColumnName("utilisateurid")
    Integer idUtilisateur;
    public String getRefMouvement() {
        return refMouvement;
    }
    public void setRefMouvement(String refMouvement) {
        this.refMouvement = refMouvement;
    }
    public String getMotif() {
        return motif;
    }
    public void setMotif(String motif) {
        this.motif = motif;
    }
    public Integer getIdMotif() {
        return idMotif;
    }
    public void setIdMotif(Integer idMotif) {
        this.idMotif = idMotif;
    }
    public Date getDaty() {
        return daty;
    }
    public void setDaty(Date daty) {
        this.daty = daty;
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
    
}
