package ca.qc.cstj.android.epreuvesynthese.helpers;

import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.JsonObject;

import ca.qc.cstj.android.epreuvesynthese.InventaireFragment;
import ca.qc.cstj.android.epreuvesynthese.R;
import ca.qc.cstj.android.epreuvesynthese.models.Explorateur;

/**
 * Created by 0738628 on 2014-11-28.
 */
public class SharedParams {

    private static String _token;
    public static Explorateur _explorateur;


    public static void setExplorateur(JsonObject explorateur){

        _explorateur = new Explorateur(explorateur);

    }

    public static void setToken(String s){

        _token = s;

    }

    public static String getToken(){

        if(_token.equals(null)){
            _token = "";
        }

        return _token;

    }

    public static boolean tokenIsValid(){

        boolean retour = false;

        try{
            if(_token.length() > 0)
                retour = true;

        }
        catch(Exception e){
            retour = false;
        }


        return retour;


    }

    public static void disconnect()
    {
        _token = "";
    }





}
