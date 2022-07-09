package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rey.material.widget.Button;
import com.rey.material.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity
{
    private ImageView splendor, activa;
    private ImageView luna, passion;
    private ImageView rickshaw;

private Button logout,checkorder;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

    logout=(Button) findViewById(R.id.adminlogout);
    checkorder=(Button)findViewById(R.id.checkorder);

    logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(AdminCategoryActivity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    });
    checkorder.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(AdminCategoryActivity.this,AdminOrders.class);

            startActivity(intent);

        }
    });

        splendor = (ImageView) findViewById(R.id.splendor);
        activa = (ImageView) findViewById(R.id.activa);

        luna = (ImageView) findViewById(R.id.luna);
        passion= (ImageView) findViewById(R.id.passion);

        rickshaw= (ImageView) findViewById(R.id.ricksaw);


        splendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "Splendor");
                startActivity(intent);
            }
        });



        activa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "Activa");
                startActivity(intent);
            }
        });




        luna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "Luna");
                startActivity(intent);
            }
        });


        passion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "Passion");
                startActivity(intent);
            }
        });






        rickshaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "Ricksaw");
                startActivity(intent);
            }
        });



    }
}