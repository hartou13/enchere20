package model.enchere;

import gdao.genericdao.ColumnName;

public class Full_V_enchere_tokn_vitaina extends Enchere {
    @ColumnName
    Double maxmise;
    @ColumnName("utilisateurid")
    Integer idUtilisateur;
    @ColumnName("miseid")
    Integer idMise;
    public Integer getIdMise() {
        return idMise;
    }
    public void setIdMise(Integer idMise) {
        this.idMise = idMise;
    }
    public Double getMaxmise() {
        return maxmise;
    }
    public void setMaxmise(Double maxmise) {
        this.maxmise = maxmise;
    }
    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }
    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    
}

