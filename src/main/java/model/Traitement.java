package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gdao.genericdao.exception.DatabaseConfException;
import model.enchere.Enchere;
import model.lot.Lot;
import model.utilisateur.Utilisateur;

public class Traitement {

	public static List<Enchere> listEnchereUtilisateur(Integer utilisateurid) throws Exception{
		List<Enchere> list=new ArrayList<>();
		try {
			ArrayList<Enchere> allEncheres=new Enchere().getAll();
			for (Enchere enchere : allEncheres) {
				if(utilisateurid==Traitement.getUtilisateurPublier(enchere).getId()) {
					list.add(enchere);
				}
			}
		} catch (SQLException | DatabaseConfException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	

	public static Utilisateur getUtilisateurPublier(Enchere enchere) {
		try {
			ArrayList<Utilisateur> listUtilisateurs=new Utilisateur().getAll();
			ArrayList<Lot> listLot=new Lot().getAll();
			for (Lot lot : listLot) {
				if(lot.getId()==enchere.getIdLot()) {
					for (Utilisateur utilisateur : listUtilisateurs) {
						if(lot.getUtilisateurId()==utilisateur.getId()) {
							return utilisateur;
						}
					}
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
