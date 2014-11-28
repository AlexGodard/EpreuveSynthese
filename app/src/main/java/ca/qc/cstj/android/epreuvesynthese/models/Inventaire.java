package ca.qc.cstj.android.epreuvesynthese.models;

import com.google.gson.JsonObject;

/**
 * Created by 1247308 on 2014-11-25.
 */
public class Inventaire {

    private int nbRuneAir;
    private int nbRuneFire;
    private int nbRuneWater;
    private int nbRuneEarth;
    private int nbRuneFusion;
    private int nbRuneLife;
    private int nbRuneLogic;

    public Inventaire(JsonObject runes){

        nbRuneAir = runes.getAsJsonPrimitive("air").getAsInt();
        nbRuneFire = runes.getAsJsonPrimitive("fire").getAsInt();
        nbRuneWater = runes.getAsJsonPrimitive("water").getAsInt();
        nbRuneEarth = runes.getAsJsonPrimitive("earth").getAsInt();
        nbRuneFusion = runes.getAsJsonPrimitive("fusion").getAsInt();
        nbRuneLife = runes.getAsJsonPrimitive("life").getAsInt();
        nbRuneLogic = runes.getAsJsonPrimitive("logic").getAsInt();

    }

    public Inventaire(){

        nbRuneAir = 0;
        nbRuneFire = 0;
        nbRuneWater = 0;
        nbRuneEarth = 0;
        nbRuneFusion = 0;
        nbRuneLife = 0;
        nbRuneLogic = 0;

    }


}
