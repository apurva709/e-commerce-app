    package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerceapp.model.Users;
import com.example.ecommerceapp.prevalent.prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class Verification extends AppCompatActivity {
    EditText ip1,ip2,ip3,ip4,ip5,ip6;
    String getotp;
    TextView number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        final Button verifyotp=findViewById(R.id.verifyotp);
        ip1=findViewById(R.id.ip1);
        ip2=findViewById(R.id.ip2);
        ip3=findViewById(R.id.ip3);
        ip4=findViewById(R.id.ip4);
        ip5=findViewById(R.id.ip5);
        ip6=findViewById(R.id.ip6);

        TextView textView=findViewById(R.id.verifymob);
        textView.setText(String.format("+91-%s",getIntent().getStringExtra("mobile")));
        getotp=getIntent().getStringExtra("otp");
        final ProgressBar progressBarv=findViewById(R.id.progressbarv);
        verifyotp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

            if(!ip1.getText().toString().trim().isEmpty() && !ip2.getText().toString().isEmpty()){
               String enterotp=ip1.getText().toString()+
                       ip2.getText().toString()+
                       ip3.getText().toString()+
                       ip4.getText().toString()+
                       ip5.getText().toString()+
                       ip6.getText().toString();
               if(getotp!=null){
                progressBarv.setVisibility(View.VISIBLE);
                verifyotp.setVisibility(View.INVISIBLE);
                   PhoneAuthCredential phonecred=PhoneAuthProvider.getCredential(getotp,enterotp);
                   FirebaseAuth.getInstance().signInWithCredential(phonecred)
                           .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                               @Override
                               public void onComplete(@NonNull Task<AuthResult> task) {
                                   progressBarv.setVisibility(View.GONE);
                                   verifyotp.setVisibility(View.VISIBLE);

                                   if(task.isSuccessful()){
                                       Intent intent =new Intent(getApplicationContext(),categoryselect.class);
                                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                       intent.putExtra("mob",getIntent().getStringExtra("mobile"));
                                       startActivity(intent);
                                   }
                                   else {
                                       Toast.makeText(Verification.this, "Enter correct otp", Toast.LENGTH_SHORT).show();
                                   }
                               }
                           });

               }
            }
            else {
                Toast.makeText(Verification.this, "please enter all", Toast.LENGTH_SHORT).show();
            }}
        });
        next();
        findViewById(R.id.textresendotp).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber("+91"+getIntent().getStringExtra("mobile"),60, TimeUnit.SECONDS,Verification.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(Verification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(newotp, forceResendingToken);
                                getotp=newotp;
                                Toast.makeText(Verification.this, "new otp is sent", Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });
    }

    private void next() {
        ip1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(!s.toString().trim().isEmpty()){
                ip2.requestFocus();
            }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ip2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    ip3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ip3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    ip4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ip4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    ip5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ip5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    ip6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
        
    }
}