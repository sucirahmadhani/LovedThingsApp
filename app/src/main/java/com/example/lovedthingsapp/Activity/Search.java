package com.example.lovedthingsapp.Activity;

import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.lovedthingsapp.Adapter.ProductAdapter;
import com.example.lovedthingsapp.Model.Product;
import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    ArrayList<Product> recycleViewHolder;
    RecyclerView recyclerViewSemuaProduk;
    Product productModel;
    ProductAdapter productAdapter;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    SearchView searchView;
    TextView textNoResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recycleViewHolder = new ArrayList<>();
        recyclerViewSemuaProduk = findViewById(R.id.recyclerViewSemuaProduk);
        textNoResults = findViewById(R.id.textNoResults);
        searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        GetProduct();

    }

    private void GetProduct() {
        firestore.collection("Product")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()){
                            ArrayList<DocumentSnapshot> list = (ArrayList<DocumentSnapshot>) queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d:list){
                                productModel=d.toObject(Product.class);
                                recycleViewHolder.add(productModel);
                            }

                            productAdapter = new ProductAdapter(recycleViewHolder, Search.this);
                            recyclerViewSemuaProduk.setHasFixedSize(true);
                            recyclerViewSemuaProduk.setAdapter(productAdapter);

                            GridLayoutManager layoutManager = new GridLayoutManager(Search.this, 2);
                            recyclerViewSemuaProduk.setLayoutManager(layoutManager);
                            productAdapter.notifyDataSetChanged();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }
    private void performSearch(String query){
        recycleViewHolder.clear();

        firestore.collection("Product")
                .whereEqualTo("namaProduk", query)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()){
                            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                Product productModel = documentSnapshot.toObject(Product.class);
                                recycleViewHolder.add(productModel);
                            }
                            textNoResults.setVisibility(View.GONE);
                            productAdapter.notifyDataSetChanged();

                        } else {
                            recycleViewHolder.clear();
                            textNoResults.setVisibility(View.VISIBLE);
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }




}