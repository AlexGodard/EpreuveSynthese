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

    public ConnexionFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_connexion, container, false);


        btnInscription = (Button) rootView.findViewById(R.id.btnInscription);
        btnConnexion = (Button) rootView.findViewById(R.id.btnConnexion);

        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction =  getFragmentManager().beginTransaction();
                transaction.replace(R.id.container,InscriptionFragment.newInstance())
                        .addToBackStack("");
                transaction.commit();

            }
        });

        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction =  getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, ScanFragment.newInstance())
                        .addToBackStack("");
                transaction.commit();
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

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
