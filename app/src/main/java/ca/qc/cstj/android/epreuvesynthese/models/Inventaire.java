package ca.qc.cstj.android.epreuvesynthese.models;

import com.google.gson.JsonObject;

//Classe inventaire
public class Inventaire {

    //Attributs
    private int nbRuneAir;
    private int nbRuneFire;
    private int nbRuneWater;
    private int nbRuneEarth;
    private int nbRuneFusion;
    private int nbRuneLife;
    private int nbRuneLogic;

    //Constructeur public qui construit l'objet à partir d'un objet json
    public Inventaire(JsonObject runes){

        nbRuneAir = runes.getAsJsonPrimitive("air").getAsInt();
        nbRuneFire = runes.getAsJsonPrimitive("fire").getAsInt();
        nbRuneWater = runes.getAsJsonPrimitive("water").getAsInt();
        nbRuneEarth = runes.getAsJsonPrimitive("earth").getAsInt();
        nbRuneFusion = runes.getAsJsonPrimitive("fusion").getAsInt();
        nbRuneLife = runes.getAsJsonPrimitive("life").getAsInt();
        nbRuneLogic = runes.getAsJsonPrimitive("logic").getAsInt();

    }

    //Constructeur public qui initialise toutes les runes avec la valeur 0
    public Inventaire(){

        nbRuneAir = 0;
        nbRuneFire = 0;
        nbRuneWater = 0;
        nbRuneEarth = 0;
        nbRuneFusion = 0;
        nbRuneLife = 0;
        nbRuneLogic = 0;

    }

    //Retourne l'objet dans un objet json
    public JsonObject getRunesAsJson(){

        JsonObject runes = new JsonObject();

        runes.addProperty("air", nbRuneAir);
        runes.addProperty("earth", nbRuneEarth);
        runes.addProperty("fire", nbRuneFire);
        runes.addProperty("life", nbRuneLife);
        runes.addProperty("logic", nbRuneLogic);
        runes.addProperty("water", nbRuneWater);
        runes.addProperty("fusion", nbRuneFusion);


        return runes;
    }

}
