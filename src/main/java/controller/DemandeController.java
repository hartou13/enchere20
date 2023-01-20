/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import c.C;
import helpers.Token;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.gestionArgent.DemandeRecharge_NON_VALIDER;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import responseHandler.*;
// import request.OwnResponse;
// import request.Success;
import responseHandler.Error;

/**
 *
 * @author orlando
 */
@RestController
@RequestMapping("/dm")
@SpringBootApplication
@CrossOrigin(origins="*")
public class DemandeController {
    @GetMapping
    public String hello(){
        return "hello";
    }
    
    @GetMapping("/notvalidate")
    public String getList(@RequestHeader(name="authorization") String token) throws SQLException, ClassNotFoundException{
        Gson gson = new Gson();
        Response retour=null;
        if(Token.verifExpired(token)) {
        DemandeRecharge_NON_VALIDER l=new DemandeRecharge_NON_VALIDER();
        C c=new C();
        Connection con=null;

        try {
            con=c.getConnection();
           ArrayList<DemandeRecharge_NON_VALIDER> ret= l.getAll(con);
           Success success=new Success(ret);
           retour=success;
        } catch (Exception ex) {
            responseHandler.Error er=new responseHandler.Error(500, ex.getMessage());
            ex.printStackTrace();
            retour=er;
        }
        finally{
            con.close();
        }
        
        return gson.toJson(retour);
        }else {
            Failure er=new Failure(new Error(403, "You are not allowed to access"));
            return gson.toJson(er);
        }
    }
    @PostMapping("/validate/{id}")   
    public String validate(@PathVariable(value="id") Integer integ,@RequestHeader(name="authorization") String token) throws ClassNotFoundException, SQLException{
        DemandeRecharge_NON_VALIDER lasa=new DemandeRecharge_NON_VALIDER();
        
        Gson gson = new Gson();
        if(Token.verifExpired(token)) {
        lasa.setId(integ);
        Response retour=null;
        try {
            lasa.valider();
             Success success=new Success("SuccessFully Validated");
           retour=success;
        } catch (Exception ex) {
            responseHandler.Error er=new responseHandler.Error(500, ex.getMessage());
            retour=er;
        }
        return gson.toJson(retour);
        }else {
            Failure er=new Failure(new Error(403, "You are not allowed to access"));
            return gson.toJson(er);
        }
    }
    
    public static void main(String[] args) {
        SpringApplication.run(DemandeController.class, args);
    }
}
