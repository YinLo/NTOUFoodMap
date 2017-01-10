package yclo.ntoufoodmap;


import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {

    private ListView food_list;
    //食物列表 food_name:食物名稱  store_name:商店名稱  like_count 按讚數量
    private ArrayList<Integer> food_id;
    private ArrayList<String> food_name;
    private ArrayList<Integer> food_price;
    private ArrayList<String> meal_type;
    private ArrayList<Boolean> like_ornot;
    private ArrayList<FoodList> menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        TextView txt_store_name = (TextView) findViewById(R.id.txtStorename);
        TextView txt_meal_type = (TextView) findViewById(R.id.txtMealType);
        SharedPreferences prefs = getSharedPreferences("Store", MODE_PRIVATE);

        //取出Store ID(暫時先用商店名稱做靜態)
        int indexOfList_prefs = prefs.getInt("IndexOfList_PREFS", -1);
        txt_store_name.setText(Cookies.getStoreName().get(indexOfList_prefs));
//        if (indexOfList_prefs != null) {
//            indexOfList_prefs = prefs.getString("IndexOfList_PREFS", "No name defined");//"No name defined" is the default value.
//            txt_store_name.setText(indexOfList_prefs);
//        }
        //取出餐點種類
        String mealType_prefs = prefs.getString("MealType_PREFS", null);
        txt_meal_type.setText(mealType_prefs);
        if (mealType_prefs.equals("")) {
            mealType_prefs = prefs.getString("MealType_PREFS", "No name defined");//"No name defined" is the default value.
            txt_meal_type.setText(mealType_prefs);
        }

        try {
            String r = ConnectAPI.sendPost("API/getMenu.php", "user=" + Cookies.getUserid() + "&storeid=" + String.valueOf(Cookies.getStoreid()) + "&mealtype="+ mealType_prefs);
            //Toast.makeText(getApplicationContext(),  "user=" + Cookies.getUserid() + "&storeid=" + String.valueOf(RecommendActivity.selectdStore) + "&mealtype="+ mealType_prefs, Toast.LENGTH_SHORT).show();
            Type type = new TypeToken<List<FoodList>>() {
            }.getType();
            menu = new Gson().fromJson(r, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        food_id = getFood_id();
        food_name = getFood_name();
        food_price = getFood_price();
        meal_type = getFood_type();
        like_ornot = getFood_ornot();

        //放入資料istView
        food_list = (ListView) findViewById(R.id.listFood);
        food_list.setAdapter(new FoodAdapter(FoodActivity.this, food_name, food_price, like_ornot));

        food_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position,
                                    long id) {
                // TODO Auto-generated method stub
                like_ornot.set(position, !(like_ornot.get(position) || false));
                ImageView img_likeOrNot = (ImageView) view.findViewById(R.id.imgLike);
                try {
                    String r = ConnectAPI.sendPost("API/likeFood.php", "user=" + Cookies.getUserid() + "&foodid=" + food_id.get(position)  + "&likeornot=" + like_ornot.get(position).toString());
                    //Toast.makeText(getApplicationContext(),  "user=" + Cookies.getUserid() + "&foodid=" + food_id.get(position)  + "&likeornot=" + like_ornot.get(position).toString(), Toast.LENGTH_SHORT).show();
                    Type type = new TypeToken<List<FoodList>>() {
                    }.getType();
                    menu = new Gson().fromJson(r, type);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (like_ornot.get(position)) {
                    img_likeOrNot.setImageResource(R.drawable.like);
                } else {
                    img_likeOrNot.setImageResource(R.drawable.unlike);
                }
                food_list.setAdapter(new FoodAdapter(FoodActivity.this, food_name, food_price, like_ornot));
            }
        });
    }


    //切換至餐點清單
    public void changeLike(View v) {
        // TODO Auto-generated method stub

    }

    private ArrayList<Integer> getFood_id() {
        ArrayList<Integer> tmp = new ArrayList<>();
        for (FoodList o : menu) {
            tmp.add(o.getFoodid());
        }
        return tmp;
    }

    private ArrayList<String> getFood_name() {
        ArrayList<String> tmp = new ArrayList<>();
        for (FoodList o : menu) {
            tmp.add(o.getFoodname());
        }
        return tmp;
    }

    private ArrayList<Integer> getFood_price() {
        ArrayList<Integer> tmp = new ArrayList<>();
        for (FoodList o : menu) {
            tmp.add(o.getPrice());
        }
        return tmp;
    }

    private ArrayList<String> getFood_type() {
        ArrayList<String> tmp = new ArrayList<>();
        for (FoodList o : menu) {
            tmp.add(o.getMealtype());
        }
        return tmp;
    }

    private ArrayList<Boolean> getFood_ornot() {
        ArrayList<Boolean> tmp = new ArrayList<>();
        for (FoodList o : menu) {
            tmp.add(o.getLikeornot());
        }
        return tmp;
    }
}
