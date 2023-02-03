package model.lot;

import gdao.genericdao.ColumnName;
import gdao.genericdao.TableName;
import gdao.inherit.DBModel;

@TableName("categorie_lot")
public class CategorieLot extends DBModel<CategorieLot, Integer> {
    @ColumnName("Categorieid")
    Integer idCategorie;
    @ColumnName("Lotid")
    Integer idLot;
    public Integer getIdCategorie() {
        return idCategorie;
    }
    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }
    public Integer getIdLot() {
        return idLot;
    }
    public void setIdLot(Integer idLot) {
        this.idLot = idLot;
    }
    
}
