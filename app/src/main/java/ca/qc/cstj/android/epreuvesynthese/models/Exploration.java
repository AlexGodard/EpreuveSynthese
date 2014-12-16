package ca.qc.cstj.android.epreuvesynthese.models;

import com.google.gson.JsonObject;

import org.joda.time.DateTime;

import ca.qc.cstj.android.epreuvesynthese.helpers.DateParser;

//Classe exploration
public class Exploration {

    //Attributs
    private String startLocation;
    private String endLocation;
    private String dateExploration;
    private Troop troop;

    //Constructeur public qui prend en paramètre un objet json
    public Exploration(JsonObject exploration)
    {
        // On doit strip les locations
        JsonObject locations = exploration.getAsJsonObject("locations");

        startLocation = locations.getAsJsonPrimitive("start").getAsString();
        endLocation = locations.getAsJsonPrimitive("end").getAsString();
        dateExploration = DateParser.ParseToDate(DateParser.ParseIso(exploration.getAsJsonPrimitive("dateExploration").getAsString())).toString();

        //Si l'exploration contient un troop
        if (exploration.getAsJsonObject("troop").has("href")) {
            troop = new Troop(exploration.getAsJsonObject("troop"));
        }
        //Sinon on instancie un troop vide
        else
        {
            troop = new Troop();
        }
    }

    //Constructeur public non utilisé
    /*public Exploration()
    {

    }*/

    //Méthode qui retourne le startLocation
    public String getStartLocation() {
        return startLocation;
    }

    //Méthode qui retourne le endLocation
    public String getEndLocation() {
        return endLocation;
    }

    //Méthode qui retourne la date de l'exploration
    public String getDateExploration() {
        return dateExploration;
    }

    //Méthode qui retourne le troop
    public Troop getTroop() {
        return troop;
    }
}
