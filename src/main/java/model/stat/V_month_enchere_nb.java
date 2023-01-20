package model.stat;

import gdao.genericdao.ColumnName;
import gdao.inherit.DBModel;

public class V_month_enchere_nb extends DBModel<V_month_enchere_nb, Integer> {
    @ColumnName
    Integer isa;

    public Integer getIsa() {
        return isa;
    }

    public void setIsa(Integer isa) {
        this.isa = isa;
    }

    
}
