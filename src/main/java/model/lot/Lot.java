package model.lot;

import gdao.genericdao.ColumnName;
import gdao.genericdao.TableName;
import model.EnchereEntity;
@TableName("Lot")
public class Lot extends EnchereEntity<Lot>{
    @ColumnName
    String refLot, nomLot,descriptionLot;
    @ColumnName
    Double valeur, nombre;
    @ColumnName
    Integer utilisateurId;
    public String getRefLot() {
        return refLot;
    }
    public void setRefLot(String refLot) {
        this.refLot = refLot;
    }
    public String getNomLot() {
        return nomLot;
    }
    public void setNomLot(String nomLot) {
        this.nomLot = nomLot;
    }
    public String getDescriptionLot() {
        return descriptionLot;
    }
    public void setDescriptionLot(String descriptionLot) {
        this.descriptionLot = descriptionLot;
    }
    public Double getValeur() {
        return valeur;
    }
    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }
    public Double getNombre() {
        return nombre;
    }
    public void setNombre(Double nombre) {
        this.nombre = nombre;
    }
    public Integer getUtilisateurId() {
        return utilisateurId;
    }
    public void setUtilisateurId(Integer utilisateurId) {
        this.utilisateurId = utilisateurId;
    }
    
}
