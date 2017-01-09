package yclo.ntoufoodmap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView store_name = (TextView) findViewById(R.id.txtStorename);
        //取出Store ID(暫時先用商店名稱做靜態)
        SharedPreferences prefs = getSharedPreferences("Store", MODE_PRIVATE);
        int indexOfList = prefs.getInt("IndexOfList_PREFS", -1);
        store_name.setText(RecommendActivity.store_name.get(indexOfList));
//        if (indexOfList != -1) {
//            indexOfList = prefs.getString("IndexOfList_PREFS", "No name defined");//"No name defined" is the default value.
//            store_name.setText(indexOfList);
//        }

        //商店評分
        float RatingBarOfList_prefs = prefs.getFloat("RatingBarOfList_PREFS", 0);
        RatingBar rb_scoring = (RatingBar) findViewById(R.id.ratingBar);
        rb_scoring.setRating(RatingBarOfList_prefs);
        if (RatingBarOfList_prefs == 0) {
            rb_scoring.setRating(0);
        }

        editor = getSharedPreferences("Store", MODE_PRIVATE).edit();
        //儲存該餐點種類
        editor.putString("MealType_PREFS", "主餐" );
        editor.commit();

    }

    //切換至套餐清單
    public void onClickSetMeal(View v) {
        // TODO Auto-generated method stub
        //儲存該餐點種類
        editor.putString("MealType_PREFS", "套餐" );
        editor.commit();

        //切換頁面
        Intent intent = new Intent();
        intent.setClass(MenuActivity.this, FoodActivity.class);
        startActivity(intent);
    }

    //切換至主餐清單
    public void onClickMainCourse(View v) {
        // TODO Auto-generated method stub
        //儲存該餐點種類
        editor.putString("MealType_PREFS", "主餐" );
        editor.commit();

        //切換頁面
        Intent intent = new Intent();
        intent.setClass(MenuActivity.this, FoodActivity.class);
        startActivity(intent);
    }

    //切換至飲料清單
    public void onClickDrink(View v) {
        // TODO Auto-generated method stub
        //儲存該餐點種類
        editor.putString("MealType_PREFS", "飲料" );
        editor.commit();

        //切換頁面
        Intent intent = new Intent();
        intent.setClass(MenuActivity.this, FoodActivity.class);
        startActivity(intent);
    }
}
