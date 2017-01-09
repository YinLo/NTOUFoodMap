package yclo.ntoufoodmap;

/**
 * Created by Yin on 2017/1/9.
 */

public class StoreList {
    int storeid;
    String storename;
    String address;
    String businesshours;
    String telephone;
    String tag;
    String image;
    float score;


    public StoreList(){

    }

    public int getStoreid() {
        return storeid;
    }

    public String getStorename() {
        return storename;
    }

    public String getAddress() {
        return address;
    }

    public String getBusinesshours() {
        return businesshours;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getTag() {
        return tag;
    }
    public String getImage() {
        return image;
    }
    public float getScore() {
        return score;
    }
}
