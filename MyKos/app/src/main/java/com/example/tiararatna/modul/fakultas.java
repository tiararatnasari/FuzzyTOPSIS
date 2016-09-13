package com.example.tiararatna.modul;

import java.io.Serializable;

/**
 * Created by Tiara Ratna on 5/16/2016.
 */
public class fakultas implements Serializable {
    private String id_fakultas, id_universitas, nama_fakultas;

    public String getId_fakultas(){     return id_fakultas;}
    public String getId_universitas(){     return id_universitas;}
    public String getNama_fakultas(){   return nama_fakultas;}
    public void setId_fakultas(String id_fakultas){     this.id_fakultas=id_fakultas;}
    public void setId_universitas(String id_universitas){     this.id_universitas=id_universitas;}
    public void setNama_fakultas(String nama_fakultas){ this.nama_fakultas=nama_fakultas;}
}
