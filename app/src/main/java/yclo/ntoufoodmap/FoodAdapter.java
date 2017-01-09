package yclo.ntoufoodmap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ro on 2017/1/7.
 */

public class FoodAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    ArrayList<String> food_name; //食物
    ArrayList<Integer> price;  //按讚數量
    ArrayList<Boolean> likeOrnot;  //是否按過讚

    public FoodAdapter(Context c, ArrayList<String> food_name, ArrayList<Integer> price, ArrayList<Boolean> likeOrnot) {
        inflater = LayoutInflater.from(c);
        this.food_name = food_name;
        this.price = price;
        this.likeOrnot = likeOrnot;
    }

    public int getCount() {
        return food_name.size();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.food_list, viewGroup, false);
        TextView txt_foodname, txt_price;
        txt_foodname = (TextView) view.findViewById(R.id.food_name);
        txt_price = (TextView) view.findViewById(R.id.price);
        ImageView img_likeOrNot = (ImageView)view.findViewById(R.id.imgLike);
        txt_foodname.setText(food_name.get(i));
        txt_price.setText(price.get(i).toString());
        if(likeOrnot.get(i))
            img_likeOrNot.setImageResource(R.drawable.like);
        else
            img_likeOrNot.setImageResource(R.drawable.unlike);
        return view;
    }
}
