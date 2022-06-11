package com.example.twophaseauthenticationsystem;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context,"Admins.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Admins(username TEXT primary key,password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Admins");
    }
    public Boolean insertuserdata(String username,String Password)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", Password);
        long result=DB.insert("Admins",null ,contentValues);
        if(result==-1) {
            return false;
        }
        else{
            return true;
        }

    }
    public Boolean updateuserdata(String username,String Password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", Password);
        Cursor cursor = DB.rawQuery("select * from Admins where username=?", new String[]{username});
        if (cursor.getCount() > 0) {
            long result = DB.update("Admins ", contentValues, "username=?", new String[]{username});
            if (result == -1) {
                return false;
            } else {
                return true;
            }

        }
        else{
            return false;
        }
    }
    public Boolean deleteuserdata(String username) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from Admins where username=?", new String[]{username});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Admins ", "username=?", new String[]{username});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else{
            return false;
        }
    }

    public Boolean logindata(String username,String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from Admins where username=? and password=?", new String[]{username,password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
