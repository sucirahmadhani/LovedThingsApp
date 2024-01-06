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
import com.example.lovedthingsapp.Domain.PriaDomain;
import com.example.lovedthingsapp.R;

import java.util.ArrayList;

public class PriaAdaptor extends RecyclerView.Adapter<PriaAdaptor.ViewHolder> {
    ArrayList<PriaDomain>priaDomains;

    public PriaAdaptor(ArrayList<PriaDomain> priaDomains) {
        this.priaDomains = priaDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.pria,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PriaAdaptor.ViewHolder holder, int position) {
        holder.priaName.setText(priaDomains.get(position).getTitle());
        String picUrl = "";
        switch (position){
            case 0:{
                picUrl="shirt1";
                holder.homeLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background));
                break;
            }
            case 1:{
                picUrl="celana";
                holder.homeLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background));
                break;
            }
            case 2:{
                picUrl="sepatu";
                holder.homeLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background));
                break;
            }
            case 3:{
                picUrl="bag1";
                holder.homeLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background));
                break;
            }
        }
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.priaPic);
    }

    @Override
    public int getItemCount() {
        return priaDomains.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView priaName;
        ImageView priaPic;
        ConstraintLayout homeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            priaName=itemView.findViewById(R.id.priaName);
            priaPic=itemView.findViewById(R.id.priaPic);
            homeLayout=itemView.findViewById(R.id.homeLayout);
        }
    }
}
