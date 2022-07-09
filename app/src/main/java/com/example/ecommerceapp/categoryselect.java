package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

public class categoryselect extends AppCompatActivity {
private ImageView ricksaw,passion,activa,luna,splendor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryselect);
        ricksaw=(ImageView) findViewById(R.id.ricksawuser);
        passion=(ImageView) findViewById(R.id.passion_user);
        activa=(ImageView) findViewById(R.id.activa_user);
        luna=(ImageView) findViewById(R.id.luna_user);
        splendor=(ImageView) findViewById(R.id.splendor_user);

        ricksaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(categoryselect.this,HomeActivity.class);
                intent.putExtra("category","Ricksaw ");
                startActivity(intent);
            }
        });
        passion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(categoryselect.this,HomeActivity.class);
                intent.putExtra("category","Passion");
                startActivity(intent);
            }
        });
        splendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(categoryselect.this,HomeActivity.class);
                intent.putExtra("category","Splendor");
                startActivity(intent);
            }
        });
        luna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(categoryselect.this,HomeActivity.class);
                intent.putExtra("category","Luna");
                startActivity(intent);
            }
        });
        activa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(categoryselect.this,HomeActivity.class);
                intent.putExtra("category","Activa");
                startActivity(intent);
            }
        });
    }
}