package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.UnsupportedEncodingException;
import java.lang.*;
import java.net.PasswordAuthentication;
import java.security.NoSuchAlgorithmException;

import connexion.Connexion;
import exception.EmailException;
import exception.PasswordException;
import helpers.Encrypte;
import helpers.Token;
import helpers.TokenManager;

public class Administrator {
    String id;
    String refAdmin;
    String email;
    String mdp;
    String nom;
    
    public Administrator() {
    }
    public Administrator(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }
    public Administrator(String id, String refAdmin, String email, String mdp, String nom) {
        this.id = id;
        this.refAdmin = refAdmin;
        this.email = email;
        this.mdp = mdp;
        this.nom = nom;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getRefAdmin() {
        return refAdmin;
    }
    public void setRefAdmin(String refAdmin) {
        this.refAdmin = refAdmin;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMdp() {
        return mdp;
    }
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public static Token connexion(Connection c,String email,String mdp)throws EmailException,PasswordException{
            String req="select * from administrateur where email=?";
            Token temp=null;
            try{    
                PreparedStatement stmt=c.prepareStatement(req);
                stmt.setString(1, email);
                ResultSet res=stmt.executeQuery();
                if(res.next()){
                    if(res.getString("mdp").equalsIgnoreCase(Encrypte.getMd5Hash(mdp))){
                        int id=res.getInt("id");
                        String nptq = TokenManager.generateToken(id+mdp);
                        temp= new Token(nptq,id);
                        temp.insert();
                        return temp;
                    }else{
                        throw new PasswordException("le mot de passe insere est eronne");
                    }
                }else{
                    throw new EmailException("veuillez vous inscrire , email inconnu");
                }
            }catch(SQLException e){
                e.printStackTrace();
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return temp;
    }
    public static Token connnexion(String email,String mdp) throws EmailException, PasswordException{
        Connection c=null;
        Token temp=null;
        try{
            c=Connexion.getConnexion();
            temp=connexion(c, email, mdp);
            if(c!=null){
                c.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return temp;
    }

}     
