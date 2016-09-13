package com.example.tiararatna.mykos;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tiararatna.adapter.identitasKos_cariAdapter;
import com.example.tiararatna.modul.bobot;
import com.example.tiararatna.modul.fuzzyFasilitas_kos;
import com.example.tiararatna.modul.identitas_kos;
import com.example.tiararatna.modul.tfn_kriteria;
import com.example.tiararatna.topsis.topsis;

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
public class cari_inputActivity extends AppCompatActivity {
    private static Button cari;
    static fuzzyFasilitas_kos fasilitasKos = new fuzzyFasilitas_kos();
    static int count_w = 0;
    static bobot w = new bobot();
    static identitasKos_cariAdapter identitasKosAdapter;
    static List<fuzzyFasilitas_kos> list_fasilitasKos = new ArrayList<>();
    static List<identitas_kos> list_identitasKos = new ArrayList<>();
    static List<identitas_kos> hasil = new ArrayList<identitas_kos>();
    public static topsis Topsis;
    static EditText jarak_kos, biaya_kos, jk, jmlh_penghuni, meja_kursi, lemari, tmpt_tidur, km_luar, kipas_angin, ac,
            tv, kulkas, dispenser, internet, ruang_tamu, dapur, mesin_cuci, pembantu, cuci_gosok, cctv_security,
            klm_renang, parkir_motor, parkir_mobil, tmpt_makan, warnet, mall, apotek_dokter, atm_bank, supermarket,
            kendaraan_umum;
    static String Jarak_kos, Biaya_kos, Jk, Jmlh_penghuni, Meja_kursi, Lemari, Tmpt_tidur,
            Km_luar, Kipas_angin, Ac, Tv, Kulkas, Dispenser, Internet, Ruang_tamu, Dapur,
            Mesin_cuci, Pembantu, Cuci_gosok, Cctv_security, Klm_renang, Parkir_motor,
            Parkir_mobil, Tmpt_makan, Warnet, Mall, Apotek_dokter, Atm_bank, Supermarket,
            Kendaraan_umum;
    String sql_id = "",sql = "", json_fasilitas, json_identitas, json_string, id_pencari;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_input);

        id_pencari=getIntent().getExtras().getString("id_pencari");
        Log.d("id_pencari",id_pencari);

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

        //listView = (ListView)findViewById(R.id.daftarkos_listview);
        //identitasKosAdapter = new identitasKos_cariAdapter(this, R.layout.activity_cari_daftarkos_row);
        //listView.setAdapter(identitasKosAdapter);

        onClickButtonListener();
    }

    public void onClickButtonListener (){
        cari = (Button)findViewById(R.id.b_cari_cari);
        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jarak_kos=jarak_kos.getText().toString();
                Biaya_kos=biaya_kos.getText().toString();
                Jk=jk.getText().toString();
                Jmlh_penghuni=jmlh_penghuni.getText().toString();
                Meja_kursi=meja_kursi.getText().toString();
                Lemari=lemari.getText().toString();
                Tmpt_tidur=tmpt_tidur.getText().toString();
                Km_luar=km_luar.getText().toString();
                Kipas_angin=kipas_angin.getText().toString();
                Ac=ac.getText().toString();
                Tv=tv.getText().toString();
                Kulkas=kulkas.getText().toString();
                Dispenser=dispenser.getText().toString();
                Ruang_tamu=ruang_tamu.getText().toString();
                Internet=internet.getText().toString();
                Dapur=dapur.getText().toString();
                Mesin_cuci=mesin_cuci.getText().toString();
                Pembantu=pembantu.getText().toString();
                Cuci_gosok=cuci_gosok.getText().toString();
                Cctv_security=cctv_security.getText().toString();
                Klm_renang=klm_renang.getText().toString();
                Parkir_motor=parkir_motor.getText().toString();
                Parkir_mobil=parkir_mobil.getText().toString();
                Tmpt_makan=tmpt_makan.getText().toString();
                Warnet=warnet.getText().toString();
                Mall=mall.getText().toString();
                Apotek_dokter=apotek_dokter.getText().toString();
                Atm_bank=atm_bank.getText().toString();
                Supermarket=supermarket.getText().toString();
                Kendaraan_umum=kendaraan_umum.getText().toString();
                getData();

                new Background(cari_inputActivity.this).execute("fasilitas");
                new Background(cari_inputActivity.this).execute("identitas");
            }
        });
    }

    public void getData(){
        if(!(Jarak_kos).equalsIgnoreCase("")){
            sql_id += "jarak_kos is not null";
            sql += "jarak_kos";
            count_w += 1;
            Log.d("sql_id jarak",sql_id);
            Log.d("sql jarak", sql);}
        if (!(Biaya_kos).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "biaya_kos is not null";
            sql += "biaya";
            count_w += 1;
            Log.d("sql_id biaya",sql_id);
            Log.d("sql biaya", sql);}
        if (!(Jk).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "lemari = 'p'";
            sql += "lemari";
            count_w += 1;
            Log.d("sql_id lemari",sql_id);
            Log.d("sql lemari", sql);}
        if (!(Jmlh_penghuni).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "penghuni = 1";
            sql += "penghuni";
            count_w += 1;
            Log.d("sql_id penghuni",sql_id);
            Log.d("sql penghuni", sql);}
        if (!(Meja_kursi).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "meja_kursi = 'ya'";
            sql += "meja_kursi";
            count_w += 1;
            Log.d("sql_id meja_kursi",sql_id);
            Log.d("sql meja_kursi", sql);}
        if (!(Lemari).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "lemari = 'ya'";
            sql += "lemari";
            count_w += 1;
            Log.d("sql_id lemari",sql_id);
            Log.d("sql lemari", sql);}
        if (!(Tmpt_tidur).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "tmpt_tidur = 'ya'";
            sql += "tmpt_tidur";
            count_w += 1;
            Log.d("sql_id tmpt_tidur",sql_id);
            Log.d("sql tmpt_tidur", sql);}
        if (!(Km_luar).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "km_luar = 'ya'";
            sql += "km_luar";
            count_w += 1;
            Log.d("sql_id km_luar",sql_id);
            Log.d("sql km_luar", sql);}
        if (!(Kipas_angin).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "kipas_angin = 'ya'";
            sql += "kipas_angin";
            count_w += 1;
            Log.d("sql_id kipas",sql_id);
            Log.d("sql kipas", sql);}
        if (!(Ac).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "ac = 'ya'";
            sql += "ac";
            count_w += 1;
            Log.d("sql_id ac",sql_id);
            Log.d("sql ac", sql);}
        if (!(Tv).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "tv = 'ya'";
            sql += "tv";
            count_w += 1;
            Log.d("sql_id tv",sql_id);
            Log.d("sql tv", sql);}
        if (!(Kulkas).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "kulkas = 'ya'";
            sql += "kulkas";
            count_w += 1;
            Log.d("sql_id kulkas",sql_id);
            Log.d("sql kulkas", sql);}
        if (!(Dispenser).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "dispenser = 'ya'";
            sql += "dispenser";
            count_w += 1;
            Log.d("sql_id dispenser",sql_id);
            Log.d("sql dispenser", sql);}
        if (!(Internet).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "internet = 'ya'";
            sql += "internet";
            count_w += 1;
            Log.d("sql_id internet",sql_id);
            Log.d("sql internet", sql);}
        if (!(Ruang_tamu).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "ruang_tamu = 'ya'";
            sql += "ruang_tamu";
            count_w += 1;
            Log.d("sql_id ruang_tamu",sql_id);
            Log.d("sql ruang_tamu", sql);}
        if (!(Dapur).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "dapur = 'ya'";
            sql += "dapur";
            count_w += 1;
            Log.d("sql_id dapur",sql_id);
            Log.d("sql dapur", sql);}
        if (!(Mesin_cuci).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "mesin_cuci = 'ya'";
            sql += "mesin_cuci";
            count_w += 1;
            Log.d("sql_id mesin_cuci",sql_id);
            Log.d("sql mesin_cuci", sql);}
        if (!(Pembantu).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "pembantu = 'ya'";
            sql += "pembantu";
            count_w += 1;
            Log.d("sql_id pembantu",sql_id);
            Log.d("sql pembantu", sql);}
        if (!(Cuci_gosok).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "cuci_gosok = 'ya'";
            sql += "cuci_gosok";
            count_w += 1;
            Log.d("sql_id cuci_gosok",sql_id);
            Log.d("sql cuci_gosok", sql);}
        if (!(Cctv_security).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "cctv_security = 'ya'";
            sql += "cctv_security";
            count_w += 1;
            Log.d("sql_id cctv_security",sql_id);
            Log.d("sql cctv_security", sql);}
        if (!(Klm_renang).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "klm_renang = 'ya'";
            sql += "klm_renang";
            count_w += 1;
            Log.d("sql_id klm_renang",sql_id);
            Log.d("sql klm_renang", sql);}
        if (!(Parkir_motor).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "parkir_motor > 0";
            sql += "parkir_motor";
            count_w += 1;
            Log.d("sql_id motor",sql_id);
            Log.d("sql motor", sql);}
        if (!(Parkir_mobil).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "parkir_mobil >0";
            sql += "parkir_mobil";
            count_w += 1;
            Log.d("sql_id mobil",sql_id);
            Log.d("sql mobil", sql);}
        if (!(Tmpt_makan).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "tmpt_makan = 1";
            sql += "tmpt_makan";
            count_w += 1;
            Log.d("sql_id tmpt_makan",sql_id);
            Log.d("sql tmpt_makan", sql);}
        if (!(Warnet).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";
            }
            sql_id += "warnet = 1";
            sql += "warnet";
            count_w += 1;
            Log.d("sql_id warnet",sql_id);
            Log.d("sql warnet", sql);}
        if (!(Mall).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "mall = 1";
            sql += "mall";
            count_w += 1;
            Log.d("sql_id mall",sql_id);
            Log.d("sql mall", sql);}
        if (!(Apotek_dokter).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "apotek_dokter = 1";
            sql += "apotek_dokter";
            count_w += 1;
            Log.d("sql_id dokter",sql_id);
            Log.d("sql doketr", sql);}
        if (!(Atm_bank).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "atm_bank = 1 AND";
            sql += "atm_bank";
            count_w += 1;
            Log.d("sql_id bank",sql_id);
            Log.d("sql bank", sql);}
        if (!(Supermarket).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "supermarket = 1";
            sql += "supermarket";
            count_w += 1;
            Log.d("sql_id supermarket",sql_id);
            Log.d("sql supermarket", sql);}
        if (!(Kendaraan_umum).equalsIgnoreCase("")){
            if (!sql_id.equalsIgnoreCase("")) {
                sql_id += " AND ";
                sql += ",";}
            sql_id += "kendaraan_umum = 1";
            sql += "kendaraan_umum";
            count_w += 1;
            Log.d("sql_id kendaraan",sql_id);
            Log.d("sql kendaraan", sql);}
    }

    public List<identitas_kos> pra_topsis(String json_fas, String json_iden) {
        //public void pra_topsis(String json_fas, String json_iden) {
        sql = "id_tfn," + sql;
        String[] output = sql.split(",");
        Log.d("output length", "" + output.length);
        try {
            jsonObject = new JSONObject(json_fas);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            while (count < jsonArray.length()) {
                JSONObject JO = jsonArray.getJSONObject(count);
                fuzzyFasilitas_kos fuzzyFasilitasKos = new fuzzyFasilitas_kos();
                fuzzyFasilitasKos.setId_tfn(JO.getString("id_tfn"));
                for (int i = 1; i < output.length; i++) {
                    if (output[i].equalsIgnoreCase("jarak_kos")) {
                        fuzzyFasilitasKos.setJarak_kos(JO.getString("jarak_kos"));
                    }if (output[i].equalsIgnoreCase("biaya_kos")) {
                        fuzzyFasilitasKos.setBiaya_kos(JO.getString("biaya_kos"));
                    }if (output[i].equalsIgnoreCase("jk")) {
                        fuzzyFasilitasKos.setJk(JO.getString("jk"));
                    }if (output[i].equalsIgnoreCase("jmlh_penghuni")) {
                        fuzzyFasilitasKos.setJmlh_penghuni(JO.getString("jmlh_penghuni"));
                    }if (output[i].equalsIgnoreCase("meja_kursi")) {
                        fuzzyFasilitasKos.setMeja_kursi(JO.getString("meja_kursi"));
                    }if (output[i].equalsIgnoreCase("lemari")) {
                        fuzzyFasilitasKos.setLemari(JO.getString("lemari"));
                    }if (output[i].equalsIgnoreCase("tmpt_tidur")) {
                        fuzzyFasilitasKos.setTmpt_tidur(JO.getString("tmpt_tidur"));
                    }if (output[i].equalsIgnoreCase("km_luar")) {
                        fuzzyFasilitasKos.setKm_luar(JO.getString("km_luar"));
                    }if (output[i].equalsIgnoreCase("kipas_angin")) {
                        fuzzyFasilitasKos.setKipas_angin(JO.getString("kipas_angin"));
                    }if (output[i].equalsIgnoreCase("ac")) {
                        fuzzyFasilitasKos.setAc(JO.getString("ac"));
                    }if (output[i].equalsIgnoreCase("tv")) {
                        fuzzyFasilitasKos.setTv(JO.getString("tv"));
                    }if (output[i].equalsIgnoreCase("kulkas")) {
                        fuzzyFasilitasKos.setKulkas(JO.getString("kulkas"));
                    }if (output[i].equalsIgnoreCase("dispenser")) {
                        fuzzyFasilitasKos.setDispenser(JO.getString("dispenser"));
                    }if (output[i].equalsIgnoreCase("internet")) {
                        fuzzyFasilitasKos.setInternet(JO.getString("internet"));
                    }if (output[i].equalsIgnoreCase("ruang_tamu")) {
                        fuzzyFasilitasKos.setRuang_tamu(JO.getString("ruang_tamu"));
                    }if (output[i].equalsIgnoreCase("dapur")) {
                        fuzzyFasilitasKos.setDapur(JO.getString("dapur"));
                    }if (output[i].equalsIgnoreCase("mesin_cuci")) {
                        fuzzyFasilitasKos.setMesin_cuci(JO.getString("mesin_cuci"));
                    }if (output[i].equalsIgnoreCase("pembantu")) {
                        fuzzyFasilitasKos.setPembantu(JO.getString("pembantu"));
                    }if (output[i].equalsIgnoreCase("cuci_gosok")) {
                        fuzzyFasilitasKos.setCuci_gosok(JO.getString("cuci_gosok"));
                    }if (output[i].equalsIgnoreCase("cctv_security")) {
                        fuzzyFasilitasKos.setCctv_security(JO.getString("cctv_security"));
                    }if (output[i].equalsIgnoreCase("klm_renang")) {
                        fuzzyFasilitasKos.setKlm_renang(JO.getString("klm_renang"));
                    }if (output[i].equalsIgnoreCase("parkir_motor")) {
                        fuzzyFasilitasKos.setParkir_motor(JO.getString("parkir_motor"));
                    }if (output[i].equalsIgnoreCase("parkir_mobil")) {
                        fuzzyFasilitasKos.setParkir_mobil(JO.getString("parkir_mobil"));
                    }if (output[i].equalsIgnoreCase("tmpt_makan")) {
                        fuzzyFasilitasKos.setTmpt_makan(JO.getString("tmpt_makan"));
                    }if (output[i].equalsIgnoreCase("warnet")) {
                        fuzzyFasilitasKos.setWarnet(JO.getString("warnet"));
                    }if (output[i].equalsIgnoreCase("mall")) {
                        fuzzyFasilitasKos.setMall(JO.getString("mall"));
                    }if (output[i].equalsIgnoreCase("apotek_dokter")) {
                        fuzzyFasilitasKos.setApotek_dokter(JO.getString("apotek_dokter"));
                    }if (output[i].equalsIgnoreCase("atm_bank")) {
                        fuzzyFasilitasKos.setAtm_bank(JO.getString("atm_bank"));
                    }if (output[i].equalsIgnoreCase("supermarket")) {
                        fuzzyFasilitasKos.setSupermarket(JO.getString("supermarket"));
                    }if (output[i].equalsIgnoreCase("kendaraan_umum")) {
                        fuzzyFasilitasKos.setKendaraan_umum(JO.getString("kendaraan_umum"));
                    }
                }
                Log.d("id_tfn", fuzzyFasilitasKos.getId_tfn());
                list_fasilitasKos.add(fuzzyFasilitasKos);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            jsonObject = new JSONObject(json_iden);
            Log.d("identitas", json_iden);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            String id_kos, nama_kos, alamat_kos, id_pemilik, nama_pemilik, telp_pemilik, id_kelurahan, nama_kelurahan, nama_kecamtan,
                    id_universitas, universitas, id_tfn, jk, meja_kursi, lemari, tmpt_tidur, km_luar, kipas_angin, ac, tv, kulkas,
                    dispenser, internet, ruang_tamu, dapur, mesin_cuci, pembantu, cuci_gosok, cctv_security, klm_renang;
            int jarak_kos, biaya_kos, jmlh_penghuni, parkir_motor, parkir_mobil, tmpt_makan, warnet, mall, apotek_dokter, atm_bank, supermarket, kendaraan_umum;
            while (count < jsonArray.length()) {
                JSONObject JO = jsonArray.getJSONObject(count);
                identitas_kos identitasKos = new identitas_kos();
                id_kos = JO.getString("id_kos");identitasKos.setId_kos(id_kos);
                Log.d("id_kos", id_kos);
                nama_kos = JO.getString("nama_kos");identitasKos.setNama_kos(nama_kos);
                alamat_kos = JO.getString("alamat_kos");identitasKos.setAlamat_kos(alamat_kos);
                id_pemilik = JO.getString("id_pemilik");identitasKos.setId_pemilik(id_pemilik);
                nama_pemilik = JO.getString("nama_pemilik");identitasKos.setNama_pemilik(nama_pemilik);
                telp_pemilik = JO.getString("telp_pemilik");identitasKos.setTelp_pemilik(telp_pemilik);
                id_kelurahan = JO.getString("id_kelurahan");identitasKos.setId_kelurahan(id_kelurahan);
                nama_kelurahan = JO.getString("nama_kelurahan");identitasKos.setKelurahan(nama_kelurahan);
                nama_kecamtan = JO.getString("nama_kecamatan");identitasKos.setKecamatan(nama_kecamtan);
                id_tfn = JO.getString("id_tfn");identitasKos.setId_tfn(id_tfn);
                jarak_kos = JO.getInt("jarak_kos");identitasKos.setJarak_kos(jarak_kos);
                biaya_kos = JO.getInt("biaya_kos");identitasKos.setBiaya_kos(biaya_kos);
                jk = JO.getString("jk");identitasKos.setJk(jk);
                jmlh_penghuni = JO.getInt("jmlh_penghuni");identitasKos.setJmlh_penghuni(jmlh_penghuni);
                meja_kursi = JO.getString("meja_kursi");identitasKos.setMeja_kursi(meja_kursi);
                lemari = JO.getString("lemari");identitasKos.setLemari(lemari);
                tmpt_tidur = JO.getString("tmpt_tidur");identitasKos.setTmpt_tidur(tmpt_tidur);
                km_luar = JO.getString("km_luar");identitasKos.setKm_luar(km_luar);
                kipas_angin = JO.getString("kipas_angin");identitasKos.setKipas_angin(kipas_angin);
                ac = JO.getString("ac");identitasKos.setAc(ac);
                tv = JO.getString("tv");identitasKos.setTv(tv);
                kulkas = JO.getString("kulkas");identitasKos.setKulkas(kulkas);
                dispenser = JO.getString("dispenser");identitasKos.setDispenser(dispenser);
                internet = JO.getString("internet");identitasKos.setInternet(internet);
                ruang_tamu = JO.getString("ruang_tamu");identitasKos.setRuang_tamu(ruang_tamu);
                dapur = JO.getString("dapur");identitasKos.setDapur(dapur);
                mesin_cuci = JO.getString("mesin_cuci");identitasKos.setMesin_cuci(mesin_cuci);
                pembantu = JO.getString("pembantu");identitasKos.setPembantu(pembantu);
                cuci_gosok = JO.getString("cuci_gosok");identitasKos.setCuci_gosok(cuci_gosok);
                cctv_security = JO.getString("cctv_security");identitasKos.setCctv_security(cctv_security);
                klm_renang = JO.getString("klm_renang");identitasKos.setKlm_renang(klm_renang);
                parkir_motor = JO.getInt("parkir_motor");identitasKos.setParkir_motor(parkir_motor);
                parkir_mobil = JO.getInt("parkir_mobil");identitasKos.setParkir_mobil(parkir_mobil);
                tmpt_makan = JO.getInt("tmpt_makan");identitasKos.setTmpt_makan(tmpt_makan);
                warnet = JO.getInt("warnet");identitasKos.setWarnet(warnet);
                mall = JO.getInt("mall");identitasKos.setMall(mall);
                apotek_dokter = JO.getInt("apotek_dokter");identitasKos.setApotek_dokter(apotek_dokter);
                atm_bank = JO.getInt("atm_bank");identitasKos.setAtm_bank(atm_bank);
                supermarket = JO.getInt("supermarket");identitasKos.setSupermarket(supermarket);
                kendaraan_umum = JO.getInt("kendaraan_umum");identitasKos.setKendaraan_umum(kendaraan_umum);
                list_identitasKos.add(identitasKos);
                count++;
            }
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data [" + e.getMessage() + "] " + json_identitas);
        }

        Topsis = new topsis(list_fasilitasKos, list_identitasKos);

        double spare = 1 / ((double) count_w + 1), simpan_w = 1;
        Log.d("count w ", "" + count_w);
        Log.d("spare ", "" + spare);

        double[] D_max = new double[list_fasilitasKos.size()];
        double[] D_min = new double[list_fasilitasKos.size()];

        for (int i = count_w + 1; i > 1; i--) {
            if (!Jarak_kos.equalsIgnoreCase("")) {
                if (Integer.parseInt(Jarak_kos) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        String[] split_jarak_kos = list_fasilitasKos.get(j).getJarak_kos().split(",");
                        tfn_kriteria.setA(Double.valueOf(split_jarak_kos[0]));
                        tfn_kriteria.setB(Double.valueOf(split_jarak_kos[1]));
                        tfn_kriteria.setC(Double.valueOf(split_jarak_kos[3]));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_biaya_tfn(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_min(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_max(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Biaya_kos.equalsIgnoreCase("")) {
                if (Integer.parseInt(Biaya_kos) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        String[] split_biaya_kos = list_fasilitasKos.get(j).getBiaya_kos().split(",");
                        tfn_kriteria.setA(Double.valueOf(split_biaya_kos[0]));
                        tfn_kriteria.setB(Double.valueOf(split_biaya_kos[1]));
                        tfn_kriteria.setC(Double.valueOf(split_biaya_kos[3]));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_biaya_tfn(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_min(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_max(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Jk.equalsIgnoreCase("")) {
                if (Integer.parseInt(Jk) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        String[] split_jk = list_fasilitasKos.get(j).getJk().split(",");
                        tfn_kriteria.setA(Double.valueOf(split_jk[0]));
                        tfn_kriteria.setB(Double.valueOf(split_jk[1]));
                        tfn_kriteria.setC(Double.valueOf(split_jk[3]));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_keuntungan_tfn(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Jmlh_penghuni.equalsIgnoreCase("")) {
                if (Integer.parseInt(Jmlh_penghuni) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        String[] split_jmlh_penghuni = list_fasilitasKos.get(j).getJmlh_penghuni().split(",");
                        tfn_kriteria.setA(Double.valueOf(split_jmlh_penghuni[0]));
                        tfn_kriteria.setB(Double.valueOf(split_jmlh_penghuni[1]));
                        tfn_kriteria.setC(Double.valueOf(split_jmlh_penghuni[3]));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_keuntungan_tfn(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Meja_kursi.equalsIgnoreCase("")) {
                if (Integer.parseInt(Meja_kursi) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(i).getMeja_kursi()));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_keuntungan_crips(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Lemari.equalsIgnoreCase("")) {
                if (Integer.parseInt(Lemari) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(i).getLemari()));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_keuntungan_crips(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Tmpt_tidur.equalsIgnoreCase("")) {
                if (Integer.parseInt(Tmpt_tidur) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(i).getTmpt_tidur()));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_keuntungan_crips(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Km_luar.equalsIgnoreCase("")) {
                if (Integer.parseInt(Km_luar) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(i).getKm_luar()));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_keuntungan_crips(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Kipas_angin.equalsIgnoreCase("")) {
                if (Integer.parseInt(Kipas_angin) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(i).getKipas_angin()));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_keuntungan_crips(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Ac.equalsIgnoreCase("")) {
                if (Integer.parseInt(Ac) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(i).getAc()));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_keuntungan_crips(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Tv.equalsIgnoreCase("")) {
                if (Integer.parseInt(Tv) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(i).getTv()));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_keuntungan_crips(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Kulkas.equalsIgnoreCase("")) {
                if (Integer.parseInt(Kulkas) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(i).getKulkas()));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_keuntungan_crips(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Dispenser.equalsIgnoreCase("")) {
                if (Integer.parseInt(Dispenser) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(i).getDispenser()));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_keuntungan_crips(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Internet.equalsIgnoreCase("")) {
                if (Integer.parseInt(Internet) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(i).getInternet()));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_keuntungan_crips(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Ruang_tamu.equalsIgnoreCase("")) {
                if (Integer.parseInt(Ruang_tamu) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(i).getRuang_tamu()));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_keuntungan_crips(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Dapur.equalsIgnoreCase("")) {
                if (Integer.parseInt(Dapur) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(i).getDapur()));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_keuntungan_crips(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Mesin_cuci.equalsIgnoreCase("")) {
                if (Integer.parseInt(Mesin_cuci) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(i).getMesin_cuci()));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_keuntungan_crips(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Pembantu.equalsIgnoreCase("")) {
                if (Integer.parseInt(Pembantu) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(i).getPembantu()));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_keuntungan_crips(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                    for (int j = 0; j < hasil_max.length; j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Cuci_gosok.equalsIgnoreCase("")) {
                if (Integer.parseInt(Cuci_gosok) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(i).getCuci_gosok()));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_keuntungan_crips(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Cctv_security.equalsIgnoreCase("")) {
                if (Integer.parseInt(Cctv_security) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(i).getCctv_security()));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_keuntungan_crips(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Klm_renang.equalsIgnoreCase("")) {
                if (Integer.parseInt(Klm_renang) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        tfn_kriteria.setA(Double.valueOf(list_fasilitasKos.get(i).getKlm_renang()));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_keuntungan_crips(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Parkir_motor.equalsIgnoreCase("")) {
                if (Integer.parseInt(Parkir_motor) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        String[] split_jmlh_penghuni = list_fasilitasKos.get(j).getParkir_motor().split(",");
                        tfn_kriteria.setA(Double.valueOf(split_jmlh_penghuni[0]));
                        tfn_kriteria.setB(Double.valueOf(split_jmlh_penghuni[1]));
                        tfn_kriteria.setC(Double.valueOf(split_jmlh_penghuni[3]));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_keuntungan_tfn(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Parkir_motor.equalsIgnoreCase("")) {
                if (Integer.parseInt(Parkir_mobil) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        String[] split_jmlh_penghuni = list_fasilitasKos.get(j).getParkir_mobil().split(",");
                        tfn_kriteria.setA(Double.valueOf(split_jmlh_penghuni[0]));
                        tfn_kriteria.setB(Double.valueOf(split_jmlh_penghuni[1]));
                        tfn_kriteria.setC(Double.valueOf(split_jmlh_penghuni[3]));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_keuntungan_tfn(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_max(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_min(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Tmpt_makan.equalsIgnoreCase("")) {
                if (Integer.parseInt(Tmpt_makan) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        String[] split_jmlh_penghuni = list_fasilitasKos.get(j).getTmpt_makan().split(",");
                        tfn_kriteria.setA(Double.valueOf(split_jmlh_penghuni[0]));
                        tfn_kriteria.setB(Double.valueOf(split_jmlh_penghuni[1]));
                        tfn_kriteria.setC(Double.valueOf(split_jmlh_penghuni[3]));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_biaya_tfn(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_min(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_max(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Warnet.equalsIgnoreCase("")) {
                if (Integer.parseInt(Warnet) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        String[] split_jmlh_penghuni = list_fasilitasKos.get(j).getWarnet().split(",");
                        tfn_kriteria.setA(Double.valueOf(split_jmlh_penghuni[0]));
                        tfn_kriteria.setB(Double.valueOf(split_jmlh_penghuni[1]));
                        tfn_kriteria.setC(Double.valueOf(split_jmlh_penghuni[3]));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_biaya_tfn(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_min(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_max(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Mall.equalsIgnoreCase("")) {
                if (Integer.parseInt(Mall) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        String[] split_jmlh_penghuni = list_fasilitasKos.get(j).getMall().split(",");
                        tfn_kriteria.setA(Double.valueOf(split_jmlh_penghuni[0]));
                        tfn_kriteria.setB(Double.valueOf(split_jmlh_penghuni[1]));
                        tfn_kriteria.setC(Double.valueOf(split_jmlh_penghuni[3]));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_biaya_tfn(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_min(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_max(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Apotek_dokter.equalsIgnoreCase("")) {
                if (Integer.parseInt(Apotek_dokter) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        String[] split_jmlh_penghuni = list_fasilitasKos.get(j).getApotek_dokter().split(",");
                        tfn_kriteria.setA(Double.valueOf(split_jmlh_penghuni[0]));
                        tfn_kriteria.setB(Double.valueOf(split_jmlh_penghuni[1]));
                        tfn_kriteria.setC(Double.valueOf(split_jmlh_penghuni[3]));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_biaya_tfn(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_min(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_max(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Atm_bank.equalsIgnoreCase("")) {
                if (Integer.parseInt(Atm_bank) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        String[] split_jmlh_penghuni = list_fasilitasKos.get(j).getAtm_bank().split(",");
                        tfn_kriteria.setA(Double.valueOf(split_jmlh_penghuni[0]));
                        tfn_kriteria.setB(Double.valueOf(split_jmlh_penghuni[1]));
                        tfn_kriteria.setC(Double.valueOf(split_jmlh_penghuni[3]));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_biaya_tfn(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_min(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_max(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Supermarket.equalsIgnoreCase("")) {
                if (Integer.parseInt(Supermarket) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        String[] split_jmlh_penghuni = list_fasilitasKos.get(j).getSupermarket().split(",");
                        tfn_kriteria.setA(Double.valueOf(split_jmlh_penghuni[0]));
                        tfn_kriteria.setB(Double.valueOf(split_jmlh_penghuni[1]));
                        tfn_kriteria.setC(Double.valueOf(split_jmlh_penghuni[3]));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_biaya_tfn(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_min(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_max(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            } else if (!Kendaraan_umum.equalsIgnoreCase("")) {
                if (Integer.parseInt(Kendaraan_umum) == i) {
                    tfn_kriteria bobot = new tfn_kriteria();
                    bobot.setA(simpan_w - (2 * spare));
                    bobot.setB(simpan_w - spare);
                    bobot.setC(simpan_w);
                    simpan_w -= spare;
                    List<tfn_kriteria> list = new ArrayList<tfn_kriteria>();
                    for (int j = 0; j < list_fasilitasKos.size(); j++) {
                        tfn_kriteria tfn_kriteria = new tfn_kriteria();
                        String[] split_jmlh_penghuni = list_fasilitasKos.get(j).getKendaraan_umum().split(",");
                        tfn_kriteria.setA(Double.valueOf(split_jmlh_penghuni[0]));
                        tfn_kriteria.setB(Double.valueOf(split_jmlh_penghuni[1]));
                        tfn_kriteria.setC(Double.valueOf(split_jmlh_penghuni[3]));
                        list.add(tfn_kriteria);
                    }
                    int[] mean = atribut_biaya_tfn(list, bobot);
                    double[] hasil_max = topsis.langkah_empat_min(list, mean[0]);
                    double[] hasil_min = topsis.langkah_empat_max(list, mean[1]);
                    for (int j = 0; j < list.size(); j++) {
                        D_max[j] += hasil_max[j];
                        D_min[j] += hasil_max[j];
                    }
                }
            }
        }
        List<identitas_kos> hasil_fuzzy_topsis = new ArrayList<identitas_kos>();
        hasil_fuzzy_topsis = topsis.langkah_lima(D_max, D_min);
        //hasil.add((identitas_kos) hasil_fuzzy_topsis);
        Intent intent = new Intent(cari_inputActivity.this, cari_daftarkosActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)hasil_fuzzy_topsis);
        intent.putExtra("BUNDLE",args);
        //intent.putParcelableArrayListExtra("hasil_topsis", hasil_fuzzy_topsis);
        //intent.putExtra("hasil_topsis", hasil_fuzzy_topsis.get(0));
        intent.putExtra("id_pencari", id_pencari);
        startActivityForResult(intent, 0);
        for (int i = 0; i < hasil_fuzzy_topsis.size(); i++) {
            Log.d("hasil_topsis", hasil_fuzzy_topsis.get(i).getNama_kos());
        }
        return hasil_fuzzy_topsis;
    }

    public static int[] atribut_biaya_tfn(List<tfn_kriteria> list, tfn_kriteria bobot) {
        list = topsis.langkah_satu_biayaA(list);
        list = topsis.langkah_dua(list, bobot);
        int[] mean = topsis.langkah_tiga(list);
        double[] D_max = topsis.langkah_empat_min(list, mean[0]);
        double[] D_min = topsis.langkah_empat_max(list, mean[1]);

        Log.d("atribut_biaya_tfn","D max " + D_max + " D min" + D_min);

        return mean;
    }

    public static int[] atribut_keuntungan_tfn(List<tfn_kriteria> list, tfn_kriteria bobot) {
        list = topsis.langkah_satu_keuntunganA(list);
        list = topsis.langkah_dua(list, bobot);
        int[] mean = topsis.langkah_tiga(list);
        double[] D_max = topsis.langkah_empat_max(list, mean[0]);
        double[] D_min = topsis.langkah_empat_min(list, mean[1]);

        Log.d("atribut_keuntungan_tfn","D max " + D_max + " D min" + D_min);

        return mean;
    }

    public static int[] atribut_keuntungan_crips(List<tfn_kriteria> list, tfn_kriteria bobot) {
        list = topsis.langkah_satu_keuntunganB(list);
        list = topsis.langkah_dua(list, bobot);
        int[] mean = topsis.langkah_tiga(list);
        double[] D_max = topsis.langkah_empat_max(list, mean[0]);
        double[] D_min = topsis.langkah_empat_min(list, mean[1]);

        Log.d("atribut_untung_crips","D max " + D_max + " D min" + D_min);

        return mean;
    }

    class Background extends AsyncTask<String, Void, String> {
        String JSON_STRING, url_fasilitas,url_identitas,metode;
        Context ctx;

        public Background(Context ctx) {this.ctx = ctx;}

        @Override
        protected void onPreExecute() {
            url_fasilitas = "http://mykos.stander.id/fasilitasKos_getByConditions.php";
            url_identitas = "http://mykos.stander.id/identitasKos_getByConditions.php";
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
                    String data = URLEncoder.encode("prioritas", "UTF-8") + "=" + URLEncoder.encode(sql_id, "UTF-8") + "&" +
                            URLEncoder.encode("kriteria", "UTF-8") + "=" + URLEncoder.encode(sql, "UTF-8");
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
            }else if (metode.equalsIgnoreCase("identitas")) {
                try {
                    URL url = new URL(url_identitas);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("prioritas", "UTF-8") + "=" + URLEncoder.encode(sql_id, "UTF-8") + "&" +
                            URLEncoder.encode("kriteria", "UTF-8") + "=" + URLEncoder.encode(sql, "UTF-8");
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
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {super.onProgressUpdate(values);}

        @Override
        protected void onPostExecute(String result) {
            if (metode.equalsIgnoreCase("fasilitas")) {
                json_fasilitas = result;
                if(json_fasilitas=="FAILURE"){
                    Log.d("json_fasilitas", json_fasilitas);
                    Toast.makeText(cari_inputActivity.this, "Tidak Ada Data", Toast.LENGTH_LONG).show();
                } else if(json_fasilitas!="FAILURE") {
                    Log.d("json_fasilitas", json_fasilitas);
                    Toast.makeText(cari_inputActivity.this, "Berhasil Mengubah Data Fuzzy Kos", Toast.LENGTH_LONG).show();
                }
            }else if (metode.equalsIgnoreCase("identitas")) {
                json_identitas = result;
                if(json_identitas=="FAILURE"){
                    Log.d("json_identitas", json_identitas);
                    Toast.makeText(cari_inputActivity.this, "Tidak Ada Data", Toast.LENGTH_LONG).show();
                } else if(json_identitas!="FAILURE") {
                    Log.d("json_identitas", json_identitas);
                    pra_topsis(json_fasilitas, json_identitas);
                    Toast.makeText(cari_inputActivity.this, "Berhasil Mengubah Data Kos", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
