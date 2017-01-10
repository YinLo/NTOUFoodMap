package yclo.ntoufoodmap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Comment;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class CommentActivity extends AppCompatActivity {

    ImageButton btnCorrect = null;
    ImageButton btnCancel = null;
    EditText editcomment;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        btnCorrect = (ImageButton) findViewById(R.id.btnCorrect);
        btnCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //確認送出
                Correct();
            }
        });


        btnCancel = (ImageButton) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //確認取消並返回上一頁
                Cancel();
            }
        });
    }

    //確認並傳送訊息
    private void Correct() {
        //彈出窗
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("是否確認評論?")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 左方按鈕方法
                        editcomment = (EditText)findViewById(R.id.editComment);
                        String response = "";
                        try {
                            RatingBar rt = (RatingBar) findViewById(R.id.ratingBar);
                            response = ConnectAPI.sendPost("API/addComments.php", "storeid=" + Cookies.getStoreid() + "&userid=" + Cookies.getUserid() + "&rt=" + rt.getRating() + "&con=" + editcomment.getText().toString());
                            //Toast.makeText(getApplicationContext(),  "user=" + Cookies.getUserid() + "&storeid=" + String.valueOf(RecommendActivity.selectdStore) + "&mealtype="+ mealType_prefs, Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        CommentData CommentData = gson.fromJson(response, CommentData.class);

                        Toast toast = Toast.makeText(CommentActivity.this,
                                CommentData.getContent(), Toast.LENGTH_SHORT);
                        toast.show();
                        Cookies.update();
                        RecommendActivity.scoring = Cookies.getStoreScore();
                        Intent intent = new Intent();
                        intent.setClass(CommentActivity.this, AppraiseActivity.class);

                        /*傳送資料庫返回評論頁面動作(未做)*/

                        startActivity(intent);
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 右方按鈕方法
                        dialog.dismiss();
                    }
                });
        AlertDialog about_dialog = builder.create();
        about_dialog.show();
    }

    private void Cancel() {
        //彈出窗
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("確定不傳送錯誤回報並返回上一頁?")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 左方按鈕方法

                        Intent intent = new Intent();
                        intent.setClass(CommentActivity.this, StoreActivity.class);

                        startActivity(intent);
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 右方按鈕方法
                        dialog.dismiss();
                    }
                });
        AlertDialog about_dialog = builder.create();
        about_dialog.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.setClass(CommentActivity.this, AppraiseActivity.class);
            startActivity(intent);
        }
        return false;
    }

    class CommentData {
        private int success;
        private String content;

        public CommentData() {
        }

        public int getSuccess() {
            return success;
        }

        public String getContent() {
            return content;
        }
    }
}
