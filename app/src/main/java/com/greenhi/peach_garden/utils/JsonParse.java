package com.greenhi.peach_garden.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.greenhi.peach_garden.item.ItemComment;
import com.greenhi.peach_garden.item.ItemDynamic;
import com.greenhi.peach_garden.item.ItemAllDynamic;
import com.greenhi.peach_garden.item.ItemUser;
import com.greenhi.peach_garden.item.RecordsDTO;
import com.greenhi.peach_garden.item.Root;

import java.lang.reflect.Type;
import java.util.List;

public class JsonParse {
    public static List<ItemDynamic> Getdynamic(String json){
        Gson gson=new Gson();
        Type ListType=new TypeToken<Root<List<ItemDynamic>>>(){}.getType();
        Root<List<ItemDynamic>> dynamics=gson.fromJson(json,ListType);
        List<ItemDynamic> result = dynamics.getResult();
        return result;
    }

    public static List<ItemComment> GetCommment(String json){
        Gson gson=new Gson();
        Type ListType=new TypeToken<Root<List<ItemComment>>>(){}.getType();
        Root<List<ItemComment>> dynamics=gson.fromJson(json,ListType);
        List<ItemComment> result = dynamics.getResult();
        return result;
    }

    public static List<ItemUser> Getuser(String json){
        Gson gson=new Gson();
        Type ListType=new TypeToken<Root<List<ItemUser>>>(){}.getType();
        Root<List<ItemUser>> users=gson.fromJson(json,ListType);
        List<ItemUser> result = users.getResult();
        return result;
    }

    public static ItemUser Getuserbyid(String json){
        Gson gson=new Gson();
        Type ListType=new TypeToken<Root<ItemUser>>(){}.getType();
        Root<ItemUser> user=gson.fromJson(json,ListType);
        ItemUser result = user.getResult();
        return result;
    }

    public  static  List<RecordsDTO> GetAllDynamic(String json){
        Gson gson=new Gson();
        Type ListType=new TypeToken<Root<ItemAllDynamic<List<RecordsDTO>>>>(){}.getType();
        Root<ItemAllDynamic<List<RecordsDTO>>> AllDynamics=gson.fromJson(json,ListType);
        List<RecordsDTO> result = AllDynamics.getResult().getRecords();
        return result;
    }

    public  static  int GetTotalPage(String json){
        Gson gson=new Gson();
        Type ListType=new TypeToken<Root<ItemAllDynamic<List<RecordsDTO>>>>(){}.getType();
        Root<ItemAllDynamic<List<RecordsDTO>>> AllDynamics=gson.fromJson(json,ListType);
        int result = AllDynamics.getResult().getTotal();
        return result;
    }

}
