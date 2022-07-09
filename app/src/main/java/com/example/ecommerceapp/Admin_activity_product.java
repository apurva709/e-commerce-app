    package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommerceapp.ViewHolder.CartViewHolder;
import com.example.ecommerceapp.model.Cart_Model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

    public class Admin_activity_product extends AppCompatActivity {
private RecyclerView productList;
RecyclerView.LayoutManager layoutManager;
private DatabaseReference cartref;
private String uid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product);
        uid=getIntent().getStringExtra("id");
        productList=findViewById(R.id.r3);
        productList.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        productList.setLayoutManager(layoutManager);
        cartref= FirebaseDatabase.getInstance().getReference().child("Cart").child("Admin")
                .child(uid).child("Products");
    }

        @Override
        protected void onStart() {
            super.onStart();
            FirebaseRecyclerOptions<Cart_Model>options=new
                    FirebaseRecyclerOptions.Builder<Cart_Model>()
                    .setQuery(cartref, Cart_Model.class)
                    .build();
            FirebaseRecyclerAdapter<Cart_Model, CartViewHolder> adapter=new FirebaseRecyclerAdapter<Cart_Model, CartViewHolder>(options) {
                @Override
                protected void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i, @NonNull Cart_Model cart_model) {
                    cartViewHolder.ViewProductQuantity.setText("Quantity: "+cart_model.getQuantity());
                    cartViewHolder.viewProductName.setText("Name: "+cart_model.getPname());
                    cartViewHolder.viewProductPrice.setText("Price: "+cart_model.getPrice());
                }

                @NonNull
                @Override
                public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);
                    CartViewHolder holder=new CartViewHolder(view);
                    return holder;
                }
            };
            productList.setAdapter(adapter);
            adapter.startListening();
        }
    }