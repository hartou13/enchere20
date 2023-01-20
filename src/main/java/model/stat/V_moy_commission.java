package model.stat;

import gdao.genericdao.ColumnName;
import gdao.inherit.DBModel;

public class V_moy_commission extends DBModel<V_moy_commission, Double>{
    @ColumnName
    Double moyenne_commission;

    public Double getMoyenne_commission() {
        return moyenne_commission;
    }

    public void setMoyenne_commission(Double moyenne_commission) {
        this.moyenne_commission = moyenne_commission;
    }
    
}
