package yclo.ntoufoodmap;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yin on 2017/1/8.
 */

public class Cookies {
    Context pref;
    private static String userid;
    private static String username;
    private static int rights;
    private static ArrayList<StoreList> store;
    private static int storeid;

    static {
        try {
            String r = ConnectAPI.sendPost("API/getStores.php", "");
            Type type = (Type) new TypeToken<List<StoreList>>() {
            }.getType();
            store = new Gson().fromJson(r, (java.lang.reflect.Type) type);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public static String getUserid() {
        return userid;
    }

    public static String getUsername() {
        return username;
    }

    public static int getRights() {
        return rights;
    }

    public static ArrayList<Integer> getStoreID() {
        ArrayList<Integer> id = new ArrayList<>();
        for (StoreList s : store) {
            id.add(s.getStoreid());
        }
        return id;
    }

    public static ArrayList<String> getStoreName() {
        ArrayList<String> name = new ArrayList<>();
        for (StoreList s : store) {
            name.add(s.getStorename());
        }
        return name;
    }

    public static ArrayList<String> getStoreAddress() {
        ArrayList<String> address = new ArrayList<>();
        for (StoreList s : store) {
            address.add(s.getAddress());
        }
        return address;
    }

    public static ArrayList<String> getStoreBusinesshours() {
        ArrayList<String> businesshours = new ArrayList<>();
        for (StoreList s : store) {
            businesshours.add(s.getBusinesshours());
        }
        return businesshours;
    }

    public static ArrayList<String> getStoreTelephone() {
        ArrayList<String> telephone = new ArrayList<>();
        for (StoreList s : store) {
            telephone.add(s.getTelephone());
        }
        return telephone;
    }

    public static ArrayList<String> getStoreTag() {
        ArrayList<String> tag = new ArrayList<>();
        for (StoreList s : store) {
            tag.add(s.getTag());
        }
        return tag;
    }

    public static ArrayList<String> getStoreImage() {
        ArrayList<String> image = new ArrayList<>();
        for (StoreList s : store) {
            image.add(s.getImage());
        }
        return image;
    }

    public static ArrayList<Float> getStoreScore() {
        ArrayList<Float> score = new ArrayList<>();
        for (StoreList s : store) {
            score.add(s.getScore());
        }
        return score;
    }

    public static ArrayList<StoreList> getStore() {
        return store;
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public static ArrayList<StoreList> getStoreForTag(String tag) {
        ArrayList<StoreList> tmp = new ArrayList<>();
        for (StoreList s : store) {
            if (s.getTag().equals(tag)) {
                tmp.add(s);
            }
        }
        return tmp;
    }

    public static ArrayList<Integer> getStoreIDForTag(ArrayList<StoreList> tmp) {
        ArrayList<Integer> id = new ArrayList<>();
        for (StoreList s : tmp) {
            id.add(s.getStoreid());
        }
        return id;
    }

    public static ArrayList<String> getStoreNameForTag(ArrayList<StoreList> tmp) {
        ArrayList<String> name = new ArrayList<>();
        for (StoreList s : tmp) {
            name.add(s.getStorename());
        }
        return name;
    }

    public static ArrayList<String> getStoreAddressForTag(ArrayList<StoreList> tmp) {
        ArrayList<String> address = new ArrayList<>();
        for (StoreList s : tmp) {
            address.add(s.getAddress());
        }
        return address;
    }

    public static ArrayList<String> getStoreBusinesshoursForTag(ArrayList<StoreList> tmp) {
        ArrayList<String> bussinseehours = new ArrayList<>();
        for (StoreList s : tmp) {
            bussinseehours.add(s.getBusinesshours());
        }
        return bussinseehours;
    }

    public static ArrayList<String> getStoreTelephoneForTag(ArrayList<StoreList> tmp) {
        ArrayList<String> telephone = new ArrayList<>();
        for (StoreList s : tmp) {
            telephone.add(s.getTelephone());
        }
        return telephone;
    }

    public static ArrayList<String> getStoreTagForTag(ArrayList<StoreList> tmp) {
        ArrayList<String> tag = new ArrayList<>();
        for (StoreList s : tmp) {
            tag.add(s.getTag());
        }
        return tag;
    }

    public static ArrayList<String> getStoreImageForTag(ArrayList<StoreList> tmp) {
        ArrayList<String> image = new ArrayList<>();
        for (StoreList s : tmp) {
            image.add(s.getImage());
        }
        return image;
    }

    public static ArrayList<Float> getStoreScoreForTag(ArrayList<StoreList> tmp) {
        ArrayList<Float> score = new ArrayList<>();
        for (StoreList s : tmp) {
            score.add(s.getScore());
        }
        return score;
=======

    public static ArrayList<StoreList> getStore(){
        return store;
>>>>>>> parent of d0dad82... tag to select store by wei
=======

    public static ArrayList<StoreList> getStore(){
        return store;
>>>>>>> parent of d0dad82... tag to select store by wei
    }

    public static int getStoreid() {
        return storeid;
    }

    public static void setStoreid(int sid) {
        storeid = sid;
    }
}
