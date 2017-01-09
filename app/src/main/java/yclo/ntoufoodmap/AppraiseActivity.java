package yclo.ntoufoodmap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class AppraiseActivity extends AppCompatActivity {

    ListView appraise_list;
    ImageButton btnAdd;
    //店家列表 store_name:商店名稱
    ArrayList<String> user_name = new <String>ArrayList(Arrays.asList("王小明", "羅尹程", "楊若榆", "劉子毅", "葉威廷", "蔡政穎", "莊惟中"));
    ArrayList<String> comments = new <String>ArrayList(Arrays.asList("颱風天的救星啊!", "分量越做越小了", "店員好正哦哦哦哦哦哦!", "怎麼一直漲價", "普普通通", "難吃", "怎麼還不快倒閉?"));
    ArrayList<Double> scoring = new <Double>ArrayList(Arrays.asList(5.0, 4.5, 5.0, 3.5, 3.0, 1.0, 1.0));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appraise);

        //將值存進list
        appraise_list = (ListView) findViewById(R.id.listAppraise);
        appraise_list.setAdapter(new AppraiseAdapter(AppraiseActivity.this, user_name, comments, scoring));

        ImageButton btnAdd = (ImageButton) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AppraiseActivity.this, CommentActivity.class);
                startActivity(intent);
            }
        });
    }
}
