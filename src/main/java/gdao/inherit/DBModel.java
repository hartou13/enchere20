package gdao.inherit;
import gdao.genericdao.GenericDAO;
import gdao.genericdao.exception.DatabaseConfException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Hart
 * @param <T>
 * @param <E>
 */
public class DBModel <T extends DBModel,E extends Object>{
    public T getById(E o) throws Exception{
            this.setPkVal(o);
            try{
                return (T)this.get().get(0);
            }catch (IndexOutOfBoundsException e){
                System.out.println("inexisant");
                return null;
            }
    }
    public T getById(E o, Connection con) throws Exception{
            this.setPkVal(o);
            try{
                return (T)this.get(con).get(0);
            }catch (IndexOutOfBoundsException e){
                System.out.println("inexisant");
                return null;
            }
    }
    public ArrayList<T> getAll() throws Exception{
            ArrayList<T> temp=GenericDAO.get(this.getClass().newInstance(), null);
            return temp;
    }
    public ArrayList<T> getAll(Connection con) throws Exception{
            ArrayList<T> temp=GenericDAO.get(this.getClass().newInstance(), con);
            return temp;
    }
    public ArrayList<T> get() throws Exception{
            ArrayList<T> temp=GenericDAO.get(this, null);
            return temp;
    }
    public ArrayList<T> get(Connection con) throws Exception{
            ArrayList<T> temp=GenericDAO.get(this, con);
            return temp;
    }
    public int delete() throws Exception{
            return GenericDAO.delete(this, null);
    }
    public int delete(Connection con) throws Exception{
            return GenericDAO.delete(this, con);
    }
    public int save() throws Exception{
            return GenericDAO.save(this, null);
    }
    public int save(Connection con) throws Exception{
            return GenericDAO.save(this, con);
    }
    public int update(T mods)throws Exception{
            return GenericDAO.update(mods,this, null);
    }
    public int update(T mods, Connection con) throws Exception{
            return GenericDAO.update(mods,this, con);
    }
    public Object getPkVal() throws Exception{
        Field fi=GenericDAO.getPK(this.getClass());
        return GenericDAO.getter(fi).invoke(this);
    }
    public void setPkVal(E o) throws Exception{
        Field fi=GenericDAO.getPK(this.getClass());
        System.out.println(fi.getName());
        GenericDAO.setter(fi).invoke(this, o);
    }
    public Vector getPossibleFk() throws Exception{
        Vector v=new Vector();
        ArrayList li=this.getAll();
        li.forEach((el)->{
            try {
                v.add((DBModel)el);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        });
        return v;
    }
}
