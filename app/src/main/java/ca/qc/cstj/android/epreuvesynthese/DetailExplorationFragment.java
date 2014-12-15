package ca.qc.cstj.android.epreuvesynthese;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ca.qc.cstj.android.epreuvesynthese.helpers.SharedParams;
import ca.qc.cstj.android.epreuvesynthese.models.Exploration;
import ca.qc.cstj.android.epreuvesynthese.models.Troop;

/**
 * Created by 1247308 on 2014-12-09.
 */
public class DetailExplorationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_HREF = "href";

    // TODO: Rename and change types of parameters
    private String href;
    private TextView tvAirRune;
    private TextView tvEarthRune;
    private TextView tvFireRune;
    private TextView tvLifeRune;
    private TextView tvLogicRune;
    private TextView tvWaterRune;
    private TextView tvCapturee;
    private TextView tvAttack;
    private TextView tvDefense;
    private TextView tvSpeed;
    private TextView tvNomTroop;
    private TextView tvDateExploration;
    private TextView tvLocationDepart;
    private TextView tvLocationFin;

    private ImageView ivImageTroop;

    private ProgressDialog progressDialog;

    private OnFragmentInteractionListener mListener;

    /*/**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param href href de l'employé a afficher
     * @return A new instance of fragment DetailFilmFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailExplorationFragment newInstance(String href) {
        DetailExplorationFragment fragment = new DetailExplorationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_HREF, href);
        fragment.setArguments(args);
        return fragment;
    }
    public DetailExplorationFragment() {
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_exploration, container, false);

        tvAirRune = (TextView) view.findViewById(R.id.tvAir);
        tvAttack = (TextView) view.findViewById(R.id.tvAttack);
        tvDefense = (TextView) view.findViewById(R.id.tvDefence);
        tvEarthRune = (TextView) view.findViewById(R.id.tvEarth);
        tvFireRune = (TextView)view.findViewById(R.id.tvFire);
        tvLifeRune = (TextView)view.findViewById(R.id.tvLife);
        tvLogicRune = (TextView) view.findViewById(R.id.tvLogic);
        tvNomTroop = (TextView)view.findViewById(R.id.tvNomTroop);
        tvSpeed = (TextView)view.findViewById(R.id.tvSpeed);
        tvWaterRune = (TextView)view.findViewById(R.id.tvWater);
        ivImageTroop = (ImageView)view.findViewById(R.id.ivImageTroop);
        tvDateExploration = (TextView)view.findViewById(R.id.tvDateExploration);
        tvLocationDepart = (TextView)view.findViewById(R.id.tvLocationDepart);
        tvLocationFin = (TextView)view.findViewById(R.id.tvLocationFin);
        tvCapturee = (TextView)view.findViewById(R.id.tvCapturee);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        progressDialog = ProgressDialog.show(getActivity(), "", "En chargement...", true, false);
        Ion.with(getActivity())
                .load(href + "?expand=troop")
                .setHeader("x-access-token", SharedParams.getToken())
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject jsonObject) {

                        Exploration exploration = new Exploration(jsonObject);

                        tvDateExploration.setText("Exploration du " + exploration.getDateExploration());
                        tvLocationDepart.setText("Location de départ : " + exploration.getStartLocation());
                        tvLocationFin.setText("Location de fin : " + exploration.getEndLocation());

                        if (exploration.getTroop().getName() != "") {
                            tvCapturee.setText("Troop capturée");
                            tvNomTroop.setText(exploration.getTroop().getName());
                            tvAttack.setText("Attaque : " + exploration.getTroop().getName());
                            tvDefense.setText("Defense : " + exploration.getTroop().getDefense());
                            tvSpeed.setText("Vitesse : " + exploration.getTroop().getSpeed());
                            Ion.with(ivImageTroop)
                                    .load(exploration.getTroop().getImageUrl());
                        }
                        else
                            tvCapturee.setText("Aucune troop de capturée :(");


                        progressDialog.dismiss();

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