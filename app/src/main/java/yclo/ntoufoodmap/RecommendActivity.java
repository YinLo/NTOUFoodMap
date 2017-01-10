package yclo.ntoufoodmap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static yclo.ntoufoodmap.R.array.tag_list;

public class RecommendActivity extends AppCompatActivity {

    ListView store_list;
    private Gson gson = new Gson();
    //店家列表 store_name:商店名稱
    private ArrayList<Integer> store_id = Cookies.getStoreID();  // 搜尋資料庫用
    private ArrayList<String> store_name = Cookies.getStoreName();
    private ArrayList<Float> scoring = Cookies.getStoreScore();
    ArrayList<String> tag = Cookies.getStoreTag();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        final ArrayList<String> store = Cookies.getStoreName();


        //種類標籤下拉式選單
        final Spinner spinner = (Spinner) findViewById(R.id.spirTag);
        ArrayAdapter<CharSequence> tagList = ArrayAdapter.createFromResource(RecommendActivity.this,
                tag_list,
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(tagList);


        //將值存進list
        store_list = (ListView) findViewById(R.id.listRest);
        store_list.setAdapter(new StoreAdapter(RecommendActivity.this, store_id, store_name, scoring));


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                for(int i=0;i<tag.size();i++) {
                    if (tag.get(i) != parent.getSelectedItem().toString() || parent.getSelectedItem().toString()=="全部") {
                        store_list.getChildAt(i).setVisibility(View.VISIBLE);
                        //Toast.makeText(RecommendActivity.this, String.valueOf(i), Toast.LENGTH_SHORT).show();
                    } else {
                        store_list.getChildAt(i).setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //點擊店家list清單
        store_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position,
                                    long id) {
                // TODO Auto-generated method stub
                SharedPreferences.Editor editor = getSharedPreferences("Store", MODE_PRIVATE).edit();
                //儲存該Store ID(暫時先用商店名稱做靜態)
                editor.putInt("IndexOfList_PREFS", position);
                editor.putFloat("RatingBarOfList_PREFS", scoring.get(position));
                editor.commit();
                Cookies.setStoreid(store_id.get(position));

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
                if(Cookies.getRights()>=0) {
                    Intent intent = new Intent();
                    intent.setClass(RecommendActivity.this, AddActivity.class);
                    startActivity(intent);
                }else{
                    AlertDialog.Builder dialog = new AlertDialog.Builder(RecommendActivity.this);
                    dialog.setTitle("警告");
                    dialog.setMessage("此功能僅限已註冊用戶使用!");
                    dialog.show();
                }
            }
        });


    }




}
