package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * EnchereController
 */import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
import com.kilometer.kilo.KiloApplication;

import c.C;
import gdao.genericdao.GenericDAO;
import gdao.genericdao.exception.DatabaseConfException;
import helpers.LotEnchere;
import helpers.Token;
import model.Historique;
import model.Traitement;
import model.enchere.Enchere;
import model.enchere.Full_V_enchere_tokn_vitaina;
import model.enchere.Full_v_enchere_en_cours;
import model.enchere.Full_v_enchere_statut;
import model.enchere.HistoriqueCommission;
import model.enchere.MiseGagnante;
import model.enchere.V_comission_actuelle;
import model.enchere.V_enchere_en_cours;
import model.enchere.V_enchere_recherche;
import model.enchere.V_enchere_statut_lot;
import model.enchere.V_enchere_tokn_vitaina;
import model.lot.Categorie;
import model.lot.CategorieLot;
import model.lot.Lot;
import model.lot.MongoSaryCrud;
import model.lot.Sary;
import requestHandler.AdvSearchParams;
import responseHandler.Error;
import responseHandler.Failure;
import responseHandler.Response;
import responseHandler.Success;

@RestController
@CrossOrigin

@RequestMapping("/encheres")
public class EnchereController {
    @PostMapping("clore/{id}")
    public String clore(Integer idEnchere){
        
        try {
            Full_V_enchere_tokn_vitaina v=new Full_V_enchere_tokn_vitaina();
            v.setId(idEnchere);
            v=(Full_V_enchere_tokn_vitaina) v.get().get(0);
            Lot lot=new Lot();
            lot.setId(v.getIdLot());
            lot=lot.get().get(0);

            KiloApplication.sendNotification(lot);

            MiseGagnante mg=new MiseGagnante();
            mg.setIdMise(v.getIdMise());
            mg.save();
            System.gc();
            return new Gson().toJson(new Success("true"));
        } catch (Exception e) {
            return new Gson().toJson(new Failure(new Error(500, e.getMessage())));
        }
    }
    @GetMapping("/toEnd")
    public String getToknHoVita(@RequestHeader(name="authorization") String token) throws ClassNotFoundException, SQLException{
        Gson gson = new Gson();
        HashMap<String , Object> res=new HashMap<>();
        if(Token.verifExpired(token)) {
        try {
            Connection con=GenericDAO.getConPost();
            res.put("liste", new Full_V_enchere_tokn_vitaina().getAll(con));
            con.close();
            System.gc();
        } catch (ClassNotFoundException | IOException | SQLException | DatabaseConfException e) {
            e.printStackTrace();

            return gson.toJson(new Failure(new Error(500, "Error getting encheres")));
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return gson.toJson(new Success(res));
        }else {
            Failure er=new Failure(new Error(403, "You are not allowed to access"));
            return gson.toJson(er);
        }
    }

    @GetMapping("/onGoing")
    public String getEnchereEnCours(@RequestHeader(name="authorization") String token) throws Exception{
        Gson gson = new Gson();
        HashMap<String , Object> res=new HashMap<>();
        if(Token.verifExpired(token)) {
        try {
            Connection con=GenericDAO.getConPost();
            ArrayList<Enchere> li=new Full_v_enchere_en_cours().getAll(con);
            for (Enchere enchere : li) {
                enchere.getListSary();
                System.out.println(enchere);
            }
            res.put("liste", li);
            con.close();
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();

            return gson.toJson(new Failure(new Error(500, "Error getting encheres")));
        }
        return gson.toJson(new Success(res));
        }else {
            Failure er=new Failure(new Error(403, "You are not allowed to access"));
            return gson.toJson(er);
        }
    }
    @GetMapping("/bettedAuction/{id}")
    public String getEnchereMise(@PathVariable Integer id,@RequestHeader(name="authorization") String token) throws Exception{
        System.out.println("--------"+id);
        Gson gson = new Gson();
        if(Token.verifExpiredTokenUser(token)) {
        HashMap<String , Object> res=new HashMap<>();
        Full_v_enchere_statut temp=new Full_v_enchere_statut();
        temp.setIdUtilisateur(id);
        try {
            Connection con=GenericDAO.getConPost();
            ArrayList<Enchere> list=temp.get(con);
            for (Enchere enchere : list) {
                enchere.getListSary();
            }
            res.put("liste", list);
            con.close();
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();

            return gson.toJson(new Failure(new Error(500, "Error getting encheres")));
        }
        return gson.toJson(new Success(res));
        }else {
            Failure er=new Failure(new Error(403, "You are not allowed to access"));
            return gson.toJson(er);
        }
    }
    @GetMapping("/ownAuction/{id}")
    public String ownAuction(@PathVariable Integer id,@RequestHeader(name="authorization") String token) throws Exception{
        System.out.println("--------"+id);
        Gson gson = new Gson();
        if(Token.verifExpiredTokenUser(token)) {
        V_enchere_statut_lot temp=new V_enchere_statut_lot();
        temp.setUtilisateur(id);
        Object o;
        try {
            Connection con=GenericDAO.getConPost();
            o= temp.get(con);
            con.close();
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();

            return gson.toJson(new Failure(new Error(500, "Error getting encheres")));
        }
        return gson.toJson(new Success(o));
        }else {
            Failure er=new Failure(new Error(403, "You are not allowed to access"));
            return gson.toJson(er);
        }
    }
    
    @GetMapping("/historique")
	public String getHistorique(@RequestParam(value="id") Integer utilisateur) {
		Gson gson=new Gson();
		
        try {
            List<Enchere> listenchere;
		listenchere = Traitement.listEnchereUtilisateur(utilisateur);
        System.gc();
		return gson.toJson(new Success(listenchere));
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new Failure(new Error(500,e.getMessage())));    
        }
	}
    @PostMapping("/search")
	public String search(@RequestBody AdvSearchParams params) throws Exception{
        Gson gson = new Gson();
        try {
            ArrayList<V_enchere_recherche> res=params.getResult();
            System.gc();
            return gson.toJson(new Success(res));
        } catch (Exception e) {
            return gson.toJson(new Failure(new Error(500,e.getMessage())));
        }
        
	}
    @PostMapping("/insertenchere")
    public String insert(@RequestBody LotEnchere lot) throws Exception{
        Lot l=lot.getLot();
        C c=new C();
        Connection con=c.getConnection();
        con.setAutoCommit(false);
        try {
            //save lot
            l.save(con);
            Lot za=l.get(con).get(0);
            // za.setUtilisateurId(null);
            System.out.println(za.getId());

            za.getId();
            // insertion sary
            MongoSaryCrud msc=new MongoSaryCrud();
			Sary temp2=new Sary();
			temp2.setIdLot(za.getId());
            for (int i = 0; i < lot.getPhoto64().length; i++) {
                temp2.setSary(lot.getPhoto64()[i]);
                msc.create(temp2);
            }
            msc.close();
            


            //save categorie
            Categorie[] listecat=lot.getListe();
            for(Categorie i:listecat){
                CategorieLot temp=new CategorieLot();
                temp.setIdLot(za.getId());
                temp.setIdCategorie(i.getId());
                temp.save(con);
            }
            
            //save enchere
            Enchere enchere =lot.getEnchere();
            enchere.setIdLot(za.getId());
            HistoriqueCommission hc=new V_comission_actuelle().get().get(0);
            enchere.setCommission(hc.getValeur());
            enchere.save(con);
            con.commit();
            con.close();
            
            
        } catch (Exception ex) {
            con.rollback();
            con.close();
            return new Gson().toJson(new Failure(new Error(500, ex.getMessage())));
        }
        return (new Gson()).toJson(lot.getListe());
    }
    @GetMapping("/{id}")
	public String getEnchere(@PathVariable(value="id") Integer idEnchere)
	{
		Gson gson=new Gson();
		try {
			Enchere enchere=new Enchere().getById(idEnchere);
			Lot lot=new Lot().getById(enchere.getIdLot());
            System.gc();
			return gson.toJson(new Success(lot));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}	
}