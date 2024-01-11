package com.example.lovedthingsapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lovedthingsapp.Adapter.CartAdapter;
import com.example.lovedthingsapp.Model.Product;
import com.example.lovedthingsapp.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Product> cartProducts;
    CartAdapter cartAdapter;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartProducts = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewCart);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new CartAdapter(cartProducts, this);
        recyclerView.setAdapter(cartAdapter);
        firestore = FirebaseFirestore.getInstance();


        firestore.collection("Product")
                .whereEqualTo("addToCart", "yes")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Product productModel = documentSnapshot.toObject(Product.class);
                        cartProducts.add(productModel);
                    }

                    
                    cartAdapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    // Handle kegagalan query
                    Toast.makeText(Cart.this, "Gagal memuat keranjang belanja", Toast.LENGTH_SHORT).show();
                });
    }

    public void back (View view) { startActivity(new Intent(Cart.this, Search.class));}
}
