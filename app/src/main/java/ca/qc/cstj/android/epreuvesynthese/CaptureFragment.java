package ca.qc.cstj.android.epreuvesynthese;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CaptureFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CaptureFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class CaptureFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_HREF = "";

    private String href;

    //Pour la partie troop
    private ImageView imgExplo;
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
    public static CaptureFragment newInstance(String href) {
        CaptureFragment fragment = new CaptureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_HREF, href);

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
        return inflater.inflate(R.layout.fragment_capture, container, false);
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
