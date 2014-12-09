package ca.qc.cstj.android.epreuvesynthese.models;

import com.google.gson.JsonObject;

/**
 * Created by 1247308 on 2014-11-21.
 */
public class Troop {

    private String href;
    private String name;
    private int attack;
    private int defense;
    private int speed;
    private String imageUrl;

    public Troop(JsonObject troop){

        if(troop.has("href")){
            href = troop.getAsJsonPrimitive("href").getAsString();
        }

        name = troop.getAsJsonPrimitive("name").getAsString();
        attack = troop.getAsJsonPrimitive("attack").getAsInt();
        defense = troop.getAsJsonPrimitive("defense").getAsInt();
        speed = troop.getAsJsonPrimitive("speed").getAsInt();
        imageUrl = troop.getAsJsonPrimitive("imageUrl").getAsString();
    }

    public Troop(){

        href = "";
        name = "";
        attack = 0;
        defense = 0;
        speed = 0;
        imageUrl = "";

    }

    public JsonObject getTroopAsJson(){

        JsonObject troop = new JsonObject();

        troop.addProperty("href", href);
        troop.addProperty("name", name);
        troop.addProperty("attack", attack);
        troop.addProperty("defense", defense);
        troop.addProperty("speed", speed);
        troop.addProperty("imageUrl", imageUrl);


        return troop;
    }
}
