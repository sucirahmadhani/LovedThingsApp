package com.example.lovedthingsapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lovedthingsapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void main_login (View view){
        startActivity(new Intent(MainActivity.this, Login.class));
    }

    public void main_signup(View view){
        startActivity(new Intent(MainActivity.this, Register.class));
    }
}