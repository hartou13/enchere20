package model.stat;

import gdao.genericdao.ColumnName;
import model.utilisateur.Utilisateur;

public class V_participation_moy extends Utilisateur{
    @ColumnName
    Integer nbParticipation;

    public Integer getNbParticipation() {
        return nbParticipation;
    }

    public void setNbParticipation(Integer nbParticipation) {
        this.nbParticipation = nbParticipation;
    }
    
}
