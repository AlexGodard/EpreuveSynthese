package ca.qc.cstj.android.epreuvesynthese;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import org.apache.http.HttpStatus;

import ca.qc.cstj.android.epreuvesynthese.helpers.SharedParams;
import ca.qc.cstj.android.epreuvesynthese.models.Inventaire;
import ca.qc.cstj.android.epreuvesynthese.models.Troop;
import ca.qc.cstj.android.epreuvesynthese.services.ServicesURI;



public class CaptureFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_HREF = "";

    private String href;
    private JsonObject objetExplo;
    private Troop troop;

    //Boutons
    private Button btnCapture;
    private Button btnContinue;

    //Locations
    private TextView startLocation;
    private TextView endLocation;


    //Pour la partie troop
    private ImageView imgExplo;
    private TextView nameExplo;
    private TextView attackExplo;
    private TextView defenseExplo;
    private TextView speedExplo;

    private TextView lblTroop;

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

        //Locations
        startLocation = (TextView)rootView.findViewById(R.id.start_explo_details_explo);
        endLocation = (TextView)rootView.findViewById(R.id.end_explo_details_explo);

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

        //Kernel
        airKernelExplo = (TextView)rootView.findViewById(R.id.capture_kernel_air);
        earthKernelExplo = (TextView)rootView.findViewById(R.id.capture_kernel_earth);
        fireKernelExplo = (TextView)rootView.findViewById(R.id.capture_kernel_fire);
        lifeKernelExplo = (TextView)rootView.findViewById(R.id.capture_kernel_life);
        logicKernelExplo = (TextView)rootView.findViewById(R.id.capture_kernel_logic);
        waterKernelExplo = (TextView)rootView.findViewById(R.id.capture_kernel_water);

        lblTroop = (TextView)rootView.findViewById(R.id.lbl_troop_capture);


        //Boutons
        btnCapture = (Button)rootView.findViewById(R.id.capture_button);
        btnContinue = (Button)rootView.findViewById(R.id.continue_button);


        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        objetExplo = new JsonObject();
        troop = new Troop();

        //Bouton qui signale qu'on veut capturer le troop
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enregistrerExploration(objetExplo, true);
            }
        });

        //Bouton qui signale qu'on veut capturer le troop
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enregistrerExploration(objetExplo, false);
            }
        });

        //On charge l'exploration
        loadExploration();

    }

    //Méthode qui charge l'exploration à partir du
    private void loadExploration(){

        //String contenant le url complet qu'on veut appeler
        String hrefFull = ServicesURI.YANNICK_SERVICE_URI + href;

        //On charge les explorations
        Ion.with(getActivity())
                .load(hrefFull)
                .asJsonObject()
                .withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> jsonObjectResponse) {

                        //Si le code du status http est de 200 on va dans cette section
                        if(jsonObjectResponse.getHeaders().getResponseCode() == HttpStatus.SC_OK) {

                            //On garde l'objet reçu en mémoire
                            objetExplo = jsonObjectResponse.getResult();

                            afficherLocations(objetExplo.getAsJsonObject("locations"));

                            afficherRunesExplorateur(SharedParams._explorateur.getRunesAsJson());

                            //Si on a un troop
                            if(objetExplo.getAsJsonObject("troop").has("name")){

                                troop = new Troop(objetExplo.getAsJsonObject("troop"));

                                //On charge l'image
                                Ion.with(imgExplo)
                                        .placeholder(R.drawable.ic_launcher)
                                        .error(R.drawable.ic_launcher)
                                        .load(troop.getTroopAsJson().getAsJsonPrimitive("imageUrl").getAsString());

                                afficherTroop(objetExplo.getAsJsonObject("troop"));

                                //On vérifie qu'il est capturable et si non on retire le bouton qui permet la capture
                                if(!isCatchable(SharedParams._explorateur.getRunesAsJson(), objetExplo.getAsJsonObject("troop").getAsJsonObject("kernel"))){

                                    retirerBoutonCapture();
                                }

                            }
                            //Sinon on retire la section qui affiche l'information du kernel
                            else{

                                //Affichage d'un qui dit à l'explorateur qu'aucun troop n'existe dans l'exploration
                                lblTroop.setText(R.string.details_capture_noTroop);

                                //On retire la section qui contient l'information du kernel d'un troop
                                retirerKernel();

                                //On a pas de troop donc le bouton de capture n'est pas nécessaire
                                retirerBoutonCapture();

                            }

                            //Si on a des runes
                            if(objetExplo.getAsJsonObject("runes").has("air")){

                                afficherRunesExplorations(objetExplo.getAsJsonObject("runes"));

                            }
                            //Si on a pas de rune, on affiche un set de runes vide
                            else{

                                afficherRunesExplorations(new Inventaire().getRunesAsJson());

                            }

                        }
                        //Sinon on affiche la fenêtre de l'inventaire
                        else{

                            FragmentManager fragmentManager = getFragmentManager();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.container, InventaireFragment.newInstance())
                                    .commit();

                        }
                    }
                });

    }

    /*
    *
    * Méthodes pour modifier l'interface dépendement de ce que l'on a dans la réponse
    *
    * */

    //Méthode qui retire le bouton de capture
    private void retirerBoutonCapture(){

        LinearLayout container = (LinearLayout)getActivity().findViewById(R.id.layoutButtons);
        container.removeView((Button)getActivity().findViewById(R.id.capture_button));

    }

    //Méthode qui retire la section contenant l'information du kernel
    private void retirerKernel(){

        LinearLayout container = (LinearLayout)getActivity().findViewById(R.id.layoutFull);
        container.removeView((LinearLayout) getActivity().findViewById(R.id.linearLayout_kernel_capture));

    }

    /**************************/

    private void afficherRunesExplorateur(JsonObject runes) {

        airInventaireExplo.setText(runes.get("air").toString());
        earthInventaireExplo.setText(runes.get("earth").toString());
        fireInventaireExplo.setText(runes.get("fire").toString());
        lifeInventaireExplo.setText(runes.get("life").toString());
        logicInventaireExplo.setText(runes.get("logic").toString());
        waterInventaireExplo.setText(runes.get("water").toString());
        fusionInventaireExplo.setText(runes.get("fusion").toString());

    }

    private void afficherLocations(JsonObject locations){

        startLocation.setText(getString(R.string.details_start) + " : " + locations.getAsJsonPrimitive("start").getAsString());
        endLocation.setText(getString(R.string.details_end) + " : " + locations.getAsJsonPrimitive("end").getAsString());
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

        nameExplo.setText(getString(R.string.details_nom) + " : " + troop.get("name").getAsString());
        attackExplo.setText(getString(R.string.details_attack) + " : " + troop.get("attack").getAsString());
        defenseExplo.setText(getString(R.string.details_defense) + " : " + troop.get("defense").getAsString());
        speedExplo.setText(getString(R.string.details_speed) + " : " + troop.get("speed").getAsString());

        if(troop.has("kernel")){
            afficherKernel(troop.getAsJsonObject("kernel"));
        }


    }

    private void afficherKernel(JsonObject kernel){

        airKernelExplo.setText(kernel.getAsJsonPrimitive("air").getAsString());
        earthKernelExplo.setText(kernel.getAsJsonPrimitive("earth").getAsString());
        fireKernelExplo.setText(kernel.getAsJsonPrimitive("fire").getAsString());
        lifeKernelExplo.setText(kernel.getAsJsonPrimitive("life").getAsString());
        logicKernelExplo.setText(kernel.getAsJsonPrimitive("logic").getAsString());
        waterKernelExplo.setText(kernel.getAsJsonPrimitive("water").getAsString());

    }

    private boolean isCatchable(JsonObject runesExplorateur, JsonObject kernel){


        try {
            if (kernel.getAsJsonPrimitive("air").getAsInt() > runesExplorateur.getAsJsonPrimitive("air").getAsInt()) {
                return false;
            }

            if (kernel.getAsJsonPrimitive("earth").getAsInt() > runesExplorateur.getAsJsonPrimitive("earth").getAsInt()) {
                return false;
            }

            if (kernel.getAsJsonPrimitive("fire").getAsInt() > runesExplorateur.getAsJsonPrimitive("fire").getAsInt()) {
                return false;
            }

            if (kernel.getAsJsonPrimitive("life").getAsInt() > runesExplorateur.getAsJsonPrimitive("life").getAsInt()) {
                return false;
            }

            if (kernel.getAsJsonPrimitive("logic").getAsInt() > runesExplorateur.getAsJsonPrimitive("logic").getAsInt()) {
                return false;
            }

            if (kernel.getAsJsonPrimitive("water").getAsInt() > runesExplorateur.getAsJsonPrimitive("water").getAsInt()) {
                return false;
            }
        }
        catch(Exception e){

            return false;
        }

        return true;
    }

    //On sauvegarde l'exploration avec ou sans la troop
    private void enregistrerExploration(JsonObject exploration, boolean capture){

        //On rend le troop impossible à ajouter
        if(!capture && exploration.getAsJsonObject("troop").has("name")){

            exploration.getAsJsonObject("troop").remove("name");
        }

        Ion.with(getActivity())
            .load("POST", ServicesURI.EXPLORATION_AJOUT_SERVICE_URI)
            .addHeader("Content-Type", "application/json")
            .setHeader("x-access-token",SharedParams.getToken())
            .setJsonObjectBody(exploration)
            .asJsonObject()
            .withResponse()
            .setCallback(new FutureCallback<Response<JsonObject>>() {

                @Override
                public void onCompleted(Exception e, Response<JsonObject> jsonObjectResponse) {

                    //Si on reçoit le code 201
                    if(jsonObjectResponse.getHeaders().getResponseCode() == HttpStatus.SC_CREATED){

                        FragmentTransaction transaction =  getFragmentManager().beginTransaction();
                        transaction.replace(R.id.container,InventaireFragment.newInstance());
                        transaction.commit();


                        Toast.makeText(getActivity().getApplicationContext(),getString(R.string.succes_exploration) , Toast.LENGTH_LONG).show();

                    }
                    else{

                        Toast.makeText(getActivity().getApplicationContext(),getString(R.string.echec_exploration) , Toast.LENGTH_LONG).show();
                    }
                }
            });


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
