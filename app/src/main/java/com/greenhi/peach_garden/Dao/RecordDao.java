package com.greenhi.peach_garden.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.greenhi.peach_garden.db.DbOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class RecordDao {
    private DbOpenHelper helper;
    public RecordDao(Context context){
        helper=new DbOpenHelper(context,"history.db",null,1);
    }
    public void insert(String name){
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="insert into histories (name) values(?)";
        db.execSQL(sql,new String[]{name});
    }
    public void delete(){
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="delete from histories";
        db.execSQL(sql);
    }
    public List<String> get(){

        List<String>list=new ArrayList<>();
        SQLiteDatabase db=helper.getReadableDatabase();
        String sql="select * from histories";
        Cursor cursor=db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int index=cursor.getColumnIndex("name");
            String name=cursor.getString(index);
            list.add(name);
        }
        cursor.close();
        db.close();

        return list;
    }
}
