package com.example.lovedthingsapp.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lovedthingsapp.Activity.Maps;
import com.example.lovedthingsapp.Activity.Search;
import com.example.lovedthingsapp.Activity.Sell;
import com.example.lovedthingsapp.Adapter.PriaAdaptor;
import com.example.lovedthingsapp.Adapter.WanitaAdaptor;
import com.example.lovedthingsapp.Model.PriaModel;
import com.example.lovedthingsapp.Model.WanitaModel;
import com.example.lovedthingsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ProgressDialog progressDialog;
    RecyclerView priaRecyclerview,wanitaRecyclerview;

    PriaAdaptor priaAdaptor;
    List<PriaModel> priaModelList;

    WanitaAdaptor wanitaAdaptor;
    List<WanitaModel> wanitaModelList;

    FirebaseFirestore firestore;

    Button btnJual, btnBeli, showMap;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        progressDialog = new ProgressDialog(getActivity());
        priaRecyclerview = root.findViewById(R.id.recyclerViewPr);
        wanitaRecyclerview = root.findViewById(R.id.recyclerViewWa);
        btnBeli = root.findViewById(R.id.btn_beli);
        btnJual = root.findViewById(R.id.btn_jual);
        showMap = root.findViewById(R.id.showMap);

        firestore = FirebaseFirestore.getInstance();

        progressDialog.setTitle("Selamat Datang di Loved Things App");
        progressDialog.setMessage("mohong menunggu...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        priaRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        priaModelList = new ArrayList<>();
        priaAdaptor = new PriaAdaptor(getContext(), priaModelList);
        priaRecyclerview.setAdapter(priaAdaptor);

        firestore.collection("Pria")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PriaModel priaModel = document.toObject(PriaModel.class);
                                priaModelList.add(priaModel);
                                priaAdaptor.notifyDataSetChanged();
                                progressDialog.dismiss();
                            }
                        } else {
                            Log.e("Firestore", "Error getting documents.", task.getException());
                        }
                    }
                });

        //Wanita
        wanitaRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        wanitaModelList = new ArrayList<>();
        wanitaAdaptor = new WanitaAdaptor(getContext(), wanitaModelList);
        wanitaRecyclerview.setAdapter(wanitaAdaptor);


        firestore.collection("Wanita")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                WanitaModel wanitaModel = document.toObject(WanitaModel.class);
                                wanitaModelList.add(wanitaModel);
                                wanitaAdaptor.notifyDataSetChanged();
                            }
                        } else {
                            Log.e("Firestore", "Error getting documents.", task.getException());
                        }
                    }
                });

        btnJual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sell.class);
                startActivity(intent);
            }
        });

        btnBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Search.class);
                startActivity(intent);
            }
        });

        showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Maps.class);
                startActivity(intent);
            }
        });

        return root;
    }

}


