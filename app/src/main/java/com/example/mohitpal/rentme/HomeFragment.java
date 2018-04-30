 package com.example.mohitpal.rentme;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mohitpal.rentme.data_model.Product_detail;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    ArrayList<Product_detail> Product_list;
    RecyclerView product_recycler;
    public HomeFragment() {
        // Required empty public constructor
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.home_fragment, container, false);
        Product_list = new ArrayList();
        product_recycler = v.findViewById(R.id.home_recycle);
        product_recycler.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false));
        get_product_list();
        return v;
    }

    private void get_product_list() {
    FirebaseDatabase data = FirebaseDatabase.getInstance();
    System.out.println("rrrr");
    data.getReference().child("product").addListenerForSingleValueEvent(new ValueEventListener() {


                                                                            @Override
                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                Product_list.clear();


                                                                                for (DataSnapshot data : dataSnapshot.getChildren()) {

                                                                                    for(DataSnapshot data2 : data.getChildren()) {
                                                                                        Product_detail details = data2.getValue(Product_detail.class);
                                                                                        System.out.println("rrrrrr");
                                                                                        Product_list.add(details);

                                                                                        Adapter adapter = new Adapter();

                                                                                        product_recycler.setAdapter(adapter);
                                                                                    }
                                                                                }
                                                                            }

                                                                            @Override
                                                                            public void onCancelled(DatabaseError databaseError) {

                                                                            }



                                                                        }
    );
}
public class view_holder extends RecyclerView.ViewHolder{

    TextView product_price,product_name;
    LinearLayout product_lay;
    ImageView product_img;
    public view_holder(View itemView) {
        super(itemView);

        product_name = itemView.findViewById(R.id.product_name);
        product_lay = itemView.findViewById(R.id.product_lay);
        product_price = itemView.findViewById(R.id.product_price);
        product_img = itemView.findViewById(R.id.product_img);
    }
}

public class Adapter extends RecyclerView.Adapter<view_holder>
{

    @Override
    public view_holder onCreateViewHolder(ViewGroup parent, int viewType) {

        view_holder v = new view_holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_cell,parent , false ));

        return v ;
    }

    @Override
    public void onBindViewHolder(view_holder holder, int position) {


        final Product_detail data=Product_list.get(position);
        holder.product_name.setText(data.name);
        holder.product_price.setText(data.price);
        holder.product_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productprice=data.price;
                String productquantity=data.quantity;
                String producttype=data.rentType;
                String productname=data.name;
                String productloc=data.loc;
                String productdescription=data.description;

                Intent i=new Intent(getActivity(),Product_details.class);
                i.putExtra("productname",productname);
                i.putExtra("productprice",productprice);
                i.putExtra("productloc",productloc);
                i.putExtra("productquantity",productquantity);
                i.putExtra("producttype",producttype);
                i.putExtra("productdescription",productdescription);
                startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Product_list.size();
    }
}
}
