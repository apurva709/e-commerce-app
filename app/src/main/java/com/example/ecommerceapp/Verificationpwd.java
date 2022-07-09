package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Verificationpwd extends AppCompatActivity {
Bundle a;
    EditText ip11,ip21,ip31,ip41,ip51,ip61;
    String getotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificationpwd);
        final Button verifyotp=findViewById(R.id.verifyotp1);
        ip11=findViewById(R.id.ip11);
        ip21=findViewById(R.id.ip21);
        ip31=findViewById(R.id.ip31);
        ip41=findViewById(R.id.ip41);
        ip51=findViewById(R.id.ip51);
        ip61=findViewById(R.id.ip61);

        TextView textView=findViewById(R.id.verifymob1);
        textView.setText(String.format("+91-%s",getIntent().getStringExtra("mobile")));
       // a=getIntent().getExtras();
        getotp=getIntent().getStringExtra("otp");
        final ProgressBar progressBarv=findViewById(R.id.progressbarv1);
        verifyotp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

                if(!ip11.getText().toString().trim().isEmpty() && !ip21.getText().toString().isEmpty()){
                    String enterotp=ip11.getText().toString()+
                            ip21.getText().toString()+
                            ip31.getText().toString()+
                            ip41.getText().toString()+
                            ip51.getText().toString()+
                            ip61.getText().toString();
                    if(getotp!=null){
                        progressBarv.setVisibility(View.VISIBLE);
                        verifyotp.setVisibility(View.INVISIBLE);
                        PhoneAuthCredential phonecred= PhoneAuthProvider.getCredential(getotp,enterotp);
                        FirebaseAuth.getInstance().signInWithCredential(phonecred)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBarv.setVisibility(View.GONE);
                                        verifyotp.setVisibility(View.VISIBLE);

                                        if(task.isSuccessful()) {
                                            Intent intent = new Intent(getApplicationContext(), Resetpwd.class);
                                            intent.putExtra("mobiles",textView.getText());
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        }
                                        else {
                                            Toast.makeText(Verificationpwd.this, "Enter correct otp", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }
                }
                else {
                    Toast.makeText(Verificationpwd.this, "please enter all", Toast.LENGTH_SHORT).show();
                }}
        });
        next();
        findViewById(R.id.textresendotp1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber("+91"+getIntent().getStringExtra("mobile"),60, TimeUnit.SECONDS,Verificationpwd.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(Verificationpwd.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(newotp, forceResendingToken);
                                getotp=newotp;
                                Toast.makeText(Verificationpwd.this, "new otp is sent", Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });
    }

    private void next() {
        ip11.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    ip21.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ip21.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    ip31.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ip31.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    ip41.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ip41.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    ip51.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ip51.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    ip61.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}