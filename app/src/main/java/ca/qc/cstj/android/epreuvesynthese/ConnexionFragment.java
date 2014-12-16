package ca.qc.cstj.android.epreuvesynthese;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.app.Service;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ca.qc.cstj.android.epreuvesynthese.helpers.SharedParams;
import ca.qc.cstj.android.epreuvesynthese.services.ServicesURI;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import org.apache.http.HttpStatus;

/**
 * Created by 1247308 on 2014-11-21.
 */
public class ConnexionFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private ProgressDialog progressDialog;
    private Button btnInscription;
    private Button btnConnexion;

    private EditText etNomUtilisateur;
    private EditText etMotDePasse;

    private OnFragmentInteractionListener mListener;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ConnexionFragment newInstance(int sectionNumber) {
        ConnexionFragment fragment = new ConnexionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public static ConnexionFragment newInstance() {
        ConnexionFragment fragment = new ConnexionFragment();

        return fragment;
    }

    public ConnexionFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_connexion, container, false);


        btnInscription = (Button) rootView.findViewById(R.id.btnInscription);
        btnConnexion = (Button) rootView.findViewById(R.id.btnConnexion);

        etMotDePasse = (EditText) rootView.findViewById(R.id.etMotDePasse);
        etNomUtilisateur = (EditText) rootView.findViewById(R.id.etNomUtilisateur);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        //Méthode qui est appelée lorsque le bouton pour s'inscrire est cliqué
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction =  getFragmentManager().beginTransaction();
                transaction.replace(R.id.container,InscriptionFragment.newInstance())
                        .addToBackStack("");
                transaction.commit();

            }
        });

        //Méthode qui est appelée lorsque le bouton pour se connecter est cliqué
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = ProgressDialog.show(getActivity(), "", "Connexion en cours...", true, false);

                // On tente de connecter l'utilisateur
                Ion.with(getActivity())
                        .load(ServicesURI.EXPLORATEURS_SERVICE_URI + "?username=" + etNomUtilisateur.getText().toString() + "&password=" + etMotDePasse.getText().toString())
                        .asJsonObject()
                        .withResponse()
                        .setCallback(new FutureCallback<Response<JsonObject>>() {

                            @Override
                            public void onCompleted(Exception e, Response<JsonObject> response) {

                               // String s = ServicesURI.EXPLORATEURS_SERVICE_URI + "?username=" + etNomUtilisateur.getText().toString() + "&password=" + etMotDePasse.getText().toString();

                                //Si tout s'est bien passé
                                if(response.getHeaders().getResponseCode() == HttpStatus.SC_OK){

                                    JsonObject json = response.getResult();

                                    //Initialisation de l'explorateur avec le json
                                    SharedParams.setExplorateur(json);

                                    //S'il y a un token
                                    if(json.has("token")) {
                                        SharedParams.setToken(json.getAsJsonObject("token").getAsJsonPrimitive("token").getAsString());
                                    }

                                    //On affiche un petit toast
                                    Toast.makeText(getActivity().getApplicationContext(),getString(R.string.succes_connexion) , Toast.LENGTH_LONG).show();

                                    //On change le fragment pour le scan fragment
                                    FragmentTransaction transaction =  getFragmentManager().beginTransaction();
                                    transaction.replace(R.id.container,ScanFragment.newInstance());
                                    transaction.commit();

                                    /*transaction
                                            .replace(R.id.container, CaptureFragment.newInstance("05A6FFA7-3669-4B59-8D9F-7FE3098C1778"))
                                            .commit();*/


                                }
                                //Si l'explorateur n'existe pas
                                else if (response.getHeaders().getResponseCode() == HttpStatus.SC_NOT_FOUND)
                                {
                                    Toast.makeText(getActivity().getApplicationContext(), "Vos informations ne sont pas valides", Toast.LENGTH_LONG).show();
                                }
                                //Pour toute autre erreur
                                else {
                                    Toast.makeText(getActivity().getApplicationContext(), "Une erreur est survenue. Réessayez plus tard", Toast.LENGTH_LONG).show();

                                }

                                progressDialog.dismiss(); //On enlève le progressdialog
                            }
                        });
            }
        });
    }





    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
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
