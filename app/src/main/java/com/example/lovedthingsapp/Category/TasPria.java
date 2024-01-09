package com.example.lovedthingsapp.Category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lovedthingsapp.Adapter.TasPriaAdapter;
import com.example.lovedthingsapp.Model.Product;
import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class TasPria extends AppCompatActivity {

    ArrayList<Product> recycleViewHolder;
    RecyclerView recyclerViewTasPria;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    TasPriaAdapter tasPriaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tas_pria);

        recycleViewHolder = new ArrayList<>();
        recyclerViewTasPria = findViewById(R.id.recyclerViewTasPria);

        GetProduct("Tas Pria");
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

                            tasPriaAdapter = new TasPriaAdapter(recycleViewHolder, TasPria.this);
                            recyclerViewTasPria.setHasFixedSize(true);
                            recyclerViewTasPria.setAdapter(tasPriaAdapter);

                            GridLayoutManager layoutManager = new GridLayoutManager(TasPria.this, 2);
                            recyclerViewTasPria.setLayoutManager(layoutManager);

                            tasPriaAdapter.notifyDataSetChanged();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}