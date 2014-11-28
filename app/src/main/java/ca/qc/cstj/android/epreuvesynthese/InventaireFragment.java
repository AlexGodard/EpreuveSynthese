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

/**
 * Created by 1247308 on 2014-11-21.
 */
public class InventaireFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    private ImageView ivAirRune;
    private ImageView ivFireRune;
    private ImageView ivEarthRune;
    private ImageView ivWaterRune;
    private ImageView ivLifeRune;
    private ImageView ivLogicRune;
    private ImageView ivFusionRune;

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

        ivAirRune = (ImageView)rootView.findViewById(R.id.ivAirRune);
        ivFireRune = (ImageView)rootView.findViewById(R.id.ivFireRune);
        ivWaterRune = (ImageView)rootView.findViewById(R.id.ivWaterRune);
        ivEarthRune = (ImageView)rootView.findViewById(R.id.ivEarthRune);
        ivLogicRune = (ImageView)rootView.findViewById(R.id.ivLogicRune);
        ivLifeRune = (ImageView)rootView.findViewById(R.id.ivLifeRune);
        ivFusionRune = (ImageView)rootView.findViewById(R.id.ivFusionRune);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
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
