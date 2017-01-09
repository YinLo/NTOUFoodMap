package yclo.ntoufoodmap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class FireFoodActivity extends AppCompatActivity {

    ListView food_list;
    //食物列表 food_name:食物名稱  store_name:商店名稱  like_count 按讚數量
    String[] food_name = new String[]{"便當", "食物A", "4號餐", "炸雞", "海洋珍珠堡", "肉絲咖哩炒麵", "菲力牛排"};
    String[] store_name = new String[]{"工學院餐廳", "夢泉餐廳", "麥當勞", "肯德基", "摩斯漢堡", "阿華炒麵", "貴族世家"};
    String[] like_count = new String[]{"30000", "299", "183", "143", "109", "58", "24"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firefood);


        food_list = (ListView) findViewById(R.id.listFireFood);
        food_list.setAdapter(new FireFoodAdapter(FireFoodActivity.this, food_name, store_name, like_count));

    }

}
