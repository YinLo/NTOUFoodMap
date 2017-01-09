package yclo.ntoufoodmap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ro on 2017/1/8.
 */

public class AppraiseAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    ArrayList<String> user_name; //使用者
    ArrayList<String> comments;  //評論
    ArrayList<Double> scoring;  //評分

    public AppraiseAdapter(Context c, ArrayList<String> user_name, ArrayList<String> comments, ArrayList<Double> scoring) {
        inflater = LayoutInflater.from(c);
        this.user_name = user_name;
        this.comments = comments;
        this.scoring = scoring;
    }

    public int getCount() {
        return user_name.size();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.appraise_list, viewGroup, false);
        TextView txt_username, txt_comments;
        txt_username = (TextView) view.findViewById(R.id.user_name);
        txt_comments = (TextView) view.findViewById(R.id.txtAppraise);
        RatingBar rb_scoring = (RatingBar) view.findViewById(R.id.ratingBar);
        txt_username.setText(user_name.get(i));
        txt_comments.setText(comments.get(i));
        rb_scoring.setRating(scoring.get(i).floatValue());
        return view;
    }
}
