package com.example.lovedthingsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lovedthingsapp.Activity.Detailed;
import com.example.lovedthingsapp.Model.Product;
import com.example.lovedthingsapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AksesorisPriaAdapter extends RecyclerView.Adapter<AksesorisPriaAdapter.ViewHolder> {

    private final ArrayList<Product> list;
    private final Context context;

    public AksesorisPriaAdapter(ArrayList<Product> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public AksesorisPriaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder, parent, false);
        return new AksesorisPriaAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull AksesorisPriaAdapter.ViewHolder holder, int position) {
        Product productModel = list.get(position);
        holder.nama.setText(productModel.getNamaProduk());
        holder.ukuran.setText(productModel.getUkuranProduk());
        holder.harga.setText(productModel.getHargaProduk());
        Picasso.get().load(productModel.getFotoProduk()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama, ukuran, harga;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.textNama);
            ukuran = itemView.findViewById(R.id.textUkuran);
            harga = itemView.findViewById(R.id.textHarga);
            imageView = itemView.findViewById(R.id.imageViewHolder);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Product clickedProduct = list.get(getAdapterPosition());

                    Intent intent = new Intent(context, Detailed.class);
                    intent.putExtra("produkID", clickedProduct.getProdukID());
                    intent.putExtra("namaProduk", clickedProduct.getNamaProduk());
                    intent.putExtra("kategoriProduk", clickedProduct.getKategoriProduk());
                    intent.putExtra("ukuranProduk", clickedProduct.getUkuranProduk());
                    intent.putExtra("hargaProduk", clickedProduct.getHargaProduk());
                    intent.putExtra("fotoProduk", clickedProduct.getFotoProduk());
                    intent.putExtra("deskripsiProduk", clickedProduct.getDeskripsiProduk());
                    context.startActivity(intent);
                }
            });
        }
    }
}