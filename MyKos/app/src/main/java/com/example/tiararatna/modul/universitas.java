package com.example.tiararatna.modul;

import java.io.Serializable;

/**
 * Created by Tiara Ratna on 5/16/2016.
 */
public class universitas implements Serializable{
    private String id_universitas, nama_universitas;

    public String getId_universitas(){      return id_universitas;}
    public String getNama_universitas(){    return nama_universitas;}
    public void setId_universitas(String id_universitas){       this.id_universitas=id_universitas;}
    public void setNama_universitas(String nama_universitas){   this.nama_universitas=nama_universitas;}
}
