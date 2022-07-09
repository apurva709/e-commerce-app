    package com.example.ecommerceapp.model;


public class Cart_Model {
    private  String pid, pname, price, quantity;

    public Cart_Model() {
    }

    public Cart_Model(String pid, String pname, String price, String quantity) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
        this.quantity = quantity;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public  String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public  String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public  String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
