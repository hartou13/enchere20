package helpers;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import connexion.Connexion;



public class Token {
    int idToken ;
    String token ;
    int idadmin;
    Timestamp dateExpiration;
    public Token(){

    }
    
    public Token(int idToken, String token, int idadmin, Timestamp dateExpiration) {
        this.idToken = idToken;
        this.token = token;
        this.idadmin = idadmin;
        this.dateExpiration = dateExpiration;
    }
    public Token(String token, int idadmin) {
        this.token = token;
        this.idadmin = idadmin;
    }
    public int getIdToken() {
        return idToken;
    }
    public void setIdToken(int idToken) {
        this.idToken = idToken;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public int getIdadmin() {
        return idadmin;
    }
    public void setIdadmin(int idadmin) {
        this.idadmin = idadmin;
    }
    public Timestamp getDateExpiration() {
        return dateExpiration;
    }
    public void setDateExpiration(Timestamp dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
    public void insert(Connection con){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp dateExp=new Timestamp(now.getYear(), now.getMonth(), now.getDate(), now.getHours()+2, now.getMinutes()+5, now.getSeconds(), now.getNanos());
        String req="insert into tokenAdmin(token,id,dateExpiration) values(?,?,?) returning idToken";
        try{
        PreparedStatement stmt= con.prepareStatement(req);
        stmt.setString(1, getToken());
        stmt.setInt(2, getIdadmin());
        stmt.setTimestamp(3, dateExp);
        System.out.println("LALALALALALALALALALAALALALALLALLALAALLAALALAL   "+stmt);
        ResultSet s=stmt.executeQuery();
        int id=0;
        if(s.next()){
            id=s.getInt("idToken");
        }
        this.setIdToken(id);
        this.setDateExpiration(dateExp);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void insert()throws SQLException {
        Connection con=null;
           con=Connexion.getConnexion();
            insert(con);
            if(con!=null)
            con.close();
    }
    public void insertTokenUser(Connection con){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp dateExp=new Timestamp(now.getYear(), now.getMonth(), now.getDate(), now.getHours()+2, now.getMinutes()+5, now.getSeconds(), now.getNanos());
        String req="insert into tokenUser(token,id,dateExpiration) values(?,?,?) returning idToken";
        try{
        PreparedStatement stmt= con.prepareStatement(req);
        stmt.setString(1, getToken());
        stmt.setInt(2, getIdadmin());
        stmt.setTimestamp(3, dateExp);
        System.out.println("LALALALALALALALALALAALALALALLALLALAALLAALALAL   "+stmt);
        ResultSet s=stmt.executeQuery();
        int id=0;
        if(s.next()){
            id=s.getInt("idToken");
        }
        this.setIdToken(id);
        this.setDateExpiration(dateExp);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void insertTokenUser()throws SQLException {
        Connection con=null;
           con=Connexion.getConnexion();
            insertTokenUser(con);
            if(con!=null)
            con.close();
    }
    public static Token select(String token,Connection c){
        token=token.replace("Bearer ","");
        Token r=null;
        String req="select * from tokenAdmin where token=?";
        try{
        PreparedStatement stmt=c.prepareStatement(req);
        stmt.setString(1, token);
        System.out.println(stmt);
        ResultSet res=stmt.executeQuery();
        if(res.next()){
            r = new Token();
            r.setIdToken(res.getInt("idToken"));
            r.setIdadmin(res.getInt("id"));
            r.setToken(res.getString("TOKEN"));
            r.setDateExpiration(res.getTimestamp("dateExpiration")); 
            System.out.println("setiavako");
        }
        return r;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return r;
    }
    public static Token selectTokenUser(String token,Connection c){
        token=token.replace("Bearer ","");
        Token r=null;
        String req="select * from tokenUser where token=?";
        try{
        PreparedStatement stmt=c.prepareStatement(req);
        stmt.setString(1, token);
        System.out.println(stmt);
        ResultSet res=stmt.executeQuery();
        if(res.next()){
            r = new Token();
            r.setIdToken(res.getInt("idToken"));
            r.setIdadmin(res.getInt("id"));
            r.setToken(res.getString("TOKEN"));
            r.setDateExpiration(res.getTimestamp("dateExpiration")); 
            System.out.println("setiavako");
        }
        return r;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return r;
    }
    public static Token select(String token)throws SQLException{
        Token rep=null;
        Connection con=null;
            con=Connexion.getConnexion();
            rep =select(token,con);
            if(con!=null)
            con.close();
            return rep;
    }
    public static Token selectTokenUser(String token)throws SQLException{
        Token rep=null;
        Connection con=null;
            con=Connexion.getConnexion();
            rep =selectTokenUser(token,con);
            if(con!=null)
            con.close();
            return rep;
    }
    public  void delete(Connection con) throws SQLException{
        String req="delete from tokenAdmin where token=?";
        try{
        PreparedStatement stmt=con.prepareStatement(req);
        stmt.setString(1,this.getToken());
        stmt.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void delete() throws SQLException,ClassNotFoundException{
        Connection c= null;
            c=Connexion.getConnexion();
            this.delete(c);
        if(c!=null){
        c.close();
        }
    }
    public  void deleteTokenUser(Connection con) throws SQLException{
        String req="delete from tokenUser where tokenUser=?";
        try{
        PreparedStatement stmt=con.prepareStatement(req);
        stmt.setString(1,this.getToken());
        stmt.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteTokenUser() throws SQLException,ClassNotFoundException{
        Connection c= null;
            c=Connexion.getConnexion();
            this.deleteTokenUser(c);
        if(c!=null){
        c.close();
        }
    }
    public static boolean verifExpired(String token,Connection c){
        try{
        Token temp=Token.select(token);
        if(temp==null) return false;
        Timestamp now= new Timestamp(System.currentTimeMillis());
    
        System.out.println("IL EST MAINTENANT ="+now.toString());
        System.out.println("IL SERA EXPIRE A  ="+temp.getDateExpiration());
        if(now.before(temp.getDateExpiration())){
            return true;  
        }
        else {
            temp.delete(); 
        }
        }catch(SQLException|ClassNotFoundException e){                                  
            e.printStackTrace();
        }
        return false;
    }
    public static boolean verifExpiredTokenUser(String token,Connection c){
        try{
        Token temp=Token.selectTokenUser(token);
        if(temp==null) return false;
        Timestamp now= new Timestamp(System.currentTimeMillis());
    
        System.out.println("IL EST MAINTENANT ="+now.toString());
        System.out.println("IL SERA EXPIRE A  ="+temp.getDateExpiration());
        if(now.before(temp.getDateExpiration())){
            return true;  
        }
        else {
            temp.delete(); 
        }
        }catch(SQLException|ClassNotFoundException e){                                  
            e.printStackTrace();
        }
        return false;
    }

    public static boolean verifExpired(String token) throws SQLException,ClassNotFoundException{
        Connection c= null;
        c=Connexion.getConnexion();
        if(c!=null)
        c.close();
        return verifExpired(token, c);
    }
    public static boolean verifExpiredTokenUser(String token) throws SQLException,ClassNotFoundException{
        Connection c= null;
        c=Connexion.getConnexion();
        if(c!=null)
        c.close();
        return verifExpiredTokenUser(token, c);
    }
}
