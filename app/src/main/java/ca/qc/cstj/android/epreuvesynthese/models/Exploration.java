package ca.qc.cstj.android.epreuvesynthese.models;

import com.google.gson.JsonObject;

import org.joda.time.DateTime;

import ca.qc.cstj.android.epreuvesynthese.helpers.DateParser;

/**
 * Created by 1247308 on 2014-12-02.
 */
public class Exploration {
    private String startLocation;
    private String endLocation;
    private String dateExploration;
    private Troop troop;

    public Exploration(JsonObject exploration)
    {
        // On doit strip les locations
        JsonObject locations = exploration.getAsJsonObject("locations");

        startLocation = locations.getAsJsonPrimitive("start").getAsString();
        endLocation = locations.getAsJsonPrimitive("end").getAsString();
        dateExploration = DateParser.ParseToDate(DateParser.ParseIso(exploration.getAsJsonPrimitive("dateExploration").getAsString())).toString();

        if (exploration.getAsJsonObject("troop").has("href")) {
            troop = new Troop(exploration.getAsJsonObject("troop"));
        }
        else
        {
            troop = new Troop();
        }
    }

    public Exploration()
    {

    }

    public String getStartLocation() {
        return startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public String getDateExploration() {
        return dateExploration;
    }

    public Troop getTroop() {
        return troop;
    }
}
