package com.example.lovedthingsapp.Model;

import java.io.Serializable;

public class PriaModel implements Serializable {

    String img_url;
    String nama;
    String jenisItem;

    public PriaModel() {
    }

    public PriaModel(String img_url, String nama) {
        this.img_url = img_url;
        this.nama = nama;
        this.jenisItem = jenisItem;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisItem() {
        return jenisItem;
    }

    public void setJenisItem(String jenisItem) {
        this.jenisItem = jenisItem;
    }
}