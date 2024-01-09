package com.example.lovedthingsapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lovedthingsapp.Adapter.ViewAdapter;
import com.example.lovedthingsapp.Fragment.HomeFragment;
import com.example.lovedthingsapp.Model.Product;
import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Sell extends AppCompatActivity {


    ArrayList<Product> recycleViewHolder;
    RecyclerView recyclerViewSell;
    Product productModel;
    ViewAdapter viewAdapter;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        recycleViewHolder = new ArrayList<>();
        recyclerViewSell = findViewById(R.id.recyclerViewSell);

        GetProduct();


    }

    private void GetProduct() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String userId = user.getUid();

            firestore.collection("Product")
                    .whereEqualTo("currentUserID", userId)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            if (!queryDocumentSnapshots.isEmpty()) {
                                recycleViewHolder.clear();

                                for (DocumentSnapshot d : queryDocumentSnapshots.getDocuments()) {
                                    productModel = d.toObject(Product.class);
                                    recycleViewHolder.add(productModel);
                                }

                                viewAdapter = new ViewAdapter(recycleViewHolder, Sell.this);
                                recyclerViewSell.setHasFixedSize(true);
                                recyclerViewSell.setAdapter(viewAdapter);

                                GridLayoutManager layoutManager = new GridLayoutManager(Sell.this, 2);
                                recyclerViewSell.setLayoutManager(layoutManager);
                                viewAdapter.notifyDataSetChanged();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Handle failure
                        }
                    });
        }
    }




    public void add_product(View view){
        startActivity(new Intent(Sell.this, AddProduct.class));
    }
    public void back (View view) { startActivity(new Intent(Sell.this, HomeFragment.class));}

}