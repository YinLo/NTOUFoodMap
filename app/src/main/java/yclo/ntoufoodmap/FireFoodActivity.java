package yclo.ntoufoodmap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FireFoodActivity extends AppCompatActivity {

    ListView food_list;
    private ArrayList<FireFoodList> fireFoodList;
    //食物列表 food_name:食物名稱  store_name:商店名稱  like_count 按讚數量
    ArrayList<String> food_name;
    ArrayList<String> store_name;
    ArrayList<Integer> like_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firefood);

        try {
            String r = ConnectAPI.sendPost("API/getFireFood.php", "");
            //Toast.makeText(getApplicationContext(),  "user=" + Cookies.getUserid() + "&storeid=" + String.valueOf(RecommendActivity.selectdStore) + "&mealtype="+ mealType_prefs, Toast.LENGTH_SHORT).show();
            Type type = new TypeToken<List<FireFoodList>>() {
            }.getType();
            fireFoodList = new Gson().fromJson(r, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        food_name = getFood_name();
        store_name = getStore_name();
        like_count = getLike_count();

        food_list = (ListView) findViewById(R.id.listFireFood);
        food_list.setAdapter(new FireFoodAdapter(FireFoodActivity.this, food_name, store_name, like_count));

    }

    private ArrayList<String> getFood_name() {
        ArrayList<String> tmp = new ArrayList<>();
        for (FireFoodList f : fireFoodList) {
            tmp.add(f.getFoodname());
        }
        return tmp;
    }

    private ArrayList<String> getStore_name() {
        ArrayList<String> tmp = new ArrayList<>();
        for (FireFoodList f : fireFoodList) {
            tmp.add(f.getStorename());
        }
        return tmp;
    }

    private ArrayList<Integer> getLike_count() {
        ArrayList<Integer> tmp = new ArrayList<>();
        for (FireFoodList f : fireFoodList) {
            tmp.add(f.getLikecount());
        }
        return tmp;
    }

}
