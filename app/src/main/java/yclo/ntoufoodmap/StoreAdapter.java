package yclo.ntoufoodmap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 2016/12/13.
 */
public class StoreAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    ArrayList<Integer> store_id;
    ArrayList<String> store_name;
    ArrayList<Double> scoring;

    public StoreAdapter(Context c, ArrayList<Integer> store_id, ArrayList<String> store_name, ArrayList<Double> scoring) {
        inflater = LayoutInflater.from(c);
        this.store_id = store_id;
        this.store_name = store_name;
        this.scoring = scoring;
    }

    public int getCount() {
        return store_id.size();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.store_list, viewGroup, false);
        TextView store_name2;
        store_name2 = (TextView) view.findViewById(R.id.store_name);
        RatingBar rb_scoring = (RatingBar) view.findViewById(R.id.ratingBar);
        store_name2.setText(store_name.get(i));
        rb_scoring.setRating(scoring.get(i).floatValue());
        return view;
    }

}
