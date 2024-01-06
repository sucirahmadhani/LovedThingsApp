package com.example.lovedthingsapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lovedthingsapp.Domain.WanitaDomain;
import com.example.lovedthingsapp.R;

import java.util.ArrayList;

public class WanitaAdaptor extends RecyclerView.Adapter<WanitaAdaptor.ViewHolder> {
    ArrayList<WanitaDomain>wanitaDomains;

    public WanitaAdaptor(ArrayList<WanitaDomain> wanitaDomains) {
        this.wanitaDomains = wanitaDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.wanita,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull WanitaAdaptor.ViewHolder holder, int position) {
        holder.wanitaName.setText(wanitaDomains.get(position).getTitle());
        String picUrl = "";
        switch (position){
            case 0:{
                picUrl="baju1";
                holder.homeLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background));
                break;
            }
            case 1:{
                picUrl="bawahan";
                holder.homeLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background));
                break;
            }
            case 2:{
                picUrl="sepatu2";
                holder.homeLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background));
                break;
            }
            case 3:{
                picUrl="tas";
                holder.homeLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background));
                break;
            }
        }
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.wanitaPic);
    }

    @Override
    public int getItemCount() {
        return wanitaDomains.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView wanitaName;
        ImageView wanitaPic;
        ConstraintLayout homeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wanitaName=itemView.findViewById(R.id.textViewWanita);
            wanitaPic=itemView.findViewById(R.id.imageViewWanita);
            homeLayout=itemView.findViewById(R.id.homeLayout);
        }
    }
}
