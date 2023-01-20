package model.stat;

import gdao.genericdao.ColumnName;
import model.utilisateur.Utilisateur;

public class V_depense_moy extends Utilisateur{
    @ColumnName
    Double somme;

    public Double getSomme() {
        return somme;
    }

    public void setSomme(Double somme) {
        this.somme = somme;
    }
    
}
