package yclo.ntoufoodmap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FoodActivity extends AppCompatActivity {

    ListView food_list;
    //食物列表 food_name:食物名稱  store_name:商店名稱  like_count 按讚數量
    String[] food_name = new String[]{"便當", "食物A", "4號餐", "炸雞", "海洋珍珠堡", "肉絲咖哩炒麵", "菲力牛排"};
    Integer[] price = new Integer[]{30000, 299, 183, 143, 109, 58, 24};
    boolean[] like_ornot = new boolean[]{true, false, true, false, true, false, true};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        TextView txt_store_name = (TextView) findViewById(R.id.txtStorename);
        TextView txt_meal_type = (TextView) findViewById(R.id.txtMealType);
        SharedPreferences prefs = getSharedPreferences("Store", MODE_PRIVATE);

        //取出Store ID(暫時先用商店名稱做靜態)
        String indexOfList_prefs = prefs.getString("IndexOfList_PREFS", null);
        txt_store_name.setText(indexOfList_prefs);
        if (indexOfList_prefs != null) {
            indexOfList_prefs = prefs.getString("IndexOfList_PREFS", "No name defined");//"No name defined" is the default value.
            txt_store_name.setText(indexOfList_prefs);
        }
        //取出餐點種類
        String mealType_prefs = prefs.getString("MealType_PREFS", null);
        txt_meal_type.setText(mealType_prefs);
        if (mealType_prefs != null) {
            mealType_prefs = prefs.getString("MealType_PREFS", "No name defined");//"No name defined" is the default value.
            txt_meal_type.setText(mealType_prefs);
        }

        //放入資料istView
        food_list = (ListView) findViewById(R.id.listFood);
        food_list.setAdapter(new FoodAdapter(FoodActivity.this, food_name, price, like_ornot));

        food_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position,
                                    long id) {
                // TODO Auto-generated method stub
                like_ornot[position] = !(like_ornot[position] || false);
                ImageView img_likeOrNot = (ImageView)view.findViewById(R.id.imgLike);
                if(like_ornot[position]) {
                    img_likeOrNot.setImageResource(R.drawable.like);
                }
                else {
                    img_likeOrNot.setImageResource(R.drawable.unlike);
                }
                food_list.setAdapter(new FoodAdapter(FoodActivity.this, food_name, price, like_ornot));
            }
        });
    }


    //切換至餐點清單
    public void changeLike(View v) {
        // TODO Auto-generated method stub

    }
}
