package yclo.ntoufoodmap;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class IndexActivity extends AppCompatActivity {

    private Button btnRecommend = null;
    private Button btnLogout = null;
    private Button btnFireFood = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        btnRecommend = (Button) findViewById(R.id.btnRecommend);
        btnFireFood = (Button) findViewById(R.id.btnFireFood);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        if (Cookies.getRights() == -1) {
            btnLogout.setVisibility(View.GONE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }


        btnRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(IndexActivity.this, RecommendActivity.class);
                startActivity(intent);
            }
        });

        btnFireFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(IndexActivity.this, FireFoodActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cookies.setRights(-2);
                Intent intent = new Intent();
                intent.setClass(IndexActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (Cookies.getRights() >= 0) {
                // 創建退出對話框
                AlertDialog isExit = new AlertDialog.Builder(this).create();
                // 設置對話框標題
                isExit.setTitle("系統提示");
                // 設置對話框消息
                isExit.setMessage("請登出!!");
                // 添加選擇按鈕並注冊監聽
                // 顯示對話框
                isExit.show();

            }
        }

        return false;

    }
}
