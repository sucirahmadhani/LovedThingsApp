package com.example.lovedthingsapp.Activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class EditProduct extends AppCompatActivity {

    Button saveButton;
    ImageView imageView;
    TextView kategori;
    EditText namaEditText, ukuranEditText, hargaEditText, deskripsiEditText;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        progressBar = findViewById(R.id.progressBar);

        Intent intent = getIntent();

        namaEditText = findViewById(R.id.nama);
        ukuranEditText = findViewById(R.id.ukuran);
        hargaEditText = findViewById(R.id.harga);
        deskripsiEditText = findViewById(R.id.deskripsi);
        imageView = findViewById(R.id.foto);
        kategori = findViewById(R.id.kategori);
        saveButton = findViewById(R.id.update_button);


        if (intent != null) {
            String namaProduk = intent.getStringExtra("namaProduk");
            String kategoriProduk = intent.getStringExtra("kategoriProduk");
            String ukuranProduk = intent.getStringExtra("ukuranProduk");
            String hargaProduk = intent.getStringExtra("hargaProduk");
            String deskripsiProduk = intent.getStringExtra("deskripsiProduk");
            String fotoProduk = intent.getStringExtra("fotoProduk");
            String produkID = intent.getStringExtra("produkID");

            namaEditText.setText(namaProduk);
            ukuranEditText.setText(ukuranProduk);
            hargaEditText.setText(hargaProduk);
            deskripsiEditText.setText(deskripsiProduk);

            if (fotoProduk != null) {
                Picasso.get().load(fotoProduk).into(imageView);
            }

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    progressBar.setVisibility(View.VISIBLE);

                    String editedNamaProduk = namaEditText.getText().toString();
                    String editedUkuranProduk = ukuranEditText.getText().toString();
                    String editedHargaProduk = hargaEditText.getText().toString();
                    String editedDeskripsiProduk = deskripsiEditText.getText().toString();

                    Map<String, Object> map = new HashMap<>();
                    map.put("namaProduk", editedNamaProduk);
                    map.put("ukuranProduk", editedUkuranProduk);
                    map.put("hargaProduk", editedHargaProduk);
                    map.put("deskripsiProduk", editedDeskripsiProduk);


                    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                    if (produkID != null) {
                        firestore.collection("Product").document(produkID)
                                .update(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(EditProduct.this, "Produk berhasil diperbarui", Toast.LENGTH_SHORT).show();
                                        showNotification("Data Tersimpan", "Produk berhasil diperbarui");

                                        Intent detailIntent = new Intent(EditProduct.this, DetailProduct.class);
                                        detailIntent.putExtra("produkID", produkID);
                                        detailIntent.putExtra("namaProduk", editedNamaProduk);
                                        detailIntent.putExtra("kategoriProduk", kategoriProduk);
                                        detailIntent.putExtra("ukuranProduk", editedUkuranProduk);
                                        detailIntent.putExtra("hargaProduk", editedHargaProduk);
                                        detailIntent.putExtra("deskripsiProduk", editedDeskripsiProduk);
                                        detailIntent.putExtra("fotoProduk", fotoProduk);
                                        startActivity(detailIntent);
                                        finish();
                                    }


                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(EditProduct.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        Toast.makeText(EditProduct.this, "ProductID is Null", Toast.LENGTH_SHORT).show();
                    }
                }
            });


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