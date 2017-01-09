package yclo.ntoufoodmap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by user on 2016/12/13.
 */
public class FireFoodAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    String[] food_name; //食物
    String[] store_name;  //食物所在之餐廳名稱
    String[] like_count;  //按讚數量

    public FireFoodAdapter(Context c, String[] food_name, String[] store_name, String[] like_count) {
        inflater = LayoutInflater.from(c);
        this.food_name = food_name;
        this.store_name = store_name;
        this.like_count = like_count;
    }

    public int getCount() {
        return food_name.length;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.firefood_list, viewGroup, false);
        TextView txt_foodname, txt_storename, txt_likecount;
        txt_foodname = (TextView) view.findViewById(R.id.food_name);
        txt_storename = (TextView) view.findViewById(R.id.store_name);
        txt_likecount = (TextView) view.findViewById(R.id.like_count);
        txt_foodname.setText(food_name[i]);
        txt_storename.setText(store_name[i]);
        txt_likecount.setText(like_count[i]);
        return view;
    }

}
