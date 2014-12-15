package ca.qc.cstj.android.epreuvesynthese.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ca.qc.cstj.android.epreuvesynthese.R;
import ca.qc.cstj.android.epreuvesynthese.helpers.DateParser;

/**
 * Created by 1247308 on 2014-11-28.
 */
public class ExplorationAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private JsonArray mJsonArray;


    public ExplorationAdapter(Context context, LayoutInflater inflater, JsonArray jsonArray) {
        mContext = context;
        mInflater = inflater;
        mJsonArray = jsonArray;
    }

    @Override
    public int getCount() {
        return mJsonArray.size();
    }

    @Override
    public JsonObject getItem(int position) {
        return mJsonArray.get(position).getAsJsonObject();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ExplorationViewHolder explorationViewHolder;



        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.row_exploration,null);
            explorationViewHolder = new ExplorationViewHolder();
            explorationViewHolder.txtDate = (TextView)convertView.findViewById(R.id.txtDate);

            convertView.setTag(explorationViewHolder);
        } else {
            explorationViewHolder = (ExplorationViewHolder)convertView.getTag();
        }

        JsonObject exploration = getItem(position);

        explorationViewHolder.txtDate.setText("Exploration du " + DateParser.ParseToDate(DateParser.ParseIso(exploration.getAsJsonPrimitive("dateExploration").getAsString())).toString());


        return convertView;

    }

    private static class ExplorationViewHolder {
        public TextView txtDate;
        public TextView txtDepart;
        public TextView txtArrivee;
        public TextView txtRunes;
        public TextView txtTroop;
    }
}
