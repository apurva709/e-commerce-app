package com.example.ecommerceapp.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapp.Interface.ItemClickListner;
import com.example.ecommerceapp.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView viewProductName,viewProductPrice,ViewProductQuantity;
    private  ItemClickListner itemClickListner;
    public Object View;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        viewProductName=itemView.findViewById(R.id.name_cart_item);
        viewProductPrice=itemView.findViewById(R.id.total_cart_item);
        ViewProductQuantity=itemView.findViewById(R.id.quantity_cart_item);

    } 

    @Override
    public void onClick(View view) {
itemClickListner.onClick(view,getAdapterPosition(),false);
    }
    public void setItemClickListner(ItemClickListner itemClickListner){
        this.itemClickListner=itemClickListner;
    }
}
