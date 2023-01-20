/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package c;
import connection.Connectar;
import connection.Postgresql;
import java.sql.Connection;

/**
 *
 * @author orlando
 */
public class C  {

    public Connection getConnection() throws Exception {
        Connectar con=new Connectar(new Postgresql("encheres5"),"postgres","root",true);
            return con.getConnection();
    }
    
}
