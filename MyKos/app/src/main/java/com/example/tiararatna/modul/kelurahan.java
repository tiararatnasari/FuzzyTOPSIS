package com.example.tiararatna.modul;

import java.io.Serializable;

/**
 * Created by Tiara Ratna on 5/16/2016.
 */
public class kelurahan implements Serializable{
    private String id_kelurahan, id_kecamatan, nama_kelurahan;

    public String getId_kelurahan(){
        return id_kelurahan;
    }
    public void setId_kelurahan(String id_kelurahan){
        this.id_kelurahan=id_kelurahan;
    }
    public String getId_kecamatan(){
        return id_kecamatan;
    }
    public void setId_kecamatan(String id_kecamatan){
        this.id_kecamatan=id_kecamatan;
    }
    public String getNama_kelurahan(){
        return nama_kelurahan;
    }
    public void setNama_kelurahan(String nama_kelurahan){
        this.nama_kelurahan=nama_kelurahan;
    }

}
