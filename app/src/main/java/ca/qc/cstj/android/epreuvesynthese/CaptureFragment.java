package ca.qc.cstj.android.epreuvesynthese;

import android.app.Activity;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import org.apache.http.HttpStatus;

import ca.qc.cstj.android.epreuvesynthese.helpers.SharedParams;
import ca.qc.cstj.android.epreuvesynthese.models.Troop;
import ca.qc.cstj.android.epreuvesynthese.services.ServicesURI;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CaptureFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CaptureFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class CaptureFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_HREF = "";

    private String href;
    private JsonObject objetExplo;
    private Troop troop;

    //Pour la partie troop
    private ImageView imgExplo;
    private TextView nameExplo;
    private TextView attackExplo;
    private TextView defenseExplo;
    private TextView speedExplo;

    //Pour la partie des runes
    //Inventaire
    private TextView airInventaireExplo;
    private TextView earthInventaireExplo;
    private TextView fireInventaireExplo;
    private TextView lifeInventaireExplo;
    private TextView logicInventaireExplo;
    private TextView waterInventaireExplo;
    private TextView fusionInventaireExplo;

    //Kernel
    private TextView airKernelExplo;
    private TextView earthKernelExplo;
    private TextView fireKernelExplo;
    private TextView lifeKernelExplo;
    private TextView logicKernelExplo;
    private TextView waterKernelExplo;
    private TextView fusionKernelExplo;

    //Runes exploration
    private TextView airExplo;
    private TextView earthExplo;
    private TextView fireExplo;
    private TextView lifeExplo;
    private TextView logicExplo;
    private TextView waterExplo;
    private TextView fusionExplo;


    private OnFragmentInteractionListener mListener;


    // TODO: Rename and change types and number of parameters
    public static CaptureFragment newInstance(String uuid) {
        CaptureFragment fragment = new CaptureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_HREF, uuid);

        fragment.setArguments(args);

        return fragment;
    }
    public CaptureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            href = getArguments().getString(ARG_HREF);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView =  inflater.inflate(R.layout.fragment_capture, container, false);


        //Runes inventaire
        airInventaireExplo = (TextView)rootView.findViewById(R.id.capture_inventaire_air);
        earthInventaireExplo = (TextView)rootView.findViewById(R.id.capture_inventaire_earth);
        fireInventaireExplo = (TextView)rootView.findViewById(R.id.capture_inventaire_fire);
        lifeInventaireExplo = (TextView)rootView.findViewById(R.id.capture_inventaire_life);
        logicInventaireExplo = (TextView)rootView.findViewById(R.id.capture_inventaire_logic);
        waterInventaireExplo = (TextView)rootView.findViewById(R.id.capture_inventaire_water);
        fusionInventaireExplo = (TextView)rootView.findViewById(R.id.capture_inventaire_fusion);

        //Runes exploration
        airExplo = (TextView)rootView.findViewById(R.id.capture_decouverte_air);
        earthExplo = (TextView)rootView.findViewById(R.id.capture_decouverte_earth);
        fireExplo = (TextView)rootView.findViewById(R.id.capture_decouverte_fire);
        lifeExplo = (TextView)rootView.findViewById(R.id.capture_decouverte_life);
        logicExplo = (TextView)rootView.findViewById(R.id.capture_decouverte_logic);
        waterExplo = (TextView)rootView.findViewById(R.id.capture_decouverte_water);

        //Troop
        imgExplo = (ImageView)rootView.findViewById(R.id.imgTroop_details_explo);
        nameExplo = (TextView)rootView.findViewById(R.id.name_details_explo);
        attackExplo = (TextView)rootView.findViewById(R.id.attack_details_explo);
        defenseExplo = (TextView)rootView.findViewById(R.id.defense_details_explo);
        speedExplo = (TextView)rootView.findViewById(R.id.speed_details_explo);



        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        objetExplo = new JsonObject();
        troop = new Troop();

        loadExploration();
    }

    private void loadExploration(){

        String hrefFull = ServicesURI.YANNICK_SERVICE_URI + href;

        Ion.with(getActivity())
                .load(hrefFull)
                .asJsonObject()
                .withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> jsonObjectResponse) {

                        if(jsonObjectResponse.getHeaders().getResponseCode() == HttpStatus.SC_OK) {

                            //On garde l'objet reçu en mémoire
                            objetExplo = jsonObjectResponse.getResult();


                            afficherRunesExplorateur(SharedParams._explorateur.getRunesAsJson());

                            //Si on a un troop
                            if(objetExplo.getAsJsonObject("troop").has("name")){

                                troop = new Troop(objetExplo.getAsJsonObject("troop"));

                                //On charge l'image
                                Ion.with(imgExplo)
                                        .placeholder(R.drawable.ic_launcher)
                                        .error(R.drawable.ic_launcher)
                                        .load(troop.getTroopAsJson().getAsJsonPrimitive("imageUrl").getAsString());

                                afficherTroop(troop.getTroopAsJson());

                            }

                            //Si on a des runes
                            if(objetExplo.getAsJsonObject("runes").has("air")){

                                afficherRunesExplorations(objetExplo.getAsJsonObject("runes"));

                            }

                        }
                        //Sinon on a la fenêtre de l'inventaires
                        else{

                            FragmentManager fragmentManager = getFragmentManager();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.container, InventaireFragment.newInstance())
                                    .commit();

                        }
                    }
                });

    }

    private void afficherRunesExplorateur(JsonObject runes) {

        airInventaireExplo.setText(runes.get("air").toString());
        earthInventaireExplo.setText(runes.get("earth").toString());
        fireInventaireExplo.setText(runes.get("fire").toString());
        lifeInventaireExplo.setText(runes.get("life").toString());
        logicInventaireExplo.setText(runes.get("logic").toString());
        waterInventaireExplo.setText(runes.get("water").toString());
        fusionInventaireExplo.setText(runes.get("fusion").toString());

    }

    private void afficherRunesExplorations(JsonObject runes){

        airExplo.setText(runes.get("air").toString());
        earthExplo.setText(runes.get("earth").toString());
        fireExplo.setText(runes.get("fire").toString());
        lifeExplo.setText(runes.get("life").toString());
        logicExplo.setText(runes.get("logic").toString());
        waterExplo.setText(runes.get("water").toString());

    }

    private void afficherTroop(JsonObject troop){

        nameExplo.setText("Name : " + troop.get("name").toString());
        attackExplo.setText("Attack : " + troop.get("attack").toString());
        defenseExplo.setText("Defense : " + troop.get("defense").toString());
        speedExplo.setText("Speed : " + troop.get("speed").toString());

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }



}
