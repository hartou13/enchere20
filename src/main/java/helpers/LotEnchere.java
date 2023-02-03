/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import model.enchere.Enchere;
import model.lot.Categorie;
import model.lot.Lot;

/**
 *
 * @author andri
 */
public class LotEnchere {
    Enchere enchere;
    Lot lot;
    Categorie[] liste;
    String[] photo64;

    
    public Categorie[] getListe() {
        return liste;
    }

    public void setListe(Categorie[] liste) {
        this.liste = liste;
    }

    

    public Enchere getEnchere() {
        return enchere;
    }

    public void setEnchere(Enchere enchere) {
        this.enchere = enchere;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public String[] getPhoto64() {
        return photo64;
    }

    public void setPhoto64(String[] photo64) {
        this.photo64 = photo64;
    }
    
}
