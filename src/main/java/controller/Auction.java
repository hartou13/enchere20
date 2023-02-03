package controller;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
// import com.project.auction.model.Categorie;
import model.lot.Categorie;
import responseHandler.*;
import responseHandler.Error;

// import com.project.auction.model.Erreur;
// import com.project.auction.model.ErreurType;
// import com.project.auction.model.Success;
import gdao.genericdao.GenericDAO;
import gdao.genericdao.exception.DatabaseConfException;
import helpers.Token;


@CrossOrigin
@RestController
@RequestMapping("/categorie")
public class Auction {
	@CrossOrigin
	@GetMapping("/getAll")
	public String getAll() throws Exception {
		Gson gson=new Gson();
		Categorie categorie=new Categorie();
		try {
			String res=gson.toJson(new responseHandler.Success(categorie.getAll()));
			System.gc();
			return res;
		} catch (SQLException | DatabaseConfException e) {
			return gson.toJson(new Failure(new responseHandler.Error(500, "Email , non reconnu veuiller vous inscrire")));
		}
		
	}
	
	@CrossOrigin
	@GetMapping("/get")
	public String get(@RequestParam("ref")String ref,@RequestHeader(name="authorization") String token) throws ClassNotFoundException, SQLException {
		Categorie categorie=new Categorie(ref);
		Gson gson=new Gson();
		if(Token.verifExpired(token)) {
		try {
			String retour= gson.toJson(new Success(GenericDAO.getById(categorie,GenericDAO.getConPost())));
			System.gc();
			return retour;
		} catch (Exception e) {
			return gson.toJson(new Failure(new Error(500,"Erreur de serveur")));
		}
		}else {
    	    Failure er=new Failure(new Error(403, "You are not allowed to access"));
        	return gson.toJson(er);
        }
	}
	
	
	@CrossOrigin
	@PostMapping("/save")
	public String insert(@RequestParam("nom")String nom,@RequestHeader(name="authorization") String token) throws Exception {
		Categorie categorie=new Categorie();
		categorie.setNomCategorie(nom);
		Gson gson=new Gson();
		if(Token.verifExpired(token)) {
		try {
			categorie.save();
			System.gc();
			return gson.toJson(new Success(true));
		} catch (DatabaseConfException e) {
			return gson.toJson(new Failure(new Error(500,"Insertion echoue")));
		}
		}else {
    	    Failure er=new Failure(new Error(403, "You are not allowed to access"));
        	return gson.toJson(er);
        }
	}
	
	@CrossOrigin
	@PutMapping("/update")
	public String update(@RequestParam("ref")String ref,@RequestParam("nom")String nom,@RequestHeader(name="authorization") String token) throws Exception {
		Categorie categorie=new Categorie(ref);
		Categorie newCategorie=new Categorie();
		Gson gson=new Gson();
		newCategorie.setNomCategorie(nom);
		if(Token.verifExpired(token)) {
		try {
			categorie.update(newCategorie);
			System.gc();
			return gson.toJson(new Success(true));
		} catch (DatabaseConfException e) {
			return gson.toJson(new Failure(new Error(500,"Mise a jour echoue")));
		}
		}else {
    	    Failure er=new Failure(new Error(403, "You are not allowed to access"));
        	return gson.toJson(er);
        }
	}
	
	
	@CrossOrigin
	@DeleteMapping("/delete")
	public String delete(@RequestParam("ref")String ref,@RequestHeader(name="authorization") String token) throws Exception {
		Categorie categorie=new Categorie(ref);
		Gson gson=new Gson();
		if(Token.verifExpired(token)) {
		try {
			categorie.delete();
			System.gc();
			return gson.toJson(new Success(true));
		} catch (DatabaseConfException e) {
			return gson.toJson(new Failure(new Error(500,"Delete echoue")));
		}
		}else {
    	    Failure er=new Failure(new Error(403, "You are not allowed to access"));
        	return gson.toJson(er);
        }
	}
}
