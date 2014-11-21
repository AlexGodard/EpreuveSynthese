package ca.qc.cstj.android.epreuvesynthese;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
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


        btnInscription = (Button) getActivity().findViewById(R.id.btnInscription);
        btnConnexion = (Button) getActivity().findViewById(R.id.btnConnexion);

        btnInscription.setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentTransaction transaction =  getFragmentManager().beginTransaction();
                transaction.replace(R.id.container,InscriptionFragment.newInstance())
                        .addToBackStack("");
                transaction.commit();

            }
        });

        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentTransaction transaction =  getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, ScanFragment.newInstance(position + 1))
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

}
