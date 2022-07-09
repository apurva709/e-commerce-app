    package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerceapp.model.Users;
import com.example.ecommerceapp.prevalent.prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

    public class Confirmation extends AppCompatActivity {
    private EditText nameEdit,phoneEdit,addressEdit,cityEdit,pincodeEdit;
    private Button cnfmbtn;
    private String totalamount="";
private DatabaseReference a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        totalamount=getIntent().getStringExtra("Total");
        Toast.makeText(this, "Total Price = "+totalamount, Toast.LENGTH_SHORT).show();
        cnfmbtn=(Button)findViewById(R.id.cnfm_btn);
        nameEdit=(EditText)findViewById(R.id.cnfm_name);
        phoneEdit=(EditText)findViewById(R.id.cnfm_phn);
        addressEdit=(EditText)findViewById(R.id.cnfm_address);
        cityEdit=(EditText)findViewById(R.id.cnfm_city);
        pincodeEdit=(EditText)findViewById(R.id.cnfm_pin);

phoneEdit.setText(String.valueOf(prevalent.CurrentOnlineUser.getPhone()));
        cnfmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkingorder();
            }

            private void checkingorder() {
                if(TextUtils.isEmpty(nameEdit.getText().toString())){
                    Toast.makeText(Confirmation.this, "Please provide full name", Toast.LENGTH_SHORT).show();
                }
                else if(phoneEdit.length()<10){
                    Toast.makeText(Confirmation.this, "Enter the correct number", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(phoneEdit.getText().toString())){
                    Toast.makeText(Confirmation.this, "Please provide phone number", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(addressEdit.getText().toString())){
                    Toast.makeText(Confirmation.this, "Please provide your address", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(cityEdit.getText().toString())){
                    Toast.makeText(Confirmation.this, "Please provide City name", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(pincodeEdit.getText().toString())){
                    Toast.makeText(Confirmation.this, "Please provide pincode number", Toast.LENGTH_SHORT).show();

                }
                else if(pincodeEdit.length()<6){
                    Toast.makeText(Confirmation.this, "please provide valid pin", Toast.LENGTH_SHORT).show();
                }
                else{
                    confirm();
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            private void confirm() {
             final  String date,time,a;
                Calendar cal=Calendar.getInstance();
                SimpleDateFormat currentDate =new SimpleDateFormat("dd/MM/YY");
                date=currentDate.format(cal.getTime());
                SimpleDateFormat currentTime=new SimpleDateFormat("HH:MM:SS");
                time=currentTime.format(cal.getTime());
                SimpleDateFormat currentTimes=new SimpleDateFormat("ddMMYYHHMM");
                  a=currentTimes.format(cal.getTime());
                final DatabaseReference order= FirebaseDatabase.getInstance().getReference().child("orders")
                        .child(a);
                HashMap<String,Object>ordermap=new HashMap<>();
                ordermap.put("phone",prevalent.CurrentOnlineUser.getPhone());
                ordermap.put("total",totalamount);
                ordermap.put("name",nameEdit.getText().toString());
                ordermap.put("phone",phoneEdit.getText().toString());
                ordermap.put("address",addressEdit.getText().toString());
                ordermap.put("date",date);
                ordermap.put("city",cityEdit.getText().toString());
                ordermap.put("time",time);
                ordermap.put("State","order not shipped");
                ordermap.put("pincode",pincodeEdit.getText().toString());
                order.updateChildren(ordermap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            FirebaseDatabase.getInstance().getReference().child("Cart").child("User")
                                    .child(prevalent.CurrentOnlineUser.getPhone()).removeValue()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(Confirmation.this, "Order has been sent successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent =new Intent(Confirmation.this,HomeActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                            finish();
                                        }
                                        }
                                    });
                        }
                    }
                });
            }
        });
    }
}