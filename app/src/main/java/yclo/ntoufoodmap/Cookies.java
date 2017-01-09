package yclo.ntoufoodmap;

/**
 * Created by Yin on 2017/1/8.
 */

public class Cookies {
    private static String userid;
    private static String username;
    private static int rights;

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
}
