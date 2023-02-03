package model.enchere;

import java.sql.Connection;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.postgresql.util.PGInterval;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import gdao.genericdao.ColumnName;
import model.EnchereEntity;
import model.lot.MongoSaryCrud;
import model.lot.Sary;

public class Enchere extends EnchereEntity<Enchere>{
    @ColumnName
    String refEnchere;
    @ColumnName
    Date debut;
    @ColumnName
    PGInterval duree;
    @ColumnName
    Double prixDeMisEnEnchere;
    
    @ColumnName("Lotid")
    Integer idLot;
    @ColumnName
    Double Commission;
    public String getRefEnchere() {
        return refEnchere;
    }
    public void setRefEnchere(String refEnchere) {
        this.refEnchere = refEnchere;
    }
    public Date getDebut() {
        return debut;
    }
    public void setDebut(Date debut) {
        this.debut = debut;
    }
    
    public Double getPrixDeMisEnEnchere() {
        return prixDeMisEnEnchere;
    }
    public void setPrixDeMisEnEnchere(Double prixDeMisEnEnchere) {
        this.prixDeMisEnEnchere = prixDeMisEnEnchere;
    }
    
    public Integer getIdLot() {
        return idLot;
    }
    public void setIdLot(Integer idLot) {
        this.idLot = idLot;
    }
    public Double getCommission() {
        return Commission;
    }
    public void setCommission(Double commission) {
        Commission = commission;
    }
    public PGInterval getDuree() {
        return duree;
    }
    public void setDuree(PGInterval duree) {
        this.duree = duree;
    }
    List<Sary> listSary;
    
    @Override
    public String toString() {
        return "Enchere [refEnchere=" + refEnchere + ", debut=" + debut + ", duree=" + duree + ", prixDeMisEnEnchere="
                + prixDeMisEnEnchere + ", idLot=" + idLot + ", Commission=" + Commission + ", listSary=" + listSary
                + "]";
    }
    @Override
    public int save() throws Exception {
        int res=super.save();
        if(listSary!=null && listSary.size()!=0){
            MongoSaryCrud msc=new MongoSaryCrud();
            int idLot=this.get().get(0).getIdLot();
            for (Sary sary : listSary) {
                sary.setIdLot(idLot);
                msc.create(sary);
            }
            msc.close();
        }
        // TODO Auto-generated method stub
        return res;
    }
    @Override
    public int save(Connection con) throws Exception {
        int res=super.save(con);
        // TODO Auto-generated method stub
        return res;
    }
    public List<Sary> getListSary() {
        if(listSary==null){
            MongoSaryCrud msc=new MongoSaryCrud();
            this.listSary=msc.getSaryLot(idLot);
            msc.close();
            // this.listSary=ss.findByidLot(idLot);
        }
        return this.listSary;
    }
    public void setListSary(List<Sary> listSary) {
        this.listSary = listSary;
    }
    public List<Sary> getSary(){
        MongoSaryCrud msc=new MongoSaryCrud();
        return msc.getSaryLot(idLot);
        
        // return ss.findByidLot(idLot);
    }
    
}
