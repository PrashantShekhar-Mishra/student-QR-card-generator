package com.example.twophaseauthenticationsystem;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText editTextTextEmailAddress, editTextNumberPassword;
    Button login, user, update, delete;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);



    editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
    editTextNumberPassword = findViewById(R.id.editTextNumberPassword);;
    login = findViewById(R.id.login);
    user = findViewById(R.id.user);
    update = findViewById(R.id.update);
    delete= findViewById(R.id.delete);
    DB = new DBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username= editTextTextEmailAddress.getText().toString();
                String password = editTextNumberPassword .getText().toString();

                Boolean log = DB.logindata(username, password);
                if(log==true) {
                    Intent i = new Intent(LoginActivity.this, AdminActivity.class);
                    startActivity(i);
                    }
                else
                    Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_SHORT).show();
            }        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username= editTextTextEmailAddress.getText().toString();
                String password = editTextNumberPassword .getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(username, password);
                if(checkinsertdata==true)
                    Toast.makeText(LoginActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(LoginActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username= editTextTextEmailAddress.getText().toString();
                String password = editTextNumberPassword .getText().toString();
                Boolean checkupdatedata = DB.updateuserdata(username,password);
                if(checkupdatedata==true)
                    Toast.makeText(LoginActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(LoginActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username= editTextTextEmailAddress.getText().toString();
                Boolean checkudeletedata = DB.deleteuserdata(username);
                if(checkudeletedata==true)
                    Toast.makeText(LoginActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(LoginActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        ;
    }
}


