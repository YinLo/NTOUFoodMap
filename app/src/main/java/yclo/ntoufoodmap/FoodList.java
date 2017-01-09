package yclo.ntoufoodmap;

/**
 * Created by user on 2017/1/9.
 */

public class FoodList {
    int storeid;
    int foodid;
    String foodname;
    int price;
    String mealtype;
    boolean likeornot;



    public FoodList(){

    }

    public int getStoreid() {
        return storeid;
    }

    public int getFoodid() {
        return foodid;
    }

    public String getFoodname() {
        return foodname;
    }

    public int getPrice() {
        return price;
    }

    public String getMealtype() {
        return mealtype;
    }
    public boolean getLikeornot() {
        return likeornot;
    }

}
