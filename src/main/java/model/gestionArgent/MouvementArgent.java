package model.gestionArgent;

import java.util.Date;

import gdao.genericdao.ColumnName;
import gdao.inherit.DBModel;
import model.EnchereEntity;

public class MouvementArgent extends EnchereEntity<MouvementArgent> {
    @ColumnName
    String refMouvement;
    @ColumnName
    String motif;
    @ColumnName("demandeRechargeid")
    Integer idDemandeRecharge;
    @ColumnName("miseGagnanteid")
    Integer idMiseGagnante;
    @ColumnName
    Date daty;

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

    

    public Integer getIdDemandeRecharge() {
        return idDemandeRecharge;
    }

    public void setIdDemandeRecharge(Integer idDemandeRecharge) {
        this.idDemandeRecharge = idDemandeRecharge;
    }

    public Integer getIdMiseGagnante() {
        return idMiseGagnante;
    }

    public void setIdMiseGagnante(Integer idMiseGagnante) {
        this.idMiseGagnante = idMiseGagnante;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

}
