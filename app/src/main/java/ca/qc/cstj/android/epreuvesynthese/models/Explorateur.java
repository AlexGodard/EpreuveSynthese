package ca.qc.cstj.android.epreuvesynthese.models;

import com.google.gson.JsonObject;

//Classe explorateur
public class Explorateur {

    //Attributs
    private String nom;
    private String href;
    private String nomUtilisateur;
    private Inventaire inventaire;

    //Constructeur public qui prend un objet Json en paramètre
    public Explorateur(JsonObject explorateur){

        nom = explorateur.getAsJsonPrimitive("name").getAsString();
        href =  explorateur.getAsJsonPrimitive("href").getAsString();
        nomUtilisateur = explorateur.getAsJsonPrimitive("username").getAsString();

        //Si il y a un objet runes on instancie son inventaire avec
        if(explorateur.has("runes")){

            inventaire = new Inventaire(explorateur.getAsJsonObject("runes"));

        }
        else{
            inventaire = new Inventaire();

        }
    }

    //Constructeur public
    public Explorateur(){

        nom = "";
        href = "";
        nomUtilisateur = "";

        inventaire = new Inventaire();

    }

    //Méthode qui retourne le nom
    public String getNom() {
        return nom;
    }

    //Méthode qui set les runes d'un explorateur à partir d'un objet Json
    public void setRunes(JsonObject json){
        inventaire = new Inventaire(json);
    }

    //Méthode qui retourne les runes sous la forme d'un objet Json
    public JsonObject getRunesAsJson(){

        return inventaire.getRunesAsJson();
    }
}
