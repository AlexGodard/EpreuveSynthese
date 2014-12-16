package ca.qc.cstj.android.epreuvesynthese.helpers;

import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.JsonObject;

import ca.qc.cstj.android.epreuvesynthese.InventaireFragment;
import ca.qc.cstj.android.epreuvesynthese.R;
import ca.qc.cstj.android.epreuvesynthese.models.Explorateur;

//Classe qui contient les informations de connexion d'un explorateur
public class SharedParams {

    //Attributs
    private static String _token;
    public static Explorateur _explorateur;

    //Méthode qui set un explorateur à partir d'un objet json
    public static void setExplorateur(JsonObject explorateur){

        _explorateur = new Explorateur(explorateur);

    }

    //Méthode qui set un token à partir d'une string
    public static void setToken(String s){

        _token = s;

    }

    //Méthode qui retourne le token
    public static String getToken(){

        if(_token.equals(null)){
            _token = "";
        }

        return _token;

    }

    //Méthode qui retourne true si un token est valide ou false dans le cas contraire
    public static boolean tokenIsValid(){

        boolean retour = false;

        //Pour valider un token on vérifie seulement s'il n'est pas une chaine vide
        //Le test est dans un try catch car il peut être null
        try{
            if(_token.length() > 0)
                retour = true;

        }
        catch(Exception e){
            retour = false;
        }


        return retour;


    }

    //Méthode qui permet de déconnecter un explorateur
    public static void disconnect()
    {
        _token = "";
    }





}
