package com.example.lovedthingsapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;


public class DetailProduct extends AppCompatActivity {


    Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        delete = findViewById(R.id.deleteProduk);

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

        Button editButton = findViewById(R.id.editProduk);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(DetailProduct.this, EditProduct.class);

                String namaProduk = intent.getStringExtra("namaProduk");
                String kategoriProduk = intent.getStringExtra("kategoriProduk");
                String ukuranProduk = intent.getStringExtra("ukuranProduk");
                String hargaProduk = intent.getStringExtra("hargaProduk");
                String deskripsiProduk = intent.getStringExtra("deskripsiProduk");
                String fotoProduk = intent.getStringExtra("fotoProduk");
                String produkID = intent.getStringExtra("produkID");

                editIntent.putExtra("namaProduk", namaProduk);
                editIntent.putExtra("kategoriProduk", kategoriProduk);
                editIntent.putExtra("ukuranProduk", ukuranProduk);
                editIntent.putExtra("hargaProduk", hargaProduk);
                editIntent.putExtra("deskripsiProduk", deskripsiProduk);
                editIntent.putExtra("fotoProduk", fotoProduk);
                editIntent.putExtra("produkID", produkID);

                startActivity(editIntent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();

                if (intent != null) {
                    String produkID = intent.getStringExtra("produkID");
                    if (produkID != null) {
                        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                        firestore.collection("Product").document(produkID)
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(DetailProduct.this, "Produk berhasil dihapus", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(DetailProduct.this, Sell.class));
                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(DetailProduct.this, "Gagal menghapus produk: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        Toast.makeText(DetailProduct.this, "ProdukID is Null", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    public void back(View view){
        startActivity(new Intent(DetailProduct.this, Sell.class));
    }

}