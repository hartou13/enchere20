package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * EnchereController
 */import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;

import gdao.genericdao.GenericDAO;
import gdao.genericdao.exception.DatabaseConfException;
import helpers.Token;
import model.Historique;
import model.Traitement;
import model.enchere.Enchere;
import model.enchere.Full_V_enchere_tokn_vitaina;
import model.enchere.Full_v_enchere_en_cours;
import model.enchere.Full_v_enchere_statut;
import model.enchere.MiseGagnante;
import model.enchere.V_enchere_en_cours;
import model.enchere.V_enchere_tokn_vitaina;
import responseHandler.Error;
import responseHandler.Failure;
import responseHandler.Success;

@RestController
@CrossOrigin

@RequestMapping("/encheres")
public class EnchereController {
    @PostMapping("clore/{id}")
    public String clore(Integer idEnchere){
        
        try {
            Full_V_enchere_tokn_vitaina v=new Full_V_enchere_tokn_vitaina();
            v.setId(idEnchere);
            v=(Full_V_enchere_tokn_vitaina) v.get().get(0);
            MiseGagnante mg=new MiseGagnante();
            mg.setIdMise(v.getIdMise());
            mg.save();
            return new Gson().toJson(new Success("true"));
        } catch (Exception e) {
            return new Gson().toJson(new Failure(new Error(500, e.getMessage())));
        }
    }
    @GetMapping("/toEnd")
    public String getToknHoVita(@RequestHeader(name="authorization") String token) throws ClassNotFoundException, SQLException{
        Gson gson = new Gson();
        HashMap<String , Object> res=new HashMap<>();
        if(Token.verifExpired(token)) {
        try {
            Connection con=GenericDAO.getConPost();
            res.put("liste", new Full_V_enchere_tokn_vitaina().getAll(con));
            con.close();
            System.gc();
        } catch (ClassNotFoundException | IOException | SQLException | DatabaseConfException e) {
            e.printStackTrace();

            return gson.toJson(new Failure(new Error(500, "Error getting encheres")));
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return gson.toJson(new Success(res));
        }else {
            Failure er=new Failure(new Error(403, "You are not allowed to access"));
            return gson.toJson(er);
        }
    }

    @GetMapping("/onGoing")
    public String getEnchereEnCours(@RequestHeader(name="authorization") String token) throws Exception{
        Gson gson = new Gson();
        HashMap<String , Object> res=new HashMap<>();
        if(Token.verifExpired(token)) {
        try {
            Connection con=GenericDAO.getConPost();
            res.put("liste", new Full_v_enchere_en_cours().getAll(con));
            con.close();
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();

            return gson.toJson(new Failure(new Error(500, "Error getting encheres")));
        }
        return gson.toJson(new Success(res));
        }else {
            Failure er=new Failure(new Error(403, "You are not allowed to access"));
            return gson.toJson(er);
        }
    }
    @GetMapping("/bettedAuction/{id}")
    public String getEnchereMise(@PathVariable Integer id,@RequestHeader(name="authorization") String token) throws Exception{
        System.out.println("--------"+id);
        Gson gson = new Gson();
        if(Token.verifExpiredTokenUser(token)) {
        HashMap<String , Object> res=new HashMap<>();
        Full_v_enchere_statut temp=new Full_v_enchere_statut();
        temp.setIdUtilisateur(id);
        try {
            Connection con=GenericDAO.getConPost();
            res.put("liste", temp.get(con));
            con.close();
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();

            return gson.toJson(new Failure(new Error(500, "Error getting encheres")));
        }
        return gson.toJson(new Success(res));
        }else {
            Failure er=new Failure(new Error(403, "You are not allowed to access"));
            return gson.toJson(er);
        }
    }
    
    @GetMapping("/historique")
	public String getHistorique(@RequestParam(value="id") Integer utilisateur) {
		Gson gson=new Gson();
		
        try {
            List<Enchere> listenchere;
		listenchere = Traitement.listEnchereUtilisateur(utilisateur);
		return gson.toJson(new Success(listenchere));
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new Failure(new Error(500,e.getMessage())));    
        }
	}
}