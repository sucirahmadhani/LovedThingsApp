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

public class Login extends AppCompatActivity {

    EditText email, password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void login(View view) {

        String emailuser = email.getText().toString();
        String passworduser = password.getText().toString();

        if (TextUtils.isEmpty(emailuser)) {
            Toast.makeText(this, "Masukkan Email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passworduser)) {
            Toast.makeText(this, "Masukkan Password!", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(emailuser, passworduser)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this, MainActivity2.class);
                            startActivity(intent);


                        } else {
                            Toast.makeText(Login.this, "Error:" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}