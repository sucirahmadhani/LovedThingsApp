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
import com.example.lovedthingsapp.Category.AksesorisWanita;
import com.example.lovedthingsapp.Category.AtasanWanita;
import com.example.lovedthingsapp.Category.BawahanWanita;
import com.example.lovedthingsapp.Category.SepatuWanita;
import com.example.lovedthingsapp.Category.TasWanita;
import com.example.lovedthingsapp.Model.WanitaModel;
import com.example.lovedthingsapp.R;

import java.util.List;

public class WanitaAdaptor extends RecyclerView.Adapter<WanitaAdaptor.ViewHolder> {

    private Context context;
    private List<WanitaModel> list;

    public WanitaAdaptor(Context context, List<WanitaModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wanita,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.waImg);
        holder.waName.setText(list.get(position).getNama());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BawahanWanita.class);
                intent.putExtra("bawahan wanita",list.get(position));
                context.startActivity(intent);



                switch (list.get(position).getJenisItem()) {
                    case "Bawahan Wanita":
                        intent = new Intent(context, BawahanWanita.class);
                        break;
                    case "Atasan Wanita":
                        intent = new Intent(context, AtasanWanita.class);
                        break;
                    case "Sepatu Wanita":
                        intent = new Intent(context, SepatuWanita.class);
                        break;
                    case "Tas Wanita":
                        intent = new Intent(context, TasWanita.class);
                        break;
                    case "Aksesoris Wanita":
                        intent = new Intent(context, AksesorisWanita.class);
                        break;
                    default:
                        // Tambahkan handling untuk jenis item lain jika diperlukan
                        return;
                }


                intent.putExtra("jenisItem", list.get(position));


                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView waImg;
        TextView waName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            waImg = itemView.findViewById(R.id.imageViewWanita);
            waName = itemView.findViewById(R.id.textViewWanita);
        }
    }
}
