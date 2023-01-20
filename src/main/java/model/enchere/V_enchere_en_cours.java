package model.enchere;

import gdao.genericdao.ColumnName;
import model.lot.Lot;

public class V_enchere_en_cours extends Enchere{
    @ColumnName(value="Lotid", fk=true)
    Lot lot;

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }
    
}
