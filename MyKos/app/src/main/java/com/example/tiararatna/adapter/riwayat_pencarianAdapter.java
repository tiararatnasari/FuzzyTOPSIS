package com.example.tiararatna.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiararatna.modul.identitas_kos;
import com.example.tiararatna.modul.pencari_kos;
import com.example.tiararatna.modul.riwayat_pencarian;
import com.example.tiararatna.mykos.R;
import com.example.tiararatna.mykos.cari_detail_infokosActivity;
import com.example.tiararatna.mykos.riwayat_detail_infokosActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiara Ratna on 5/16/2016.
 */
public class riwayat_pencarianAdapter extends ArrayAdapter {
    String json_string, json_identitas, json_fasilitas, json_kec, json_kel, id_pemilik, JSON_STRING;
    List list= new ArrayList();
    int getId;
    pencari_kos pencariKos;
    public riwayat_pencarianAdapter(Context context, int resource, pencari_kos pencariKos){
        super(context,resource);
        this.pencariKos = pencariKos;
    }
    public void add(riwayat_pencarian object){
        super.add(object);
        list.add(object);
    }
    @Override
    public int getCount(){return list.size();}
    @Override
    public riwayat_pencarian getItem(int position){
        riwayat_pencarian riwayatPencarian= (riwayat_pencarian) list.get(position);
        return riwayatPencarian;}
    public riwayat_pencarian getRiwayatPencarian(){
        Log.d("getId2", ""+getId);
        riwayat_pencarian riwayatPencarian = (riwayat_pencarian) list.get(getId);
        return riwayatPencarian;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        View row;
        row=convertView;
        final riwayat_pencarianHolder riwayatPencarianHolder;
        if(row==null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.activity_riwayat_daftarkos_row,parent,false);
            riwayatPencarianHolder = new riwayat_pencarianHolder();
            riwayatPencarianHolder.tx_nama_kos = (TextView) row.findViewById(R.id.row_riwayat_nama);
            riwayatPencarianHolder.tx_alamat_kos = (TextView) row.findViewById(R.id.row_riwayat_alamat);
            row.setTag(riwayatPencarianHolder);
        }
        else{
            riwayatPencarianHolder = (riwayat_pencarianHolder)row.getTag();

        }
        riwayat_pencarian riwayatPencarian = (riwayat_pencarian) this.getItem(position);
        riwayatPencarianHolder.tx_nama_kos.setText(riwayatPencarian.getNama_kos());
        riwayatPencarianHolder.tx_alamat_kos.setText(riwayatPencarian.getAlamat_kos());
        riwayatPencarianHolder.tx_nama_kos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                riwayatPencarianHolder.tx_nama_kos.setBackgroundColor(android.R.color.holo_orange_light);
                Toast.makeText(getContext(), "row ke-"+position, Toast.LENGTH_SHORT).show();
                getId = position;
                riwayat_pencarian riwayatPencarian = getItem(getId);
                Intent myIntent = new Intent(v.getContext(), riwayat_detail_infokosActivity.class);
                myIntent.putExtra("riwayat_pencarian", riwayatPencarian);
                myIntent.putExtra("pencari", pencariKos);
                v.getContext().startActivity(myIntent);
            }
        });
        return row;
    }
    static class riwayat_pencarianHolder{
        TextView tx_nama_kos, tx_alamat_kos;
    }
}