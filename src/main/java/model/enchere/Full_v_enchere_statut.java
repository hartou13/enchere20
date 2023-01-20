package model.enchere;

import gdao.genericdao.ColumnName;

public class Full_v_enchere_statut extends Enchere{
    @ColumnName("utilisateurid")
    Integer idUtilisateur;
    @ColumnName
    String status;
    
    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
