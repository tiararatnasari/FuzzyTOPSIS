package com.example.tiararatna.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tiararatna.modul.pemilik_kos;
import com.example.tiararatna.mykos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiara Ratna on 5/16/2016.
 */
public class pemilik_kosAdapter extends ArrayAdapter{
    List list= new ArrayList();
    public pemilik_kosAdapter(Context context, int resource){
        super(context,resource);
    }
    public void add(pemilik_kos object){
        super.add(object);
        list.add(object);
    }
    @Override
    public int getCount(){return list.size();}
    @Override
    public Object getItem(int position){return list.get(position);}
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row;
        row=convertView;
        pemilik_kosHolder pemilikkosHolder;
        if(row==null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_layout,parent,false);
            pemilikkosHolder = new pemilik_kosHolder();
            pemilikkosHolder.tx_id_pencari = (TextView) row.findViewById(R.id.f_id_pencari);
            pemilikkosHolder.tx_nama_pencari = (TextView) row.findViewById(R.id.f_nama_pencari);
            pemilikkosHolder.tx_telp_pencari = (TextView) row.findViewById(R.id.f_telp_pencari);
            pemilikkosHolder.tx_username_pencari = (TextView) row.findViewById(R.id.f_username_pencari);
            pemilikkosHolder.tx_password_pencari = (TextView) row.findViewById(R.id.f_password_pencari);
            row.setTag(pemilikkosHolder);
        }
        else{
            pemilikkosHolder = (pemilik_kosHolder)row.getTag();
        }
        pemilik_kos pemilikkos = (pemilik_kos) this.getItem(position);
        pemilikkosHolder.tx_id_pencari.setText(pemilikkos.getId_pemilik());
        pemilikkosHolder.tx_nama_pencari.setText(pemilikkos.getNama_pemilik());
        pemilikkosHolder.tx_telp_pencari.setText(pemilikkos.getTelp_pemilik());
        pemilikkosHolder.tx_username_pencari.setText(pemilikkos.getUsername_pemilik());
        pemilikkosHolder.tx_password_pencari.setText(pemilikkos.getPassword_pemilik());
        return row;
    }
    static class pemilik_kosHolder{
        TextView tx_id_pencari, tx_nama_pencari, tx_telp_pencari, tx_alamat_pencari, tx_username_pencari, tx_password_pencari;
    }
}
