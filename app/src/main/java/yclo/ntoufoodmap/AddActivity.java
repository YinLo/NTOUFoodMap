package yclo.ntoufoodmap;

import android.Manifest;
import static android.Manifest.permission.*;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.FileNotFoundException;

public class AddActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1 ;
    String img_selected = "";  //要更換的圖片

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //種類標籤下拉式選單
        Spinner spinner = (Spinner) findViewById(R.id.spirTag);
        ArrayAdapter<CharSequence> tagList = ArrayAdapter.createFromResource(AddActivity.this,
                R.array.tag_list,
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(tagList);

        Button btnImg_Store = (Button)findViewById(R.id.btnImgStore);
        btnImg_Store.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                img_selected = "R.id.imgStore";
                int permission = ActivityCompat.checkSelfPermission(AddActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    // 無權限，向使用者請求
                    ActivityCompat.requestPermissions(
                            AddActivity.this,
                            new String[] {WRITE_EXTERNAL_STORAGE,
                                    READ_EXTERNAL_STORAGE},
                            REQUEST_EXTERNAL_STORAGE
                    );
                    Intent intent = new Intent();
                    /* 开启Pictures画面Type设定为image */
                    intent.setType("image/*");
                    /* 使用Intent.ACTION_GET_CONTENT这个Action */
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    /* 取得相片后返回本画面 */
                    startActivityForResult(intent, 1);
                }
            }

        });

        Button btnImg_Menu = (Button)findViewById(R.id.btnImgMenu);
        btnImg_Menu.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                img_selected = "R.id.imgMenu";
                int permission = ActivityCompat.checkSelfPermission(AddActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    // 無權限，向使用者請求
                    ActivityCompat.requestPermissions(
                            AddActivity.this,
                            new String[] {WRITE_EXTERNAL_STORAGE,
                                    READ_EXTERNAL_STORAGE},
                            REQUEST_EXTERNAL_STORAGE
                    );
                    Intent intent = new Intent();
                     /* 开启Pictures画面Type设定为image */
                    intent.setType("image/*");
                     /* 使用Intent.ACTION_GET_CONTENT这个Action */
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                     /* 取得相片后返回本画面 */
                    startActivityForResult(intent, 1);
                }
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            Log.e("uri", uri.toString());
            ContentResolver cr = this.getContentResolver();
            try {

                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                ImageView imageView = null;
                if( img_selected == "R.id.imgStore")
                    imageView = (ImageView) findViewById(R.id.imgStore);
                else if( img_selected == "R.id.imgMenu" )
                    imageView = (ImageView) findViewById(R.id.imgMenu);
                /* 将Bitmap设定到ImageView */
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(),e);
            }
        }

    }
}