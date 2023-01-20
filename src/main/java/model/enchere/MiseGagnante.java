package model.enchere;

import java.util.Date;

import gdao.genericdao.ColumnName;
import model.EnchereEntity;

public class MiseGagnante extends EnchereEntity<MiseGagnante> {
    @ColumnName("miseid")
    Integer idMise;

    public Integer getIdMise() {
        return idMise;
    }

    public void setIdMise(Integer idMise) {
        this.idMise = idMise;
    }
    
    
    
}
