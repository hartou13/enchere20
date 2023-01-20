package model.stat;

import gdao.genericdao.ColumnName;
import model.lot.Categorie;

public class Full_v_trends extends Categorie{
    @ColumnName
    Integer nbMise;

    public Integer getNbMise() {
        return nbMise;
    }

    public void setNbMise(Integer nbMise) {
        this.nbMise = nbMise;
    }
    
}
