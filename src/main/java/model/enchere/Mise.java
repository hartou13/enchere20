package model.enchere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import gdao.genericdao.ColumnName;
import model.EnchereEntity;

public class Mise extends EnchereEntity<Mise>{
    @ColumnName
    String refMise;
    @ColumnName
    Double somme;
    @ColumnName
    Date daty;
    @ColumnName("utilisateurid")
    Integer idUtilisateur;
    @ColumnName("enchereid")
    Integer idEnchere;
    public String getRefMise() {
        return refMise;
    }
    public void setRefMise(String refMise) {
        this.refMise = refMise;
    }
    public Double getSomme() {
        return somme;
    }
    public void setSomme(Double somme) {
        this.somme = somme;
    }
    public Date getDaty() {
        return daty;
    }
    public void setDaty(Date daty) {
        this.daty = daty;
    }
    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }
    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    public Integer getIdEnchere() {
        return idEnchere;
    }
    public void setIdEnchere(Integer idEnchere) {
        this.idEnchere = idEnchere;
    }
    
    public Mise getMiseMax(Integer idEnchere,Connection con) throws Exception {
    	Mise mise=new Mise();
    	String sql="select * from mise where enchereid="+idEnchere+" and somme=(select max(somme) from mise where enchereid="+idEnchere+")";
    	PreparedStatement stmt=con.prepareStatement(sql);
    	ResultSet res=stmt.executeQuery();
    	while(res.next()) {
    		mise=getById(res.getInt("id"));
    	}
    	return mise;
    }
    @Override
    public String toString() {
        return "Mise [refMise=" + refMise + ", somme=" + somme + ", daty=" + daty + ", idUtilisateur=" + idUtilisateur
                + ", idEnchere=" + idEnchere + "]";
    }
    
    

}
