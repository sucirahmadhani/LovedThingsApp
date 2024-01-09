package com.example.lovedthingsapp.Category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lovedthingsapp.Adapter.BawahanPriaAdapter;
import com.example.lovedthingsapp.Model.Product;
import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class BawahanPria extends AppCompatActivity {

    ArrayList<Product> recycleViewHolder;
    RecyclerView recyclerViewBawahanPria;
    BawahanPriaAdapter bawahanPriaAdapter;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bawahan_pria);

        recycleViewHolder = new ArrayList<>();
        recyclerViewBawahanPria = findViewById(R.id.recyclerViewBawahanPria);

        GetProduct("Bawahan Pria");
    }

    private void GetProduct(String category) {
        firestore.collection("Product")
                .whereEqualTo("kategoriProduk", category)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()){
                            recycleViewHolder.clear();

                            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                Product productModel = documentSnapshot.toObject(Product.class);
                                recycleViewHolder.add(productModel);
                            }

                            bawahanPriaAdapter = new BawahanPriaAdapter(recycleViewHolder, BawahanPria.this);
                            recyclerViewBawahanPria.setHasFixedSize(true);
                            recyclerViewBawahanPria.setAdapter(bawahanPriaAdapter);

                            GridLayoutManager layoutManager = new GridLayoutManager(BawahanPria.this, 2);
                            recyclerViewBawahanPria.setLayoutManager(layoutManager);

                            bawahanPriaAdapter.notifyDataSetChanged();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}