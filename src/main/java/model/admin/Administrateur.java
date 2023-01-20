package model.admin;

import gdao.genericdao.ColumnName;
import model.EnchereEntity;

public class Administrateur extends EnchereEntity<Administrateur>{
    @ColumnName
    String refAdmin, email, mdp, nom;

    public String getRefAdmin() {
        return refAdmin;
    }

    public void setRefAdmin(String refAdmin) {
        this.refAdmin = refAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
