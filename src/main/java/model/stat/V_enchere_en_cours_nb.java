package model.stat;

import gdao.genericdao.ColumnName;
import gdao.inherit.DBModel;

public class V_enchere_en_cours_nb extends DBModel<V_enchere_en_cours_nb, String> {
    @ColumnName
    Integer isa;

    public Integer getIsa() {
        return isa;
    }

    public void setIsa(Integer isa) {
        this.isa = isa;
    }
}
