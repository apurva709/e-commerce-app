package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerceapp.model.Order;
import com.example.ecommerceapp.model.Order;
import com.example.ecommerceapp.prevalent.prevalent;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Orders extends AppCompatActivity {
    private RecyclerView orderlist;
    private DatabaseReference orderref;

    //String mob1=getIntent().getStringExtra("mobileno");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        orderref= FirebaseDatabase.getInstance().getReference().child("orders");
        orderlist=findViewById(R.id.r1);
        orderlist.setLayoutManager(new LinearLayoutManager(this));}
        public TextView username,userphone,userTotal,userdate,useradd;
        public Button showdetail;

       /* public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.o_user_name);
            userphone=itemView.findViewById(R.id.O_phone_no);
            userTotal=itemView.findViewById(R.id.o_total);
            useradd=itemView.findViewById(R.id.o_add);
            userdate=itemView.findViewById(R.id.o_date);
            showdetail=itemView.findViewById(R.id.o_showdetail);
*/


    }
