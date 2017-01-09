package yclo.ntoufoodmap;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin = null;
    private TextView user = null;
    private TextView pwd = null;
    private Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());

        user = (TextView)findViewById(R.id.txtUser);
        pwd = (TextView)findViewById(R.id.txtPwd);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String response = "";
                try {
                    response = ConnectAPI.sendPost("API/login.php", "user=" + user.getText().toString() + "&pwd=" + pwd.getText().toString());
                }catch (Exception e){
                    e.printStackTrace();
                }

                UserData userData = gson.fromJson(response, UserData.class);
                if(userData.getSuccess()==1) {
                    if(userData.getContent().get("verfication").equals("1")) {
                        Cookies.setUserid(userData.getContent().get("userid"));
                        Cookies.setUsername(userData.getContent().get("username"));
                        Cookies.setRights(Integer.parseInt(userData.getContent().get("rights")));
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this, IndexActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    class UserData{
        private int success;
        private Map<String, String> content;

        public UserData(){

        }

        public int getSuccess(){
            return  success;
        }

        public Map<String, String> getContent(){
            return content;
        }
    }
}
