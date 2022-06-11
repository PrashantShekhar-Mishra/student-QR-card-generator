package com.example.twophaseauthenticationsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DBPARTICIPANT DB;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlogin);
        Button collectData,generateQr,Result,download;
        generateQr = findViewById(R.id.generateQr);
        collectData = findViewById(R.id.collectData);
        Result= findViewById(R.id.Result);
        download=findViewById(R.id.download);

        DB = new DBPARTICIPANT(this);

        collectData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(AdminActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                int k=1;
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(k+") Name :"+res.getString(0)+"\n");
                    buffer.append("address :"+res.getString(1)+"\n");
                    buffer.append("Date of Birth :"+res.getString(2)+"\n");
                    buffer.append("college  :"+res.getString(3)+"\n");
                    buffer.append("Email :"+res.getString(4)+"\n\n");
                    k+=1;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
        generateQr.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),QRDISPLAY.class);
                startActivity(i);

            }


        });
        Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(AdminActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                int k=1;
                while(res.moveToNext()){
                    buffer.append(k+")"+res.getString(0)+"  "+res.getString(3)+ "  " +res.getString(4)+"\n\n");
                    k+=1;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
                builder.setCancelable(true);
                builder.setTitle("final attendance");
                data =buffer.toString();
                builder.setMessage(data);
                builder.show();
            }        });
        download.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),QRDISPLAY.class);
                startActivity(i);

            }
        });


    }}

