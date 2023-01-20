package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;

import gdao.genericdao.GenericDAO;
import gdao.genericdao.exception.DatabaseConfException;
import helpers.Token;
import model.stat.V_depense_moy;
import model.stat.*;
import model.utilisateur.Utilisateur;
import responseHandler.*;
import responseHandler.Error;

@RestController
@RequestMapping("/stat")
@CrossOrigin(origins={"http://localhost:3001/","http://localhost:3000/","http://localhost:80/kilometrage/*"})
public class StatController {
    @GetMapping()
    public String getStat(@RequestHeader(name="authorization") String token) throws Exception{
        Gson gson = new Gson();
        if(Token.verifExpired(token)) {
        HashMap<String , Object> res=new HashMap<>();
        try {
            Connection con=GenericDAO.getConPost();

            ArrayList<Utilisateur> v_depense_moy=new V_depense_moy().getAll(con);
            res.put("v_depense_moy", v_depense_moy);
            res.put("v_participation_moy",new V_participation_moy().getAll(con));
            res.put("v_lot_owner_nb", new V_lot_owner_nb().getAll(con));
            res.put("v_moy_commission", new V_moy_commission().getAll(con));
            res.put("full_v_trends", new Full_v_trends().getAll(con));
            res.put("full_v_nb_miseur_par_enchere", new Full_v_nb_miseur_par_enchere().getAll(con));
            res.put("full_v_nb_mise_par_enchere", new Full_v_nb_mise_par_enchere().getAll(con));
            res.put("v_enchere_en_cours_nb", new V_enchere_en_cours_nb().getAll(con));
            res.put("v_today_enchere_nb", new V_today_enchere_nb().getAll(con));
            res.put("v_month_enchere_nb", new V_month_enchere_nb().getAll(con));
            con.close();
            System.gc();
        } catch (ClassNotFoundException | IOException | SQLException | DatabaseConfException e) {
            e.printStackTrace();
            return gson.toJson(new Failure(new Error(500, "Error getting stats")));
        }
        return gson.toJson(new Success(res));
    }else {
        Failure er=new Failure(new Error(403, "You are not allowed to access"));
        return gson.toJson(er);
        }
}}
