package model;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connexion.Connexion;
import exception.EmailException;
import exception.PasswordException;
import helpers.Encrypte;
import helpers.Token;
import helpers.TokenManager;

public class User {
    int id;
    String refUtilisateur;
    String email;
    String mdp;
    String nom;
    String prenom;
    Date dateDeNaissance;  
    public User() {
    }
    public User(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }
    public User(String refUtilisateur, String email, String mdp, String nom, String prenom, String dateDeNaissance) throws EmailException{
        setRefUtilisateur(refUtilisateur);
        setEmail(email);
        setMdp(mdp);
        setNom(prenom);
        setPrenom(prenom);
        setDateDeNaissance(dateDeNaissance);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getRefUtilisateur() {
        return refUtilisateur;
    }
    public void setRefUtilisateur(String refUtilisateur) {
        this.refUtilisateur = refUtilisateur;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email)throws EmailException {
        this.email=email;
    }
    public String getMdp() {
        return mdp;
    }
    public void setMdp(String mdp) {
        if(mdp!=null){
            this.mdp = Encrypte.getMd5Hash(mdp);
        }
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }
    public void setDateDeNaissance(String dateDeNaissance) {
        Date d=Date.valueOf(dateDeNaissance);
        this.dateDeNaissance = d;
    }
    
    public static boolean verifEmail(Connection c, String email){
        boolean rep=true;
        String req="select * from utilisateur where email=?";
        try{
        PreparedStatement stmt=c.prepareStatement(req);
        stmt.setString(1, email);
        ResultSet res=stmt.executeQuery();
        if(res.next()){
            return rep;
        }
        else rep =false;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return rep;
    }
    public static boolean verifEmail(String email){
        Connection c=null;
        boolean rep=true;
        try {
            c=Connexion.getConnexion();
            rep=verifEmail(c, email);
            if(c!=null){
                c.close();
            }
        } catch (SQLException e) {
            // TODO: handle exception
        }
        return rep;
    }
    public  void inscription(Connection c)throws EmailException{
        String req="insert into utilisateur(email,mdp,nom,prenom,dateDeNaissance) values(?,?,?,?,?)";
        if(verifEmail(this.getEmail())==true){
            throw new EmailException("Veuillez entrer un autre email , elle existe deja");
        }
        else{
            try{
            PreparedStatement stmt=c.prepareStatement(req);
            stmt.setString(1, this.getEmail());
            stmt.setString(2, this.getMdp());
            stmt.setString(3, this.getNom());
            stmt.setString(4, this.getPrenom());
            stmt.setDate(5, this.dateDeNaissance);
            stmt.execute();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    public void inscription()throws EmailException{
        Connection c=null;
        try{
            c=Connexion.getConnexion();
            this.inscription(c);
            if(c!=null){
                c.close();
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static Token connexion(Connection c,String email,String mdp)throws EmailException,PasswordException{
        String req="select * from utilisateur where email=?";
        Token temp=null;
        try{    
            PreparedStatement stmt=c.prepareStatement(req);
            stmt.setString(1, email);
            ResultSet res=stmt.executeQuery();
            if(res.next()){
                if(res.getString("mdp").equalsIgnoreCase(mdp)){
                    int id=res.getInt("id");
                    String nptq = TokenManager.generateToken(id+mdp);
                    temp= new Token(nptq,id);
                    temp.insertTokenUser();
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
