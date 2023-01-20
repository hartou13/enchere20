/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.gestionArgent;

import c.C;
import dbaccess.valid.Validable;
import gdao.genericdao.ColumnName;
import gdao.genericdao.TableName;
import gdao.inherit.DBModel;
import java.sql.Connection;
import java.util.Date;

/**
 *
 * @author orlando
 */
@TableName("DemandeRecharge_NON_VALIDER")
public class DemandeRecharge_NON_VALIDER extends DBModel<DemandeRecharge_NON_VALIDER, Integer>  implements Validable{

    @ColumnName(pk=true)
    Integer id;
    @ColumnName
    String refdemande;
    @ColumnName
    Double somme;

    @ColumnName
    Integer idutilisateur;

    @ColumnName
    String refutilisateur;

    @ColumnName
    String nom;

    @ColumnName
    String prenom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRefdemande() {
        return refdemande;
    }

    public void setRefdemande(String refdemande) {
        this.refdemande = refdemande;
    }

    public Double getSomme() {
        return somme;
    }

    public void setSomme(Double somme) {
        this.somme = somme;
    }

    public Integer getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(Integer idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public String getRefutilisateur() {
        return refutilisateur;
    }

    public void setRefutilisateur(String refutilisateur) {
        this.refutilisateur = refutilisateur;
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

    @Override
    public void valider() throws Exception {
        MouvementArgent mv=new MouvementArgent();
        mv.setIdDemandeRecharge(id);
        mv.setMotif("Recharge Fond!");
        mv.setDaty(new Date());
        C c=new C();
        Connection con=c.getConnection();
        mv.save(con);
        con.close();
        System.gc();
    }
    
}
