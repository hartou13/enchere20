package model.utilisateur;

import java.util.Date;

import gdao.genericdao.ColumnName;
import gdao.genericdao.TableName;
import model.EnchereEntity;
@TableName("utilisateur")
public class Utilisateur extends EnchereEntity<Utilisateur>{
    @ColumnName
    String refUtilisateur, email, mdp, nom, prenom;
    @ColumnName
    Date dateDeNaissance;
    
    public String getRefUtilisateur() {
        return refUtilisateur;
    }
    public void setRefUtilisateur(String refUtilisateur) {
        this.refUtilisateur = refUtilisateur;
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
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }
    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }
    @Override
    public String toString() {
        return "Utilisateur [refUtilisateur=" + refUtilisateur + ", email=" + email + ", mdp=" + mdp + ", nom=" + nom
                + ", prenom=" + prenom + ", dateDeNaissance=" + dateDeNaissance + "]";
    }
    
}
