package com.example.picker.fivepxdemo.Data;

import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by 17930 on 2016/2/3.
 */
public class HuiMaiJson implements Serializable{
    private String jsondata=null;
    private String Item_Num;


    private List<String> Item_cid=new ArrayList<>();
    private List<String> Item_url=new ArrayList<>();
    private List<String> Item_name=new ArrayList<>();
    private List<Map<String,Object>> Img_maps=new ArrayList<>();

   // private List<String>Img_url=new ArrayList<>();

    private List<String> Item_oldprice=new ArrayList<>();
    private List<String> Item_newPrice=new ArrayList<>();
    private List<String> Item_discount=new ArrayList<>();



    public HuiMaiJson(String jsondata) {
        this.jsondata = jsondata;
        JieJson();
    }

    public String getJsondata() {
        return jsondata;
    }

    public void setJsondata(String jsondata) {
        this.jsondata = jsondata;
    }

    public String getItem_Num() {
        return Item_Num;
    }

    public void setItem_Num(String item_Num) {
        Item_Num = item_Num;
    }

    public List<String> getItem_discount() {
        return Item_discount;
    }

    public void setItem_discount(List<String> item_discount) {
        Item_discount = item_discount;
    }

    public List<String> getItem_newPrice() {
        return Item_newPrice;
    }

    public void setItem_newPrice(List<String> item_newPrice) {
        Item_newPrice = item_newPrice;
    }

    public List<String> getItem_oldprice() {
        return Item_oldprice;
    }

    public void setItem_oldprice(List<String> item_oldprice) {
        Item_oldprice = item_oldprice;
    }



    public List<String> getItem_cid() {
        return Item_cid;
    }

    public void setItem_cid(List<String> item_cid) {
        Item_cid = item_cid;
    }

    public List<String> getItem_url() {
        return Item_url;
    }

    public void setItem_url(List<String> item_url) {
        Item_url = item_url;
    }

    public List<String> getItem_name() {
        return Item_name;
    }

    public void setItem_name(List<String> item_name) {
        Item_name = item_name;
    }


    public int array2leught=0;

    public List<Map<String, Object>> getImg_maps() {
        return Img_maps;
    }

    public void setImg_maps(List<Map<String, Object>> img_maps) {
        Img_maps = img_maps;
    }

    private void JieJson() {
        try {
            JSONObject json1=new JSONObject(jsondata);
            Item_Num=json1.getString("item_num");
            JSONArray jsonArray1=json1.getJSONArray("item_data");
            for (int i=0;i<jsonArray1.length();i++){
                JSONObject jsonObject2=jsonArray1.getJSONObject(i);
                Item_cid.add(jsonObject2.getString("cid"));
                Item_url.add(jsonObject2.getString("item_url"));
                Item_name.add(jsonObject2.getString("name"));
                Item_oldprice.add(jsonObject2.getString("o_price"));
                Item_newPrice.add(jsonObject2.getString("d_price"));
                Item_discount.add(jsonObject2.getString("discount"));
                JSONArray jsonArray2=jsonObject2.getJSONArray("img_url");
                Map<String,Object> maps=new HashMap<>();
                for (int j=0;j<jsonArray2.length();j++){
                    JSONObject jsonObject3=jsonArray2.getJSONObject(j);
                    maps.put(j+"",jsonObject3.getString("url"));
                }
                Img_maps.add(maps);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
