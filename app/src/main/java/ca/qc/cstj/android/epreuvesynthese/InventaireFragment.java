package ca.qc.cstj.android.epreuvesynthese;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import org.apache.http.HttpStatus;
import org.w3c.dom.Text;

import ca.qc.cstj.android.epreuvesynthese.helpers.SharedParams;
import ca.qc.cstj.android.epreuvesynthese.services.ServicesURI;

/**
 * Created by 1247308 on 2014-11-21.
 */
public class InventaireFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    //Attributs
    private TextView tvAirRune;
    private TextView tvEarthRune;
    private TextView tvFireRune;
    private TextView tvLifeRune;
    private TextView tvLogicRune;
    private TextView tvWaterRune;
    private TextView tvFusionRune;

    private ProgressDialog progressDialog;

    private OnFragmentInteractionListener mListener;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static InventaireFragment newInstance() {
        InventaireFragment fragment = new InventaireFragment();

        return fragment;
    }

    public InventaireFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inventaire, container, false);

        tvAirRune = (TextView)rootView.findViewById(R.id.tvAir);
        tvEarthRune = (TextView)rootView.findViewById(R.id.tvEarth);
        tvFireRune = (TextView)rootView.findViewById(R.id.tvFire);
        tvLifeRune = (TextView)rootView.findViewById(R.id.tvLife);
        tvLogicRune = (TextView)rootView.findViewById(R.id.tvLogic);
        tvWaterRune = (TextView)rootView.findViewById(R.id.tvWater);
        tvFusionRune = (TextView)rootView.findViewById(R.id.tvFusion);

/*
        ivAirRune = (ImageView)rootView.findViewById(R.id.ivAirRune);
        ivFireRune = (ImageView)rootView.findViewById(R.id.ivFireRune);
        ivWaterRune = (ImageView)rootView.findViewById(R.id.ivWaterRune);
        ivEarthRune = (ImageView)rootView.findViewById(R.id.ivEarthRune);
        ivLogicRune = (ImageView)rootView.findViewById(R.id.ivLogicRune);
        ivLifeRune = (ImageView)rootView.findViewById(R.id.ivLifeRune);
        ivFusionRune = (ImageView)rootView.findViewById(R.id.ivFusionRune);*/

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        loadRunes();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void loadRunes(){

        progressDialog = ProgressDialog.show(getActivity(), "", "En chargement...", true, false);

        //Appel du webservice
        Ion.with(getActivity())
                .load(ServicesURI.RUNES_SERVICE_URI)
                .setHeader("x-access-token", SharedParams.getToken())
                .asJsonObject()
                .withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> jsonObjectResponse) {

                        //Si on a pu récupérer les runes via le webservice, on met à jour l'information qu'on avait dans l'objet explorateur global pour les nouvelles runes
                        if(jsonObjectResponse.getHeaders().getResponseCode() == HttpStatus.SC_OK){

                            JsonObject runes = jsonObjectResponse.getResult();

                            SharedParams._explorateur.setRunes(runes);
                        }

                        //On affiche l'information des runes
                        afficherRunes(SharedParams._explorateur.getRunesAsJson());

                        progressDialog.dismiss(); //On enlève le progressbar
                    }
                });

    }

    //Méthode qui affiche les runes
    private void afficherRunes(JsonObject runes){

        tvAirRune.setText(runes.getAsJsonPrimitive("air").getAsString());
        tvEarthRune.setText(runes.getAsJsonPrimitive("earth").getAsString());
        tvFireRune.setText(runes.getAsJsonPrimitive("fire").getAsString());
        tvLifeRune.setText(runes.getAsJsonPrimitive("life").getAsString());
        tvLogicRune.setText(runes.getAsJsonPrimitive("logic").getAsString());
        tvWaterRune.setText(runes.getAsJsonPrimitive("water").getAsString());
        tvFusionRune.setText(runes.getAsJsonPrimitive("fusion").getAsString());
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
