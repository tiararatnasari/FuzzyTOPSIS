package com.example.tiararatna.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiararatna.modul.identitas_kos;
import com.example.tiararatna.modul.pencari_kos;
import com.example.tiararatna.mykos.R;
import com.example.tiararatna.mykos.cari_detail_infokosActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiara Ratna on 5/16/2016.
 */
public class identitasKos_cariAdapter extends ArrayAdapter {
    String json_string, json_identitas, json_fasilitas, json_kec, json_kel, id_pemilik, JSON_STRING;
    List list= new ArrayList();
    int getId;
    pencari_kos pencariKos;
    public identitasKos_cariAdapter(Context context, int resource, pencari_kos pencariKos){
        super(context,resource);
        this.pencariKos = pencariKos;
    }
    public void add(identitas_kos object){
        super.add(object);
        list.add(object);
    }
    @Override
    public int getCount(){return list.size();}
    @Override
    public identitas_kos getItem(int position){
        identitas_kos identitasKos= (identitas_kos) list.get(position);
        return identitasKos;}
    @Override
    public long getItemId(int position) {return position;}
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        View row;
        row=convertView;
        final identitas_kosHolder identitaskosHolder;
        if(row==null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.activity_riwayat_daftarkos_row,parent,false);
            identitaskosHolder = new identitas_kosHolder();
            identitaskosHolder.tx_nama_kos = (TextView) row.findViewById(R.id.row_riwayat_nama);
            identitaskosHolder.tx_alamat_kos = (TextView) row.findViewById(R.id.row_riwayat_alamat);
            row.setTag(identitaskosHolder);
        }
        else{
            identitaskosHolder = (identitas_kosHolder)row.getTag();
        }
        identitas_kos identitaskos = (identitas_kos) this.getItem(position);
        identitaskosHolder.tx_nama_kos.setText(identitaskos.getNama_kos());
        identitaskosHolder.tx_alamat_kos.setText(identitaskos.getAlamat_kos());
        identitaskosHolder.tx_nama_kos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                identitaskosHolder.tx_nama_kos.setBackgroundColor(R.color.colorKuning);
                Toast.makeText(getContext(), "row ke-"+position, Toast.LENGTH_SHORT).show();
                getId = position;
                identitas_kos identitasKos = getItem(getId);
                Intent myIntent = new Intent(v.getContext(), cari_detail_infokosActivity.class);
                myIntent.putExtra("identitas_kos", identitasKos);
                myIntent.putExtra("pencari", pencariKos);
                v.getContext().startActivity(myIntent);
            }
        });
        return row;
    }

    static class identitas_kosHolder{
        TextView tx_nama_kos, tx_alamat_kos;
    }
}
