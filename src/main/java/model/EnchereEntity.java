package model;

import gdao.genericdao.ColumnName;
import gdao.inherit.DBModel;

public class EnchereEntity<T extends DBModel<T, Integer>> extends DBModel<T , Integer> {
    @ColumnName(pk=true)
    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
