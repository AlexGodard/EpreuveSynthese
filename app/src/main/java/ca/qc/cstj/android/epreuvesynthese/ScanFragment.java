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
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanFragment extends Fragment implements ZXingScannerView.ResultHandler{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    private ZXingScannerView mScannerView;
    private OnFragmentInteractionListener mListener;
    private ArrayList<Integer> mSelectedIndices;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ScanFragment newInstance() {
        ScanFragment fragment = new ScanFragment();

        return fragment;
    }

    public ScanFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        mScannerView = new ZXingScannerView(getActivity());
        if(state != null) {
        } else {

        }

        return mScannerView;
    }

    @Override
    public void onStart(){
        super.onStart();
        mScannerView.setResultHandler(this);
        mScannerView.stopCamera();
        mScannerView.startCamera();
    }


    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.stopCamera();
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mScannerView.stopCamera();
        mListener = null;
    }

    @Override
    public void handleResult(com.google.zxing.Result result) {

        mScannerView.stopCamera();

        //On change le fragment pourun captureFragment
        getFragmentManager().beginTransaction()
                .replace(R.id.container, CaptureFragment.newInstance(result.toString()))
                .commit();

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