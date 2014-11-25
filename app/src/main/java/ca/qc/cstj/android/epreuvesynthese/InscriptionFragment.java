package ca.qc.cstj.android.epreuvesynthese;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
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

import ca.qc.cstj.android.epreuvesynthese.services.ServicesURI;

/**
 * Created by 1247308 on 2014-11-21.
 */
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

    public void ajouterExplorateur(){
        JsonObject body = new JsonObject();

        /*if (etVerificationMotDePasse.getText().toString() != etMotDePasse.getText().toString())
            // Message d'erreur
            return;*/

        if (!etVerificationMotDePasse.getText().toString().isEmpty() &&
            !etMotDePasse.getText().toString().isEmpty() &&
            !etNom.getText().toString().isEmpty() &&
            !etNomUtilisateur.getText().toString().isEmpty())
        {
            body.addProperty("name",etNom.getText().toString());
            body.addProperty("username",etNomUtilisateur.getText().toString());
            body.addProperty("password",etMotDePasse.getText().toString());

            Ion.with(getActivity())
                    .load("POST", ServicesURI.EXPLORATEURS_SERVICE_URI)
                    .addHeader("Content-Type", "application/json")
                    .setJsonObjectBody(body)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                                     @Override
                                     public void onCompleted(Exception e, JsonObject jsonObject) {
                                         Toast.makeText(getActivity().getApplicationContext(),"Explorateur créé avec succès!" , Toast.LENGTH_LONG).show();
                                     }
                    });
        }
        else
        {
            Toast.makeText(getActivity().getApplicationContext(),
                    "Erreur : Vous devez remplir tout les champs", Toast.LENGTH_LONG).show();
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

                FragmentTransaction transaction =  getFragmentManager().beginTransaction();
                transaction.replace(R.id.container,ScanFragment.newInstance())
                        .addToBackStack("");
                transaction.commit();
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
