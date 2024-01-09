package com.example.lovedthingsapp.Model;

public class CartModel {
    String currentDate;
    String currentTime;
    String hargaProduk;
    String namaProduk;
    String ukuranProduk;
    String img_url;

    public CartModel() {
    }

    public CartModel(String currentDate, String currentTime, String hargaProduk, String namaProduk, String ukuranProduk, String img_url) {
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.hargaProduk = hargaProduk;
        this.namaProduk = namaProduk;
        this.ukuranProduk = ukuranProduk;
        this.img_url = img_url;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getHargaProduk() {
        return hargaProduk;
    }

    public void setHargaProduk(String hargaProduk) {
        this.hargaProduk = hargaProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getUkuranProduk() {
        return ukuranProduk;
    }

    public void setUkuranProduk(String ukuranProduk) {
        this.ukuranProduk = ukuranProduk;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

}
