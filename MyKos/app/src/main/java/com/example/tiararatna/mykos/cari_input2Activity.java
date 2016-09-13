package com.example.tiararatna.mykos;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tiararatna.adapter.DropDownSpinner;
import com.example.tiararatna.fuzzy.Fuzzy;
import com.example.tiararatna.modul.bobot;
import com.example.tiararatna.modul.fuzzyFasilitas_kos;
import com.example.tiararatna.modul.identitas_kos;
import com.example.tiararatna.modul.pencari_kos;
import com.example.tiararatna.modul.tfn_kriteria;
import com.example.tiararatna.topsis.topsis;
import com.example.tiararatna.topsis.pembobotan;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiara Ratna on 4/19/2016.
 */
public class cari_input2Activity extends AppCompatActivity {
    private static Button clear, cari;
    static double dist;
    static int count_w = 0, id = 1, jumlah_pencarian;
    static bobot w = new bobot();
    static List<fuzzyFasilitas_kos> list_fasilitasKos = new ArrayList<>();
    static List<identitas_kos> list_identitasKos = new ArrayList<>();
    static List<tfn_kriteria> bobot = new ArrayList<>();
    public static topsis Topsis;
    static EditText jarak_kos, biaya_kos, jk, jmlh_penghuni, meja_kursi, lemari, tmpt_tidur, km_luar, kipas_angin, ac,
            tv, kulkas, dispenser, internet, ruang_tamu, dapur, mesin_cuci, pembantu, cuci_gosok, cctv_security,
            klm_renang, parkir_motor, parkir_mobil, tmpt_makan, warnet, mall, apotek_dokter, atm_bank, supermarket,
            kendaraan_umum;
    static Button b_jarak_kos, b_biaya_kos, b_jk, b_jmlh_penghuni, b_meja_kursi, b_lemari, b_tmpt_tidur, b_km_luar,
            b_kipas_angin, b_ac, b_tv, b_kulkas, b_dispenser, b_internet, b_ruang_tamu, b_dapur, b_mesin_cuci, b_pembantu,
            b_cuci_gosok, b_cctv_security, b_klm_renang, b_parkir_motor, b_parkir_mobil, b_tmpt_makan, b_warnet, b_mall,
            b_apotek_dokter, b_atm_bank, b_supermarket, b_kendaraan_umum;
    static String Jarak_kos, Biaya_kos, Jk, Jmlh_penghuni, Meja_kursi, Lemari, Tmpt_tidur,
            Km_luar, Kipas_angin, Ac, Tv, Kulkas, Dispenser, Internet, Ruang_tamu, Dapur,
            Mesin_cuci, Pembantu, Cuci_gosok, Cctv_security, Klm_renang, Parkir_motor,
            Parkir_mobil, Tmpt_makan, Warnet, Mall, Apotek_dokter, Atm_bank, Supermarket,
            Kendaraan_umum, Id_prodi, latitude, longitude, jarak;
    String sql_id = "", sql = "", json_fasilitas, json_identitas, json_jarak, json_string, json_prodi;
    pencari_kos pencariKos;
    JSONObject jsonObject, jsonObject_fas;
    JSONArray jsonArray, jsonArray_fas;
    Spinner univ,fak,prodi,jenis_kelamin, jmlh_cari;
    static ArrayAdapter<CharSequence> adapter_univ, adapter_fak, adapter_prodi, adapter_jk, adapter_jmlh_cari;
    int index_univ, index_fak, index_prodi, index_jk, index_jc;
    fuzzyFasilitas_kos fuzzyFasilitasKos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_input2);

        pencariKos = (pencari_kos) getIntent().getExtras().get("pencari");
        Log.d("id_pencari", pencariKos.getId_prodi());

        id = 1;

        jarak_kos = (EditText) findViewById(R.id.f_input_jarak);
        biaya_kos = (EditText) findViewById(R.id.f_input_biaya);
        jk = (EditText) findViewById(R.id.f_input_jk);
        jmlh_penghuni = (EditText) findViewById(R.id.f_input_jmlpenghuni);
        meja_kursi = (EditText) findViewById(R.id.f_input_mejakursi);
        lemari = (EditText) findViewById(R.id.f_input_lemari);
        tmpt_tidur = (EditText) findViewById(R.id.f_input_tmpttidur);
        km_luar = (EditText) findViewById(R.id.f_input_kmluar);
        kipas_angin = (EditText) findViewById(R.id.f_input_kipas);
        ac = (EditText) findViewById(R.id.f_input_ac);
        tv = (EditText) findViewById(R.id.f_input_tv);
        kulkas = (EditText) findViewById(R.id.f_input_kulkas);
        dispenser = (EditText) findViewById(R.id.f_input_dispenser);
        internet = (EditText) findViewById(R.id.f_input_internet);
        ruang_tamu = (EditText) findViewById(R.id.f_input_ruangtamu);
        dapur = (EditText) findViewById(R.id.f_input_dapur);
        mesin_cuci = (EditText) findViewById(R.id.f_input_mesincuci);
        pembantu = (EditText) findViewById(R.id.f_input_pembantu);
        cuci_gosok = (EditText) findViewById(R.id.f_input_cucigosok);
        cctv_security = (EditText) findViewById(R.id.f_input_cctv);
        klm_renang = (EditText) findViewById(R.id.f_input_kolamrenang);
        parkir_motor = (EditText) findViewById(R.id.f_input_parkirmotor);
        parkir_mobil = (EditText) findViewById(R.id.f_input_parkirmobil);
        tmpt_makan = (EditText) findViewById(R.id.f_input_restaurant);
        warnet = (EditText) findViewById(R.id.f_input_warnet);
        mall = (EditText) findViewById(R.id.f_input_mall);
        apotek_dokter = (EditText) findViewById(R.id.f_input_apotek);
        atm_bank = (EditText) findViewById(R.id.f_input_bank);
        supermarket = (EditText) findViewById(R.id.f_input_supermarket);
        kendaraan_umum = (EditText) findViewById(R.id.f_input_kendaraanumum);
        univ = (Spinner) findViewById(R.id.s_input_univ);
        fak = (Spinner) findViewById(R.id.s_input_fak);
        prodi = (Spinner) findViewById(R.id.s_input_prodi);
        jenis_kelamin = (Spinner) findViewById(R.id.s_input_jk);
        jmlh_cari = (Spinner) findViewById(R.id.s_input_jmlh_cari);

        String penggal_univ = pencariKos.getId_prodi().substring(2,3);
        univ.setId((Integer.parseInt(penggal_univ))-1);
        String penggal_fak = pencariKos.getId_pencari().substring(4,5);
        fak.setId((Integer.parseInt(penggal_fak))-1);
        String penggal_prodi = pencariKos.getId_pencari().substring(6,7);
        prodi.setId((Integer.parseInt(penggal_prodi))-1);
        if(pencariKos.getJk_pencari().equalsIgnoreCase("p")) {
            jenis_kelamin.setId(0);
        } else if(pencariKos.getJk_pencari().equalsIgnoreCase("l")) {
            jenis_kelamin.setId(1);
        }

        adapter_univ = ArrayAdapter.createFromResource(this, R.array.univ, android.R.layout.simple_spinner_item);
        adapter_univ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        univ.setAdapter(adapter_univ);
        univ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index_univ = univ.getSelectedItemPosition();
                adapter_fak = DropDownSpinner.fakultas(cari_input2Activity.this, index_univ);
                adapter_fak.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                fak.setAdapter(adapter_fak);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });
        fak.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index_fak = fak.getSelectedItemPosition();
                adapter_prodi = DropDownSpinner.prodi(cari_input2Activity.this, index_univ, index_fak);
                adapter_prodi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                prodi.setAdapter(adapter_prodi);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });
        prodi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                index_prodi = prodi.getSelectedItemPosition();
                Id_prodi = DropDownSpinner.idProdi(index_univ,index_fak,index_prodi);
                Toast.makeText(getBaseContext(), "You have selected item : "+Id_prodi, Toast.LENGTH_SHORT).show();
            }
            public void onNothingSelected(AdapterView<?> arg0) {}
        });
        adapter_jk = ArrayAdapter.createFromResource(this, R.array.jenis_kelamin, android.R.layout.simple_spinner_item);
        adapter_jk.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenis_kelamin.setAdapter(adapter_jk);
        jenis_kelamin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                index_jk = jenis_kelamin.getSelectedItemPosition();
                Toast.makeText(getBaseContext(), "You have selected item : "+index_jk, Toast.LENGTH_SHORT).show();
            }
            public void onNothingSelected(AdapterView<?> arg0) {}
        });
        adapter_jmlh_cari = ArrayAdapter.createFromResource(this, R.array.jumlah_cari, android.R.layout.simple_spinner_item);
        adapter_jmlh_cari.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jmlh_cari.setAdapter(adapter_jmlh_cari);
        jmlh_cari.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                index_jc = jmlh_cari.getSelectedItemPosition();
                Toast.makeText(getBaseContext(), "You have selected item : "+index_jc, Toast.LENGTH_SHORT).show();
            }
            public void onNothingSelected(AdapterView<?> arg0) {}
        });
        onClickButtonListener();
        onClickButtonListenerInput();
        onClickButtonListenerClear();
    }

    public void onClickButtonListener() {
        cari = (Button) findViewById(R.id.b_cari_cari);
        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jarak_kos = jarak_kos.getText().toString();
                Biaya_kos = biaya_kos.getText().toString();
                Jk = jk.getText().toString();
                Jmlh_penghuni = jmlh_penghuni.getText().toString();
                Meja_kursi = meja_kursi.getText().toString();
                Lemari = lemari.getText().toString();
                Tmpt_tidur = tmpt_tidur.getText().toString();
                Km_luar = km_luar.getText().toString();
                Kipas_angin = kipas_angin.getText().toString();
                Ac = ac.getText().toString();
                Tv = tv.getText().toString();
                Kulkas = kulkas.getText().toString();
                Dispenser = dispenser.getText().toString();
                Ruang_tamu = ruang_tamu.getText().toString();
                Internet = internet.getText().toString();
                Dapur = dapur.getText().toString();
                Mesin_cuci = mesin_cuci.getText().toString();
                Pembantu = pembantu.getText().toString();
                Cuci_gosok = cuci_gosok.getText().toString();
                Cctv_security = cctv_security.getText().toString();
                Klm_renang = klm_renang.getText().toString();
                Parkir_motor = parkir_motor.getText().toString();
                Parkir_mobil = parkir_mobil.getText().toString();
                Tmpt_makan = tmpt_makan.getText().toString();
                Warnet = warnet.getText().toString();
                Mall = mall.getText().toString();
                Apotek_dokter = apotek_dokter.getText().toString();
                Atm_bank = atm_bank.getText().toString();
                Supermarket = supermarket.getText().toString();
                Kendaraan_umum = kendaraan_umum.getText().toString();
                if(Jarak_kos.equalsIgnoreCase("")&&Biaya_kos.equalsIgnoreCase("")&&Jk.equalsIgnoreCase("")&&Jmlh_penghuni.equalsIgnoreCase("")&&Meja_kursi.equalsIgnoreCase("")&&Lemari.equalsIgnoreCase("")&&Tmpt_tidur.equalsIgnoreCase("")&&Km_luar.equalsIgnoreCase("")&&Kipas_angin.equalsIgnoreCase("")&&Ac.equalsIgnoreCase("")&&Tv.equalsIgnoreCase("")&&Kulkas.equalsIgnoreCase("")&&Dispenser.equalsIgnoreCase("")&&Ruang_tamu.equalsIgnoreCase("")&&Internet.equalsIgnoreCase("")&&Dapur.equalsIgnoreCase("")&&Mesin_cuci.equalsIgnoreCase("")&&Pembantu.equalsIgnoreCase("")&&Cuci_gosok.equalsIgnoreCase("")&&Cctv_security.equalsIgnoreCase("")&&Klm_renang.equalsIgnoreCase("")&&Parkir_motor.equalsIgnoreCase("")&&Parkir_mobil.equalsIgnoreCase("")&&Tmpt_makan.equalsIgnoreCase("")&&Warnet.equalsIgnoreCase("")&&Mall.equalsIgnoreCase("")&&Apotek_dokter.equalsIgnoreCase("")&&Atm_bank.equalsIgnoreCase("")&&Supermarket.equalsIgnoreCase("")&&Kendaraan_umum.equalsIgnoreCase("")){
                    Toast.makeText(cari_input2Activity.this, "Data Kosong", Toast.LENGTH_LONG).show();
                } else {
                    getData();
                    new Background(cari_input2Activity.this).execute("identitas");
                    new Background(cari_input2Activity.this).execute("fasilitas");
                }
            }
        });
    }

    public void onClickButtonListenerClear() {
        clear = (Button) findViewById(R.id.b_cari_clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jarak_kos.setText("");
                b_jarak_kos.setClickable(true);
                biaya_kos.setText("");
                b_biaya_kos.setClickable(true);
                jk.setText("");
                b_jk.setClickable(true);
                jmlh_penghuni.setText("");
                b_jmlh_penghuni.setClickable(true);
                meja_kursi.setText("");
                b_meja_kursi.setClickable(true);
                lemari.setText("");
                b_lemari.setClickable(true);
                tmpt_tidur.setText("");
                b_tmpt_tidur.setClickable(true);
                km_luar.setText("");
                b_km_luar.setClickable(true);
                kipas_angin.setText("");
                b_kipas_angin.setClickable(true);
                ac.setText("");
                b_ac.setClickable(true);
                tv.setText("");
                b_tv.setClickable(true);
                kulkas.setText("");
                b_kulkas.setClickable(true);
                dispenser.setText("");
                b_dispenser.setClickable(true);
                internet.setText("");
                b_internet.setClickable(true);
                ruang_tamu.setText("");
                b_ruang_tamu.setClickable(true);
                dapur.setText("");
                b_dapur.setClickable(true);
                mesin_cuci.setText("");
                b_mesin_cuci.setClickable(true);
                pembantu.setText("");
                b_pembantu.setClickable(true);
                cuci_gosok.setText("");
                b_cuci_gosok.setClickable(true);
                cctv_security.setText("");
                b_cctv_security.setClickable(true);
                klm_renang.setText("");
                b_klm_renang.setClickable(true);
                parkir_motor.setText("");
                b_parkir_motor.setClickable(true);
                parkir_mobil.setText("");
                b_parkir_mobil.setClickable(true);
                tmpt_makan.setText("");
                b_tmpt_makan.setClickable(true);
                warnet.setText("");
                b_warnet.setClickable(true);
                mall.setText("");
                b_mall.setClickable(true);
                apotek_dokter.setText("");
                b_apotek_dokter.setClickable(true);
                atm_bank.setText("");
                b_atm_bank.setClickable(true);
                supermarket.setText("");
                b_supermarket.setClickable(true);
                kendaraan_umum.setText("");
                b_kendaraan_umum.setClickable(true);

                id = 1;
                count_w = 0;
                sql_id = "";
                sql = "";
                bobot.clear();
                topsis.delete();
            }
        });
    }

    public void onClickButtonListenerInput() {

        b_jarak_kos = (Button) findViewById(R.id.b_input_jarak);
        b_jarak_kos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jarak_kos.setText("" + id);
                id++;
                Log.d("id", "" + id);
                b_jarak_kos.setClickable(false);
            }
        });
        b_biaya_kos = (Button) findViewById(R.id.b_input_biaya);
        b_biaya_kos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biaya_kos.setText("" + id);
                id++;
                Log.d("id", "" + id);
                b_biaya_kos.setClickable(false);
            }
        });
        b_jk = (Button) findViewById(R.id.b_input_jk);
        b_jk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jk.setText("" + id);
                id++;
                Log.d("id", "" + id);
                b_jk.setClickable(false);
            }
        });
        b_jmlh_penghuni = (Button) findViewById(R.id.b_input_jmlpenghuni);
        b_jmlh_penghuni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jmlh_penghuni.setText("" + id);
                id++;
                Log.d("id", "" + id);
                b_jmlh_penghuni.setClickable(false);
            }
        });
        b_meja_kursi = (Button) findViewById(R.id.b_input_mejakursi);
        b_meja_kursi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meja_kursi.setText("" + id);
                id++;
                Log.d("id", "" + id);
                b_meja_kursi.setClickable(false);
            }
        });
        b_lemari = (Button) findViewById(R.id.b_input_lemari);
        b_lemari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lemari.setText("" + id);
                id++;
                Log.d("id", "" + id);
                b_lemari.setClickable(false);
            }
        });
        b_tmpt_tidur = (Button) findViewById(R.id.b_input_tmpttidur);
        b_tmpt_tidur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmpt_tidur.setText("" + id);
                id++;
                Log.d("id", "" + id);
                b_tmpt_tidur.setClickable(false);
            }
        });
        b_km_luar = (Button) findViewById(R.id.b_input_kmluar);
        b_km_luar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                km_luar.setText("" + id);
                id++;
                Log.d("id", "" + id);
                b_km_luar.setClickable(false);
            }
        });
        b_kipas_angin = (Button) findViewById(R.id.b_input_kipas);
        b_kipas_angin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kipas_angin.setText("" + id);
                id++;
                Log.d("id", "" + id);
                b_kipas_angin.setClickable(false);
            }
        });
        b_ac = (Button) findViewById(R.id.b_input_ac);
        b_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ac.setText("" + id);
                id++;
                Log.d("id", "" + id);
                b_ac.setClickable(false);
            }
        });
        b_tv = (Button) findViewById(R.id.b_input_tv);
        b_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("" + id);
                id++;
                Log.d("id", "" + id);
                b_tv.setClickable(false);
            }
        });
        b_kulkas = (Button) findViewById(R.id.b_input_kulkas);
        b_kulkas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kulkas.setText("" + id);
                id++;
                Log.d("id", "" + id);
                b_kulkas.setClickable(false);
            }
        });
        b_dispenser = (Button) findViewById(R.id.b_input_dispenser);
        b_dispenser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispenser.setText("" + id);
                id++;
                Log.d("id", "" + id);
                b_dispenser.setClickable(false);
            }
        });
        b_internet = (Button) findViewById(R.id.b_input_internet);
        b_internet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                internet.setText("" + id);
                id++;
                Log.d("id", "" + id);
                b_internet.setClickable(false);
            }
        });
        b_ruang_tamu = (Button) findViewById(R.id.b_input_ruangtamu);
        b_ruang_tamu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ruang_tamu.setText("" + id);
                id++;
                b_ruang_tamu.setClickable(false);
            }
        });
        b_dapur = (Button) findViewById(R.id.b_input_dapur);
        b_dapur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dapur.setText("" + id);
                id++;
                b_dapur.setClickable(false);
            }
        });
        b_mesin_cuci = (Button) findViewById(R.id.b_input_mesincuci);
        b_mesin_cuci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mesin_cuci.setText("" + id);
                id++;
                b_mesin_cuci.setClickable(false);
            }
        });
        b_pembantu = (Button) findViewById(R.id.b_input_pembantu);
        b_pembantu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pembantu.setText("" + id);
                id++;
                b_pembantu.setClickable(false);
            }
        });
        b_cuci_gosok = (Button) findViewById(R.id.b_input_cucigosok);
        b_cuci_gosok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cuci_gosok.setText("" + id);
                id++;
                b_cuci_gosok.setClickable(false);
            }
        });
        b_cctv_security = (Button) findViewById(R.id.b_input_cctv);
        b_cctv_security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cctv_security.setText("" + id);
                id++;
                b_cctv_security.setClickable(false);
            }
        });
        b_klm_renang = (Button) findViewById(R.id.b_input_kolamrenang);
        b_klm_renang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klm_renang.setText("" + id);
                id++;
                b_klm_renang.setClickable(false);
            }
        });
        b_parkir_motor = (Button) findViewById(R.id.b_input_parkirmotor);
        b_parkir_motor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parkir_motor.setText("" + id);
                id++;
                b_parkir_motor.setClickable(false);
            }
        });
        b_parkir_mobil = (Button) findViewById(R.id.b_input_parkirmobil);
        b_parkir_mobil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parkir_mobil.setText("" + id);
                id++;
                b_parkir_mobil.setClickable(false);
            }
        });
        b_tmpt_makan = (Button) findViewById(R.id.b_input_restaurant);
        b_tmpt_makan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmpt_makan.setText("" + id);
                id++;
                b_tmpt_makan.setClickable(false);
            }
        });
        b_warnet = (Button) findViewById(R.id.b_input_warnet);
        b_warnet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                warnet.setText("" + id);
                id++;
                b_warnet.setClickable(false);
            }
        });
        b_mall = (Button) findViewById(R.id.b_input_mall);
        b_mall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mall.setText("" + id);
                id++;
                b_mall.setClickable(false);
            }
        });
        b_apotek_dokter = (Button) findViewById(R.id.b_input_apotek);
        b_apotek_dokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apotek_dokter.setText("" + id);
                id++;
                b_apotek_dokter.setClickable(false);
            }
        });
        b_atm_bank = (Button) findViewById(R.id.b_input_bank);
        b_atm_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atm_bank.setText("" + id);
                id++;
                b_atm_bank.setClickable(false);
            }
        });
        b_supermarket = (Button) findViewById(R.id.b_input_supermarket);
        b_supermarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supermarket.setText("" + id);
                id++;
                b_supermarket.setClickable(false);
            }
        });
        b_kendaraan_umum = (Button) findViewById(R.id.b_input_kendaraanumum);
        b_kendaraan_umum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kendaraan_umum.setText("" + id);
                id++;
                b_kendaraan_umum.setClickable(false);
            }
        });
    }

    public void getData() {
        if (!(Jarak_kos).equalsIgnoreCase("")) {
            count_w += 1;
            //sql += "jarak_kos";
            new Background(cari_input2Activity.this).execute("prodi");
            Log.d("id", Id_prodi);
        }
        if (!(Biaya_kos).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "biaya_kos is not null";
            sql += ",biaya_kos";
            count_w += 1;
            Log.d("sql_id biaya", sql_id);
            Log.d("sql biaya", sql);
        }
        if (!(Jk).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "jk = '"+pencariKos.getJk_pencari()+"'";
            sql += ",jk";
            count_w += 1;
            Log.d("sql_id lemari", sql_id);
            Log.d("sql lemari", sql);
        }
        if (!(Jmlh_penghuni).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "jmlh_penghuni > 0";
            sql += ",jmlh_penghuni";
            count_w += 1;
            Log.d("sql_id penghuni", sql_id);
            Log.d("sql penghuni", sql);
        }
        if (!(Meja_kursi).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "meja_kursi = 'y'";
            sql += ",meja_kursi";
            count_w += 1;
            Log.d("sql_id meja_kursi", sql_id);
            Log.d("sql meja_kursi", sql);
        }
        if (!(Lemari).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "lemari = 'y'";
            sql += ",lemari";
            count_w += 1;
            Log.d("sql_id lemari", sql_id);
            Log.d("sql lemari", sql);
        }
        if (!(Tmpt_tidur).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "tmpt_tidur = 'y'";
            sql += ",tmpt_tidur";
            count_w += 1;
            Log.d("sql_id tmpt_tidur", sql_id);
            Log.d("sql tmpt_tidur", sql);
        }
        if (!(Km_luar).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "km_luar = 't'";
            sql += ",km_luar";
            count_w += 1;
            Log.d("sql_id km_luar", sql_id);
            Log.d("sql km_luar", sql);
        }
        if (!(Kipas_angin).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "kipas_angin = 'y'";
            sql += ",kipas_angin";
            count_w += 1;
            Log.d("sql_id kipas", sql_id);
            Log.d("sql kipas", sql);
        }
        if (!(Ac).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "ac = 'y'";
            sql += ",ac";
            count_w += 1;
            Log.d("sql_id ac", sql_id);
            Log.d("sql ac", sql);
        }
        if (!(Tv).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "tv = 'y'";
            sql += ",tv";
            count_w += 1;
            Log.d("sql_id tv", sql_id);
            Log.d("sql tv", sql);
        }
        if (!(Kulkas).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "kulkas = 'y'";
            sql += ",kulkas";
            count_w += 1;
            Log.d("sql_id kulkas", sql_id);
            Log.d("sql kulkas", sql);
        }
        if (!(Dispenser).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "dispenser = 'y'";
            sql += ",dispenser";
            count_w += 1;
            Log.d("sql_id dispenser", sql_id);
            Log.d("sql dispenser", sql);
        }
        if (!(Internet).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "internet = 'y'";
            sql += ",internet";
            count_w += 1;
            Log.d("sql_id internet", sql_id);
            Log.d("sql internet", sql);
        }
        if (!(Ruang_tamu).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "ruang_tamu = 'y'";
            sql += ",ruang_tamu";
            count_w += 1;
            Log.d("sql_id ruang_tamu", sql_id);
            Log.d("sql ruang_tamu", sql);
        }
        if (!(Dapur).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "dapur = 'y'";
            sql += ",dapur";
            count_w += 1;
            Log.d("sql_id dapur", sql_id);
            Log.d("sql dapur", sql);
        }
        if (!(Mesin_cuci).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "mesin_cuci = 'y'";
            sql += ",mesin_cuci";
            count_w += 1;
            Log.d("sql_id mesin_cuci", sql_id);
            Log.d("sql mesin_cuci", sql);
        }
        if (!(Pembantu).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "pembantu = 'y'";
            sql += ",pembantu";
            count_w += 1;
            Log.d("sql_id pembantu", sql_id);
            Log.d("sql pembantu", sql);
        }
        if (!(Cuci_gosok).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "cuci_gosok = 'y'";
            sql += ",cuci_gosok";
            count_w += 1;
            Log.d("sql_id cuci_gosok", sql_id);
            Log.d("sql cuci_gosok", sql);
        }
        if (!(Cctv_security).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "cctv_security = 'y'";
            sql += ",cctv_security";
            count_w += 1;
            Log.d("sql_id cctv_security", sql_id);
            Log.d("sql cctv_security", sql);
        }
        if (!(Klm_renang).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "klm_renang = 'y'";
            sql += ",klm_renang";
            count_w += 1;
            Log.d("sql_id klm_renang", sql_id);
            Log.d("sql klm_renang", sql);
        }
        if (!(Parkir_motor).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "parkir_motor > 0";
            sql += ",parkir_motor";
            count_w += 1;
            Log.d("sql_id motor", sql_id);
            Log.d("sql motor", sql);
        }
        if (!(Parkir_mobil).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "parkir_mobil > 0";
            sql += ",parkir_mobil";
            count_w += 1;
            Log.d("sql_id mobil", sql_id);
            Log.d("sql mobil", sql);
        }
        if (!(Tmpt_makan).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "tmpt_makan > 0";
            sql += ",tmpt_makan";
            count_w += 1;
            Log.d("sql_id tmpt_makan", sql_id);
            Log.d("sql tmpt_makan", sql);
        }
        if (!(Warnet).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "warnet > 0";
            sql += ",warnet";
            count_w += 1;
            Log.d("sql_id warnet", sql_id);
            Log.d("sql warnet", sql);
        }
        if (!(Mall).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "mall > 0";
            sql += ",mall";
            count_w += 1;
            Log.d("sql_id mall", sql_id);
            Log.d("sql mall", sql);
        }
        if (!(Apotek_dokter).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "apotek_dokter > 0";
            sql += ",apotek_dokter";
            count_w += 1;
            Log.d("sql_id dokter", sql_id);
            Log.d("sql doketr", sql);
        }
        if (!(Atm_bank).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "atm_bank > 0";
            sql += ",atm_bank";
            count_w += 1;
            Log.d("sql_id bank", sql_id);
            Log.d("sql bank", sql);
        }
        if (!(Supermarket).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "supermarket > 0";
            sql += ",supermarket";
            count_w += 1;
            Log.d("sql_id supermarket", sql_id);
            Log.d("sql supermarket", sql);
        }
        if (!(Kendaraan_umum).equalsIgnoreCase("")) {
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
            }
            sql_id += "kendaraan_umum > 0";
            sql += ",kendaraan_umum";
            count_w += 1;
            Log.d("sql_id kendaraan", sql_id);
            Log.d("sql kendaraan", sql);
        }

    }

    public List<identitas_kos> pra_topsis(String json_prod, String json_fas, String json_iden) {
        //sql = "id_tfn," + sql;
        String[] output = sql.split(",");
        Log.d("output length", "" + output.length);
        for (int i = 0; i < output.length; i++) {
            Log.d("out", output[i]);
        }
        if (!(Jarak_kos).equalsIgnoreCase("")) {
            try {
                jsonObject = new JSONObject(json_prod);
                Log.d("prodi", json_prod);
                jsonArray = jsonObject.getJSONArray("server_response");
                JSONObject JO = jsonArray.getJSONObject(0);
                latitude = JO.getString("latitude");
                longitude = JO.getString("longitude");
            } catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data [" + e.getMessage() + "] " + json_identitas);
            }
        }

        try {
            jsonObject = new JSONObject(json_iden);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            String id_kos, nama_kos, alamat_kos, latitude_kos, longitude_kos, id_pemilik, nama_pemilik, telp_pemilik, id_kelurahan, nama_kelurahan, nama_kecamtan,
                    id_tfn, jk, meja_kursi, lemari, tmpt_tidur, km_luar, kipas_angin, ac, tv, kulkas,
                    dispenser, internet, ruang_tamu, dapur, mesin_cuci, pembantu, cuci_gosok, cctv_security, klm_renang;
            int biaya_kos, jmlh_penghuni, parkir_motor, parkir_mobil, tmpt_makan, warnet, mall, apotek_dokter, atm_bank, supermarket, kendaraan_umum;
            if(index_jc == 0){
                jumlah_pencarian = 5;
            } else if (index_jc == 1){
                jumlah_pencarian = 10;
            } else if (index_jc == 2){
                jumlah_pencarian = 20;
            } else if (index_jc == 3){
                jumlah_pencarian = jsonArray.length();
            }
            while (count < jumlah_pencarian) {
                JSONObject JO = jsonArray.getJSONObject(count);
                identitas_kos identitasKos = new identitas_kos();
                id_kos = JO.getString("id_kos");
                identitasKos.setId_kos(id_kos);
                nama_kos = JO.getString("nama_kos");
                identitasKos.setNama_kos(nama_kos);
                alamat_kos = JO.getString("alamat_kos");
                identitasKos.setAlamat_kos(alamat_kos);
                latitude_kos = JO.getString("latitude_kos");
                identitasKos.setLatitude_kos(latitude_kos);
                longitude_kos = JO.getString("longitude_kos");
                identitasKos.setLongitude_kos(longitude_kos);
                id_pemilik = JO.getString("id_pemilik");
                identitasKos.setId_pemilik(id_pemilik);
                nama_pemilik = JO.getString("nama_pemilik");
                identitasKos.setNama_pemilik(nama_pemilik);
                telp_pemilik = JO.getString("telp_pemilik");
                identitasKos.setTelp_pemilik(telp_pemilik);
                id_kelurahan = JO.getString("id_kelurahan");
                identitasKos.setId_kelurahan(id_kelurahan);
                nama_kelurahan = JO.getString("nama_kelurahan");
                identitasKos.setKelurahan(nama_kelurahan);
                nama_kecamtan = JO.getString("nama_kecamatan");
                identitasKos.setKecamatan(nama_kecamtan);
                id_tfn = JO.getString("id_tfn");
                identitasKos.setId_tfn(id_tfn);
                biaya_kos = JO.getInt("biaya_kos");
                identitasKos.setBiaya_kos(biaya_kos);
                jk = JO.getString("jk");
                identitasKos.setJk(jk);
                jmlh_penghuni = JO.getInt("jmlh_penghuni");
                identitasKos.setJmlh_penghuni(jmlh_penghuni);
                meja_kursi = JO.getString("meja_kursi");
                identitasKos.setMeja_kursi(meja_kursi);
                lemari = JO.getString("lemari");
                identitasKos.setLemari(lemari);
                tmpt_tidur = JO.getString("tmpt_tidur");
                identitasKos.setTmpt_tidur(tmpt_tidur);
                km_luar = JO.getString("km_luar");
                identitasKos.setKm_luar(km_luar);
                kipas_angin = JO.getString("kipas_angin");
                identitasKos.setKipas_angin(kipas_angin);
                ac = JO.getString("ac");
                identitasKos.setAc(ac);
                tv = JO.getString("tv");
                identitasKos.setTv(tv);
                kulkas = JO.getString("kulkas");
                identitasKos.setKulkas(kulkas);
                dispenser = JO.getString("dispenser");
                identitasKos.setDispenser(dispenser);
                internet = JO.getString("internet");
                identitasKos.setInternet(internet);
                ruang_tamu = JO.getString("ruang_tamu");
                identitasKos.setRuang_tamu(ruang_tamu);
                dapur = JO.getString("dapur");
                identitasKos.setDapur(dapur);
                mesin_cuci = JO.getString("mesin_cuci");
                identitasKos.setMesin_cuci(mesin_cuci);
                pembantu = JO.getString("pembantu");
                identitasKos.setPembantu(pembantu);
                cuci_gosok = JO.getString("cuci_gosok");
                identitasKos.setCuci_gosok(cuci_gosok);
                cctv_security = JO.getString("cctv_security");
                identitasKos.setCctv_security(cctv_security);
                klm_renang = JO.getString("klm_renang");
                identitasKos.setKlm_renang(klm_renang);
                parkir_motor = JO.getInt("parkir_motor");
                identitasKos.setParkir_motor(parkir_motor);
                parkir_mobil = JO.getInt("parkir_mobil");
                identitasKos.setParkir_mobil(parkir_mobil);
                tmpt_makan = JO.getInt("tmpt_makan");
                identitasKos.setTmpt_makan(tmpt_makan);
                warnet = JO.getInt("warnet");
                identitasKos.setWarnet(warnet);
                mall = JO.getInt("mall");
                identitasKos.setMall(mall);
                apotek_dokter = JO.getInt("apotek_dokter");
                identitasKos.setApotek_dokter(apotek_dokter);
                atm_bank = JO.getInt("atm_bank");
                identitasKos.setAtm_bank(atm_bank);
                supermarket = JO.getInt("supermarket");
                identitasKos.setSupermarket(supermarket);
                kendaraan_umum = JO.getInt("kendaraan_umum");
                identitasKos.setKendaraan_umum(kendaraan_umum);
                list_identitasKos.add(identitasKos);
                count++;
            }
        }catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data [" + e.getMessage() + "] " + json_identitas);
            }
            try{
                jsonObject_fas = new JSONObject(json_fas);
                jsonArray_fas = jsonObject_fas.getJSONArray("server_response");
                int count = 0;
                if(index_jc == 0){
                    jumlah_pencarian = 5;
                } else if (index_jc == 1){
                    jumlah_pencarian = 10;
                } else if (index_jc == 2){
                    jumlah_pencarian = 20;
                } else if (index_jc == 3){
                    jumlah_pencarian = jsonArray.length();
                } if (jsonArray.length() < jumlah_pencarian){
                    jumlah_pencarian = jsonArray.length();
                }
            while (count < jumlah_pencarian) {
                JSONObject JO_fas = jsonArray_fas.getJSONObject(count);
                fuzzyFasilitasKos = new fuzzyFasilitas_kos();
                fuzzyFasilitasKos.setId_tfn(JO_fas.getString("id_tfn"));
                Log.d("id", fuzzyFasilitasKos.getId_tfn());
                for (int i = 0; i < output.length; i++) {
                    if (!(Jarak_kos).equalsIgnoreCase("")) {
                        LatLng kos = new LatLng(Float.parseFloat(list_identitasKos.get(i).getLatitude_kos()), Float.parseFloat(list_identitasKos.get(i).getLongitude_kos()));
                        LatLng univ = new LatLng(Float.parseFloat(latitude), Float.parseFloat(longitude));
                        Log.d("la kos", list_identitasKos.get(i).getLatitude_kos());
                        Log.d("lo kos", list_identitasKos.get(i).getLongitude_kos());
                        Double distance = (SphericalUtil.computeDistanceBetween(univ, kos) / 1000);
                        fuzzyFasilitasKos.setJarak_kos(Fuzzy.jarak(distance));
                    }if (output[i].equalsIgnoreCase("biaya_kos")) {
                        fuzzyFasilitasKos.setBiaya_kos(JO_fas.getString("biaya_kos"));
                        Log.d("biaya", fuzzyFasilitasKos.getBiaya_kos());
                    }if (output[i].equalsIgnoreCase("jk")) {
                        fuzzyFasilitasKos.setJk(JO_fas.getString("jk"));
                        Log.d("jk", fuzzyFasilitasKos.getJk());
                    }if (output[i].equalsIgnoreCase("jmlh_penghuni")) {
                        fuzzyFasilitasKos.setJmlh_penghuni(JO_fas.getString("jmlh_penghuni"));
                        Log.d("jmlh_penghuni", fuzzyFasilitasKos.getJmlh_penghuni());
                    }if (output[i].equalsIgnoreCase("meja_kursi")) {
                        fuzzyFasilitasKos.setMeja_kursi(JO_fas.getString("meja_kursi"));
                        Log.d("meja", fuzzyFasilitasKos.getMeja_kursi());
                    }if (output[i].equalsIgnoreCase("lemari")) {
                        fuzzyFasilitasKos.setLemari(JO_fas.getString("lemari"));
                        Log.d("lemari", fuzzyFasilitasKos.getLemari());
                    }if (output[i].equalsIgnoreCase("tmpt_tidur")) {
                        fuzzyFasilitasKos.setTmpt_tidur(JO_fas.getString("tmpt_tidur"));
                        Log.d("tmpt_tidur", fuzzyFasilitasKos.getTmpt_tidur());
                    }if (output[i].equalsIgnoreCase("km_luar")) {
                        fuzzyFasilitasKos.setKm_luar(JO_fas.getString("km_luar"));
                        Log.d("km_luar", fuzzyFasilitasKos.getKm_luar());
                    }if (output[i].equalsIgnoreCase("kipas_angin")) {
                        fuzzyFasilitasKos.setKipas_angin(JO_fas.getString("kipas_angin"));
                        Log.d("kipas_angin", fuzzyFasilitasKos.getKipas_angin());
                    }if (output[i].equalsIgnoreCase("ac")) {
                        fuzzyFasilitasKos.setAc(JO_fas.getString("ac"));
                        Log.d("ac", fuzzyFasilitasKos.getAc());
                    }if (output[i].equalsIgnoreCase("tv")) {
                        fuzzyFasilitasKos.setTv(JO_fas.getString("tv"));
                        Log.d("tv", fuzzyFasilitasKos.getTv());
                    }if (output[i].equalsIgnoreCase("kulkas")) {
                        fuzzyFasilitasKos.setKulkas(JO_fas.getString("kulkas"));
                        Log.d("kulkas", fuzzyFasilitasKos.getKulkas());
                    }if (output[i].equalsIgnoreCase("dispenser")) {
                        fuzzyFasilitasKos.setDispenser(JO_fas.getString("dispenser"));
                        Log.d("dispenser", fuzzyFasilitasKos.getDispenser());
                    }if (output[i].equalsIgnoreCase("internet")) {
                        fuzzyFasilitasKos.setInternet(JO_fas.getString("internet"));
                        Log.d("internet", fuzzyFasilitasKos.getInternet());
                    }if (output[i].equalsIgnoreCase("ruang_tamu")) {
                        fuzzyFasilitasKos.setRuang_tamu(JO_fas.getString("ruang_tamu"));
                        Log.d("ruang_tamu", fuzzyFasilitasKos.getRuang_tamu());
                    }if (output[i].equalsIgnoreCase("dapur")) {
                        fuzzyFasilitasKos.setDapur(JO_fas.getString("dapur"));
                        Log.d("dapur", fuzzyFasilitasKos.getDapur());
                    }if (output[i].equalsIgnoreCase("mesin_cuci")) {
                        fuzzyFasilitasKos.setMesin_cuci(JO_fas.getString("mesin_cuci"));
                        Log.d("mesin_cuci", fuzzyFasilitasKos.getMesin_cuci());
                    }if (output[i].equalsIgnoreCase("pembantu")) {
                        fuzzyFasilitasKos.setPembantu(JO_fas.getString("pembantu"));
                        Log.d("pembantu", fuzzyFasilitasKos.getPembantu());
                    }if (output[i].equalsIgnoreCase("cuci_gosok")) {
                        fuzzyFasilitasKos.setCuci_gosok(JO_fas.getString("cuci_gosok"));
                        Log.d("cuci_gosok", fuzzyFasilitasKos.getCuci_gosok());
                    }if (output[i].equalsIgnoreCase("cctv_security")) {
                        fuzzyFasilitasKos.setCctv_security(JO_fas.getString("cctv_security"));
                        Log.d("cctv", fuzzyFasilitasKos.getCctv_security());
                    }if (output[i].equalsIgnoreCase("klm_renang")) {
                        fuzzyFasilitasKos.setKlm_renang(JO_fas.getString("klm_renang"));
                        Log.d("klm_renang", fuzzyFasilitasKos.getKlm_renang());
                    }if (output[i].equalsIgnoreCase("parkir_motor")) {
                        fuzzyFasilitasKos.setParkir_motor(JO_fas.getString("parkir_motor"));
                    }if (output[i].equalsIgnoreCase("parkir_mobil")) {
                        fuzzyFasilitasKos.setParkir_mobil(JO_fas.getString("parkir_mobil"));
                    }if (output[i].equalsIgnoreCase("tmpt_makan")) {
                        fuzzyFasilitasKos.setTmpt_makan(JO_fas.getString("tmpt_makan"));
                    }if (output[i].equalsIgnoreCase("warnet")) {
                        fuzzyFasilitasKos.setWarnet(JO_fas.getString("warnet"));
                    }if (output[i].equalsIgnoreCase("mall")) {
                        fuzzyFasilitasKos.setMall(JO_fas.getString("mall"));
                    }if (output[i].equalsIgnoreCase("apotek_dokter")) {
                        fuzzyFasilitasKos.setApotek_dokter(JO_fas.getString("apotek_dokter"));
                    }if (output[i].equalsIgnoreCase("atm_bank")) {
                        fuzzyFasilitasKos.setAtm_bank(JO_fas.getString("atm_bank"));
                    }if (output[i].equalsIgnoreCase("supermarket")) {
                        fuzzyFasilitasKos.setSupermarket(JO_fas.getString("supermarket"));
                    }if (output[i].equalsIgnoreCase("kendaraan_umum")) {
                        fuzzyFasilitasKos.setKendaraan_umum(JO_fas.getString("kendaraan_umum"));
                    }
                }
                list_fasilitasKos.add(fuzzyFasilitasKos);
                count++;
            }
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data [" + e.getMessage() + "] " + json_identitas);
        }


        Topsis = new topsis(list_fasilitasKos, list_identitasKos);
        Log.d("prioritas", ""+count_w);
        bobot = pembobotan.bobot(count_w);
        Log.d("biaya", ""+Biaya_kos);

        double[] D_max = new double[list_fasilitasKos.size()];
        double[] D_min = new double[list_fasilitasKos.size()];

        Log.d("jumlah pencarian", ""+jumlah_pencarian);
        for (int i = 1; i < (count_w+1); i++) {
            if ((!Jarak_kos.equalsIgnoreCase("")) && (Integer.parseInt(Jarak_kos) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    String[] split_jarak_kos = list_fasilitasKos.get(j).getJarak_kos().split(",");
                    tfn_kriteria.setA(Double.valueOf(split_jarak_kos[0]));
                    tfn_kriteria.setB(Double.valueOf(split_jarak_kos[1]));
                    tfn_kriteria.setC(Double.valueOf(split_jarak_kos[2]));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_biaya_tfn(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[1]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[0]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            }else if ((!Biaya_kos.equalsIgnoreCase("")) && (Integer.parseInt(Biaya_kos) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    String[] split_biaya_kos = list_fasilitasKos.get(j).getBiaya_kos().split(",");
                    tfn_kriteria.setA(Double.valueOf(split_biaya_kos[0]));
                    tfn_kriteria.setB(Double.valueOf(split_biaya_kos[1]));
                    tfn_kriteria.setC(Double.valueOf(split_biaya_kos[2]));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_biaya_tfn(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[1]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[0]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Jk.equalsIgnoreCase("")) && (Integer.parseInt(Jk) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    Log.d("list", ""+list_fasilitasKos.size());
                    Log.d("j", ""+j);
                    tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(j).getJk()));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_keuntungan_tfn(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Jmlh_penghuni.equalsIgnoreCase("")) && (Integer.parseInt(Jmlh_penghuni) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    String[] split_jmlh_penghuni = list_fasilitasKos.get(j).getJmlh_penghuni().split(",");
                    tfn_kriteria.setA(Double.valueOf(split_jmlh_penghuni[0]));
                    tfn_kriteria.setB(Double.valueOf(split_jmlh_penghuni[1]));
                    tfn_kriteria.setC(Double.valueOf(split_jmlh_penghuni[2]));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_biaya_tfn(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[1]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[0]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Meja_kursi.equalsIgnoreCase("")) && (Integer.parseInt(Meja_kursi) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(j).getMeja_kursi()));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_keuntungan_crips(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Lemari.equalsIgnoreCase("")) && (Integer.parseInt(Lemari) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(j).getLemari()));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_keuntungan_crips(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Tmpt_tidur.equalsIgnoreCase("")) && (Integer.parseInt(Tmpt_tidur) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(j).getTmpt_tidur()));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_keuntungan_crips(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Km_luar.equalsIgnoreCase("")) && (Integer.parseInt(Km_luar) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(j).getKm_luar()));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_keuntungan_crips(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Kipas_angin.equalsIgnoreCase("")) && (Integer.parseInt(Kipas_angin) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(j).getKipas_angin()));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_keuntungan_crips(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Ac.equalsIgnoreCase("")) && (Integer.parseInt(Ac) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(j).getAc()));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_keuntungan_crips(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Tv.equalsIgnoreCase("")) && (Integer.parseInt(Tv) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(j).getTv()));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_keuntungan_crips(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Kulkas.equalsIgnoreCase("")) && (Integer.parseInt(Kulkas) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(j).getKulkas()));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_keuntungan_crips(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Dispenser.equalsIgnoreCase("")) && (Integer.parseInt(Dispenser) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(j).getDispenser()));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_keuntungan_crips(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Internet.equalsIgnoreCase("")) && (Integer.parseInt(Internet) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(j).getInternet()));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_keuntungan_crips(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Ruang_tamu.equalsIgnoreCase("")) && (Integer.parseInt(Ruang_tamu) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    Log.d("ruang tamu",list_fasilitasKos.get(j).getRuang_tamu());
                    tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(j).getRuang_tamu()));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_keuntungan_crips(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Dapur.equalsIgnoreCase("")) && (Integer.parseInt(Dapur) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(j).getDapur()));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_keuntungan_crips(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Mesin_cuci.equalsIgnoreCase("")) && (Integer.parseInt(Mesin_cuci) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(j).getMesin_cuci()));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_keuntungan_crips(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Pembantu.equalsIgnoreCase("")) && (Integer.parseInt(Pembantu) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(j).getPembantu()));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_keuntungan_crips(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                for (int j = 0; j < hasil_max.length; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Cuci_gosok.equalsIgnoreCase("")) && (Integer.parseInt(Cuci_gosok) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(j).getCuci_gosok()));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_keuntungan_crips(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Cctv_security.equalsIgnoreCase("")) && (Integer.parseInt(Cctv_security) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(j).getCctv_security()));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_keuntungan_crips(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Klm_renang.equalsIgnoreCase("")) && (Integer.parseInt(Klm_renang) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(j).getKlm_renang()));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_keuntungan_crips(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Parkir_motor.equalsIgnoreCase("")) && (Integer.parseInt(Parkir_motor) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    String[] split_jmlh_penghuni = list_fasilitasKos.get(j).getParkir_motor().split(",");
                    tfn_kriteria.setA(Double.valueOf(split_jmlh_penghuni[0]));
                    tfn_kriteria.setB(Double.valueOf(split_jmlh_penghuni[1]));
                    tfn_kriteria.setC(Double.valueOf(split_jmlh_penghuni[2]));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_keuntungan_tfn(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Parkir_mobil.equalsIgnoreCase("")) && (Integer.parseInt(Parkir_mobil) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    String[] split_jmlh_penghuni = list_fasilitasKos.get(j).getParkir_mobil().split(",");
                    tfn_kriteria.setA(Double.valueOf(split_jmlh_penghuni[0]));
                    tfn_kriteria.setB(Double.valueOf(split_jmlh_penghuni[1]));
                    tfn_kriteria.setC(Double.valueOf(split_jmlh_penghuni[2]));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_keuntungan_tfn(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Tmpt_makan.equalsIgnoreCase("")) && (Integer.parseInt(Tmpt_makan) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    String[] split_jmlh_penghuni = list_fasilitasKos.get(j).getTmpt_makan().split(",");
                    tfn_kriteria.setA(Double.valueOf(split_jmlh_penghuni[0]));
                    tfn_kriteria.setB(Double.valueOf(split_jmlh_penghuni[1]));
                    tfn_kriteria.setC(Double.valueOf(split_jmlh_penghuni[2]));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_biaya_tfn(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[1]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[0]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Warnet.equalsIgnoreCase("")) && (Integer.parseInt(Warnet) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    String[] split_jmlh_penghuni = list_fasilitasKos.get(j).getWarnet().split(",");
                    tfn_kriteria.setA(Double.valueOf(split_jmlh_penghuni[0]));
                    tfn_kriteria.setB(Double.valueOf(split_jmlh_penghuni[1]));
                    tfn_kriteria.setC(Double.valueOf(split_jmlh_penghuni[2]));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_biaya_tfn(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[1]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[0]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Mall.equalsIgnoreCase("")) && (Integer.parseInt(Mall) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    String[] split_jmlh_penghuni = list_fasilitasKos.get(j).getMall().split(",");
                    tfn_kriteria.setA(Double.valueOf(split_jmlh_penghuni[0]));
                    tfn_kriteria.setB(Double.valueOf(split_jmlh_penghuni[1]));
                    tfn_kriteria.setC(Double.valueOf(split_jmlh_penghuni[2]));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_biaya_tfn(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[1]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[0]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Apotek_dokter.equalsIgnoreCase("")) && (Integer.parseInt(Apotek_dokter) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    String[] split_jmlh_penghuni = list_fasilitasKos.get(j).getApotek_dokter().split(",");
                    tfn_kriteria.setA(Double.valueOf(split_jmlh_penghuni[0]));
                    tfn_kriteria.setB(Double.valueOf(split_jmlh_penghuni[1]));
                    tfn_kriteria.setC(Double.valueOf(split_jmlh_penghuni[2]));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_biaya_tfn(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[1]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[0]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Atm_bank.equalsIgnoreCase("")) && (Integer.parseInt(Atm_bank) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    String[] split_jmlh_penghuni = list_fasilitasKos.get(j).getAtm_bank().split(",");
                    tfn_kriteria.setA(Double.valueOf(split_jmlh_penghuni[0]));
                    tfn_kriteria.setB(Double.valueOf(split_jmlh_penghuni[1]));
                    tfn_kriteria.setC(Double.valueOf(split_jmlh_penghuni[2]));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_biaya_tfn(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[1]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[0]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Supermarket.equalsIgnoreCase("")) && (Integer.parseInt(Supermarket) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    String[] split_jmlh_penghuni = list_fasilitasKos.get(j).getSupermarket().split(",");
                    tfn_kriteria.setA(Double.valueOf(split_jmlh_penghuni[0]));
                    tfn_kriteria.setB(Double.valueOf(split_jmlh_penghuni[1]));
                    tfn_kriteria.setC(Double.valueOf(split_jmlh_penghuni[2]));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_biaya_tfn(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[1]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[0]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            } else if ((!Kendaraan_umum.equalsIgnoreCase("")) && (Integer.parseInt(Kendaraan_umum) == i)) {
                List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                for (int j = 0; j < jumlah_pencarian; j++) {
                    tfn_kriteria tfn_kriteria = new tfn_kriteria();
                    String[] split_jmlh_penghuni = list_fasilitasKos.get(j).getKendaraan_umum().split(",");
                    tfn_kriteria.setA(Double.valueOf(split_jmlh_penghuni[0]));
                    tfn_kriteria.setB(Double.valueOf(split_jmlh_penghuni[1]));
                    tfn_kriteria.setC(Double.valueOf(split_jmlh_penghuni[2]));
                    list.add(tfn_kriteria);
                }
                int[] mean = atribut_biaya_tfn(list, bobot.get(i-1));
                double[] hasil_max = topsis.langkah_empat_max(list, mean[1]);
                double[] hasil_min = topsis.langkah_empat_min(list, mean[0]);
                for (int j = 0; j < jumlah_pencarian; j++) {
                    D_max[j] += hasil_max[j];
                    D_min[j] += hasil_min[j];
                }
            }
        }
        Log.d("D_max", ""+D_max[0]);
        Log.d("D_min", ""+D_min[0]);

        List<identitas_kos> hasil_fuzzy_topsis = topsis.langkah_lima(D_max, D_min);
        for (int i = 0; i < hasil_fuzzy_topsis.size(); i++) {Log.d("hasil_topsis", hasil_fuzzy_topsis.get(i).getNama_kos());}
        Intent intent = new Intent(cari_input2Activity.this, cari_daftarkosActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST", (Serializable) hasil_fuzzy_topsis);
        intent.putExtra("BUNDLE", args);
        intent.putExtra("pencari", pencariKos);
        startActivityForResult(intent, 0);
        onClickButtonListenerClear();

        count_w = 0;
        sql_id = "";
        sql = "";
        bobot.clear();
        hasil_fuzzy_topsis.clear();
        json_prod="";
        json_fas="";
        json_iden="";
        D_max= new double[0];
        D_min = new double[0];
        topsis.delete();
        //this.finish();
        return hasil_fuzzy_topsis;
    }

    public static int[] atribut_biaya_tfn(List<tfn_kriteria> list, tfn_kriteria bobot) {
        list = topsis.langkah_satu_biayaA(list);
        list = topsis.langkah_dua(list, bobot);
        int[] mean = topsis.langkah_tiga(list);
        return mean;
    }

    public static int[] atribut_keuntungan_tfn(List<tfn_kriteria> list, tfn_kriteria bobot) {
        list = topsis.langkah_satu_keuntunganA(list);
        list = topsis.langkah_dua(list, bobot);
        int[] mean = topsis.langkah_tiga(list);
        return mean;
    }

    public static int[] atribut_keuntungan_crips(List<tfn_kriteria> list, tfn_kriteria bobot) {
        list = topsis.langkah_satu_keuntunganB(list);
        list = topsis.langkah_dua(list, bobot);
        int[] mean = topsis.langkah_tiga(list);
        return mean;
    }

    class Background extends AsyncTask<String, Void, String> {
        String result_jarak, JSON_STRING, url_fasilitas, url_identitas, url_prodi, metode, url_jarak;
        Context ctx;

        public Background(Context ctx) {this.ctx = ctx;}

        @Override
        protected void onPreExecute() {
            url_fasilitas = "http://mykos.stander.id/fasilitasKos_getByConditions.php";
            url_identitas = "http://mykos.stander.id/identitasKos_getByConditions.php";
            url_prodi = "http://mykos.stander.id/program_studi.php";
            url_jarak = "https://maps.googleapis.com/maps/api/directions/json?";
        }

        @Override
        protected String doInBackground(String... params) {
            metode = params[0];
            Log.d("metode", metode);
            if (metode.equalsIgnoreCase("fasilitas")) {
                try {
                    URL url = new URL(url_fasilitas);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data;
                    if (sql.equalsIgnoreCase("jarak_kos")) {
                        data = URLEncoder.encode("prioritas", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8") + "&" +
                                URLEncoder.encode("kriteria", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
                    } else {
                        data = URLEncoder.encode("prioritas", "UTF-8") + "=" + URLEncoder.encode("where " + sql_id, "UTF-8") + "&" +
                                URLEncoder.encode("kriteria", "UTF-8") + "=" + URLEncoder.encode(sql, "UTF-8");
                    }
                    Log.d("data", data);
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS));
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((JSON_STRING = bufferedReader.readLine()) != null) {
                        stringBuilder.append(JSON_STRING + "\n");
                    }
                    bufferedReader.close();
                    IS.close();
                    httpURLConnection.disconnect();

                    return stringBuilder.toString().trim();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return "Error";
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error";
                }
            } else if (metode.equalsIgnoreCase("identitas")) {
                try {
                    URL url = new URL(url_identitas);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data;
                    if (sql.equalsIgnoreCase("jarak_kos")) {
                        data = URLEncoder.encode("prioritas", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
                    } else {
                        data = URLEncoder.encode("prioritas", "UTF-8") + "=" + URLEncoder.encode("where " + sql_id, "UTF-8");
                    }
                    Log.d("data", data);
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS));
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((JSON_STRING = bufferedReader.readLine()) != null) {
                        stringBuilder.append(JSON_STRING + "\n");
                    }
                    bufferedReader.close();
                    IS.close();
                    httpURLConnection.disconnect();

                    return stringBuilder.toString().trim();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return "Error";
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error";
                }
            } else if (metode.equalsIgnoreCase("jarak")) {
                String latitude = params[1];
                String longitude = params[2];
                String latitude_univ = params[3];
                String longitude_univ = params[4];
                if((!latitude.equalsIgnoreCase("0"))&&(!longitude.equalsIgnoreCase("0"))) {
                    StringBuilder stringBuilder = new StringBuilder();
                    Double dist = 0.0;
                    try {
                        String url = "http://maps.googleapis.com/maps/api/directions/json?origin=" + latitude + "," + longitude + "&destination=" + latitude_univ + "," + longitude_univ + "&mode=driving&sensor=false";
                        HttpPost httppost = new HttpPost(url);
                        HttpClient client = new DefaultHttpClient();
                        HttpResponse response;
                        stringBuilder = new StringBuilder();
                        response = client.execute(httppost);
                        HttpEntity entity = response.getEntity();
                        InputStream stream = entity.getContent();
                        int b;
                        while ((b = stream.read()) != -1) {
                            stringBuilder.append((char) b);
                        }
                    } catch (ClientProtocolException e) {
                    } catch (IOException e) {
                    }
                    //try {
                    //    URL url = new URL(url_jarak);
                    //    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    //   httpURLConnection.setRequestMethod("POST");
                    //    httpURLConnection.setDoOutput(true);
                    //    OutputStream OS = httpURLConnection.getOutputStream();
                    //    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    //    String data = URLEncoder.encode("origin", "UTF-8") + "=" + URLEncoder.encode(latitude, "UTF-8") + "," + URLEncoder.encode(longitude, "UTF-8") + "&" +
                    //            URLEncoder.encode("destination", "UTF-8") + "=" + URLEncoder.encode(latitude_univ, "UTF-8") + "," + URLEncoder.encode(longitude_univ, "UTF-8");
                    //    Log.d("data", data);
                    //    bufferedWriter.write(data);
                    //    bufferedWriter.flush();
                    //    bufferedWriter.close();
                    //    OS.close();
                    //    InputStream IS = httpURLConnection.getInputStream();
                    //    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS));
                    //    StringBuilder stringBuilder = new StringBuilder();
                    //    while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    //        stringBuilder.append(JSON_STRING + "\n");
                    //    }
                    //    bufferedReader.close();
                    //    IS.close();
                    //    httpURLConnection.disconnect();
                    //    result_jarak = stringBuilder.toString().trim();
                    //    Log.d("result", result_jarak);
                    //}catch (MalformedURLException e) {
                    //    e.printStackTrace();
                    //    return "Error";
                    //}catch (IOException e) {
                    //    e.printStackTrace();
                    //    return "Error";
                    //}

                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject = new JSONObject(String.valueOf(stringBuilder));
                        JSONArray array = jsonObject.getJSONArray("routes");
                        JSONObject routes = array.getJSONObject(0);
                        JSONArray legs = routes.getJSONArray("legs");
                        JSONObject steps = legs.getJSONObject(0);
                        JSONObject distance = steps.getJSONObject("distance");
                        jarak = distance.getString("text").replaceAll("[^\\d.]", "");
                        Log.i("Distance", jarak);
                        dist = Double.parseDouble(jarak);
                        Log.d("dist", ""+dist);
                        fuzzyFasilitasKos.setJarak_kos(Fuzzy.jarak(dist));
                        return jarak;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return jarak;
            } else if (metode.equalsIgnoreCase("prodi")) {
                try {
                    URL url = new URL(url_prodi);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("id_prodi", "UTF-8") + "=" + URLEncoder.encode(Id_prodi, "UTF-8");
                    Log.d("data", data);
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS));
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((JSON_STRING = bufferedReader.readLine()) != null) {
                        stringBuilder.append(JSON_STRING + "\n");
                    }
                    bufferedReader.close();
                    IS.close();
                    httpURLConnection.disconnect();
                    Log.d("prodi", stringBuilder.toString().trim());
                    return stringBuilder.toString().trim();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return "Error";
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error";
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("result", result);
            if (metode.equalsIgnoreCase("fasilitas")) {
                //json_fasilitas = result;
                //Log.d("json_fasilitas", json_fasilitas);
                if (result.equalsIgnoreCase("FAILURE")) {
                    Toast.makeText(cari_input2Activity.this, "Tidak Ada Data", Toast.LENGTH_LONG).show();
                } else if (!result.equalsIgnoreCase("FAILURE")) {
                    json_fasilitas = result;
                    Log.d("json_fasilitas", json_fasilitas);
                    pra_topsis(json_prodi, json_fasilitas, json_identitas);
                    Toast.makeText(cari_input2Activity.this, "Berhasil Mengubah Data Fuzzy Kos", Toast.LENGTH_LONG).show();
                }
            } else if (metode.equalsIgnoreCase("identitas")) {
                //json_identitas = result;
                //Log.d("json_identitas", json_identitas);
                if (result.equalsIgnoreCase("FAILURE")) {
                    Toast.makeText(cari_input2Activity.this, "Tidak Ada Data", Toast.LENGTH_LONG).show();
                } else if (!result.equalsIgnoreCase("FAILURE")) {
                    json_identitas = result;
                    Log.d("json_identitas", json_identitas);
                    Toast.makeText(cari_input2Activity.this, "Berhasil Mengubah Data Kos", Toast.LENGTH_LONG).show();
                }
            } else if (metode.equalsIgnoreCase("jarak")) {
                //Log.d("result", result);
                //json_jarak = result;
            } else if (metode.equalsIgnoreCase("prodi")) {
                Log.d("result", result);
                if (result.equalsIgnoreCase("FAILURE")) {
                    Toast.makeText(cari_input2Activity.this, "Tidak Ada Data", Toast.LENGTH_LONG).show();
                } else if (!result.equalsIgnoreCase("FAILURE")) {
                    json_prodi = result;
                    Log.d("json_prodi", json_prodi);
                    Toast.makeText(cari_input2Activity.this, "Berhasil Mengubah Data Kos", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
