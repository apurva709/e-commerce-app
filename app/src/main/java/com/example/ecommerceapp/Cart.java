package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerceapp.ViewHolder.CartViewHolder;
import com.example.ecommerceapp.model.Cart_Model;
import com.example.ecommerceapp.prevalent.prevalent;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Cart extends AppCompatActivity {
private RecyclerView recyclerView;
private RecyclerView.LayoutManager layoutManager;
private Button NextProcessBtn;
private TextView txttotal,txtmsg1;
public DatabaseReference a;
private int total=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView=findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        NextProcessBtn=(Button)findViewById(R.id.next_cart);
        txttotal=(TextView)findViewById(R.id.total_cart);
        txtmsg1=(TextView)findViewById(R.id.msg_1);
        txttotal.setText("Total is "+String.valueOf(total));

        NextProcessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(total<1){
                    Toast.makeText(Cart.this, "Your Cart is empty add the products", Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Cart.this,HomeActivity.class);
                    startActivity(i);
                }
                else{
                    Intent intent= new Intent(Cart.this,Confirmation.class);
                intent.putExtra("Total",String.valueOf(total));
                intent.putExtra("phone", (Parcelable) a);
                startActivity(intent);
                finish();}
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkorder();
        final DatabaseReference cartListRef= FirebaseDatabase.
                getInstance().getReference().child("Cart");
        FirebaseRecyclerOptions<Cart_Model>options=
                new FirebaseRecyclerOptions.Builder<Cart_Model>()
                .setQuery(cartListRef.child("User").
                                child(prevalent.CurrentOnlineUser.getPhone()).
                                child("Products"),Cart_Model.class).
                        build();
        FirebaseRecyclerAdapter<Cart_Model, CartViewHolder>adapter
                =new FirebaseRecyclerAdapter<Cart_Model, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int i, @NonNull Cart_Model cart_model) {
                holder.ViewProductQuantity.setText("Quantity: "+cart_model.getQuantity());
                holder.viewProductName.setText("Name: "+cart_model.getPname());
                holder.viewProductPrice.setText("Price: "+cart_model.getPrice());
                    int price=((Integer.valueOf(cart_model.getPrice())))* Integer.valueOf(cart_model.getQuantity());
                    total=total+price;
                   txttotal.setText("Total: "+Integer.toString(total));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CharSequence options[] = new CharSequence[]
                                {
                                        "Edit",
                                        "Delete"
                                };
                        AlertDialog.Builder builder=new AlertDialog.Builder(Cart.this);
                        builder.setTitle("Cart Option:");

                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                if(i==0){
                                    Intent intent=new Intent(Cart.this,productdetail.class);
                                    intent.putExtra("pid",cart_model.getPid());
                                    startActivity(intent);
                                }
                                if(i==1){
                                    cartListRef.child("User")
                                            .child(prevalent.CurrentOnlineUser.getPhone())
                                            .child("Products")
                                            .child(cart_model.getPname())
                                            .removeValue()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(Cart.this, "Item is deleted successfully", Toast.LENGTH_SHORT).show();
                                                Intent intent=new Intent(Cart.this,HomeActivity.class);
                                                startActivity(intent);
                                            }
                                        }
                                    });
                                    cartListRef.child("Admin")
                                            .child(prevalent.CurrentOnlineUser.getPhone())
                                            .child("Products")
                                            .child(cart_model.getPname())
                                            .removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        Toast.makeText(Cart.this, "Item is deleted successfully", Toast.LENGTH_SHORT).show();
                                                        Intent intent=new Intent(Cart.this,HomeActivity.class);
                                                        startActivity(intent);
                                                    }
                                                }
                                            });
                                }
                            }
                        });
                        builder.show();
                    }
                });
            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);
                CartViewHolder holder=new CartViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();


    }

    private void checkorder(){

        DatabaseReference orderref;
        orderref=FirebaseDatabase.getInstance().getReference().child("orders").child(prevalent.CurrentOnlineUser.getPhone());
        orderref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              if(snapshot.exists()){
                  String shippingState=snapshot.child("State").getValue().toString();
                  String userName=snapshot.child("name").getKey().toString();

                  if(shippingState.equals("Shipped")){
                      txttotal.setText("dear"+userName+"\n order has been shipped successfully");
                      txtmsg1.setVisibility(View.VISIBLE);
                      NextProcessBtn.setVisibility(View.GONE);
                      recyclerView.setVisibility(View.GONE);


                  }
               /*   else if(shippingState.equals("order not shipped")){
                      txttotal.setText(" order has not been shipped");
                      NextProcessBtn.setVisibility(View.GONE);
                      recyclerView.setVisibility(View.GONE);
                    //  Toast.makeText(Cart.this, "will be soon arriving", Toast.LENGTH_SHORT).show();
                        txtmsg1.setText("congrats your order has received we will soon verify and dispatch ");
                        txtmsg1.setVisibility(View.VISIBLE);}*/
                  }
              }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}