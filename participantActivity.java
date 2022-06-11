package com.example.twophaseauthenticationsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class participantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participant);

        EditText Name,Address,DOB,College,Email;
        Button Submit;
        DBPARTICIPANT DB;
        Name = findViewById(R.id.Name);
        Address = findViewById(R.id.Address);
        DOB = findViewById(R.id.DOB);
        College= findViewById(R.id.College);
        Email = findViewById(R.id.Email);
        Submit = findViewById(R.id.Submit);
        DB = new DBPARTICIPANT(this);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= Name.getText().toString();
                String address= Address.getText().toString();
                String dob= DOB.getText().toString();
                String college= College.getText().toString();
                String email= Email.getText().toString();
                Boolean checkinsertdata = DB.insertuserdata(name,address,dob,college,email);
                if(checkinsertdata==true)
                    Toast.makeText(participantActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(participantActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });

    }
}
