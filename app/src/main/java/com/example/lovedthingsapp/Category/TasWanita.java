package com.example.lovedthingsapp.Category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lovedthingsapp.Adapter.TasWanitaAdapter;
import com.example.lovedthingsapp.Model.Product;
import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class TasWanita extends AppCompatActivity {

    ArrayList<Product> recycleViewHolder;
    RecyclerView recyclerViewTasWanita;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    TasWanitaAdapter tasWanitaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tas_wanita);

        recycleViewHolder = new ArrayList<>();
        recyclerViewTasWanita = findViewById(R.id.recyclerViewTasWanita);

        GetProduct("Tas Wanita");
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

                            tasWanitaAdapter = new TasWanitaAdapter(recycleViewHolder, TasWanita.this);
                            recyclerViewTasWanita.setHasFixedSize(true);
                            recyclerViewTasWanita.setAdapter(tasWanitaAdapter);

                            GridLayoutManager layoutManager = new GridLayoutManager(TasWanita.this, 2);
                            recyclerViewTasWanita.setLayoutManager(layoutManager);

                            tasWanitaAdapter.notifyDataSetChanged();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}