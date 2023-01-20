package model.gestionArgent;

import java.util.Date;

import gdao.genericdao.ColumnName;
import model.EnchereEntity;

/**
 * V_vola_miditra
 */
public class V_vola_miditra extends EnchereEntity<V_vola_miditra> {
    @ColumnName
    String refMouvement;
    @ColumnName
    String motif;
    @ColumnName("demandeRechargeid")
    String idDemandeRecharge;
    @ColumnName
    Date daty;
    @ColumnName
    Double somme;
    @ColumnName("utilisateurid")
    String idUtilisateur;
    
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
    public String getIdDemandeRecharge() {
        return idDemandeRecharge;
    }
    public void setIdDemandeRecharge(String idDemandeRecharge) {
        this.idDemandeRecharge = idDemandeRecharge;
    }
    
    public Date getDaty() {
        return daty;
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
    public void setDaty(Date daty) {
        this.daty = daty;
    }
    
    
}