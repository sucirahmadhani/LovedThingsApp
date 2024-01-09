package com.example.lovedthingsapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lovedthingsapp.R;
import com.squareup.picasso.Picasso;

public class Detailed extends AppCompatActivity {

    Button add_cart, buy_now;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        add_cart = findViewById(R.id.add_cart);
        buy_now = findViewById(R.id.buy_now);

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

    }
    public void back(View view){
        finish();
    }
}