package com.example.tiararatna.modul;

import java.io.Serializable;

/**
 * Created by Tiara Ratna on 5/16/2016.
 */
public class pemilik_kos implements Serializable{
    private String id_pemilik, nama_pemilik, telp_pemilik, alamat_pemilik, username_pemilik, password_pemilik;

    public String getId_pemilik() {
        return id_pemilik;
    }

    public void setId_pemilik(String id_pemilik) {
        this.id_pemilik = id_pemilik;
    }

    public String getNama_pemilik() {
        return nama_pemilik;
    }

    public void setNama_pemilik(String nama_pemilik) {
        this.nama_pemilik = nama_pemilik;
    }

    public String getTelp_pemilik() {
        return telp_pemilik;
    }

    public void setTelp_pemilik(String telp_pemilik) {
        this.telp_pemilik = telp_pemilik;
    }

    public String getUsername_pemilik() {
        return username_pemilik;
    }

    public void setUsername_pemilik(String username_pemilik) {
        this.username_pemilik = username_pemilik;
    }

    public String getPassword_pemilik() {
        return password_pemilik;
    }

    public void setPassword_pemilik(String password_pemilik) {
        this.password_pemilik = password_pemilik;
    }
}
