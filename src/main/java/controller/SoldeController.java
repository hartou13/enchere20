package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import gdao.genericdao.GenericDAO;
import gdao.genericdao.exception.DatabaseConfException;
import helpers.Token;
import model.gestionArgent.V_solde_user;
import responseHandler.Error;
import responseHandler.*;

@RestController
@RequestMapping("/solde")
@CrossOrigin(origins={"http://localhost:3001/","http://localhost:3000/","http://localhost:80/kilometrage/*"})

public class SoldeController {
    @GetMapping()
    public String getSolde(@RequestHeader(name="authorization") String token) throws Exception{
        Gson gson = new Gson();
        if(Token.verifExpiredTokenUser(token)) {
        HashMap<String , Object> res=new HashMap<>();
        try {
            Connection con=GenericDAO.getConPost();
            res.put("liste", new V_solde_user().getAll(con));
            con.close();
            System.gc();
        } catch (ClassNotFoundException | IOException | SQLException | DatabaseConfException e) {
            e.printStackTrace();

            return gson.toJson(new Failure(new Error(500, "Error getting solde")));
        }
        return gson.toJson(new Success(res));
        }else {
            Failure er=new Failure(new Error(403, "You are not allowed to access"));
            return gson.toJson(er);
        }
    }
    @GetMapping("/{idUtilisateur}")
    public String getSoldeusr(@PathVariable(value="idUtilisateur") String idUtilisateur, @RequestHeader(name="authorization") String token) throws Exception{
        Gson gson = new Gson();
        if(Token.verifExpiredTokenUser(token)) {
        HashMap<String , Object> res=new HashMap<>();
        try {
            Connection con=GenericDAO.getConPost();
            res.put("liste", new V_solde_user().getById(Integer.parseInt(idUtilisateur), con));
            con.close();
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();

            return gson.toJson(new Failure(new Error(500, "Error getting solde")));
        }
        return gson.toJson(new Success(res));
        }else {
            Failure er=new Failure(new Error(403, "You are not allowed to access"));
            return gson.toJson(er);
        }
    }
}
