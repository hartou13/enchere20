package model.lot;

import gdao.genericdao.ColumnName;
import model.EnchereEntity;

public class Categorie extends EnchereEntity<Categorie>{
    @ColumnName
    String refCategorie,nomCategorie;

    public String getRefCategorie() {
        return refCategorie;
    }

    public void setRefCategorie(String refCategorie) {
        this.refCategorie = refCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public Categorie() {}
	
	public Categorie(String ref) {
		setRefCategorie(ref);
	}
	
    
}
