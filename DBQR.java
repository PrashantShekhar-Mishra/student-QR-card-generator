package com.example.twophaseauthenticationsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBQR extends SQLiteOpenHelper {


    public DBQR(Context context) {
        super(context,"Qr.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase Dp) {
        Dp.execSQL("create Table QR(qr TEXT primary key,password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase Dp, int i, int i1) {
        Dp.execSQL("drop Table if exists Qr");
    }
    public void insertuserdata(String qr,String Password)
    {
        SQLiteDatabase Dp = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("qr", qr);
        contentValues.put("password", Password);
        Dp.insert("QR",null ,contentValues);
    }
    public Cursor getdata()
    {
        SQLiteDatabase Dp = this.getWritableDatabase();
        Cursor cursor = Dp.rawQuery("Select * from QR", null);
        return cursor;
    }

    }

