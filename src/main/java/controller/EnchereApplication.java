package controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exception.EmailException;
import exception.PasswordException;
import helpers.Encrypte;
import helpers.ErrInfo;
import helpers.Error;
import helpers.OwnResponse;
import helpers.Success;
import helpers.Token;
import model.Administrator;
import model.User;
import model.utilisateur.Utilisateur;
import responseHandler.Failure;
import responseHandler.Response;

@SpringBootApplication
@RestController
@CrossOrigin

public class EnchereApplication {

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	
	@PostMapping("/ConnexionAdmin")
	public Response connexionAdmin(@RequestBody Administrator admin){
		try{
			Token token=Administrator.connnexion(admin.getEmail(),admin.getMdp());
			
			System.gc();
			return new responseHandler.Success(token);
		}catch(EmailException e){
			return new Failure(new responseHandler.Error(500, "Email , non reconnu veuiller vous inscrire"));
		}catch(PasswordException e){
			return new Failure(new responseHandler.Error(500, "mot de passe erone"));
		}
	}
	@PostMapping("/ConnexionUser")
	public OwnResponse connexionUser(@RequestBody User utilisateur){
		try{
			Token token=User.connnexion(utilisateur.getEmail(),utilisateur.getMdp());
			
			System.gc();
			return new Success(token);
		}catch(EmailException e){
			e.printStackTrace();
			Error er=new Error();
			er.setInfo(new ErrInfo(500, "Email , non reconnu veuiller vous inscrire"));
			return er;
		}catch(PasswordException e){
			e.printStackTrace();
			e.printStackTrace();
			Error er=new Error();
			er.setInfo(new ErrInfo(500, "mot de passe erone"));
			return er;
		}
	}
	@PostMapping(value="/Inscription")
	public OwnResponse inscription(@RequestBody User u) {
		try{
			u.inscription();
			Utilisateur usr=new Utilisateur();
			usr.setEmail(u.getEmail());
			usr.setDateDeNaissance(u.getDateDeNaissance());
			usr.setMdp(Encrypte.SHA1(u.getMdp()));
			usr=usr.get().get(0);
			System.gc();
			return new Success(usr);
		}catch(EmailException e){
			// e.printStackTrace();
			Error er=new Error();
			er.setInfo(new ErrInfo(500, "cette Email existe deja inserer un nouveau"));
			System.gc();
			return er;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			Error er=new Error();
			er.setInfo(new ErrInfo(500, e.getMessage()));
			System.gc();
			
			return er;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			Error er=new Error();
			er.setInfo(new ErrInfo(500, e.getMessage()));
			System.gc();
			
			return er;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Error er=new Error();
			er.setInfo(new ErrInfo(500, e.getMessage()));
			System.gc();
			
			return er;
		}
	}	
}
