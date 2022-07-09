package com.example.ecommerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class Otpenter extends AppCompatActivity {
    TextView number;
    Button otpbutton,bypass;
    ImageView otpimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpenter);
        number=findViewById(R.id.inputmob);

        otpbutton=findViewById(R.id.buttongetotp);
        bypass=findViewById(R.id.bypasso);
        otpimg=findViewById(R.id.otpimage);
        ProgressBar progressbar=findViewById(R.id.progressbar);
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
                Intent intent=new Intent(Otpenter.this,categoryselect.class);
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

                         PhoneAuthProvider.getInstance().verifyPhoneNumber("+91"+number.getText().toString(),60, TimeUnit.SECONDS,Otpenter.this,
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
                                         Toast.makeText(Otpenter.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                     }

                                     @Override
                                     public void onCodeSent(@NonNull String otp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                         super.onCodeSent(otp, forceResendingToken);
                                         progressbar.setVisibility(View.GONE);
                                         otpbutton.setVisibility(View.VISIBLE);
                                         Intent intent = new Intent(getApplicationContext(), Verification.class);
                                         intent.putExtra("mobile", number.getText().toString());
                                         intent.putExtra("otp",otp);
                                         startActivity(intent);
                                                                              }
                                 });

                     } else {
                         Toast.makeText(Otpenter.this, "enter correct number", Toast.LENGTH_SHORT).show();
                     }
                 }else{
                         Toast.makeText(Otpenter.this, "Enter the number", Toast.LENGTH_SHORT).show();
                     }
                 }


         });
    }
}