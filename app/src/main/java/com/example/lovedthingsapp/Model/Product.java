package com.example.lovedthingsapp.Model;

public class Product {
    private String NamaProduk, DeskripsiProduk, KategoriProduk, UkuranProduk, HargaProduk, FotoProduk, ProdukID, CurrentUserID, AddToCart;

    public Product (){

        this.AddToCart = "no";
    }

    public Product(String namaProduk, String deskripsiProduk, String kategoriProduk, String ukuranProduk, String hargaProduk, String fotoProduk, String produkID, String currentUserID) {
        NamaProduk = namaProduk;
        DeskripsiProduk = deskripsiProduk;
        KategoriProduk = kategoriProduk;
        UkuranProduk = ukuranProduk;
        HargaProduk = hargaProduk;
        FotoProduk = fotoProduk;
        ProdukID = produkID;
        CurrentUserID = currentUserID;
        AddToCart = "no";
    }

    public String getNamaProduk() {
        return NamaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        NamaProduk = namaProduk;
    }

    public String getDeskripsiProduk() {
        return DeskripsiProduk;
    }

    public void setDeskripsiProduk(String deskripsiProduk) {
        DeskripsiProduk = deskripsiProduk;
    }

    public String getKategoriProduk() {
        return KategoriProduk;
    }

    public void setKategoriProduk(String kategoriProduk) {
        KategoriProduk = kategoriProduk;
    }

    public String getUkuranProduk() {
        return UkuranProduk;
    }

    public void setUkuranProduk(String ukuranProduk) {
        UkuranProduk = ukuranProduk;
    }

    public String getHargaProduk() {
        return HargaProduk;
    }

    public void setHargaProduk(String hargaProduk) {
        HargaProduk = hargaProduk;
    }

    public String getProdukID() {
        return ProdukID;
    }

    public void setProdukID(String produkID) {
        ProdukID = produkID;
    }

    public String getFotoProduk() {
        return FotoProduk;
    }

    public void setFotoProduk(String fotoProduk) {
        FotoProduk = fotoProduk;
    }

    public String getCurrentUserID() {
        return CurrentUserID;
    }

    public void setCurrentUserID(String currentUserID) {
        CurrentUserID = currentUserID;
    }
}

