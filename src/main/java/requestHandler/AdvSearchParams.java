package requestHandler;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.postgresql.util.PGInterval;

import gdao.genericdao.ColumnName;
import gdao.genericdao.GenericDAO;
import gdao.genericdao.exception.DatabaseConfException;
import model.enchere.V_enchere_recherche;

//{"word":["test"],"datemin":["2023-01-03"],"datemax":["2023-01-31"],"prixmin":["0"],"prixmax":["24998"],"cat":["2","3"],"status":["1"]}
public class AdvSearchParams {
    String[] word;
    Date[] datemin, datemax;
    Double[] prixmin,prixmax;
    Integer[] cat;
    Integer[] status;
    public String[] getWord() {
        return word;
    }
    public void setWord(String[] word) {
        this.word = word;
    }
    public Date[] getDatemin() {
        return datemin;
    }
    public void setDatemin(Date[] datemin) {
        this.datemin = datemin;
    }
    public Date[] getDatemax() {
        return datemax;
    }
    public void setDatemax(Date[] datemax) {
        this.datemax = datemax;
    }
    public Double[] getPrixmin() {
        return prixmin;
    }
    public void setPrixmin(Double[] prixmin) {
        this.prixmin = prixmin;
    }
    public Double[] getPrixmax() {
        return prixmax;
    }
    public void setPrixmax(Double[] prixmax) {
        this.prixmax = prixmax;
    }
    public Integer[] getCat() {
        return cat;
    }
    public void setCat(Integer[] cat) {
        this.cat = cat;
    }
    public Integer[] getStatus() {
        return status;
    }
    public void setStatus(Integer[] status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "AdvSearchParams [word=" + Arrays.toString(word) + ", datemin=" + Arrays.toString(datemin) + ", datemax="
                + Arrays.toString(datemax) + ", prixmin=" + Arrays.toString(prixmin) + ", prixmax="
                + Arrays.toString(prixmax) + ", cat=" + Arrays.toString(cat) + ", status=" + Arrays.toString(status)
                + "]";
    }
    public ArrayList<V_enchere_recherche> getResult() throws Exception{
        String sql=" select v_enchere_recherche.* from v_enchere_recherche left join categorie_lot on categorie_lot.lotid=v_enchere_recherche.lotid where 1=1  ";
        ArrayList<Object> list=new ArrayList<>();
        if(this.getWord()!=null){
            String[] temp=this.getWord();
            sql+=" and ( nomlot like ? )";
            list.add("%"+temp[0]+"%");
        }
        if(this.getDatemin()!=null){
            sql+=" and debut + duree > ?";
            list.add(this.getDatemin()[0]);
        }
        if(this.getDatemax()!=null){
            sql+=" and debut < ?";
            list.add(this.getDatemax()[0]);
        }
        if(this.getPrixmin()!=null){
            sql+=" and prixdemisenenchere > ? ";
            list.add(this.getPrixmin()[0]);
        }
        if(this.getPrixmax()!=null){
            sql+=" and prixdemisenenchere < ? ";
            list.add(this.getPrixmax()[0]);
        }
        if(this.getStatus()!=null){
            sql+=" and (";
            for (int i = 0; i < status.length; i++) {
                if(i!=0)
                    sql+= " or "; 
                sql+=" status = ? ";
                String ataoAo="";
                if(this.getStatus()[i]==0){
                    ataoAo = "did not begin";
                }
                else if(this.getStatus()[i]==1){
                    ataoAo = "going on";
                }
                else if(this.getStatus()[i]==2){
                    ataoAo = "to be finished";
                }
                else if(this.getStatus()[i]==3){
                    ataoAo = "finished";
                }
                list.add(ataoAo);
            }
            sql+=")";
        }
        if(this.getCat()!=null){
            sql+=" and (";
            for (int i = 0; i < cat.length; i++) {
                if(i!=0)
                    sql+= " or "; 
                sql+=" categorieid = ? ";
                list.add(cat[i]);
            }
            sql+=" )";
        }
        sql +=" group by id, refenchere, debut, duree, prixdemisenenchere, v_enchere_recherche.lotid, commission, status, nomlot";
        Connection con = GenericDAO.getConPost();
        PreparedStatement ps = con.prepareStatement(sql);
        GenericDAO.setObjectInStatement(list, ps);
        System.out.println(ps);
        // System.out.println(ps);
        ResultSet RS=ps.executeQuery();
        ArrayList<String> champSelect = new ArrayList();
        ArrayList<Field> fiSetable = new ArrayList<>();
        GenericDAO.listGetable(champSelect, fiSetable, new V_enchere_recherche());
        ArrayList<V_enchere_recherche> res=new ArrayList<>();
        while (RS.next()) {
            V_enchere_recherche otemp = new V_enchere_recherche();
            for (int i = 0; i < champSelect.size(); i++) {
                Method set = GenericDAO.setter(fiSetable.get(i));
                if(RS.getObject(champSelect.get(i))!=null){
                    if (fiSetable.get(i).getType().getSimpleName().equalsIgnoreCase("Date")) {
                        java.sql.Date temp=RS.getDate(champSelect.get(i));
                        set.invoke(otemp, new Date(temp.getTime()));
                    } 
                    else if (fiSetable.get(i).getType().getSimpleName().equalsIgnoreCase("Duration")) {
                        PGInterval pgInterval = (PGInterval) RS.getObject(champSelect.get(i));
                        Duration duration = Duration.ofSeconds((long) pgInterval.getSeconds(), pgInterval.getMicroSeconds() * 1000);
                        set.invoke(otemp, duration);
                    } else {
                        System.out.println(fiSetable.get(i).getType().getName()+" "+RS.getObject(champSelect.get(i)).getClass().getName());
                        set.invoke(otemp, RS.getObject(champSelect.get(i)));
                    }
                }
            }
            otemp.getListSary();
            res.add(otemp);
        }
        RS.close();
        ps.close();
        con.close();

        return res;
    }
}
