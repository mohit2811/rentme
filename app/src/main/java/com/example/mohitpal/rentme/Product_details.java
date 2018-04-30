package com.example.mohitpal.rentme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mohitpal.rentme.data_model.createaccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Product_details extends AppCompatActivity {
TextView name,des, location,price,quantity;
  String  sname,sdes, slocation,sprice,squantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        name = findViewById(R.id.Name_prod);
        price = findViewById(R.id.price_prod);
        des =findViewById(R.id.prod_description);
        location = findViewById(R.id.prod_Location);
        quantity = findViewById(R.id.prod_quantity);
        name.setText(getIntent().getStringExtra("productname"));
        price.setText(getIntent().getStringExtra("productprice")+"  "+getIntent().getStringExtra("producttype"));
       des.setText(getIntent().getStringExtra("productdescription"));
                quantity.setText(getIntent().getStringExtra("productquantity"));
                location.setText(getIntent().getStringExtra("productloc"));
        Button book = findViewById(R.id.book);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Product_details.this, Home.class));
            }
        });
    }
}
