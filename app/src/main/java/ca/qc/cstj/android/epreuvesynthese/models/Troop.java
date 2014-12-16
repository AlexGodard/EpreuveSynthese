package ca.qc.cstj.android.epreuvesynthese.models;

import com.google.gson.JsonObject;

//Classe Troop
public class Troop {

    //Attributs
    private String href;
    private String name;
    private int attack;
    private int defense;
    private int speed;
    private String imageUrl;

    //Constructeur public qui prend en paramètre un objet json
    public Troop(JsonObject troop){

        //On initialise le href avec une variable vide parce que pas tous les troops en ont un
        href = "";

        //Si l'objet contient un href
        if(troop.has("href")){
            href = troop.getAsJsonPrimitive("href").getAsString();
        }

        name = troop.getAsJsonPrimitive("name").getAsString();
        attack = troop.getAsJsonPrimitive("attack").getAsInt();
        defense = troop.getAsJsonPrimitive("defense").getAsInt();
        speed = troop.getAsJsonPrimitive("speed").getAsInt();
        imageUrl = troop.getAsJsonPrimitive("imageUrl").getAsString();
    }

    //Constructeur public par défaut d'un troop
    public Troop(){

        href = "";
        name = "";
        attack = 0;
        defense = 0;
        speed = 0;
        imageUrl = "";

    }

    //Méthode qui retourne un objet json de l'objet troop
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

    //Méthode qui retourne le href
    public String getHref() {
        return href;
    }

    //Méthode qui retourne le nom
    public String getName() {
        return name;
    }

    //Méthode qui retourne l'attaque
    public int getAttack() {
        return attack;
    }

    //Méthode qui retourne la vitesse
    public int getSpeed() {
        return speed;
    }

    //Méthode qui retourne la défense
    public int getDefense() {
        return defense;
    }

    //Méthode qui retourne le url de l'image
    public String getImageUrl() {
        return imageUrl;
    }
}
