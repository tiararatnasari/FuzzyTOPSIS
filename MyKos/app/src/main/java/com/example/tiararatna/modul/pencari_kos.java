package com.example.tiararatna.modul;

import java.io.Serializable;

/**
 * Created by Tiara Ratna on 5/16/2016.
 */
public class pencari_kos implements Serializable{
    private String id_pencari, id_prodi, nama_prodi, nama_fakultas, nama_universitas, nama_pencari, telp_pencari, alamat_pencari, jk_pencari, username_pencari, password_pencari;
    private String latitude, longitude;
    public String getId_pencari(){          return id_pencari;}
    public String getId_prodi(){            return id_prodi;}
    public String getNama_prodi(){          return nama_prodi;}
    public String getNama_fakultas(){       return nama_fakultas;}
    public String getNama_universitas(){    return nama_universitas;}
    public String getLatitude(){            return latitude;}
    public String getLongitude(){           return longitude;}
    public String getNama_pencari(){        return nama_pencari;}
    public String getJk_pencari(){          return jk_pencari;}
    public String getUsername_pencari(){    return username_pencari;}
    public String getPassword_pencari(){    return password_pencari;}
    public void setId_pencari(String id_pencari){               this.id_pencari=id_pencari;}
    public void setId_prodi(String id_prodi){                   this.id_prodi=id_prodi;}
    public void setNama_prodi(String nama_prodi){               this.nama_prodi=nama_prodi;}
    public void setNama_fakultas(String nama_fakultas){         this.nama_fakultas=nama_fakultas;}
    public void setNama_universitas(String nama_universitas){   this.nama_universitas=nama_universitas;}
    public void setLatitude(String latitude) {                  this.latitude=latitude;}
    public void setLongitude(String longitude) {                this.longitude=longitude;}
    public void setNama_pencari(String nama_pencari){           this.nama_pencari=nama_pencari;}
    public void setJk_pencari(String jk_pencari){               this.jk_pencari=jk_pencari;}
    public void setUsername_pencari(String username_pencari){   this.username_pencari=username_pencari;}
    public void setPassword_pencari(String password_pencari){   this.password_pencari=password_pencari;}
}
