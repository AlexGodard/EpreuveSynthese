package ca.qc.cstj.android.epreuvesynthese.models;

import com.google.gson.JsonObject;

/**
 * Created by 1247308 on 2014-11-21.
 */
public class Explorateur {

    private String nom;
    private String nomUtilisateur;
    private Inventaire inventaire;

    public Explorateur(JsonObject explorateur){

        nom = explorateur.getAsJsonPrimitive("name").getAsString();
        nomUtilisateur = explorateur.getAsJsonPrimitive("username").getAsString();

        if(explorateur.has("runes")){

            inventaire = new Inventaire(explorateur.getAsJsonObject("runes"));

        }
        else{
            inventaire = new Inventaire();

        }

    }

    public Explorateur(){

        nom = "";
        nomUtilisateur = "";

        inventaire = new Inventaire();

    }
}
