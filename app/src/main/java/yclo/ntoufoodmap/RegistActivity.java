package yclo.ntoufoodmap;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

/**
 * Created by user on 2017/1/3.
 */

public class RegistActivity extends AppCompatActivity {
    private Button btnRegister;
    private EditText editUserid;
    private EditText editPwd;
    private EditText editUsername;
    private EditText editMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        btnRegister = (Button) findViewById(R.id.btnRegist);
        editUserid = (EditText) findViewById(R.id.editUserid);
        editPwd = (EditText) findViewById(R.id.editPwd);
        editUsername = (EditText) findViewById(R.id.editUsername);
        editMail = (EditText) findViewById(R.id.editMail);

        final AlertDialog.Builder dialog = new AlertDialog.Builder(RegistActivity.this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (5 > editUserid.length() || editUserid.length() > 16) {
                    dialog.setTitle("警告");
                    dialog.setMessage("請輸入帳號(5~16字元)");
                }else
                if (5 > editPwd.length() || editPwd.length() > 16) {
                    dialog.setTitle("警告");
                    dialog.setMessage("請輸入密碼(5~16字元)");
                }else
                if (editUsername.length()<=0) {
                    dialog.setTitle("警告");
                    dialog.setMessage("請輸入使用者名稱");
                }else
                if (editMail.length()<=0) {
                    dialog.setTitle("警告");
                    dialog.setMessage("請輸入信箱");
                }else{
                    try {
                        String p = "account="+editUserid.getText()+"&pwd="+editPwd.getText()+"&name="+editUsername.getText()+"&email="+editMail.getText();
                        String r = ConnectAPI.sendPost("API/register.php", p);
                        RegData rd = new Gson().fromJson(r, RegData.class);
                        if(rd.getSuccess() == 1){
                            dialog.setTitle("系統提示");
                            dialog.setMessage(rd.getContent());
                            clearText();
                        }else if(rd.getSuccess() == 0){
                            dialog.setTitle("系統錯誤");
                            dialog.setMessage(rd.getContent());
                            editMail.setText("");
                            editPwd.setText("");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }

    private void clearText(){
        editUserid.setText("");
        editPwd.setText("");
        editUsername.setText("");
        editMail.setText("");
    }

    class RegData{
        private int success;
        private String content;

        public RegData(){}

        public int getSuccess(){
            return success;
        }

        public String getContent(){
            return content;
        }
    };
}
