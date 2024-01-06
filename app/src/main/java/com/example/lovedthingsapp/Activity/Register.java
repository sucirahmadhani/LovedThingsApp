package com.example.lovedthingsapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText nama, email, nohp, password, repassword;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        nohp = findViewById(R.id.nohp);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);

    }


    public void signup(View view) {

        String namauser = nama.getText().toString();
        String emailuser = email.getText().toString();
        String nohpuser = nohp.getText().toString();
        String passworduser = password.getText().toString();
        String repassworduser = repassword.getText().toString();

        if (TextUtils.isEmpty(namauser)) {
            Toast.makeText(this, "Masukkan Nama!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(emailuser)) {
            Toast.makeText(this, "Masukkan Email!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(nohpuser)) {
            Toast.makeText(this, "Masukkan No.Handphone!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(passworduser)) {
            Toast.makeText(this, "Masukkan Password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (passworduser.length() < 6){
            Toast.makeText(this, "Password terlalu singkat, masukkan minimal 6 karakter", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(repassworduser)) {
            Toast.makeText(this, "Ulangi Password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!passworduser.equals(repassworduser)) {
            Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(emailuser,passworduser)
                .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(Register.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this, Login.class));
                        }else {
                            Toast.makeText(Register.this, "Registrasi Gagal"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}