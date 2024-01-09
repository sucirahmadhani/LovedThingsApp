package com.example.lovedthingsapp.Category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lovedthingsapp.Adapter.BawahanWanitaAdapter;
import com.example.lovedthingsapp.Model.Product;
import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class BawahanWanita extends AppCompatActivity {

    ArrayList<Product> recycleViewHolder;
    RecyclerView recyclerViewBawahanWanita;
    BawahanWanitaAdapter bawahanWanitaAdapter;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bawahan_wanita);

        recycleViewHolder = new ArrayList<>();
        recyclerViewBawahanWanita = findViewById(R.id.recyclerViewBawahanWanita);

        GetProduct("Bawahan Wanita");
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

                            bawahanWanitaAdapter = new BawahanWanitaAdapter(recycleViewHolder, BawahanWanita.this);
                            recyclerViewBawahanWanita.setHasFixedSize(true);
                            recyclerViewBawahanWanita.setAdapter(bawahanWanitaAdapter);

                            GridLayoutManager layoutManager = new GridLayoutManager(BawahanWanita.this, 2);
                            recyclerViewBawahanWanita.setLayoutManager(layoutManager);

                            bawahanWanitaAdapter.notifyDataSetChanged();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}