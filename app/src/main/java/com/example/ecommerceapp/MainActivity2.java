package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.ecommerceapp.model.Users;
import com.example.ecommerceapp.prevalent.prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

public class MainActivity2 extends AppCompatActivity {
    private com.rey.material.widget.Button guest, loginButton;
    private ProgressDialog loadingBar;
    private String parentDbName="Users";
    private DataSnapshot dataSnapshot;
    private DatabaseReference d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_main2);
        guest = (com.rey.material.widget.Button) findViewById(R.id.main2_guest_btn);
        loginButton = (com.rey.material.widget.Button) findViewById(R.id.main2_login_btn);
        loadingBar = new ProgressDialog(this);

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Users usersData = dataSnapshot.child("Users").child("0000000000").getValue(Users.class);
            //    String ann=d.child("Users").child("0000000000");
               // prevalent.CurrentOnlineUser = ann;
                Intent intent=new Intent(MainActivity2.this,categoryselect.class);
                startActivity(intent);
                {

                }
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}