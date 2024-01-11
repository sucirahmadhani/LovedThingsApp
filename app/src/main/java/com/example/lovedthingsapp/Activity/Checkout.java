package com.example.lovedthingsapp.Activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class Checkout extends AppCompatActivity {

    Button checkout;
    private static final int MAP_REQUEST_CODE = 123;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        checkout = findViewById(R.id.btn_checkout);
        EditText alamatEditText = findViewById(R.id.alamat);
        firestore = FirebaseFirestore.getInstance();

        Intent intent = getIntent();

        TextView namaTextView = findViewById(R.id.nama);
        TextView ukuranTextView = findViewById(R.id.ukuran);
        TextView hargaTextView = findViewById(R.id.harga);
        ImageView imageView = findViewById(R.id.foto);

        if (intent != null) {
            intent.getStringExtra("produkID");
            String namaProduk = intent.getStringExtra("namaProduk");
            String ukuranProduk = intent.getStringExtra("ukuranProduk");
            String hargaProduk = intent.getStringExtra("hargaProduk");
            String fotoProduk = intent.getStringExtra("fotoProduk");

            if (fotoProduk != null) {
                Picasso.get().load(fotoProduk).into(imageView);
            }

            if (namaProduk != null) {
                namaTextView.setText(namaProduk);
            }

            if (ukuranProduk != null) {
                ukuranTextView.setText(ukuranProduk);
            }
            if (hargaProduk != null) {
                hargaTextView.setText(hargaProduk);
            }
        }


        alamatEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Memulai Activity Maps
                Intent intent = new Intent(Checkout.this, Maps.class);
                startActivityForResult(intent, MAP_REQUEST_CODE);
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProductFromDatabase(intent.getStringExtra("produkID"));
                showNotification("Pembelian Berhasil!", "Terimakasih telah berbelanja.");
                startActivity(new Intent(Checkout.this, MainActivity2.class));
            }

            private void deleteProductFromDatabase(String produkID) {
                firestore.collection("Product").document(produkID)
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(Task<Void> task) {
                                if (task.isSuccessful()) {

                                } else {
                                    Toast.makeText(Checkout.this, "Gagal", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MAP_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String selectedAddress = data.getStringExtra("selectedAddress");

            EditText alamatEditText = findViewById(R.id.alamat);
            alamatEditText.setText(selectedAddress);
        }
    }
    private void showNotification(String title, String content) {
        int notificationId = 1;
        String channelId = "my_channel_01";
        CharSequence channelName = "My Channel";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            channel.setDescription("Deskripsi Channel");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500});
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(notificationId, builder.build());
    }
}