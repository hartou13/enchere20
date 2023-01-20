package model.enchere;

import java.util.Date;

import gdao.genericdao.ColumnName;
import gdao.genericdao.TableName;
import model.EnchereEntity;
@TableName("historique_commission")
public class HistoriqueCommission extends EnchereEntity<HistoriqueCommission> {
    @ColumnName
    Date daty;
    @ColumnName
    Double valeur;
    public Date getDaty() {
        return daty;
    }
    public void setDaty(Date daty) {
        this.daty = daty;
    }
    public Double getValeur() {
        return valeur;
    }
    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }
    
    
}
