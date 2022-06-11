package com.example.twophaseauthenticationsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBPARTICIPANT extends SQLiteOpenHelper {


    public DBPARTICIPANT(Context context) {
        super(context,"USER.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Admins(username TEXT ,Address TEXT, dob TEXT,College TEXT,Email TEXT primary key)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists USER");
    }
    public Boolean insertuserdata(String username,String Address,String dob,String College,String Email)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username", username);
        contentValues.put("Address", Address);
        contentValues.put("dob",dob);
        contentValues.put("College",College);
        contentValues.put("Email",Email);
        long result=DB.insert("Admins",null ,contentValues);
        if(result==-1) {
            return false;
        }
        else{
            return true;
        }

    }
    public Boolean updateuserdata(String username,String Address,String dob,String College,String Email) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username", username);
        contentValues.put("Address", Address);
        contentValues.put("dob",dob);
        contentValues.put("College",College);
        contentValues.put("Email",Email);
        Cursor cursor = DB.rawQuery("select * from Admins where Email=?", new String[]{Email});
        if (cursor.getCount() > 0) {
            long result = DB.update("USER ", contentValues, "Email=?", new String[]{Email});
            if (result == -1) {
                return false;
            }
            else {
                return true;
            }

        }
        else{
            return false;
        }
    }
    public Boolean deleteuserdata(String Email) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from Admins where Email=?", new String[]{Email});
        if (cursor.getCount() > 0) {
            long result = DB.delete("USER ", "Email=?", new String[]{Email});
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
    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Admins", null);
        return cursor;

    }
}



