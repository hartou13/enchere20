package controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import helpers.Token;
import model.enchere.HistoriqueCommission;
import responseHandler.Error;
import responseHandler.Failure;
import responseHandler.Response;
import responseHandler.Success;

@RestController
@RequestMapping("/historiqueCommission")
public class HistoriqueCommissionController {
    @PostMapping
    public String addCommi(@RequestBody HistoriqueCommission hc,@RequestHeader(name="authorization") String token) throws ClassNotFoundException, SQLException{
        Gson gson = new Gson();
        if(Token.verifExpired(token)) {
        try {
            
            hc.save();
            return gson.toJson(new Success(hc.get().get(0)));
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new Failure(new Error(500, e.getLocalizedMessage())));
        }
        }else {
            Failure er=new Failure(new Error(403, "You are not allowed to access"));
            return gson.toJson(er);
        }
    }
}
