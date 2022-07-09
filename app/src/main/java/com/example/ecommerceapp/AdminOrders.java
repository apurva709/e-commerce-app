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

import com.example.ecommerceapp.model.AdminOrder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminOrders extends AppCompatActivity {
    private RecyclerView orderlist;
    private DatabaseReference orderref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_orders);
        orderref= FirebaseDatabase.getInstance().getReference().child("orders");
        orderlist=findViewById(R.id.r1);
        orderlist.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<AdminOrder>options =new FirebaseRecyclerOptions.Builder<AdminOrder>()
                .setQuery(orderref,AdminOrder.class).build();
        FirebaseRecyclerAdapter<AdminOrder,AdminOrdersViewHolder>adapter=new FirebaseRecyclerAdapter<AdminOrder, AdminOrdersViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AdminOrdersViewHolder adminOrdersViewHolder, int i, @NonNull AdminOrder adminOrder) {
                adminOrdersViewHolder.username.setText("Name:"+adminOrder.getName());
                adminOrdersViewHolder.userphone.setText("Phone:"+adminOrder.getPhone());
                adminOrdersViewHolder.useradd.setText("Address:"+adminOrder.getAddress()+" City"+adminOrder.getCity());
                adminOrdersViewHolder.userdate.setText("ordered at:"+adminOrder.getDate()+" "+adminOrder.getTime());
                adminOrdersViewHolder.userTotal.setText("Total Amount:"+adminOrder.getTotal());

                adminOrdersViewHolder.showdetail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id=getRef(i).getKey();
                        Intent intent=new Intent(AdminOrders.this,Admin_activity_product.class);
                        intent.putExtra("id",id);
                        startActivity(intent);
                    }
                });
                adminOrdersViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence options[]=new CharSequence[]{
                                "Yes",
                                "NO"
                        };
                        AlertDialog.Builder builder=new AlertDialog.Builder(AdminOrders.this);
                        builder.setTitle("Shipped");
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               if(which==0){
                                   String id=getRef(i).getKey();
                                   RemoveOrder(id);
                               }
                               else
                               {
                               }
                            }
                        });
                        builder.show();
                    }
                });
            }

            @NonNull
            @Override
            public AdminOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_layout,parent,false);
                return new AdminOrdersViewHolder(view);
            }
        };
        orderlist.setAdapter(adapter);
        adapter.startListening();
    }

    private void RemoveOrder(String id) {
        orderref.child(id).removeValue();
    }

    public static class AdminOrdersViewHolder extends RecyclerView.ViewHolder{


        public TextView username,userphone,userTotal,userdate,useradd;
public Button showdetail;
        public AdminOrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.o_user_name);
            userphone=itemView.findViewById(R.id.O_phone_no);
            userTotal=itemView.findViewById(R.id.o_total);
            useradd=itemView.findViewById(R.id.o_add);
            userdate=itemView.findViewById(R.id.o_date);
            showdetail=itemView.findViewById(R.id.o_showdetail);
        }
    }
}