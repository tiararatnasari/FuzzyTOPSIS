package com.example.tiararatna.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tiararatna.modul.pencari_kos;
import com.example.tiararatna.mykos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiara Ratna on 5/16/2016.
 */
public class pencari_kosAdapter extends ArrayAdapter{
    List list= new ArrayList();
    public pencari_kosAdapter(Context context, int resource){
        super(context,resource);
    }
    public void add(pencari_kos object){
        super.add(object);
        list.add(object);
    }
    @Override
    public int getCount(){
        return list.size();
    }
    @Override
    public Object getItem(int position){
        return list.get(position);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row;
        row=convertView;
        pencari_kosHolder pencarikosHolder;
        if(row==null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_layout,parent,false);
            pencarikosHolder = new pencari_kosHolder();
            pencarikosHolder.tx_id_pencari = (TextView) row.findViewById(R.id.f_id_pencari);
            pencarikosHolder.tx_nama_pencari = (TextView) row.findViewById(R.id.f_nama_pencari);
            pencarikosHolder.tx_univ = (TextView) row.findViewById(R.id.f_univ);
            pencarikosHolder.tx_fak = (TextView) row.findViewById(R.id.f_fak);
            pencarikosHolder.tx_prodi = (TextView) row.findViewById(R.id.f_prodi);
            pencarikosHolder.tx_username_pencari = (TextView) row.findViewById(R.id.f_username_pencari);
            pencarikosHolder.tx_password_pencari = (TextView) row.findViewById(R.id.f_password_pencari);
            row.setTag(pencarikosHolder);
        }
        else{
            pencarikosHolder = (pencari_kosHolder)row.getTag();
        }
        pencari_kos pencarikos = (pencari_kos) this.getItem(position);
        pencarikosHolder.tx_id_pencari.setText(pencarikos.getId_pencari());
        pencarikosHolder.tx_nama_pencari.setText(pencarikos.getNama_pencari());
        pencarikosHolder.tx_univ.setText(pencarikos.getNama_universitas());
        pencarikosHolder.tx_fak.setText(pencarikos.getNama_fakultas());
        pencarikosHolder.tx_prodi.setText(pencarikos.getNama_prodi());
        pencarikosHolder.tx_username_pencari.setText(pencarikos.getUsername_pencari());
        pencarikosHolder.tx_password_pencari.setText(pencarikos.getPassword_pencari());
        return row;
    }
    static class pencari_kosHolder{
        TextView tx_id_pencari, tx_nama_pencari, tx_telp_pencari, tx_alamat_pencari, tx_univ, tx_fak, tx_prodi, tx_username_pencari, tx_password_pencari;
    }
}
