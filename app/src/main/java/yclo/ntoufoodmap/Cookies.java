package yclo.ntoufoodmap;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

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
            Type type = (Type) new TypeToken<List<StoreList>>(){}.getType();
            store = new Gson().fromJson(r, (java.lang.reflect.Type) type);
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

    public static ArrayList<Integer> getStoreID(){
        ArrayList<Integer> id = new ArrayList<>();
        for(StoreList s : store){
            id.add(s.getStoreid());
        }
        return id;
    }

    public static ArrayList<String> getStoreName(){
        ArrayList<String> name = new ArrayList<>();
        for(StoreList s : store){
            name.add(s.getStorename());
        }
        return name;
    }

    public static ArrayList<Float> getStoreScore(){
        ArrayList<Float> score = new ArrayList<>();
        for(StoreList s : store){
            score.add(s.getScore());
        }
        return score;
    }



    public static ArrayList<StoreList> getStore(){
        return store;
    }
}
