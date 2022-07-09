package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.ecommerceapp.model.Products;
import com.example.ecommerceapp.prevalent.prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class productdetail extends AppCompatActivity {
    private Button addcartbtn;
    private ImageView ProductImage;
    private ElegantNumberButton numberButton;
    private TextView productprice,productdescription,productname;
    private String productId="",state="Normal";
    String proname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail);
        productId=getIntent().getStringExtra("pid");
        addcartbtn = (Button)findViewById(R.id.add_to_cart_btn);
        numberButton=(ElegantNumberButton) findViewById(R.id.number_button);
        ProductImage=(ImageView) findViewById(R.id.product_image_d);
        productprice=(TextView) findViewById(R.id.product_price_d);
        productdescription=(TextView) findViewById(R.id.product_description_d);
        productname=(TextView) findViewById(R.id.product_name_d);
        productId =getIntent().getStringExtra("pid");
        getproductdetails(productId);
        addcartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state.equals("Order Placed")||state.equals("Order Shipped")){
                    //Toast.makeText(productdetail.this, "you can purchase more once order is confirmed", Toast.LENGTH_LONG).show();
cart();
                }
                else {
                    cart();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkorder();
    }

    private void cart() {
        String date,time;
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat currentDate =new SimpleDateFormat("dd/MM/YY");
        date=currentDate.format(cal.getTime());
        SimpleDateFormat currentTime=new SimpleDateFormat("HH:MM:SS");
        time=currentTime.format(cal.getTime());
        SimpleDateFormat currentTimes=new SimpleDateFormat("ddMMYYHHMM");

       final DatabaseReference cart=FirebaseDatabase.getInstance().getReference().child("Cart");
        final HashMap<String,Object> Cart=new HashMap<>();
        Cart.put("pname",productname.getText().toString());
        Cart.put("pid",productId);
        Cart.put("price",productprice.getText().toString());
        Cart.put("date",date);
        Cart.put("time",time);
        Cart.put("quantity",numberButton.getNumber());
        proname=productname.getText().toString();
        final String b;
        b=currentTimes.format(cal.getTime());
        cart.child("User").child(prevalent.CurrentOnlineUser.getPhone()).child("Products").child(b).updateChildren(Cart).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                    cart.child("Admin").child(prevalent.CurrentOnlineUser.getPhone()).child("Products").child(b).updateChildren(Cart).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete( @NonNull Task<Void> task) {
                            Toast.makeText(productdetail.this, "Added to cart", Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent(productdetail.this,HomeActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });


    }


    private void getproductdetails(String productId) {
        String detail =getIntent().getStringExtra("cat");
        DatabaseReference productRef= FirebaseDatabase.getInstance().getReference().child("Products").child(detail);
           productRef.child(productId).addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   if(snapshot.exists()){
                       Products products=snapshot.getValue(Products.class);
                       productname.setText(products.getPname());
                       productprice.setText(products.getPrice());
                       productdescription.setText(products.getDescription());
                       Picasso.get().load(products.getImage()).into(ProductImage);          }
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });

    }
    private void checkorder(){

        DatabaseReference orderref;
        orderref=FirebaseDatabase.getInstance().getReference().child("orders").child(prevalent.CurrentOnlineUser.getPhone());
        orderref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String shippingState=snapshot.child("State").getValue().toString();


                    if(shippingState.equals("Shipped")){
                        state="Order shipped";
                    }
                    else if(shippingState.equals("order not shipped")){
                        state="Order Placed";
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}