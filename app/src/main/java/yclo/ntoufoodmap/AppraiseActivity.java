package yclo.ntoufoodmap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppraiseActivity extends AppCompatActivity {

    ListView appraise_list;
    ImageButton btnAdd;
    //店家列表 store_name:商店名稱
    ArrayList<String> user_name;
    ArrayList<String> comments;
    ArrayList<Float> scoring;
    private ArrayList<CommentList> commentlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appraise);

        TextView store_name = (TextView) findViewById(R.id.txtStorename);
        SharedPreferences prefs = getSharedPreferences("Store", Context.MODE_PRIVATE);
        int indexOfList_prefs = prefs.getInt("IndexOfList_PREFS", -1);
        store_name.setText(RecommendActivity.store_name.get(indexOfList_prefs));


        try {
            String r = ConnectAPI.sendPost("API/getComment.php", "storeid=" + String.valueOf(Cookies.getStoreid()));
            //Toast.makeText(getApplicationContext(),  "user=" + Cookies.getUserid() + "&storeid=" + String.valueOf(RecommendActivity.selectdStore) + "&mealtype="+ mealType_prefs, Toast.LENGTH_SHORT).show();
            Type type = new TypeToken<List<CommentList>>() {
            }.getType();
            commentlist = new Gson().fromJson(r, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        user_name = getUser_name();
        comments = getComment();
        scoring = getScore();

        //將值存進list
        appraise_list = (ListView) findViewById(R.id.listAppraise);
        appraise_list.setAdapter(new AppraiseAdapter(AppraiseActivity.this, user_name, comments, scoring));

        ImageButton btnAdd = (ImageButton) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Cookies.getRights()>=0) {
                    Intent intent = new Intent();
                    intent.setClass(AppraiseActivity.this, CommentActivity.class);
                    startActivity(intent);
                }else{
                    AlertDialog.Builder dialog = new AlertDialog.Builder(AppraiseActivity.this);
                    dialog.setTitle("警告");
                    dialog.setMessage("此功能僅限已註冊用戶使用!");
                    dialog.show();
                }
            }
        });
    }

    private ArrayList<String> getUser_name() {
        ArrayList<String> tmp = new ArrayList<>();
        for (CommentList c : commentlist) {
            tmp.add(c.getUsername());
        }
        return tmp;
    }

    private ArrayList<String> getComment() {
        ArrayList<String> tmp = new ArrayList<>();
        for (CommentList c : commentlist) {
            tmp.add(c.getComment());
        }
        return tmp;
    }

    private ArrayList<Float> getScore() {
        ArrayList<Float> tmp = new ArrayList<>();
        for (CommentList c : commentlist) {
            tmp.add(c.getScore());
        }
        return tmp;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.setClass(AppraiseActivity.this, StoreActivity.class);
            startActivity(intent);
        }
        return false;
    }
}
