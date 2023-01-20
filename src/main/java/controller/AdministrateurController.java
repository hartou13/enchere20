package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import gdao.genericdao.GenericDAO;
import helpers.Encrypte;
import helpers.Token;
import model.admin.Administrateur;
import responseHandler.Error;
import responseHandler.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin

public class AdministrateurController {
    @PutMapping()
    public String update(@RequestBody Administrateur user,@RequestHeader(name="authorization") String token) {
        
        Gson gson = new Gson();
        Administrateur userTaloha=new Administrateur();
        try {
            if(Token.verifExpired(token)) {
            user.setMdp(Encrypte.getMd5Hash(user.getMdp()));
            userTaloha.setId(user.getId());
            userTaloha.setRefAdmin(user.getRefAdmin());
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
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Failure er=new Failure(new Error(403, "You are not allowed to access"));
            return gson.toJson(er);
            
        } 
    }
    @GetMapping("{idAdmin}")
    public String getListUsers(@PathVariable(value="idAdmin") String idAdmin,@RequestHeader(name="authorization") String token) throws ClassNotFoundException, SQLException{
        Gson gson = new Gson();
        HashMap<String , Object> res=new HashMap<>();
        if(Token.verifExpired(token)) {
        try {
            Connection con=GenericDAO.getConPost();
            Administrateur ref=new Administrateur();
            ref.setId(Integer.parseInt(idAdmin));
            res.put("liste", ref.getAll(con));
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
}
