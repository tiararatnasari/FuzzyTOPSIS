package com.example.tiararatna.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiararatna.modul.identitas_kos;
import com.example.tiararatna.mykos.R;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

/**
 * Created by Tiara Ratna on 5/16/2016.
 */
public class identitasKos_kelolaAdapter extends ArrayAdapter {
    String json_string, json_identitas, json_fasilitas, json_kec, json_kel, id_pemilik, JSON_STRING;
    List list= new ArrayList();
    int getId;
    public identitasKos_kelolaAdapter(Context context, int resource){
        super(context,resource);
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
    public identitas_kos getIdentitasKos(){
        Log.d("getId2", ""+getId);
        identitas_kos identitasKos= (identitas_kos) list.get(getId);
        return identitasKos;
    }
    @Override
    public long getItemId(int position) {return position;}
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        View row;
        row=convertView;
        final identitas_kosHolder identitaskosHolder;
        if(row==null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.activity_kelola_infokos_row,parent,false);
            identitaskosHolder = new identitas_kosHolder();
            identitaskosHolder.tx_nama_kos = (TextView) row.findViewById(R.id.row_kelola_nama);
            identitaskosHolder.tx_alamat_kos = (TextView) row.findViewById(R.id.row_kelola_alamat);
            row.setTag(identitaskosHolder);
        }
        else{
            identitaskosHolder = (identitas_kosHolder)row.getTag();

        }
        identitas_kos identitaskos = (identitas_kos) this.getItem(position);
        identitaskosHolder.tx_nama_kos.setText(identitaskos.getNama_kos());
        identitaskosHolder.tx_alamat_kos.setText(identitaskos.getAlamat_kos());
        identitaskosHolder.tx_nama_kos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                identitaskosHolder.tx_nama_kos.setBackgroundColor(android.R.color.holo_orange_light);
                Toast.makeText(getContext(), "row ke-"+position, Toast.LENGTH_SHORT).show();
                Log.d("position", ""+position);
                getId = position;
                Log.d("getId1", ""+getId);
            }
        });
        //list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        //{
        //    @Override
        //    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
        //    {
        //        identitas_kos identitasKos = (identitas_kos) identitaskosHolder.getItem(position);
        //    }
        //});
        return row;
    }
    //private int getItemPositionByAdapterId(final long id) {
    //    for (int i = 0; i < adapter.getCount(); i++) {
    //        if (adapter.getItemId(i) == id)
    //            return i;
    //    }
    //    return -1;
    //
    // }
    public void onItemClick(AdapterView parent, View v, int position, long id){
        final identitas_kosHolder identitaskosHolder = null;
        identitas_kos identitasKos = getItem(position);
    }
    static class identitas_kosHolder{
        TextView tx_nama_kos, tx_alamat_kos;
    }
}