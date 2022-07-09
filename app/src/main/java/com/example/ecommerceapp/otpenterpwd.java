package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class otpenterpwd extends AppCompatActivity {

    TextView number;
    Button otpbutton,bypass;
    ImageView otpimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpenterpwd);
        number=findViewById(R.id.inputmob1);
        otpbutton=findViewById(R.id.buttongetotp1);
        bypass=findViewById(R.id.bypasso1);
        otpimg=findViewById(R.id.otpimage1);
        ProgressBar progressbar=findViewById(R.id.progressbar1);
        number.setText(String.format("%s",getIntent().getStringExtra("mobiles")));
        DatabaseReference reference;
        reference= FirebaseDatabase.getInstance().getReference().child("Users");
        otpimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bypass.setVisibility(View.VISIBLE);
            }
        });
        bypass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(otpenterpwd.this,Resetpwd.class);
                startActivity(intent);
            }
        });
        otpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!number.getText().toString().trim().isEmpty()) {
                    if ((number.getText().toString().trim().length() == 10)) {
                        progressbar.setVisibility(View.VISIBLE);
                        otpbutton.setVisibility(View.GONE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber("+91"+number.getText().toString(),60, TimeUnit.SECONDS,otpenterpwd.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        progressbar.setVisibility(View.GONE);
                                        otpbutton.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressbar.setVisibility(View.GONE);
                                        otpbutton.setVisibility(View.VISIBLE);
                                        Toast.makeText(otpenterpwd.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String otp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        super.onCodeSent(otp, forceResendingToken);
                                        progressbar.setVisibility(View.GONE);
                                        otpbutton.setVisibility(View.VISIBLE);
                                        Intent intent = new Intent(getApplicationContext(), Verificationpwd.class);
                                        intent.putExtra("mobile", number.getText().toString());
                                        intent.putExtra("otp",otp);
                                        startActivity(intent);
                                        Intent intent1 = new Intent(getApplicationContext(), Resetpwd.class);
                                        intent1.putExtra("mobiles", number.getText().toString());
                                    }
                                });

                    } else {
                        Toast.makeText(otpenterpwd.this, "enter correct number", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(otpenterpwd.this, "Enter the number", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }
    }