package model.gestionArgent;

import java.util.Date;

import gdao.genericdao.ColumnName;
import model.EnchereEntity;

public class V_vola_mivoaka extends EnchereEntity<V_vola_mivoaka> {
    @ColumnName
    String refMouvement;
    @ColumnName
    String motif;
    @ColumnName("misegagnanteid")
    String idMiseGagnante;
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
    public String getIdMiseGagnante() {
        return idMiseGagnante;
    }
    public void setIdMiseGagnante(String idMiseGagnante) {
        this.idMiseGagnante = idMiseGagnante;
    }
    
    
    
}
