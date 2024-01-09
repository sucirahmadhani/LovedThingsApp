package com.example.lovedthingsapp.Category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lovedthingsapp.Adapter.AksesorisWanitaAdapter;
import com.example.lovedthingsapp.Model.Product;
import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AksesorisWanita extends AppCompatActivity {

    ArrayList<Product> recycleViewHolder;
    RecyclerView recyclerViewAksesorisWanita;
    AksesorisWanitaAdapter aksesorisWanitaAdapter;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aksesoris_wanita);

        recycleViewHolder = new ArrayList<>();
        recyclerViewAksesorisWanita = findViewById(R.id.recyclerViewAksesrosisWanita);

        GetProduct("Aksesoris Wanita");
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

                            aksesorisWanitaAdapter = new AksesorisWanitaAdapter(recycleViewHolder, AksesorisWanita.this);
                            recyclerViewAksesorisWanita.setHasFixedSize(true);
                            recyclerViewAksesorisWanita.setAdapter(aksesorisWanitaAdapter);

                            GridLayoutManager layoutManager = new GridLayoutManager(AksesorisWanita.this, 2);
                            recyclerViewAksesorisWanita.setLayoutManager(layoutManager);

                            aksesorisWanitaAdapter.notifyDataSetChanged();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

}