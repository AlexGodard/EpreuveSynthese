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
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import org.apache.http.HttpStatus;

import ca.qc.cstj.android.epreuvesynthese.adapters.ExplorationAdapter;
import ca.qc.cstj.android.epreuvesynthese.models.Exploration;
import ca.qc.cstj.android.epreuvesynthese.services.ServicesURI;
import ca.qc.cstj.android.epreuvesynthese.helpers.SharedParams;

public class ExplorationFragment extends Fragment{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    private ProgressDialog progressDialog;
    private Exploration exploration;
    private TextView txtNomExplorateur;
    private ListView lstExploration;
    private ExplorationAdapter explorationAdapter;

    private OnFragmentInteractionListener mListener;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ExplorationFragment newInstance() {
        ExplorationFragment fragment = new ExplorationFragment();

        return fragment;
    }

    public ExplorationFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_explorations, container, false);

        txtNomExplorateur = (TextView) rootView.findViewById(R.id.txtNomExplorateur);


        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        txtNomExplorateur.setText("Pour l'explorateur " + SharedParams._explorateur.getNom());
        lstExploration = (ListView) getActivity().findViewById(R.id.lstExplorations);

        loadExplorations();

        lstExploration.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String href = explorationAdapter.getItem(position).getAsJsonPrimitive("href").getAsString();

                FragmentTransaction transaction =  getFragmentManager().beginTransaction();
                transaction.replace(R.id.container,DetailExplorationFragment.newInstance(href))
                        .addToBackStack("");
                transaction.commit();

            }
        });
    }

    private void loadExplorations()
    {
        progressDialog = ProgressDialog.show(getActivity(),"","En chargement...",true,false);

        Ion.with(getActivity())
                .load(ServicesURI.EXPLORATIONS_SERVICES_URI)
                .setHeader("x-access-token", SharedParams.getToken())
                .asJsonArray()
                .withResponse()
                .setCallback(new FutureCallback<Response<JsonArray>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonArray> response) {

                        if(response.getHeaders().getResponseCode() == HttpStatus.SC_OK){
                            explorationAdapter = new ExplorationAdapter(getActivity(),
                                    getActivity().getLayoutInflater(), response.getResult());
                            lstExploration.setAdapter(explorationAdapter);
                        }
                        else
                        {
                            //Erreur 404
                        }


                        progressDialog.dismiss();
                    }
                });
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
