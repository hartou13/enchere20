package connexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Connexion {
    public static Connection getConnexion () {
        Connection conn=null;
          try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/encheres5","postgres","root");
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }
           catch (SQLException e) {
            e.printStackTrace();
          }
          return conn;  
    }    
}
