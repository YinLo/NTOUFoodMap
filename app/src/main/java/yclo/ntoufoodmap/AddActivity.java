package yclo.ntoufoodmap;

import android.Manifest;

import static android.Manifest.permission.*;
import static android.R.attr.bitmap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.FileNotFoundException;

public class AddActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    String img_selected = "";  //要更換的圖片
    private EditText editStorename;
    private EditText editAddress;
    private EditText editBusinesshour;
    private EditText editPhone;
    private Spinner spirTag;
    private Button btnRegist;
    private String imgStoreURL = "";
    private String imgmenuURL = "";

    private String imagepath=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editStorename = (EditText) findViewById(R.id.editStorename);
        editAddress = (EditText) findViewById(R.id.editAddress);
        editBusinesshour = (EditText) findViewById(R.id.editBusinesshour);
        editPhone = (EditText) findViewById(R.id.editPhone);
        btnRegist = (Button) findViewById(R.id.btnRegist);

        //種類標籤下拉式選單
        spirTag = (Spinner) findViewById(R.id.spirTag);
        ArrayAdapter<CharSequence> tagList = ArrayAdapter.createFromResource(AddActivity.this,
                R.array.tag_list,
                android.R.layout.simple_spinner_dropdown_item);
        spirTag.setAdapter(tagList);

        Button btnImg_Store = (Button) findViewById(R.id.btnImgStore);
        btnImg_Store.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_selected = "R.id.imgStore";
                int permission = ActivityCompat.checkSelfPermission(AddActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    // 無權限，向使用者請求
                    ActivityCompat.requestPermissions(
                            AddActivity.this,
                            new String[]{WRITE_EXTERNAL_STORAGE,
                                    READ_EXTERNAL_STORAGE},
                            REQUEST_EXTERNAL_STORAGE
                    );
                }

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), 1);
            }

        });

        Button btnImg_Menu = (Button) findViewById(R.id.btnImgMenu);
        btnImg_Menu.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                img_selected = "R.id.imgMenu";
                int permission = ActivityCompat.checkSelfPermission(AddActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    // 無權限，向使用者請求
                    ActivityCompat.requestPermissions(
                            AddActivity.this,
                            new String[]{WRITE_EXTERNAL_STORAGE,
                                    READ_EXTERNAL_STORAGE},
                            REQUEST_EXTERNAL_STORAGE
                    );
                }
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), 1);

            }

        });

        btnRegist.setOnClickListener(new View.OnClickListener() {
            AlertDialog.Builder dialog = new AlertDialog.Builder(AddActivity.this);

            @Override
            public void onClick(View v) {
                if (editStorename.length() <= 0) {
                    dialog.setTitle("警告");
                    dialog.setMessage("請輸入店家名稱");
                    dialog.show();
                } else if (editAddress.length() <= 0) {
                    dialog.setTitle("警告");
                    dialog.setMessage("請輸入地址");
                    dialog.show();
                } else {
                    String response = "";
                    try {
                        response = ConnectAPI.sendPost("API/addStores.php", "name=" + editStorename.getText().toString() + "&address=" + editAddress.getText().toString() + "&bh=" +
                                editBusinesshour.getText().toString() + "&tel=" + editPhone.getText().toString() + "&tag="+spirTag.getSelectedItem().toString() +"&image="+imgStoreURL+"&menuImg="+imgmenuURL);
                        //response = ConnectAPI.sendPost("API/addStores.php", "");
                        //Toast.makeText(AddActivity.this, spirTag.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    AddStoreData addsD = new Gson().fromJson(response,AddStoreData.class);
                    if(addsD.getSuccess() == 1){
                        dialog.setTitle("系統提醒");
                        dialog.setMessage("成功新增");
                        dialog.show();
                        clearEditText();
                    }else if(addsD.getSuccess() == 0){
                        dialog.setTitle("錯誤");
                        dialog.setMessage(addsD.getContent());
                        dialog.show();
                        clearEditText();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            Log.e("uri", uri.toString());
            ContentResolver cr = this.getContentResolver();
            try {

                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                ImageView imageView = null;
                if (img_selected == "R.id.imgStore") {
                    imageView = (ImageView) findViewById(R.id.imgStore);
                    imgStoreURL = uri.toString();
                    imagepath = imgStoreURL;
                } else if (img_selected == "R.id.imgMenu") {
                    imageView = (ImageView) findViewById(R.id.imgMenu);
                    imgmenuURL = uri.toString();
                    imagepath = imgmenuURL;
                }
                /* 将Bitmap设定到ImageView */
                Toast.makeText(AddActivity.this, "Uploading file path:" +imagepath, Toast.LENGTH_LONG).show();
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(), e);
            }



        }
    }

    private void clearEditText(){
        editStorename.setText("");
        editAddress.setText("");
        editBusinesshour.setText("");
        editPhone.setText("");
    }
    class AddStoreData {
        private int success;
        private String content;

        public AddStoreData() {
        }

        public int getSuccess() {
            return success;
        }

        public String getContent() {
            return content;
        }
    }





}