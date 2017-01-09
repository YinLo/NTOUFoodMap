package yclo.ntoufoodmap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 2017/1/3.
 */

public class StoreActivity extends AppCompatActivity {

    ImageButton btnReport = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        TextView store_name = (TextView) findViewById(R.id.txtStorename);
        //取出Store ID(暫時先用商店名稱做靜態)
        SharedPreferences prefs = getSharedPreferences("Store", Context.MODE_PRIVATE);
        String indexOfList_prefs = prefs.getString("IndexOfList_PREFS", null);
        store_name.setText(indexOfList_prefs);
        if (indexOfList_prefs != null) {
            indexOfList_prefs = prefs.getString("IndexOfList_PREFS", "No name defined");//"No name defined" is the default value.
            store_name.setText(indexOfList_prefs);
        }

        //商店評分
        float RatingBarOfList_prefs = prefs.getFloat("RatingBarOfList_PREFS", 0);
        RatingBar rb_scoring = (RatingBar) findViewById(R.id.ratingBar);
        rb_scoring.setRating(RatingBarOfList_prefs);
        if (RatingBarOfList_prefs == 0) {
            rb_scoring.setRating(0);
        }

        //切換至錯誤回報
        btnReport = (ImageButton)findViewById(R.id.btnReport);
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(StoreActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

    }


    //切換至餐點清單
    public void onClickMenu(View v) {
        // TODO Auto-generated method stub
        Intent intent = new Intent();
        intent.setClass(StoreActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    //切換至地圖
    public void onClickMap(View v) {
        // TODO Auto-generated method stub
        Intent intent = new Intent();
        intent.setClass(StoreActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    //切換至餐點清單
    public void onClickComments(View v) {
        // TODO Auto-generated method stub
        Intent intent = new Intent();
        intent.setClass(StoreActivity.this, AppraiseActivity.class);
        startActivity(intent);
    }

}
