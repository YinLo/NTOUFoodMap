package yclo.ntoufoodmap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class RecommendActivity extends AppCompatActivity {

    ListView store_list;
    //店家列表 store_name:商店名稱
    ArrayList<Integer> store_id = new <Integer>ArrayList( Arrays.asList(1, 2, 3, 4, 5, 6, 7) );  // 搜尋資料庫用
    ArrayList<String> store_name = new <String>ArrayList(Arrays.asList("工學院餐廳", "夢泉餐廳", "麥當勞", "肯德基", "摩斯漢堡", "阿華炒麵", "貴族世家"));
    ArrayList<Double> scoring = new <Double>ArrayList(Arrays.asList(5.0, 5.0, 4.5, 3.5, 3.0, 1.0, 1.0));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        //種類標籤下拉式選單
        Spinner spinner = (Spinner) findViewById(R.id.spirTag);
        ArrayAdapter<CharSequence> tagList = ArrayAdapter.createFromResource(RecommendActivity.this,
                R.array.tag_list,
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(tagList);


        //將值存進list
        store_list = (ListView) findViewById(R.id.listRest);
        store_list.setAdapter(new StoreAdapter(RecommendActivity.this, store_id, store_name, scoring));

        //點擊店家list清單
        store_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position,
                                    long id) {
                // TODO Auto-generated method stub
                SharedPreferences.Editor editor = getSharedPreferences("Store", MODE_PRIVATE).edit();
                //儲存該Store ID(暫時先用商店名稱做靜態)
                editor.putString("IndexOfList_PREFS", store_name.get(position) );
                editor.putFloat("RatingBarOfList_PREFS", scoring.get(position).floatValue());
                editor.commit();

                //換頁
                Intent intent = new Intent();
                intent.setClass(RecommendActivity.this, StoreActivity.class);
                startActivity(intent);
            }
        });

        ImageButton btnAdd = (ImageButton) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RecommendActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });


    }



}
