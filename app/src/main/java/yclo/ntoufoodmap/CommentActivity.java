package yclo.ntoufoodmap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

public class CommentActivity extends AppCompatActivity {

    private ImageButton btnCorrect = null;
    private ImageButton btnCancel = null;
    private EditText editReport = null;
    private RatingBar ratingBar = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
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

                        //傳送成功訊息
                        Toast toast = Toast.makeText(CommentActivity.this,
                                "成功送出!", Toast.LENGTH_LONG);
                        //顯示Toast
                        toast.show();
                        Intent intent = new Intent();
                        intent.setClass(CommentActivity.this, AppraiseActivity.class);

                        /*傳送資料庫返回評論頁面動作(未做)*/
                        try{
                            String p = "storeid=" + Cookies.getStoreid() + "&userid=" + Cookies.getUserid() + "&con=" + editReport.getText()+"&rt="+ratingBar.getRating();
                            String r = ConnectAPI.sendPost("API/addComments.php", p);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
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

                        //傳送成功訊息
                        Toast toast = Toast.makeText(CommentActivity.this,
                                "成功送出!", Toast.LENGTH_LONG);
                        //顯示Toast
                        toast.show();
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
}
