package model.stat;

import gdao.genericdao.ColumnName;
import model.utilisateur.Utilisateur;

public class V_lot_owner_nb extends Utilisateur{
    @ColumnName
    Integer isa;

    public Integer getIsa() {
        return isa;
    }

    public void setIsa(Integer isa) {
        this.isa = isa;
    }
    
}
