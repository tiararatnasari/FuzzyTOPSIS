package com.example.tiararatna.modul;

import java.io.Serializable;

/**
 * Created by Tiara Ratna on 5/16/2016.
 */
public class program_studi implements Serializable{
    private String id_prodi, id_fakultas, nama_prodi;

    public String getId_prodi(){    return id_prodi;}
    public String getId_fakultas(){ return id_fakultas;}
    public String getNama_prodi(){  return nama_prodi;}
    public void setId_prodi(String id_prodi){       this.id_prodi=id_prodi;}
    public void setId_fakultas(String id_fakultas){ this.id_fakultas=id_fakultas;}
    public void setNama_prodi(String nama_prodi){   this.nama_prodi=nama_prodi;}
}
