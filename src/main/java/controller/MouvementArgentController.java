package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;

import gdao.genericdao.GenericDAO;
import gdao.genericdao.exception.DatabaseConfException;
import helpers.Token;
import model.enchere.V_enchere_tokn_vitaina;
import model.gestionArgent.V_historique_mouvement;
import responseHandler.Failure;
import responseHandler.Success;
import responseHandler.Error;

@RestController
@RequestMapping("/vola")
@CrossOrigin

public class MouvementArgentController {
    @GetMapping()
    public String getHistorique(@RequestHeader(name="authorization") String token) throws Exception{
        Gson gson = new Gson();
        if(Token.verifExpired(token)) {
        HashMap<String , Object> res=new HashMap<>();
        try {
            Connection con=GenericDAO.getConPost();
            res.put("liste", new V_historique_mouvement().getAll(con));
            con.close();
            System.gc();
        } catch (ClassNotFoundException | IOException | SQLException | DatabaseConfException e) {
            e.printStackTrace();

            return gson.toJson(new Failure(new Error(500, "Error getting history")));
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return gson.toJson(new Success(res));
        }else {
            Failure er=new Failure(new Error(403, "You are not allowed to access"));
            return gson.toJson(er);
        }
    }
}
