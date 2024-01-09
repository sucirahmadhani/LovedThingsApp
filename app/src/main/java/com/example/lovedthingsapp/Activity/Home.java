package com.example.lovedthingsapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lovedthingsapp.Adapter.PriaAdaptor;
import com.example.lovedthingsapp.Adapter.WanitaAdaptor;
import com.example.lovedthingsapp.Domain.PriaDomain;
import com.example.lovedthingsapp.Domain.WanitaDomain;
import com.example.lovedthingsapp.R;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerViewWanita();
        recyclerViewPria();
    }
    public void penjualan(View view){

        startActivity(new Intent(Home.this, Sell.class));
    }

    public void searchHome(View view){
        startActivity(new Intent(Home.this, Search.class));
    }

    private void recyclerViewWanita() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList=findViewById(R.id.recyclerViewWanita);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<WanitaDomain> wanita=new ArrayList<>();
        wanita.add(new WanitaDomain("Atasan", "baju1"));
        wanita.add(new WanitaDomain("Bawahan", "bawahan"));
        wanita.add(new WanitaDomain("Sepatu/Sandal", "sepatu2"));
        wanita.add(new WanitaDomain("Tas", "Tas"));

        adapter=new WanitaAdaptor(wanita);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void recyclerViewPria() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList=findViewById(R.id.recyclerViewPria);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<PriaDomain> pria=new ArrayList<>();
        pria.add(new PriaDomain("Atasan", "shirt1"));
        pria.add(new PriaDomain("Bawahan", "celana"));
        pria.add(new PriaDomain("Sepatu/Sandal", "sepatu"));
        pria.add(new PriaDomain("Tas", "bag1"));

        adapter=new PriaAdaptor(pria);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}