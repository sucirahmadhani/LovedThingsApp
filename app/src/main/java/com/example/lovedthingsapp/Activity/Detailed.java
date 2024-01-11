package com.example.lovedthingsapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class Detailed extends AppCompatActivity {

    FirebaseFirestore firestore;
    Button add_cart, buy_now;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        add_cart = findViewById(R.id.add_cart);
        buy_now = findViewById(R.id.buy_now);
        firestore = FirebaseFirestore.getInstance();

        Intent intent = getIntent();

        TextView namaTextView = findViewById(R.id.namaProduk);
        TextView kategoriTextView = findViewById(R.id.kategori);
        TextView ukuranTextView = findViewById(R.id.ukuran);
        TextView hargaTextView = findViewById(R.id.harga);
        TextView deskripsiTextView = findViewById(R.id.deskripsi);
        ImageView imageView = findViewById(R.id.foto);

        if (intent != null) {
            intent.getStringExtra("produkID");
            String namaProduk = intent.getStringExtra("namaProduk");
            String kategoriProduk = intent.getStringExtra("kategoriProduk");
            String ukuranProduk = intent.getStringExtra("ukuranProduk");
            String hargaProduk = intent.getStringExtra("hargaProduk");
            String deskripsiProduk = intent.getStringExtra("deskripsiProduk");
            String fotoProduk = intent.getStringExtra("fotoProduk");

            if (fotoProduk != null) {
                Picasso.get().load(fotoProduk).into(imageView);
            }

            if (namaProduk != null) {
                namaTextView.setText(namaProduk);
            }
            if (kategoriProduk != null) {
                kategoriTextView.setText(kategoriProduk);
            }
            if (ukuranProduk != null) {
                ukuranTextView.setText(ukuranProduk);
            }
            if (hargaProduk != null) {
                hargaTextView.setText(hargaProduk);
            }
            if (deskripsiProduk != null) {
                deskripsiTextView.setText(deskripsiProduk);
            }
        }

        add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartIntent = new Intent(Detailed.this, Cart.class);

                String namaProduk = intent.getStringExtra("namaProduk");
                String kategoriProduk = intent.getStringExtra("kategoriProduk");
                String ukuranProduk = intent.getStringExtra("ukuranProduk");
                String hargaProduk = intent.getStringExtra("hargaProduk");
                String deskripsiProduk = intent.getStringExtra("deskripsiProduk");
                String fotoProduk = intent.getStringExtra("fotoProduk");
                String produkID = intent.getStringExtra("produkID");

                addProductToCart(produkID, namaProduk, kategoriProduk, ukuranProduk, hargaProduk, deskripsiProduk, fotoProduk);

                cartIntent.putExtra("namaProduk", namaProduk);
                cartIntent.putExtra("kategoriProduk", kategoriProduk);
                cartIntent.putExtra("ukuranProduk", ukuranProduk);
                cartIntent.putExtra("hargaProduk", hargaProduk);
                cartIntent.putExtra("deskripsiProduk", deskripsiProduk);
                cartIntent.putExtra("fotoProduk", fotoProduk);
                cartIntent.putExtra("produkID", produkID);

                startActivity(cartIntent);
            }
        });

    }
    private void addProductToCart(String produkID, String namaProduk, String kategoriProduk, String ukuranProduk, String hargaProduk, String deskripsiProduk, String fotoProduk) {
        DocumentReference productDocument = firestore.collection("Product").document(produkID);

        productDocument.update("addToCart", "yes")
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
    public void back(View view){
        finish();
    }
}