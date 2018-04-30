package com.example.mohitpal.rentme.data_model;

/**
 * Created by Mohit pal on 4/27/2018.
 */

public class Product_detail {
    public String name, loc, price, quantity, rentType,description;
public  Product_detail(){}
public Product_detail(String name, String loc, String price, String quantity, String rentType,String description){
    this.name=name;
    this.loc=loc;
    this.description=description;
    this.price=price;
    this.quantity=quantity;
    this.rentType=rentType;
}
}
