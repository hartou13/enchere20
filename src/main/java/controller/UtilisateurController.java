package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.tomcat.jni.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import gdao.genericdao.GenericDAO;
import gdao.genericdao.exception.DatabaseConfException;
import helpers.Token;
import model.utilisateur.Utilisateur;
import responseHandler.Error;
import responseHandler.*;

@RestController
@RequestMapping("/users")
@CrossOrigin

public class UtilisateurController {
    @GetMapping()
    public String getListUsers(@RequestHeader(name="authorization") String token) throws ClassNotFoundException, SQLException{
        Gson gson = new Gson();
        if(Token.verifExpired(token)) {
        HashMap<String , Object> res=new HashMap<>();
        try {
            Connection con=GenericDAO.getConPost();
            res.put("liste", new Utilisateur().getAll(con));
            con.close();
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new Failure(new Error(500, "Error getting save")));
        }
        return gson.toJson(new Success(res));
        }else {
            Failure er=new Failure(new Error(403, "You are not allowed to access"));
            return gson.toJson(er);
        }
    }
    @PutMapping()
    public String update(@RequestBody Utilisateur user,@RequestHeader(name="authorization") String token) throws ClassNotFoundException, SQLException {
        
        Gson gson = new Gson();
        if(Token.verifExpiredTokenUser(token)) {
        Utilisateur userTaloha=new Utilisateur();
        userTaloha.setId(user.getId());
        userTaloha.setRefUtilisateur(user.getRefUtilisateur());
        try {
            userTaloha.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new Failure(new Error(500, "erreur update")));
        }
        return gson.toJson(new Success("update effectué"));
        }else {
        Failure er=new Failure(new Error(403, "You are not allowed to access"));
        return gson.toJson(er);
        }
    }
}
