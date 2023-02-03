package controller;

import java.util.Date;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import gdao.genericdao.GenericDAO;
import model.enchere.Enchere;
import model.enchere.Mise;
import model.lot.Lot;
import responseHandler.Error;
import responseHandler.Failure;
import responseHandler.Success;

@CrossOrigin
@RestController
@RequestMapping("/encherir")
@SpringBootApplication
public class EncherirController {
	
	
	@GetMapping("/rencherir/{idenchere}")
	public String rencherir(@PathVariable(value = "idenchere") Integer idEnchere,@RequestParam(name = "idUtilisateur") Integer idUtilisateur,@RequestParam(name = "somme") Double somme) {
		Gson gson=new Gson();
		try {
			Mise mise=new Mise().getMiseMax(idEnchere,GenericDAO.getConPost());
			// System.out.println(mise);
			if(mise==null || mise.getSomme()==null|| mise.getSomme()<somme) {
				 if(mise !=null && mise.getSomme()==null && mise.getIdUtilisateur()==idUtilisateur) {
					return gson.toJson(new Failure(new Error(403,"You are already the winner")));
				}
				else {
					Mise newMise=new Mise();
					newMise.setIdEnchere(idEnchere);
					newMise.setIdUtilisateur(idUtilisateur);
					newMise.setSomme(somme);
					newMise.setDaty(new Date());
					newMise.save();
					System.gc();
					return gson.toJson(new Success("Success"));
				}
			}
			else {
				return gson.toJson(new Failure(new Error(403,"The balance is less than the max "+mise.getSomme())));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.gc();
			e.printStackTrace();
		}
		return null;
	}
	
	
	@GetMapping("/getMiseMax")
	public String getMiseMax(@RequestParam(name = "id") Integer idEnchere) {
		Gson gson=new Gson();
		try {
			Mise mise=new Mise().getMiseMax(idEnchere, GenericDAO.getConPost());
			System.gc();
			return gson.toJson(new Success(mise.getSomme()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.gc();
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/Enchere/{id}")
	public String getEnchere(@PathVariable(value="id") Integer idEnchere)
	{
		Gson gson=new Gson();
		try {
			Enchere enchere=new Enchere().getById(idEnchere);
			Lot lot=new Lot().getById(enchere.getIdLot());
			return gson.toJson(new Success(lot));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}	
	
}
