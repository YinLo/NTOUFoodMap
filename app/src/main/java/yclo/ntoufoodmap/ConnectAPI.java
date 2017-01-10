package yclo.ntoufoodmap;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Yin on 2017/1/8.
 */

public class ConnectAPI{
    private final static String server = "http://140.121.197.105/~lab308server/";
    private final static String USER_AGENT = "Mozilla/5.0";

    // HTTP POST request
    public static String sendPost(String api, String parameters) throws Exception {

        URL obj = new URL(server+api);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setDoInput (true);
        con.setDoOutput (true);
        con.setUseCaches (false);
        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        //con.setRequestProperty("Content-Type", "multipart/form-data");

        // Send post request
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
        writer.write(parameters);
        writer.flush();
        writer.close();
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

//        System.out.println("\nSending 'POST' request to URL : " + url);
//        System.out.println("Post parameters : " + parameters);
//        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        con.disconnect();
        //print result
        return  response.toString();

    }
}
