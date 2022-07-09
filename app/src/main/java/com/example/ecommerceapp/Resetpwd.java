package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerceapp.model.Users;
import com.example.ecommerceapp.prevalent.prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Resetpwd extends AppCompatActivity {
    TextView phone;
    EditText password, conifrmpassword;
    Button cnfm;
    String ph;
    CheckBox chkbk;

        private DatabaseReference dbref= FirebaseDatabase.
            getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpwd);

        chkbk=(CheckBox) findViewById(R.id.chkbk);
        password = (EditText) findViewById(R.id.newpass);
        conifrmpassword = (EditText) findViewById(R.id.newcnfpass);
        cnfm = (Button) findViewById(R.id.rst);

        phone=(TextView)findViewById(R.id.phonereset);
        phone.setText(String.format(getIntent().getStringExtra("mobiles")));
    ph=phone.toString();

    cnfm.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            resetpwd();
        }
    });

    }

    public void resetpwd() {
        String pwd = password.getText().toString();
        String cnfpwd = conifrmpassword.getText().toString();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "password is empty", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(cnfpwd)){
            Toast.makeText(this, "confirm password is empty", Toast.LENGTH_SHORT).show();
        }
        else if(!pwd.equals(cnfpwd)){
            Toast.makeText(this, "password and confirm password did not match", Toast.LENGTH_SHORT).show();
        }
         else{
            reset();
            }

    }

    public void reset() {
        String cnfpwd = conifrmpassword.getText().toString();
            dbref.child("Users").child("8780973400")
                .child("password").setValue(cnfpwd);
        Toast.makeText(this, "Your password is successfully reset", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(Resetpwd.this,LoginActivity.class);
        startActivity(intent);
    }
}