package ca.qc.cstj.android.epreuvesynthese.models;

import com.google.gson.JsonObject;

import org.joda.time.DateTime;

/**
 * Created by 1247308 on 2014-12-02.
 */
public class Exploration {
    private String nomExplorateur;
    private String startLocation;
    private String endLocation;
    private DateTime dateExploration;
    private Boolean troopCaptured;

    public void Exploration(JsonObject exploration)
    {

    }
}
