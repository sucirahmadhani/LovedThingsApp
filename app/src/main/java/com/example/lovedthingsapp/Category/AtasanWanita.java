package com.example.lovedthingsapp.Category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lovedthingsapp.Adapter.AtasanWanitaAdapter;
import com.example.lovedthingsapp.Model.Product;
import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AtasanWanita extends AppCompatActivity {

    ArrayList<Product> recycleViewHolder;
    RecyclerView recyclerViewAtasanWanita;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    AtasanWanitaAdapter atasanWanitaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atasan_wanita);

        recycleViewHolder = new ArrayList<>();
        recyclerViewAtasanWanita = findViewById(R.id.recyclerViewAtasanWanita);

        GetProduct("Atasan Wanita");
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

                            atasanWanitaAdapter = new AtasanWanitaAdapter(recycleViewHolder, AtasanWanita.this);
                            recyclerViewAtasanWanita.setHasFixedSize(true);
                            recyclerViewAtasanWanita.setAdapter(atasanWanitaAdapter);

                            GridLayoutManager layoutManager = new GridLayoutManager(AtasanWanita.this, 2);
                            recyclerViewAtasanWanita.setLayoutManager(layoutManager);

                            atasanWanitaAdapter.notifyDataSetChanged();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}