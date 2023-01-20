package model.enchere;

import gdao.genericdao.ColumnName;

public class Full_v_enchere_en_cours extends Enchere{
    @ColumnName
    Double maxmise;
    @ColumnName("utilisateurid")
    Integer idUtilisateur;
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
