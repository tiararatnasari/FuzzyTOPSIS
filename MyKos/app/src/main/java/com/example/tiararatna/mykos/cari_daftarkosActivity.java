package com.example.tiararatna.mykos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.tiararatna.adapter.identitasKos_cariAdapter;
import com.example.tiararatna.modul.identitas_kos;
import com.example.tiararatna.modul.pencari_kos;
import com.example.tiararatna.mykos.cari_input2Activity;
import java.util.ArrayList;

/**
 * Created by Tiara Ratna on 4/19/2016.
 */
public class cari_daftarkosActivity extends AppCompatActivity {
    ListView listView;
    static identitasKos_cariAdapter identitasKosAdapter;
    ArrayList<identitas_kos> daftar ;
    identitas_kos identitasKos;
    pencari_kos pencariKos;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_daftarkos);
        pencariKos= (pencari_kos) getIntent().getExtras().get("pencari");
        Log.d("id_pencari",pencariKos.getId_pencari());

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        listView = (ListView)findViewById(R.id.daftarkos_listview);
        identitasKosAdapter = new identitasKos_cariAdapter(this, R.layout.activity_cari_daftarkos_row, pencariKos);
        listView.setAdapter(identitasKosAdapter);
        ArrayList<identitas_kos> object = (ArrayList<identitas_kos>) args.getSerializable("ARRAYLIST");
        for (int i = 0; i < object.size(); i++) {
            identitasKosAdapter.add(object.get(i));
            Log.d("getid",""+identitasKosAdapter.getItemId(i));
            Log.d("i",""+i);
        }
        Log.d("daftar", object.get(0).getId_kos());
        onBackpressed();
    }
    public void onBackpressed(){

    }
}
