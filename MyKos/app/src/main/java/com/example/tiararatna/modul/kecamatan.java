package com.example.tiararatna.modul;

import java.io.Serializable;

/**
 * Created by Tiara Ratna on 5/16/2016.
 */
public class kecamatan implements Serializable{
    private String id_kecamatan, nama_kecamatan;

    public String getId_kecamatan(){
        return id_kecamatan;
    }
    public void setId_kecamatan(String id_kecamatan){
        this.id_kecamatan=id_kecamatan;
    }
    public String getNama_kecamatan(){
        return nama_kecamatan;
    }
    public void setNama_kecamatan(String nama_kecamatan){
        this.nama_kecamatan=nama_kecamatan;
    }

}
