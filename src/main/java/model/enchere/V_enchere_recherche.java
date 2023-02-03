package model.enchere;

import gdao.genericdao.ColumnName;

public class V_enchere_recherche extends Enchere{
    @ColumnName
    String nomlot;

    public String getNomlot() {
        return nomlot;
    }

    public void setNomlot(String nomlot) {
        this.nomlot = nomlot;
    }
    
}
