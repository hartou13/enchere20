package connexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import gdao.genericdao.GenericDAO;
import gdao.genericdao.exception.DatabaseConfException;
public class Connexion {
    public static Connection getConnexion () {
        Connection conn=null;
          try {
            Class.forName("org.postgresql.Driver");
            conn = GenericDAO.getConPost();       
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }
           catch (SQLException e) {
            e.printStackTrace();
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          } catch (DatabaseConfException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          return conn;  
    }    
}
