package com.example.mohitpal.rentme;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mohitpal.rentme.data_model.Product_detail;
import com.example.mohitpal.rentme.data_model.createaccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class RentFragment extends Fragment {
    String sdescription="no Description", sname, sloc, sprice, squantity, srentType;
    EditText  description,name, loc, price, quantity;
    RadioGroup renttype;
    Button submit;
    ImageView productimage;
    public RentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v =inflater.inflate(R.layout.fragment_rent, container, false);
        name=v.findViewById(R.id.product_name);
        productimage =v.findViewById(R.id.productimage);
        description =v.findViewById(R.id.product_description);
        productimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog d=new camera(getActivity());
                d.show();
            }
        });
        loc=v.findViewById(R.id.product_loc);
        price=v.findViewById(R.id.price);
        quantity=v.findViewById(R.id.product_quantity);
        renttype=v.findViewById(R.id.rent);
        submit=v.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sname = name.getText().toString();
                sdescription = description.getText().toString();
                sloc = loc.getText().toString();
                srentType =((RadioButton)v.findViewById(renttype.getCheckedRadioButtonId())).getText().toString();
                sprice = price.getText().toString();
                squantity = quantity.getText().toString();
                if (sname.length() >= 3 ) {

                } else {
                    name.setError("Enter name");
                    return;
                }
                if (srentType.length() >= 3 ) {

                } else {
                    name.setError("Select rent type");
                    return;
                }
                if (squantity.length() >= 1 ) {

                } else {
                    name.setError("Enter Quantity");
                    return;
                }
                if (price.length() >= 2) {

                } else {
                    Toast.makeText(getActivity(), "Enter price", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (sloc.length() >= 3) {

                } else {
                    Toast.makeText(getActivity(), "Enter Location", Toast.LENGTH_SHORT).show();
                    return;
                }

                final ProgressDialog progress_bar = new ProgressDialog(getActivity() );
                progress_bar.setTitle("please wait");
                progress_bar.setMessage("Adding product");
                progress_bar.show();

                final FirebaseAuth f_auth = FirebaseAuth.getInstance();



                        Product_detail data = new Product_detail(sname, sloc,  sprice, squantity, srentType , sdescription);
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                            String email = f_auth.getCurrentUser().getEmail().replace(".", "");
                            database.getReference().child("product").child(email).child(sname+sloc).setValue(data);
                            Toast.makeText(getActivity(), "done", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getActivity(), Home.class);
                            startActivity(i);
            }
        });
        return v;
    }


}
