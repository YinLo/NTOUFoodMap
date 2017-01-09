package yclo.ntoufoodmap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user on 2017/1/3.
 */

public class StoreActivity extends AppCompatActivity {

    ImageButton btnReport = null;
    TextView txtHours = null;
    TextView txtPhone = null;
    ArrayList<String> businesshours = Cookies.getStoreBusinesshours();
    ArrayList<String> telephone = Cookies.getStoreTelephone();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        TextView store_name = (TextView) findViewById(R.id.txtStorename);
        //取出Index of Store List
        SharedPreferences prefs = getSharedPreferences("Store", Context.MODE_PRIVATE);
        int indexOfList_prefs = prefs.getInt("IndexOfList_PREFS", -1);
        store_name.setText(Cookies.getStoreName().get(indexOfList_prefs));


        //商店評分
        float RatingBarOfList_prefs = prefs.getFloat("RatingBarOfList_PREFS", 0);
        RatingBar rb_scoring = (RatingBar) findViewById(R.id.ratingBar);
        rb_scoring.setRating(Cookies.getStoreScore().get(indexOfList_prefs));
        if (RatingBarOfList_prefs == 0) {
            rb_scoring.setRating(0);
        }
        //Toast.makeText(getApplicationContext(), RecommendActivity.scoring.get(indexOfList_prefs).toString(), Toast.LENGTH_SHORT).show();

        //切換至錯誤回報
        btnReport = (ImageButton)findViewById(R.id.btnReport);
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Cookies.getRights()>=0) {
                    Intent intent = new Intent();
                    intent.setClass(StoreActivity.this, ReportActivity.class);
                    startActivity(intent);
                }else{
                    AlertDialog.Builder dialog = new AlertDialog.Builder(StoreActivity.this);
                    dialog.setTitle("警告");
                    dialog.setMessage("此功能僅限已註冊用戶使用!");
                    dialog.show();
                }
            }
        });

        txtHours = (TextView)findViewById(R.id.txtHours);
        txtHours.setText(businesshours.get(indexOfList_prefs));

        txtPhone = (TextView)findViewById(R.id.txtPhone);
        txtPhone.setText(telephone.get(indexOfList_prefs));


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
