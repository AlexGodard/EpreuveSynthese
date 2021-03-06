package ca.qc.cstj.android.epreuvesynthese;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import org.apache.http.HttpStatus;

import ca.qc.cstj.android.epreuvesynthese.helpers.SharedParams;
import ca.qc.cstj.android.epreuvesynthese.models.Explorateur;
import ca.qc.cstj.android.epreuvesynthese.services.ServicesURI;

public class InscriptionFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private ProgressDialog progressDialog;
    private Button btnSoumettre;
    private EditText etNom;
    private EditText etNomUtilisateur;
    private EditText etMotDePasse;
    private EditText etVerificationMotDePasse;

    private OnFragmentInteractionListener mListener;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static InscriptionFragment newInstance() {
        InscriptionFragment fragment = new InscriptionFragment();

        return fragment;
    }

    public InscriptionFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inscription, container, false);

        btnSoumettre = (Button) rootView.findViewById(R.id.btnSoumettre);
        etNom = (EditText) rootView.findViewById(R.id.etNom);
        etNomUtilisateur = (EditText) rootView.findViewById(R.id.etNomUtilisateur);
        etMotDePasse = (EditText) rootView.findViewById(R.id.etMotDePasse);
        etVerificationMotDePasse = (EditText) rootView.findViewById(R.id.etVerificationMotDePasse);

        return rootView;
    }

    //Méthode qui ajoute un explorateur
    public void ajouterExplorateur(){

        //On initialise un nouvel objet json
        JsonObject body = new JsonObject();
        progressDialog = ProgressDialog.show(getActivity(), "", "Création de l'explorateur en cours...", true, false);

        if (!etVerificationMotDePasse.getText().toString().isEmpty() &&
            !etMotDePasse.getText().toString().isEmpty() &&
            !etNom.getText().toString().isEmpty() &&
            !etNomUtilisateur.getText().toString().isEmpty()){

            //On ajoute les informations à l'objet json qui sera envoyé au webservice
            body.addProperty("name",etNom.getText().toString());
            body.addProperty("username",etNomUtilisateur.getText().toString());
            body.addProperty("password",etMotDePasse.getText().toString());

            //Appel au webservice
            Ion.with(getActivity())
                .load("POST", ServicesURI.EXPLORATEURS_SERVICE_URI)
                .addHeader("Content-Type", "application/json")
                .setJsonObjectBody(body)
                .asJsonObject()
                .withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {

                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> jsonObjectResponse) {


                    progressDialog.dismiss(); //On enlève le progressbar

                    //Si on reçoit le code 201
                    if(jsonObjectResponse.getHeaders().getResponseCode() == HttpStatus.SC_CREATED){

                        JsonObject json = jsonObjectResponse.getResult();

                        //On initialise l'explorateur connecté (et créé)
                        SharedParams.setExplorateur(json);

                        //S'il y a un token
                        if(json.has("token")) {
                            SharedParams.setToken(json.getAsJsonObject("token").getAsJsonPrimitive("token").getAsString());
                        }

                        //On affiche un petit message
                        Toast.makeText(getActivity().getApplicationContext(),getString(R.string.succes_inscription) , Toast.LENGTH_LONG).show();

                        //On change le fragment pour un scanFragment
                        FragmentTransaction transaction =  getFragmentManager().beginTransaction();
                        transaction.replace(R.id.container,ScanFragment.newInstance());
                        transaction.commit();

                    }
                    //Si une erreur est survenue
                    else{

                        Toast.makeText(getActivity().getApplicationContext(),getString(R.string.error_inscription) , Toast.LENGTH_LONG).show();
                    }

                    }
                });
        }
        //Si certains champs ne sont pas valides
        else
        {
            Toast.makeText(getActivity().getApplicationContext(),
                    getString(R.string.nonValide_inscription), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        btnSoumettre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Verifications
                ajouterExplorateur();

            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (InscriptionFragment.OnFragmentInteractionListener) activity;
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
