package model.lot;

import gdao.genericdao.ColumnName;
import gdao.inherit.DBModel;

public class CategorieLot extends DBModel<CategorieLot, Integer> {
    @ColumnName("Categorieid")
    String idCategorie;
    @ColumnName("Lotid")
    String idLot;
    public String getIdCategorie() {
        return idCategorie;
    }
    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }
    public String getIdLot() {
        return idLot;
    }
    public void setIdLot(String idLot) {
        this.idLot = idLot;
    }
    
}
