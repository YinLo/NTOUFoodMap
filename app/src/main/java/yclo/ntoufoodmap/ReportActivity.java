package yclo.ntoufoodmap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class ReportActivity extends AppCompatActivity {

    ImageButton btnCorrect = null;
    ImageButton btnCancel = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

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
        builder.setMessage("是否傳送回報訊息?")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 左方按鈕方法

                        //傳送成功訊息
                        Toast toast = Toast.makeText(ReportActivity.this,
                                "成功送出!", Toast.LENGTH_LONG);
                        //顯示Toast
                        toast.show();
                        Intent intent = new Intent();
                        intent.setClass(ReportActivity.this, StoreActivity.class);

                        /*傳送資料庫返回店家頁面動作(未做)*/

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
                        Toast toast = Toast.makeText(ReportActivity.this,
                                "成功送出!", Toast.LENGTH_LONG);
                        //顯示Toast
                        toast.show();
                        Intent intent = new Intent();
                        intent.setClass(ReportActivity.this, StoreActivity.class);

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
