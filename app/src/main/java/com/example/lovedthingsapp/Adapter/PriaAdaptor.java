package com.example.lovedthingsapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lovedthingsapp.Category.AksesorisPria;
import com.example.lovedthingsapp.Category.AtasanPria;
import com.example.lovedthingsapp.Category.BawahanPria;
import com.example.lovedthingsapp.Category.SepatuPria;
import com.example.lovedthingsapp.Category.TasPria;
import com.example.lovedthingsapp.Model.PriaModel;
import com.example.lovedthingsapp.R;

import java.util.List;

public class PriaAdaptor extends RecyclerView.Adapter<PriaAdaptor.ViewHolder> {

    private Context context;
    private List<PriaModel> list;

    public PriaAdaptor(Context context, List<PriaModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pria,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.prImg);
        holder.prName.setText(list.get(position).getNama());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;

                switch (list.get(position).getJenisItem()) {
                    case "Bawahan Pria":
                        intent = new Intent(context, BawahanPria.class);
                        break;
                    case "Atasan Pria":
                        intent = new Intent(context, AtasanPria.class);
                        break;
                    case "Sepatu Pria":
                        intent = new Intent(context, SepatuPria.class);
                        break;
                    case "Aksesoris Pria":
                        intent = new Intent(context, AksesorisPria.class);
                        break;
                    case "Tas Pria":
                        intent = new Intent(context, TasPria.class);
                        break;
                    default:
                        // Tambahkan handling untuk jenis item lain jika diperlukan
                        return;
                }

                // Menambahkan data tambahan ke Intent
                intent.putExtra("jenisItem", list.get(position));

                // Memulai activity baru
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView prImg;
        TextView prName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            prImg = itemView.findViewById(R.id.priaPic);
            prName = itemView.findViewById(R.id.priaName);
        }
    }
}