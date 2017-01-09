package test;

import com.google.gson.Gson;

import java.util.ArrayList;

import yclo.ntoufoodmap.ConnectAPI;
import yclo.ntoufoodmap.StoreList;

/**
 * Created by Yin on 2017/1/8.
 */

public class Cookies {
    private static String userid;
    private static String username;
    private static int rights;
    private static ArrayList<StoreList> store;

    static{
        try{
            String r = ConnectAPI.sendPost("API/getStores.php","");
            store = new Gson().fromJson(r, ArrayList.class);
        }catch (Exception e){e.printStackTrace();}
    }
    public static void setUserid(String u) {
        userid = u;
    }

    public static void setUsername(String un) {
        username = un;
    }

    public static void setRights(int r) {
        rights = r;
    }

    public static String getUserid(String u) {
        return userid;
    }

    public static String getUsername(String un) {
        return username;
    }

    public static int getRights(int r) {
        return rights;
    }

    public static ArrayList<String> getStoreName(){
        ArrayList<String> name = new ArrayList<>();
        for(StoreList s : store){
            name.add(s.getStorename());
        }
        return name;
    }

    public static ArrayList<StoreList> getStore(){
        return store;
    }
}
