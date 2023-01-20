package model.gestionArgent;

import java.io.Serializable;

import gdao.genericdao.ColumnName;
import model.utilisateur.Utilisateur;

public class V_solde_user extends Utilisateur implements Serializable{
    @ColumnName
    Double solde;

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }
    
}
