package model.stat;

import gdao.genericdao.ColumnName;
import model.enchere.Enchere;

public class Full_v_nb_mise_par_enchere extends Enchere {
    @ColumnName
    Integer isa;

    public Integer getIsa() {
        return isa;
    }

    public void setIsa(Integer isa) {
        this.isa = isa;
    }
}
