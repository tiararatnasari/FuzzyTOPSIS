package com.example.tiararatna.mykos;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.tiararatna.adapter.DropDownSpinner;
import com.example.tiararatna.adapter.identitasKos_cariAdapter;
import com.example.tiararatna.fuzzy.Fuzzy;
import com.example.tiararatna.modul.fuzzyFasilitas_kos;
import com.example.tiararatna.modul.identitas_kos;
import com.example.tiararatna.modul.kecamatan;
import com.example.tiararatna.modul.kelurahan;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Tiara Ratna on 4/19/2016.
 */
public class kelola_detail_infokosActivity extends AppCompatActivity {
    private static Button save;
    private static ImageButton next, prev;
    int index_univ, index_kec, index_kel, index_univlama, index_keclama, index_kellama;
    identitas_kos identitasKos, identitasKos_ubah;
    fuzzyFasilitas_kos fuzzyFasilitasKos, fuzzyFasilitasKos_ubah;
    kecamatan Kecamatan = new kecamatan();
    kelurahan Kelurahan = new kelurahan();
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private ViewFlipper mViewFlipper;
    private Animation.AnimationListener mAnimationListener;
    private Context mContext;

    @SuppressWarnings("deprecation")
    private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());
    EditText nama_kos, alamat_kos, jarak_kos, biaya_kos, jmlh_penghuni, parkir_motor, parkir_mobil,
            tmpt_makan, warnet, mall, apotek_dokter, atm_bank, supermarket, kendaraan_umum;
    CheckBox meja_kursi, lemari, tmpt_tidur, km_luar, kipas_angin, ac, tv, kulkas, dispenser, internet, ruang_tamu,
            dapur, mesin_cuci, pembantu, cuci_gosok, cctv_security, klm_renang;
    Spinner kecamatan, kelurahan, univ, jk;
    String Id_kos, Nama_kos, Alamat_kos, Id_pemilik, Id_kelurahan, Id_universitas, Id_tfn, Jk,
            Meja_kursi, Lemari, Tmpt_tidur, Km_luar, Kipas_angin, Ac, Tv, Kulkas, Dispenser, Internet, Ruang_tamu,
            Dapur, Mesin_cuci, Pembantu, Cuci_gosok, Cctv_security, Klm_renang;
    int Jarak_kos, Biaya_kos, Jmlh_penghuni, Parkir_motor, Parkir_mobil, Tmpt_makan, Warnet, Mall, Apotek_dokter,
            Atm_bank, Supermarket, Kendaraan_umum;
    String Id_tfn_tfn, Jarak_kos_tfn, Biaya_kos_tfn, Jk_tfn, Jmlh_penghuni_tfn, Meja_kursi_tfn, Lemari_tfn,
            Tmpt_tidur_tfn, Km_luar_tfn, Kipas_angin_tfn, Ac_tfn, Tv_tfn, Kulkas_tfn, Dispenser_tfn, Internet_tfn,
            Ruang_tamu_tfn, Dapur_tfn, Mesin_cuci_tfn, Pembantu_tfn, Cuci_gosok_tfn, Cctv_security_tfn, Klm_renang_tfn,
            Parkir_motor_tfn, Parkir_mobil_tfn, Tmpt_makan_tfn, Warnet_tfn, Mall_tfn, Apotek_dokter_tfn,
            Atm_bank_tfn, Supermarket_tfn, Kendaraan_umum_tfn;
    String meja_kursi_ubah, lemari_ubah, tmpt_tidur_ubah, km_luar_ubah, kipas_angin_ubah, ac_ubah, tv_ubah, kulkas_ubah,
            dispenser_ubah, internet_ubah, ruang_tamu_ubah, dapur_ubah, mesin_cuci_ubah, pembantu_ubah, cuci_gosok_ubah,
            cctv_security_ubah, klm_renang_ubah;
    String[] universitas = {"Universitas Airlangga", "Institut Teknologi Sepuluh Nopember",
            "Universitas Negeri Surabaya", "Universitas Kristen Petra", "universitas Surabaya",
            "Universitas Ciputra", "Universitas Hang Tuah", "Universitas 17 Agustus 1945",
            "Universitas Katolik Widya Mandala", "Universitas Dr. Soetomo", "Universitas Wijaya Kusuma",
            "Universitas Muhammadiyah Surabaya", "Universitas Pembangunan Nasional Veteran Jatim",
            "Sekolah Tinggi Ilmu Ekonomi Indonesia", "Universitas PGRI Adi Buana"};
    String[] id = {"u01", "u02", "u03", "u04", "u05", "u06", "u07", "u08", "u09", "u10", "u11", "u12", "u13", "u14", "u15"};
    String[] spinner_idkec, spinner_namakec, spinner_idkel, spinner_idkec2, spinner_namakel, pilih_kelurahan;
    String metode, json_string, json_fas, json_kecamatan, id_kecamatan, nama_kecamatan, json_kelurahan, id_kelurahan, nama_kelurahan;
    JSONObject jsonObject;
    JSONArray jsonArray;
    static ArrayAdapter<CharSequence> adapter_jk;
    int index_jk;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_detail_infokos);
        mViewFlipper = (ViewFlipper) this.findViewById(R.id.view_flipper);

        metode = getIntent().getExtras().getString("metode");
        if (metode.equalsIgnoreCase("change")) {
            identitasKos = (identitas_kos) getIntent().getExtras().get("identitas_kos");
            json_fas = getIntent().getExtras().getString("fasilitas_kos");
            try {
                jsonObject = new JSONObject(json_fas);
                jsonArray = jsonObject.getJSONArray("server_response");
                JSONObject JO = jsonArray.getJSONObject(0);
                fuzzyFasilitasKos = new fuzzyFasilitas_kos();
                Id_tfn_tfn = JO.getString("id_tfn");
                fuzzyFasilitasKos.setId_tfn(Id_tfn_tfn);
                Biaya_kos_tfn = JO.getString("biaya_kos");
                fuzzyFasilitasKos.setBiaya_kos(Biaya_kos_tfn);
                Jk_tfn = JO.getString("jk");
                fuzzyFasilitasKos.setJk(Jk_tfn);
                Jmlh_penghuni_tfn = JO.getString("jmlh_penghuni");
                fuzzyFasilitasKos.setJmlh_penghuni(Jmlh_penghuni_tfn);
                Meja_kursi_tfn = JO.getString("meja_kursi");
                fuzzyFasilitasKos.setMeja_kursi(Meja_kursi_tfn);
                Lemari_tfn = JO.getString("lemari");
                fuzzyFasilitasKos.setLemari(Lemari_tfn);
                Tmpt_tidur_tfn = JO.getString("tmpt_tidur");
                fuzzyFasilitasKos.setTmpt_tidur(Tmpt_tidur_tfn);
                Km_luar_tfn = JO.getString("km_luar");
                fuzzyFasilitasKos.setKm_luar(Km_luar_tfn);
                Kipas_angin_tfn = JO.getString("kipas_angin");
                fuzzyFasilitasKos.setKipas_angin(Kipas_angin_tfn);
                Ac_tfn = JO.getString("ac");
                fuzzyFasilitasKos.setAc(Ac_tfn);
                Tv_tfn = JO.getString("tv");
                fuzzyFasilitasKos.setTv(Tv_tfn);
                Kulkas_tfn = JO.getString("kulkas");
                fuzzyFasilitasKos.setKulkas(Kulkas_tfn);
                Dispenser_tfn = JO.getString("dispenser");
                fuzzyFasilitasKos.setDispenser(Dispenser_tfn);
                Internet_tfn = JO.getString("internet");
                fuzzyFasilitasKos.setInternet(Internet_tfn);
                Ruang_tamu_tfn = JO.getString("ruang_tamu");
                fuzzyFasilitasKos.setRuang_tamu(Ruang_tamu_tfn);
                Dapur_tfn = JO.getString("dapur");
                fuzzyFasilitasKos.setDapur(Dapur_tfn);
                Mesin_cuci_tfn = JO.getString("mesin_cuci");
                fuzzyFasilitasKos.setMesin_cuci(Mesin_cuci_tfn);
                Pembantu_tfn = JO.getString("pembantu");
                fuzzyFasilitasKos.setPembantu(Pembantu_tfn);
                Cuci_gosok_tfn = JO.getString("cuci_gosok");
                fuzzyFasilitasKos.setCuci_gosok(Cuci_gosok_tfn);
                Cctv_security_tfn = JO.getString("cctv_security");
                fuzzyFasilitasKos.setCctv_security(Cctv_security_tfn);
                Klm_renang_tfn = JO.getString("klm_renang");
                fuzzyFasilitasKos.setKlm_renang(Klm_renang_tfn);
                Parkir_motor_tfn = JO.getString("parkir_motor");
                fuzzyFasilitasKos.setParkir_motor(Parkir_motor_tfn);
                Parkir_mobil_tfn = JO.getString("parkir_mobil");
                fuzzyFasilitasKos.setParkir_mobil(Parkir_mobil_tfn);
                Tmpt_makan_tfn = JO.getString("tmpt_makan");
                fuzzyFasilitasKos.setTmpt_makan(Tmpt_makan_tfn);
                Warnet_tfn = JO.getString("warnet");
                fuzzyFasilitasKos.setWarnet(Warnet_tfn);
                Mall_tfn = JO.getString("mall");
                fuzzyFasilitasKos.setMall(Mall_tfn);
                Apotek_dokter_tfn = JO.getString("apotek_dokter");
                fuzzyFasilitasKos.setApotek_dokter(Apotek_dokter_tfn);
                Atm_bank_tfn = JO.getString("atm_bank");
                fuzzyFasilitasKos.setAtm_bank(Atm_bank_tfn);
                Supermarket_tfn = JO.getString("supermarket");
                fuzzyFasilitasKos.setSupermarket(Supermarket_tfn);
                Kendaraan_umum_tfn = JO.getString("kendaraan_umum");
                fuzzyFasilitasKos.setKendaraan_umum(Kendaraan_umum_tfn);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (metode.equalsIgnoreCase("add")) {
            Id_pemilik = getIntent().getExtras().getString("id_pemilik");
        }
        json_kecamatan = getIntent().getExtras().getString("kecamatan");
        try {
            jsonObject = new JSONObject(json_kecamatan);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            spinner_idkec = new String[jsonArray.length()];
            spinner_namakec = new String[jsonArray.length()];
            while (count < jsonArray.length()) {
                JSONObject JO = jsonArray.getJSONObject(count);
                Kecamatan = new kecamatan();
                id_kecamatan = JO.getString("id_kecamatan");
                nama_kecamatan = JO.getString("nama_kecamatan");
                spinner_idkec[count] = id_kecamatan;
                spinner_namakec[count] = nama_kecamatan;
                count++;
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        json_kelurahan = getIntent().getExtras().getString("kelurahan");
        try {
            jsonObject = new JSONObject(json_kelurahan);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            spinner_idkel = new String[jsonArray.length()];
            spinner_idkec2 = new String[jsonArray.length()];
            spinner_namakel = new String[jsonArray.length()];
            while (count < jsonArray.length()) {
                JSONObject JO = jsonArray.getJSONObject(count);
                Kelurahan = new kelurahan();
                id_kelurahan = JO.getString("id_kelurahan");
                id_kecamatan = JO.getString("id_kecamatan");
                nama_kelurahan = JO.getString("nama_kelurahan");
                spinner_idkel[count] = id_kelurahan;
                spinner_idkec2[count] = id_kecamatan;
                spinner_namakel[count] = nama_kelurahan;
                count++;
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        nama_kos = (EditText) findViewById(R.id.f_kelola_nama);
        alamat_kos = (EditText) findViewById(R.id.f_kelola_alamat);
        kecamatan = (Spinner) findViewById(R.id.f_kelola_kecamatan);
        ArrayAdapter<String> adapter_kec = new ArrayAdapter<String>(kelola_detail_infokosActivity.this,
                android.R.layout.simple_spinner_item, spinner_namakec);
        kecamatan.setAdapter(adapter_kec);
        kecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                index_kec = kecamatan.getSelectedItemPosition();
                Toast.makeText(getBaseContext(), "You have selected item : " + spinner_idkec[index_kec], Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        kelurahan = (Spinner) findViewById(R.id.f_kelola_kelurahan);
        String dipilih = spinner_idkec[index_kec];
        pilih_kelurahan = new String[spinner_idkel.length];
        for (int i = 0; i < spinner_idkel.length; i++) {
            if (spinner_idkec2[i].equalsIgnoreCase(dipilih)) {
                pilih_kelurahan[i] = spinner_namakel[i];
            }
        }
        ArrayAdapter<String> adapter_kel = new ArrayAdapter<String>(kelola_detail_infokosActivity.this,
                android.R.layout.simple_spinner_item, spinner_namakel);
        kelurahan.setAdapter(adapter_kel);
        kelurahan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                index_kel = kelurahan.getSelectedItemPosition();
                Log.d("index kel", "" + index_kel);
                Toast.makeText(getBaseContext(), "You have selected item : " + spinner_namakel[index_kel], Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        biaya_kos = (EditText) findViewById(R.id.f_kelola_biaya);
        jk = (Spinner) findViewById(R.id.s_kelola_jk);
        adapter_jk = ArrayAdapter.createFromResource(kelola_detail_infokosActivity.this, R.array.jk_penghuni, android.R.layout.simple_spinner_item);
        adapter_jk.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jk.setAdapter(adapter_jk);
        jk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                index_jk = jk.getSelectedItemPosition();
                Jk = DropDownSpinner.jenisKelamin(index_jk);
            }
            public void onNothingSelected(AdapterView<?> arg0) {}
        });

        jmlh_penghuni = (EditText) findViewById(R.id.f_kelola_jmlpenghuni);
        meja_kursi = (CheckBox) findViewById(R.id.c_kelola_mejakursi);
        lemari = (CheckBox) findViewById(R.id.c_kelola_lemari);
        tmpt_tidur = (CheckBox) findViewById(R.id.c_kelola_tempattidur);
        km_luar = (CheckBox) findViewById(R.id.c_kelola_kmluar);
        kipas_angin = (CheckBox) findViewById(R.id.c_kelola_kipasangin);
        ac = (CheckBox) findViewById(R.id.c_kelola_ac);
        tv = (CheckBox) findViewById(R.id.c_kelola_tv);
        kulkas = (CheckBox) findViewById(R.id.c_kelola_kulkas);
        dispenser = (CheckBox) findViewById(R.id.c_kelola_dispenser);
        internet = (CheckBox) findViewById(R.id.c_kelola_internet);
        ruang_tamu = (CheckBox) findViewById(R.id.c_kelola_ruangtamu);
        dapur = (CheckBox) findViewById(R.id.c_kelola_dapur);
        mesin_cuci = (CheckBox) findViewById(R.id.c_kelola_mesincuci);
        pembantu = (CheckBox) findViewById(R.id.c_kelola_pembantu);
        cuci_gosok = (CheckBox) findViewById(R.id.c_kelola_cucigosok);
        cctv_security = (CheckBox) findViewById(R.id.c_kelola_cctv);
        klm_renang = (CheckBox) findViewById(R.id.c_kelola_kolamrenang);
        parkir_motor = (EditText) findViewById(R.id.f_kelola_parkirmotor);
        parkir_mobil = (EditText) findViewById(R.id.f_kelola_parkirmobil);
        tmpt_makan = (EditText) findViewById(R.id.f_kelola_restaurant);
        warnet = (EditText) findViewById(R.id.f_kelola_warnet);
        mall = (EditText) findViewById(R.id.f_kelola_mall);
        apotek_dokter = (EditText) findViewById(R.id.f_kelola_apotek);
        atm_bank = (EditText) findViewById(R.id.f_kelola_bank);
        supermarket = (EditText) findViewById(R.id.f_kelola_supermarket);
        kendaraan_umum = (EditText) findViewById(R.id.f_kelola_kendaraanumum);

        if (metode.equalsIgnoreCase("change")) {
            Id_kos = identitasKos.getId_kos();
            Log.d("id_kos", Id_kos);
            Nama_kos = identitasKos.getNama_kos();
            nama_kos.setText(Nama_kos);
            Log.d("nama_kos", Nama_kos);
            Alamat_kos = identitasKos.getAlamat_kos();
            alamat_kos.setText(Alamat_kos);
            Id_pemilik = identitasKos.getId_pemilik();
            Id_kelurahan = identitasKos.getId_kelurahan();
            for (int i = 0; i < spinner_idkel.length; i++) {
                if (Id_kelurahan.equalsIgnoreCase(spinner_idkel[i])) {
                    index_kellama = i;
                }
                for (int j = 0; j < spinner_idkec.length; j++) {
                    if (spinner_idkec2[i].equalsIgnoreCase(spinner_idkec[j])) {
                        index_keclama = i;
                    }
                }
            }
            kecamatan.setId(index_keclama);
            kelurahan.setId(index_kellama);
            Id_tfn = identitasKos.getId_tfn();
            Biaya_kos = identitasKos.getBiaya_kos();
            biaya_kos.setText(Integer.toString(Biaya_kos));
            Jk = identitasKos.getJk();
            if(Jk.equalsIgnoreCase("p")){
                jk.setId(0);
            } else if(Jk.equalsIgnoreCase("l")){
                jk.setId(1);
            } else if(Jk.equalsIgnoreCase("c")){
                jk.setId(2);
            }
            Jmlh_penghuni = identitasKos.getJmlh_penghuni();
            jmlh_penghuni.setText(Integer.toString(Jmlh_penghuni));
            Meja_kursi = identitasKos.getMeja_kursi();
            if (Meja_kursi.equalsIgnoreCase("y")) {
                meja_kursi.setChecked(true);
            } else if (Meja_kursi.equalsIgnoreCase("t")) {
                meja_kursi.setChecked(false);
            }
            Lemari = identitasKos.getLemari();
            if (Lemari.equalsIgnoreCase("y")) {
                lemari.setChecked(true);
            } else if (Lemari.equalsIgnoreCase("t")) {
                lemari.setChecked(false);
            }
            Tmpt_tidur = identitasKos.getTmpt_tidur();
            if (Tmpt_tidur.equalsIgnoreCase("y")) {
                tmpt_tidur.setChecked(true);
            } else if (Tmpt_tidur.equalsIgnoreCase("t")) {
                tmpt_tidur.setChecked(false);
            }
            Km_luar = identitasKos.getKm_luar();
            if (Km_luar.equalsIgnoreCase("y")) {
                km_luar.setChecked(true);
            } else if (Km_luar.equalsIgnoreCase("t")) {
                km_luar.setChecked(false);
            }
            Kipas_angin = identitasKos.getKipas_angin();
            if (Kipas_angin.equalsIgnoreCase("y")) {
                kipas_angin.setChecked(true);
            } else if (Kipas_angin.equalsIgnoreCase("t")) {
                kipas_angin.setChecked(false);
            }
            Ac = identitasKos.getAc();
            if (Ac.equalsIgnoreCase("y")) {
                ac.setChecked(true);
            } else if (Ac.equalsIgnoreCase("t")) {
                ac.setChecked(false);
            }
            Tv = identitasKos.getTv();
            if (Tv.equalsIgnoreCase("y")) {
                tv.setChecked(true);
            } else if (Tv.equalsIgnoreCase("t")) {
                tv.setChecked(false);
            }
            Kulkas = identitasKos.getKulkas();
            if (Kulkas.equalsIgnoreCase("y")) {
                kulkas.setChecked(true);
            } else if (Kulkas.equalsIgnoreCase("t")) {
                kulkas.setChecked(false);
            }
            Dispenser = identitasKos.getDispenser();
            if (Dispenser.equalsIgnoreCase("y")) {
                dispenser.setChecked(true);
            } else if (Dispenser.equalsIgnoreCase("t")) {
                dispenser.setChecked(false);
            }
            Internet = identitasKos.getInternet();
            if (Internet.equalsIgnoreCase("y")) {
                internet.setChecked(true);
            } else if (Internet.equalsIgnoreCase("t")) {
                internet.setChecked(false);
            }
            Ruang_tamu = identitasKos.getRuang_tamu();
            if (Ruang_tamu.equalsIgnoreCase("y")) {
                ruang_tamu.setChecked(true);
            } else if (Ruang_tamu.equalsIgnoreCase("t")) {
                ruang_tamu.setChecked(false);
            }
            Dapur = identitasKos.getDapur();
            if (Dapur.equalsIgnoreCase("y")) {
                dapur.setChecked(true);
            } else if (Dapur.equalsIgnoreCase("t")) {
                dapur.setChecked(false);
            }
            Mesin_cuci = identitasKos.getMesin_cuci();
            if (Mesin_cuci.equalsIgnoreCase("y")) {
                mesin_cuci.setChecked(true);
            } else if (Mesin_cuci.equalsIgnoreCase("t")) {
                lemari.setChecked(false);
            }
            Pembantu = identitasKos.getPembantu();
            if (Pembantu.equalsIgnoreCase("y")) {
                pembantu.setChecked(true);
            } else if (Pembantu.equalsIgnoreCase("t")) {
                pembantu.setChecked(false);
            }
            Cuci_gosok = identitasKos.getCuci_gosok();
            if (Cuci_gosok.equalsIgnoreCase("y")) {
                cuci_gosok.setChecked(true);
            } else if (Cuci_gosok.equalsIgnoreCase("t")) {
                cuci_gosok.setChecked(false);
            }
            Cctv_security = identitasKos.getCctv_security();
            if (Cctv_security.equalsIgnoreCase("y")) {
                cctv_security.setChecked(true);
            } else if (Cctv_security.equalsIgnoreCase("t")) {
                cctv_security.setChecked(false);
            }
            Klm_renang = identitasKos.getKlm_renang();
            if (Klm_renang.equalsIgnoreCase("y")) {
                klm_renang.setChecked(true);
            } else if (Klm_renang.equalsIgnoreCase("t")) {
                klm_renang.setChecked(false);
            }
            Parkir_motor = identitasKos.getParkir_motor();
            parkir_motor.setText(Integer.toString(Parkir_motor));
            Parkir_mobil = identitasKos.getParkir_mobil();
            parkir_mobil.setText(Integer.toString(Parkir_mobil));
            Tmpt_makan = identitasKos.getTmpt_makan();
            tmpt_makan.setText(Integer.toString(Tmpt_makan));
            Warnet = identitasKos.getWarnet();
            warnet.setText(Integer.toString(Warnet));
            Mall = identitasKos.getMall();
            mall.setText(Integer.toString(Mall));
            Apotek_dokter = identitasKos.getApotek_dokter();
            apotek_dokter.setText(Integer.toString(Apotek_dokter));
            Atm_bank = identitasKos.getAtm_bank();
            atm_bank.setText(Integer.toString(Atm_bank));
            Supermarket = identitasKos.getSupermarket();
            supermarket.setText(Integer.toString(Supermarket));
            Kendaraan_umum = identitasKos.getKendaraan_umum();
            kendaraan_umum.setText(Integer.toString(Kendaraan_umum));
        }
        onClickButtonListenerNext();
        onClickButtonListenerPrev();
        onClickButtonListenerSave();
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                // right to left swipe
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_in));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
                    mViewFlipper.showNext();
                    return true;
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_out));
                    mViewFlipper.showPrevious();
                    return true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }
    }

    public void onClickButtonListenerSave() {
        save = (Button) findViewById(R.id.b_kelola_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = nama_kos.getText().toString();
                String alamat = alamat_kos.getText().toString();
                String biaya = biaya_kos.getText().toString();
                String jnsklmin = Jk;
                String penghuni = jmlh_penghuni.getText().toString();
                String motor = parkir_motor.getText().toString();
                String mobil = parkir_mobil.getText().toString();
                String makan = tmpt_makan.getText().toString();
                String wrnet = warnet.getText().toString();
                String mll = mall.getText().toString();
                String dokter = apotek_dokter.getText().toString();
                String bank = atm_bank.getText().toString();
                String market = supermarket.getText().toString();
                String kendaraan = kendaraan_umum.getText().toString();

                if (nama.equalsIgnoreCase("") && alamat.equalsIgnoreCase("") && biaya.equalsIgnoreCase("") && jnsklmin.equalsIgnoreCase("") && penghuni.equalsIgnoreCase("") && motor.equalsIgnoreCase("") && mobil.equalsIgnoreCase("") && makan.equalsIgnoreCase("") && wrnet.equalsIgnoreCase("") && mll.equalsIgnoreCase("") && dokter.equalsIgnoreCase("") && bank.equalsIgnoreCase("") && market.equalsIgnoreCase("") && kendaraan.equalsIgnoreCase("")) {
                    Toast.makeText(kelola_detail_infokosActivity.this, "Data Kosong", Toast.LENGTH_LONG).show();
                }else if (nama.equalsIgnoreCase("") || alamat.equalsIgnoreCase("") || biaya.equalsIgnoreCase("") || jnsklmin.equalsIgnoreCase("") || penghuni.equalsIgnoreCase("") || motor.equalsIgnoreCase("") || mobil.equalsIgnoreCase("") || makan.equalsIgnoreCase("") || wrnet.equalsIgnoreCase("") || mll.equalsIgnoreCase("") || dokter.equalsIgnoreCase("") || bank.equalsIgnoreCase("") || market.equalsIgnoreCase("") || kendaraan.equalsIgnoreCase("")) {
                    Toast.makeText(kelola_detail_infokosActivity.this, "Data Belum Lengkap", Toast.LENGTH_LONG).show();
                }else if (!nama.equalsIgnoreCase("") && !alamat.equalsIgnoreCase("") && !biaya.equalsIgnoreCase("") && !jnsklmin.equalsIgnoreCase("") && !penghuni.equalsIgnoreCase("") && !motor.equalsIgnoreCase("") && !mobil.equalsIgnoreCase("") && !makan.equalsIgnoreCase("") && !wrnet.equalsIgnoreCase("") && !mll.equalsIgnoreCase("") && !dokter.equalsIgnoreCase("") && !bank.equalsIgnoreCase("") && !market.equalsIgnoreCase("") && !kendaraan.equalsIgnoreCase("")) {
                    if (metode.equalsIgnoreCase("add")) {
                        identitasKos = new identitas_kos();
                        identitasKos.setId_kos("");
                        identitasKos.setNama_kos(nama);
                        identitasKos.setAlamat_kos(alamat);
                        identitasKos.setId_pemilik(Id_pemilik);
                        identitasKos.setId_kelurahan(spinner_idkel[index_kel]);
                        identitasKos.setBiaya_kos(Integer.parseInt(biaya));
                        identitasKos.setJk(jnsklmin);
                        identitasKos.setJmlh_penghuni(Integer.parseInt(penghuni));
                        if (meja_kursi.isChecked()) {
                            identitasKos.setMeja_kursi("y");
                        } else {
                            identitasKos.setMeja_kursi("t");
                        }
                        if (lemari.isChecked()) {
                            identitasKos.setLemari("y");
                        } else {
                            identitasKos.setLemari("t");
                        }
                        if (tmpt_tidur.isChecked()) {
                            identitasKos.setTmpt_tidur("y");
                        } else {
                            identitasKos.setTmpt_tidur("t");
                        }
                        if (km_luar.isChecked()) {
                            identitasKos.setKm_luar("y");
                        } else {
                            identitasKos.setKm_luar("t");
                        }
                        if (kipas_angin.isChecked()) {
                            identitasKos.setKipas_angin("y");
                        } else {
                            identitasKos.setKipas_angin("t");
                        }
                        if (ac.isChecked()) {
                            identitasKos.setAc("y");
                        } else {
                            identitasKos.setAc("t");
                        }
                        if (tv.isChecked()) {
                            identitasKos.setTv("y");
                        } else {
                            identitasKos.setTv("t");
                        }
                        if (kulkas.isChecked()) {
                            identitasKos.setKulkas("y");
                        } else {
                            identitasKos.setKulkas("t");
                        }
                        if (dispenser.isChecked()) {
                            identitasKos.setDispenser("y");
                        } else {
                            identitasKos.setDispenser("t");
                        }
                        if (internet.isChecked()) {
                            identitasKos.setInternet("y");
                        } else {
                            identitasKos.setInternet("t");
                        }
                        if (ruang_tamu.isChecked()) {
                            identitasKos.setRuang_tamu("y");
                        } else {
                            identitasKos.setRuang_tamu("t");
                        }
                        if (dapur.isChecked()) {
                            identitasKos.setDapur("y");
                        } else {
                            identitasKos.setDapur("t");
                        }
                        if (mesin_cuci.isChecked()) {
                            identitasKos.setMesin_cuci("y");
                        } else {
                            identitasKos.setMesin_cuci("t");
                        }
                        if (pembantu.isChecked()) {
                            identitasKos.setPembantu("y");
                        } else {
                            identitasKos.setPembantu("t");
                        }
                        if (cuci_gosok.isChecked()) {
                            identitasKos.setCuci_gosok("y");
                        } else {
                            identitasKos.setCuci_gosok("t");
                        }
                        if (cctv_security.isChecked()) {
                            identitasKos.setCctv_security("y");
                        } else {
                            identitasKos.setCctv_security("t");
                        }
                        if (klm_renang.isChecked()) {
                            identitasKos.setKlm_renang("y");
                        } else {
                            identitasKos.setKlm_renang("t");
                        }
                        identitasKos.setParkir_motor(Integer.parseInt(motor));
                        identitasKos.setParkir_mobil(Integer.parseInt(mobil));
                        identitasKos.setTmpt_makan(Integer.parseInt(makan));
                        identitasKos.setWarnet(Integer.parseInt(wrnet));
                        identitasKos.setMall(Integer.parseInt(mll));
                        identitasKos.setApotek_dokter(Integer.parseInt(dokter));
                        identitasKos.setAtm_bank(Integer.parseInt(bank));
                        identitasKos.setSupermarket(Integer.parseInt(market));
                        identitasKos.setKendaraan_umum(Integer.parseInt(kendaraan));

                        fuzzyFasilitasKos = new fuzzyFasilitas_kos();
                        fuzzyFasilitasKos.setBiaya_kos(Fuzzy.biaya(identitasKos.getBiaya_kos()));
                        fuzzyFasilitasKos.setJk(Fuzzy.jk(identitasKos.getJk()));
                        fuzzyFasilitasKos.setJmlh_penghuni(Fuzzy.penghuni(identitasKos.getJmlh_penghuni()));
                        fuzzyFasilitasKos.setMeja_kursi(Fuzzy.fasilitas(identitasKos.getMeja_kursi()));
                        fuzzyFasilitasKos.setLemari(Fuzzy.fasilitas(identitasKos.getLemari()));
                        fuzzyFasilitasKos.setTmpt_tidur(Fuzzy.fasilitas(identitasKos.getTmpt_tidur()));
                        fuzzyFasilitasKos.setKm_luar(Fuzzy.fasilitas(identitasKos.getKm_luar()));
                        fuzzyFasilitasKos.setKipas_angin(Fuzzy.fasilitas(identitasKos.getKipas_angin()));
                        fuzzyFasilitasKos.setAc(Fuzzy.fasilitas(identitasKos.getAc()));
                        fuzzyFasilitasKos.setTv(Fuzzy.fasilitas(identitasKos.getTv()));
                        fuzzyFasilitasKos.setKulkas(Fuzzy.fasilitas(identitasKos.getKulkas()));
                        fuzzyFasilitasKos.setDispenser(Fuzzy.fasilitas(identitasKos.getDispenser()));
                        fuzzyFasilitasKos.setInternet(Fuzzy.fasilitas(identitasKos.getInternet()));
                        fuzzyFasilitasKos.setRuang_tamu(Fuzzy.fasilitas(identitasKos.getRuang_tamu()));
                        fuzzyFasilitasKos.setDapur(Fuzzy.fasilitas(identitasKos.getDapur()));
                        fuzzyFasilitasKos.setMesin_cuci(Fuzzy.fasilitas(identitasKos.getMesin_cuci()));
                        fuzzyFasilitasKos.setPembantu(Fuzzy.fasilitas(identitasKos.getPembantu()));
                        fuzzyFasilitasKos.setCuci_gosok(Fuzzy.fasilitas(identitasKos.getCuci_gosok()));
                        fuzzyFasilitasKos.setCctv_security(Fuzzy.fasilitas(identitasKos.getCctv_security()));
                        fuzzyFasilitasKos.setKlm_renang(Fuzzy.fasilitas(identitasKos.getKlm_renang()));
                        fuzzyFasilitasKos.setParkir_motor(Fuzzy.parkir_motor(identitasKos.getParkir_motor()));
                        fuzzyFasilitasKos.setParkir_mobil(Fuzzy.parkir_mobil(identitasKos.getParkir_mobil()));
                        fuzzyFasilitasKos.setTmpt_makan(Fuzzy.fasum(identitasKos.getTmpt_makan()));
                        fuzzyFasilitasKos.setWarnet(Fuzzy.fasum(identitasKos.getWarnet()));
                        fuzzyFasilitasKos.setMall(Fuzzy.fasum(identitasKos.getMall()));
                        fuzzyFasilitasKos.setApotek_dokter(Fuzzy.fasum(identitasKos.getApotek_dokter()));
                        fuzzyFasilitasKos.setAtm_bank(Fuzzy.fasum(identitasKos.getAtm_bank()));
                        fuzzyFasilitasKos.setSupermarket(Fuzzy.fasum(identitasKos.getSupermarket()));
                        fuzzyFasilitasKos.setKendaraan_umum(Fuzzy.fasum(identitasKos.getKendaraan_umum()));
                        new Background(kelola_detail_infokosActivity.this).execute();
                    } else if (metode.equalsIgnoreCase("change")) {
                        identitasKos_ubah = new identitas_kos();
                        identitasKos_ubah.setId_kos(identitasKos.getId_kos());
                        identitasKos_ubah.setNama_kos("");
                        identitasKos_ubah.setAlamat_kos("");
                        identitasKos_ubah.setId_pemilik(identitasKos.getId_pemilik());
                        identitasKos_ubah.setId_kelurahan("");
                        identitasKos_ubah.setId_tfn(identitasKos.getId_tfn());
                        identitasKos_ubah.setBiaya_kos(0);
                        identitasKos_ubah.setJk("");
                        identitasKos_ubah.setJmlh_penghuni(0);
                        identitasKos_ubah.setMeja_kursi("");
                        identitasKos_ubah.setLemari("");
                        identitasKos_ubah.setTmpt_tidur("");
                        identitasKos_ubah.setKm_luar("");
                        identitasKos_ubah.setKipas_angin("");
                        identitasKos_ubah.setAc("");
                        identitasKos_ubah.setTv("");
                        identitasKos_ubah.setKulkas("");
                        identitasKos_ubah.setDispenser("");
                        identitasKos_ubah.setInternet("");
                        identitasKos_ubah.setRuang_tamu("");
                        identitasKos_ubah.setDapur("");
                        identitasKos_ubah.setMesin_cuci("");
                        identitasKos_ubah.setPembantu("");
                        identitasKos_ubah.setCuci_gosok("");
                        identitasKos_ubah.setCctv_security("");
                        identitasKos_ubah.setKlm_renang("");
                        identitasKos_ubah.setParkir_motor(0);
                        identitasKos_ubah.setParkir_mobil(0);
                        identitasKos_ubah.setTmpt_makan(0);
                        identitasKos_ubah.setWarnet(0);
                        identitasKos_ubah.setMall(0);
                        identitasKos_ubah.setApotek_dokter(0);
                        identitasKos_ubah.setAtm_bank(0);
                        identitasKos_ubah.setSupermarket(0);
                        identitasKos_ubah.setKendaraan_umum(0);

                        fuzzyFasilitasKos_ubah = new fuzzyFasilitas_kos();
                        fuzzyFasilitasKos_ubah.setId_tfn(fuzzyFasilitasKos.getId_tfn());
                        fuzzyFasilitasKos_ubah.setBiaya_kos("");
                        fuzzyFasilitasKos_ubah.setJk("");
                        fuzzyFasilitasKos_ubah.setJmlh_penghuni("");
                        fuzzyFasilitasKos_ubah.setMeja_kursi("");
                        fuzzyFasilitasKos_ubah.setLemari("");
                        fuzzyFasilitasKos_ubah.setTmpt_tidur("");
                        fuzzyFasilitasKos_ubah.setKm_luar("");
                        fuzzyFasilitasKos_ubah.setKipas_angin("");
                        fuzzyFasilitasKos_ubah.setAc("");
                        fuzzyFasilitasKos_ubah.setTv("");
                        fuzzyFasilitasKos_ubah.setKulkas("");
                        fuzzyFasilitasKos_ubah.setDispenser("");
                        fuzzyFasilitasKos_ubah.setInternet("");
                        fuzzyFasilitasKos_ubah.setRuang_tamu("");
                        fuzzyFasilitasKos_ubah.setDapur("");
                        fuzzyFasilitasKos_ubah.setMesin_cuci("");
                        fuzzyFasilitasKos_ubah.setPembantu("");
                        fuzzyFasilitasKos_ubah.setCuci_gosok("");
                        fuzzyFasilitasKos_ubah.setCctv_security("");
                        fuzzyFasilitasKos_ubah.setKlm_renang("");
                        fuzzyFasilitasKos_ubah.setParkir_motor("");
                        fuzzyFasilitasKos_ubah.setParkir_mobil("");
                        fuzzyFasilitasKos_ubah.setTmpt_makan("");
                        fuzzyFasilitasKos_ubah.setWarnet("");
                        fuzzyFasilitasKos_ubah.setMall("");
                        fuzzyFasilitasKos_ubah.setApotek_dokter("");
                        fuzzyFasilitasKos_ubah.setAtm_bank("");
                        fuzzyFasilitasKos_ubah.setSupermarket("");
                        fuzzyFasilitasKos_ubah.setKendaraan_umum("");

                        if (!identitasKos.getNama_kos().equalsIgnoreCase(nama)) {
                            identitasKos_ubah.setNama_kos(nama);
                            Log.d("nama", nama);
                        }
                        if (!identitasKos.getAlamat_kos().equalsIgnoreCase(alamat)) {
                            identitasKos_ubah.setAlamat_kos(alamat);
                            Log.d("Alamat", alamat);
                            //getLocationInfo(identitasKos_ubah.getAlamat_kos());
                        }
                        if (!identitasKos.getId_kelurahan().equalsIgnoreCase(spinner_idkel[index_kel])) {
                            identitasKos_ubah.setId_kelurahan(spinner_idkel[index_kel]);
                            Log.d("id_kelurahan", spinner_idkel[index_kel]);
                            //}if (identitasKos.getJarak_kos() != Integer.parseInt(jarak_kos.getText().toString())) {
                            //    identitasKos_ubah.setJarak_kos(Integer.parseInt(jarak_kos.getText().toString()));
                            //    fuzzyFasilitasKos_ubah.setJarak_kos(Fuzzy.jarak(identitasKos_ubah.getJarak_kos()));
                            //    Log.d("jarak", jarak_kos.getText().toString());
                        }
                        if (identitasKos.getBiaya_kos() != Integer.parseInt(biaya)) {
                            identitasKos_ubah.setBiaya_kos(Integer.parseInt(biaya));
                            fuzzyFasilitasKos_ubah.setBiaya_kos(Fuzzy.biaya(identitasKos_ubah.getBiaya_kos()));
                            Log.d("biaya", biaya);
                        }
                        if (!identitasKos.getJk().equalsIgnoreCase(jnsklmin)) {
                            identitasKos_ubah.setJk(jnsklmin);
                            fuzzyFasilitasKos_ubah.setJk(Fuzzy.jk(identitasKos_ubah.getJk()));
                            Log.d("jk", jnsklmin);
                        }
                        if (identitasKos.getJmlh_penghuni() != (Integer.parseInt(penghuni))) {
                            identitasKos_ubah.setJmlh_penghuni(Integer.parseInt(penghuni));
                            fuzzyFasilitasKos_ubah.setJmlh_penghuni(Fuzzy.penghuni(identitasKos_ubah.getJmlh_penghuni()));
                            Log.d("jmlh_penghuni", penghuni);
                        }
                        if (meja_kursi.isChecked()) {
                            meja_kursi_ubah = "y";
                        } else {
                            meja_kursi_ubah = "t";
                        }
                        if (!identitasKos.getMeja_kursi().equalsIgnoreCase(meja_kursi_ubah)) {
                            identitasKos_ubah.setMeja_kursi(meja_kursi_ubah);
                            fuzzyFasilitasKos_ubah.setMeja_kursi(Fuzzy.fasilitas(identitasKos_ubah.getMeja_kursi()));
                            Log.d("meja_kursi", meja_kursi_ubah);
                        }
                        if (lemari.isChecked()) {
                            lemari_ubah = "y";
                        } else {
                            lemari_ubah = "t";
                        }
                        if (!identitasKos.getLemari().equalsIgnoreCase(lemari_ubah)) {
                            identitasKos_ubah.setLemari(lemari_ubah);
                            fuzzyFasilitasKos_ubah.setLemari(Fuzzy.fasilitas(identitasKos_ubah.getLemari()));
                            Log.d("lemari", lemari_ubah);
                        }
                        if (tmpt_tidur.isChecked()) {
                            tmpt_tidur_ubah = "y";
                        } else {
                            tmpt_tidur_ubah = "t";
                        }
                        if (!identitasKos.getTmpt_tidur().equalsIgnoreCase(tmpt_tidur_ubah)) {
                            identitasKos_ubah.setTmpt_tidur(tmpt_tidur_ubah);
                            fuzzyFasilitasKos_ubah.setTmpt_tidur(Fuzzy.fasilitas(identitasKos_ubah.getTmpt_tidur()));
                            Log.d("tmpt_tidur", tmpt_tidur_ubah);
                        }
                        if (km_luar.isChecked()) {
                            km_luar_ubah = "y";
                        } else {
                            km_luar_ubah = "t";
                        }
                        if (!identitasKos.getKm_luar().equalsIgnoreCase(km_luar_ubah)) {
                            identitasKos_ubah.setKm_luar(km_luar_ubah);
                            fuzzyFasilitasKos_ubah.setKm_luar(Fuzzy.fasilitas(identitasKos_ubah.getKm_luar()));
                            Log.d("km_luar", km_luar_ubah);
                        }
                        if (kipas_angin.isChecked()) {
                            kipas_angin_ubah = "y";
                        } else {
                            kipas_angin_ubah = "t";
                        }
                        if (!identitasKos.getKipas_angin().equalsIgnoreCase(kipas_angin_ubah)) {
                            identitasKos_ubah.setKipas_angin(kipas_angin_ubah);
                            fuzzyFasilitasKos_ubah.setKipas_angin(Fuzzy.fasilitas(identitasKos_ubah.getKipas_angin()));
                            Log.d("kipas_angin", kipas_angin_ubah);
                        }
                        if (ac.isChecked()) {
                            ac_ubah = "y";
                        } else {
                            ac_ubah = "t";
                        }
                        if (!identitasKos.getAc().equalsIgnoreCase(ac_ubah)) {
                            identitasKos_ubah.setAc(ac_ubah);
                            fuzzyFasilitasKos_ubah.setAc(Fuzzy.fasilitas(identitasKos_ubah.getAc()));
                            Log.d("ac", ac_ubah);
                        }
                        if (tv.isChecked()) {
                            tv_ubah = "y";
                        } else {
                            tv_ubah = "t";
                        }
                        if (!identitasKos.getTv().equalsIgnoreCase(tv_ubah)) {
                            identitasKos_ubah.setTv(tv_ubah);
                            fuzzyFasilitasKos_ubah.setTv(Fuzzy.fasilitas(identitasKos_ubah.getTv()));
                            Log.d("tv", tv_ubah);
                        }
                        if (kulkas.isChecked()) {
                            kulkas_ubah = "y";
                        } else {
                            kulkas_ubah = "t";
                        }
                        if (!identitasKos.getKulkas().equalsIgnoreCase(kulkas_ubah)) {
                            identitasKos_ubah.setKulkas(kulkas_ubah);
                            fuzzyFasilitasKos_ubah.setKulkas(Fuzzy.fasilitas(identitasKos_ubah.getKulkas()));
                            Log.d("kulkas", kulkas_ubah);
                        }
                        if (dispenser.isChecked()) {
                            dispenser_ubah = "y";
                        } else {
                            dispenser_ubah = "t";
                        }
                        if (!identitasKos.getDispenser().equalsIgnoreCase(dispenser_ubah)) {
                            identitasKos_ubah.setDispenser(dispenser_ubah);
                            fuzzyFasilitasKos_ubah.setDispenser(Fuzzy.fasilitas(identitasKos_ubah.getDispenser()));
                            Log.d("dispenser", dispenser_ubah);
                        }
                        if (internet.isChecked()) {
                            internet_ubah = "y";
                        } else {
                            internet_ubah = "t";
                        }
                        if (!identitasKos.getInternet().equalsIgnoreCase(internet_ubah)) {
                            identitasKos_ubah.setInternet(internet_ubah);
                            fuzzyFasilitasKos_ubah.setInternet(Fuzzy.fasilitas(identitasKos_ubah.getInternet()));
                            Log.d("internet", internet_ubah);
                        }
                        if (ruang_tamu.isChecked()) {
                            ruang_tamu_ubah = "y";
                        } else {
                            ruang_tamu_ubah = "t";
                        }
                        if (!identitasKos.getRuang_tamu().equalsIgnoreCase(ruang_tamu_ubah)) {
                            identitasKos_ubah.setRuang_tamu(ruang_tamu_ubah);
                            fuzzyFasilitasKos_ubah.setRuang_tamu(Fuzzy.fasilitas(identitasKos_ubah.getRuang_tamu()));
                            Log.d("ruang_tamu", ruang_tamu_ubah);
                        }
                        if (dapur.isChecked()) {
                            dapur_ubah = "y";
                        } else {
                            dapur_ubah = "t";
                        }
                        if (!identitasKos.getDapur().equalsIgnoreCase(dapur_ubah)) {
                            identitasKos_ubah.setDapur(dapur_ubah);
                            fuzzyFasilitasKos_ubah.setDapur(Fuzzy.fasilitas(identitasKos_ubah.getDapur()));
                            Log.d("dapur", dapur_ubah);
                        }
                        if (mesin_cuci.isChecked()) {
                            mesin_cuci_ubah = "y";
                        } else {
                            mesin_cuci_ubah = "t";
                        }
                        if (!identitasKos.getMesin_cuci().equalsIgnoreCase(mesin_cuci_ubah)) {
                            identitasKos_ubah.setMesin_cuci(mesin_cuci_ubah);
                            fuzzyFasilitasKos_ubah.setMesin_cuci(Fuzzy.fasilitas(identitasKos_ubah.getMesin_cuci()));
                            Log.d("mesin_cuci", mesin_cuci_ubah);
                        }
                        if (pembantu.isChecked()) {
                            pembantu_ubah = "y";
                        } else {
                            pembantu_ubah = "t";
                        }
                        if (!identitasKos.getPembantu().equalsIgnoreCase(pembantu_ubah)) {
                            identitasKos_ubah.setPembantu(pembantu_ubah);
                            fuzzyFasilitasKos_ubah.setPembantu(Fuzzy.fasilitas(identitasKos_ubah.getPembantu()));
                            Log.d("pembantu", pembantu_ubah);
                        }
                        if (cuci_gosok.isChecked()) {
                            cuci_gosok_ubah = "y";
                        } else {
                            cuci_gosok_ubah = "t";
                        }
                        if (!identitasKos.getCuci_gosok().equalsIgnoreCase(cuci_gosok_ubah)) {
                            identitasKos_ubah.setCuci_gosok(cuci_gosok_ubah);
                            fuzzyFasilitasKos_ubah.setCuci_gosok(Fuzzy.fasilitas(identitasKos_ubah.getCuci_gosok()));
                            Log.d("cuci_gosok", cuci_gosok_ubah);
                        }
                        if (cctv_security.isChecked()) {
                            cctv_security_ubah = "y";
                        } else {
                            cctv_security_ubah = "t";
                        }
                        if (!identitasKos.getCctv_security().equalsIgnoreCase(cctv_security_ubah)) {
                            identitasKos_ubah.setCctv_security(cctv_security_ubah);
                            fuzzyFasilitasKos_ubah.setCctv_security(Fuzzy.fasilitas(identitasKos_ubah.getCctv_security()));
                            Log.d("cctv", cctv_security_ubah);
                        }
                        if (klm_renang.isChecked()) {
                            klm_renang_ubah = "y";
                        } else {
                            klm_renang_ubah = "t";
                        }
                        if (!identitasKos.getKlm_renang().equalsIgnoreCase(klm_renang_ubah)) {
                            identitasKos_ubah.setKlm_renang(klm_renang_ubah);
                            fuzzyFasilitasKos_ubah.setKlm_renang(Fuzzy.fasilitas(identitasKos_ubah.getKlm_renang()));
                            Log.d("klm_renang", klm_renang_ubah);
                        }
                        if (identitasKos.getParkir_motor() != Integer.parseInt(motor)) {
                            identitasKos_ubah.setParkir_motor(Integer.parseInt(motor));
                            fuzzyFasilitasKos_ubah.setParkir_motor(Fuzzy.parkir_motor(identitasKos_ubah.getParkir_motor()));
                            Log.d("parkir_motor", motor);
                        }
                        if (identitasKos.getParkir_mobil() != Integer.parseInt(mobil)) {
                            identitasKos_ubah.setParkir_mobil(Integer.parseInt(mobil));
                            fuzzyFasilitasKos_ubah.setParkir_mobil(Fuzzy.parkir_mobil(identitasKos_ubah.getParkir_mobil()));
                            Log.d("parkir_mobil", mobil);
                        }
                        if (identitasKos.getTmpt_makan() != Integer.parseInt(makan)) {
                            identitasKos_ubah.setTmpt_makan(Integer.parseInt(makan));
                            fuzzyFasilitasKos_ubah.setTmpt_makan(Fuzzy.fasum(identitasKos_ubah.getTmpt_makan()));
                            Log.d("tmpt_makan", makan);
                        }
                        if (identitasKos.getWarnet() != Integer.parseInt(wrnet)) {
                            identitasKos_ubah.setWarnet(Integer.parseInt(wrnet));
                            fuzzyFasilitasKos_ubah.setWarnet(Fuzzy.fasum(identitasKos_ubah.getWarnet()));
                            Log.d("warnet", wrnet);
                        }
                        if (identitasKos.getMall() != Integer.parseInt(mll)) {
                            identitasKos_ubah.setMall(Integer.parseInt(mll));
                            fuzzyFasilitasKos_ubah.setMall(Fuzzy.fasum(identitasKos_ubah.getMall()));
                            Log.d("mall", mll);
                        }
                        if (identitasKos.getApotek_dokter() != Integer.parseInt(dokter)) {
                            identitasKos_ubah.setApotek_dokter(Integer.parseInt(dokter));
                            fuzzyFasilitasKos_ubah.setApotek_dokter(Fuzzy.fasum(identitasKos_ubah.getApotek_dokter()));
                            Log.d("apotek", dokter);
                        }
                        if (identitasKos.getAtm_bank() != Integer.parseInt(bank)) {
                            identitasKos_ubah.setAtm_bank(Integer.parseInt(bank));
                            fuzzyFasilitasKos_ubah.setAtm_bank(Fuzzy.fasum(identitasKos_ubah.getAtm_bank()));
                            Log.d("atm", bank);
                        }
                        if (identitasKos.getSupermarket() != Integer.parseInt(market)) {
                            identitasKos_ubah.setSupermarket(Integer.parseInt(market));
                            fuzzyFasilitasKos_ubah.setSupermarket(Fuzzy.fasum(identitasKos_ubah.getSupermarket()));
                            Log.d("supermarket", market);
                        }
                        if (identitasKos.getKendaraan_umum() != Integer.parseInt(kendaraan)) {
                            identitasKos_ubah.setKendaraan_umum(Integer.parseInt(kendaraan));
                            fuzzyFasilitasKos_ubah.setKendaraan_umum(Fuzzy.fasum(identitasKos_ubah.getKendaraan_umum()));
                            Log.d("kendaraan umum", kendaraan);
                        }
                        new Background(kelola_detail_infokosActivity.this).execute();
                    }
                }
            }
        });
    }

    //public GeoPoint getLocationFromAddress(String strAddress) {
    //    Geocoder coder = new Geocoder(this);
    //    List<Address> address;
    //    GeoPoint p1 = null;
    //    try {
    //        address = coder.getFromLocationName(strAddress, 5);
    //        if (address == null) {
    //            return null;
    //        }
    //        Address location = address.get(0);
    //        location.getLatitude();
    //        location.getLongitude();
    //        p1 = new GeoPoint((int) (location.getLatitude() * 1E6),
    //                (int) (location.getLongitude() * 1E6));
    //        Log.d("p1", "" + p1);
    //        return p1;
    //    }
    //}
    //public static JSONObject getLocationInfo(String address) {
    //    StringBuilder stringBuilder = new StringBuilder();
    //    try {
    //        address = address.replaceAll(" ","%20");
    //        HttpPost httppost = new HttpPost("http://maps.google.com/maps/api/geocode/json?address=" + address + "&sensor=false");
    //        HttpClient client = new DefaultHttpClient();
    //        HttpResponse response;
    //        stringBuilder = new StringBuilder();
    //        response = client.execute(httppost);
    //        HttpEntity entity = response.getEntity();
    //        InputStream stream = entity.getContent();
    //        int b;
    //        while ((b = stream.read()) != -1) {
    //            stringBuilder.append((char) b);
    //        }
    //    } catch (ClientProtocolException e) {
    //    } catch (IOException e) {
    //    }
    //    JSONObject jsonObject = new JSONObject();
    //    getLatLong(jsonObject);
    //    try {
    //        jsonObject = new JSONObject(stringBuilder.toString());
    //    } catch (JSONException e) {
    //        e.printStackTrace();
    //    }
    //    return jsonObject;
    //}
    //public static boolean getLatLong(JSONObject jsonObject) {
    //    try {
    //        double longitute = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
    //                .getJSONObject("geometry").getJSONObject("location")
    //                .getDouble("lng");
    //        Log.d("lng", ""+longitute);
    //        double latitude = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
    //                .getJSONObject("geometry").getJSONObject("location")
    //                .getDouble("lat");
    //        Log.d("lat", ""+latitude);
    //    } catch (JSONException e) {
    //        return false;
    //    }
    //    return true;
    //}
    public void onClickButtonListenerNext() {
        next = (ImageButton) findViewById(R.id.swipe_right);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewFlipper.showNext();
            }
        });
    }

    public void onClickButtonListenerPrev() {
        prev = (ImageButton) findViewById(R.id.swipe_left);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewFlipper.showPrevious();
            }
        });
    }

    class Background extends AsyncTask<Void, Void, String> {
        String JSON_STRING, url_add, url_change;
        Context ctx;

        public Background(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {
            url_add = "http://mykos.stander.id/identitas_fasilitasKos_add.php";
            url_change = "http://mykos.stander.id/identitas_fasilitasKos_change.php";
        }

        @Override
        protected String doInBackground(Void... params) {
            if (metode.equals("add")) {
                try {
                    URL url = new URL(url_add);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data =
                            //URLEncoder.encode("jarak_kos_fasilitas", "UTF-8") + "=" + URLEncoder.encode("" + fuzzyFasilitasKos.getJarak_kos(), "UTF-8") + "&" +
                            URLEncoder.encode("biaya_kos_fasilitas", "UTF-8") + "=" + URLEncoder.encode("" + fuzzyFasilitasKos.getBiaya_kos(), "UTF-8") + "&" +
                                    URLEncoder.encode("jk_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos.getJk(), "UTF-8") + "&" +
                                    URLEncoder.encode("jmlh_penghuni_fasilitas", "UTF-8") + "=" + URLEncoder.encode("" + fuzzyFasilitasKos.getJmlh_penghuni(), "UTF-8") + "&" +
                                    URLEncoder.encode("meja_kursi_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos.getMeja_kursi(), "UTF-8") + "&" +
                                    URLEncoder.encode("lemari_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos.getLemari(), "UTF-8") + "&" +
                                    URLEncoder.encode("tmpt_tidur_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos.getTmpt_tidur(), "UTF-8") + "&" +
                                    URLEncoder.encode("km_luar_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos.getKm_luar(), "UTF-8") + "&" +
                                    URLEncoder.encode("kipas_angin_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos.getKipas_angin(), "UTF-8") + "&" +
                                    URLEncoder.encode("ac_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos.getAc(), "UTF-8") + "&" +
                                    URLEncoder.encode("tv_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos.getTv(), "UTF-8") + "&" +
                                    URLEncoder.encode("kulkas_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos.getKulkas(), "UTF-8") + "&" +
                                    URLEncoder.encode("dispenser_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos.getDispenser(), "UTF-8") + "&" +
                                    URLEncoder.encode("internet_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos.getInternet(), "UTF-8") + "&" +
                                    URLEncoder.encode("ruang_tamu_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos.getRuang_tamu(), "UTF-8") + "&" +
                                    URLEncoder.encode("dapur_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos.getDapur(), "UTF-8") + "&" +
                                    URLEncoder.encode("mesin_cuci_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos.getMesin_cuci(), "UTF-8") + "&" +
                                    URLEncoder.encode("pembantu_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos.getPembantu(), "UTF-8") + "&" +
                                    URLEncoder.encode("cuci_gosok_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos.getCuci_gosok(), "UTF-8") + "&" +
                                    URLEncoder.encode("cctv_security_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos.getCctv_security(), "UTF-8") + "&" +
                                    URLEncoder.encode("klm_renang_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos.getKlm_renang(), "UTF-8") + "&" +
                                    URLEncoder.encode("parkir_motor_fasilitas", "UTF-8") + "=" + URLEncoder.encode("" + fuzzyFasilitasKos.getParkir_motor(), "UTF-8") + "&" +
                                    URLEncoder.encode("parkir_mobil_fasilitas", "UTF-8") + "=" + URLEncoder.encode("" + fuzzyFasilitasKos.getParkir_mobil(), "UTF-8") + "&" +
                                    URLEncoder.encode("tmpt_makan_fasilitas", "UTF-8") + "=" + URLEncoder.encode("" + fuzzyFasilitasKos.getTmpt_makan(), "UTF-8") + "&" +
                                    URLEncoder.encode("warnet_fasilitas", "UTF-8") + "=" + URLEncoder.encode("" + fuzzyFasilitasKos.getWarnet(), "UTF-8") + "&" +
                                    URLEncoder.encode("mall_fasilitas", "UTF-8") + "=" + URLEncoder.encode("" + fuzzyFasilitasKos.getMall(), "UTF-8") + "&" +
                                    URLEncoder.encode("apotek_dokter_fasilitas", "UTF-8") + "=" + URLEncoder.encode("" + fuzzyFasilitasKos.getApotek_dokter(), "UTF-8") + "&" +
                                    URLEncoder.encode("atm_bank_fasilitas", "UTF-8") + "=" + URLEncoder.encode("" + fuzzyFasilitasKos.getAtm_bank(), "UTF-8") + "&" +
                                    URLEncoder.encode("supermarket_fasilitas", "UTF-8") + "=" + URLEncoder.encode("" + fuzzyFasilitasKos.getSupermarket(), "UTF-8") + "&" +
                                    URLEncoder.encode("kendaraan_umum_fasilitas", "UTF-8") + "=" + URLEncoder.encode("" + fuzzyFasilitasKos.getKendaraan_umum(), "UTF-8") + "&" +
                                    URLEncoder.encode("nama_kos_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos.getNama_kos(), "UTF-8") + "&" +
                                    URLEncoder.encode("alamat_kos_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos.getAlamat_kos(), "UTF-8") + "&" +
                                    URLEncoder.encode("id_pemilik_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos.getId_pemilik(), "UTF-8") + "&" +
                                    URLEncoder.encode("id_kelurahan_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos.getId_kelurahan(), "UTF-8") + "&" +
                                    //URLEncoder.encode("jarak_kos_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos.getJarak_kos(), "UTF-8") + "&" +
                                    URLEncoder.encode("biaya_kos_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos.getBiaya_kos(), "UTF-8") + "&" +
                                    URLEncoder.encode("jk_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos.getJk(), "UTF-8") + "&" +
                                    URLEncoder.encode("jmlh_penghuni_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos.getJmlh_penghuni(), "UTF-8") + "&" +
                                    URLEncoder.encode("meja_kursi_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos.getMeja_kursi(), "UTF-8") + "&" +
                                    URLEncoder.encode("lemari_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos.getLemari(), "UTF-8") + "&" +
                                    URLEncoder.encode("tmpt_tidur_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos.getTmpt_tidur(), "UTF-8") + "&" +
                                    URLEncoder.encode("km_luar_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos.getKm_luar(), "UTF-8") + "&" +
                                    URLEncoder.encode("kipas_angin_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos.getKipas_angin(), "UTF-8") + "&" +
                                    URLEncoder.encode("ac_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos.getAc(), "UTF-8") + "&" +
                                    URLEncoder.encode("tv_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos.getTv(), "UTF-8") + "&" +
                                    URLEncoder.encode("kulkas_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos.getKulkas(), "UTF-8") + "&" +
                                    URLEncoder.encode("dispenser_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos.getDispenser(), "UTF-8") + "&" +
                                    URLEncoder.encode("internet_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos.getInternet(), "UTF-8") + "&" +
                                    URLEncoder.encode("ruang_tamu_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos.getRuang_tamu(), "UTF-8") + "&" +
                                    URLEncoder.encode("dapur_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos.getDapur(), "UTF-8") + "&" +
                                    URLEncoder.encode("mesin_cuci_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos.getMesin_cuci(), "UTF-8") + "&" +
                                    URLEncoder.encode("pembantu_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos.getPembantu(), "UTF-8") + "&" +
                                    URLEncoder.encode("cuci_gosok_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos.getCuci_gosok(), "UTF-8") + "&" +
                                    URLEncoder.encode("cctv_security_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos.getCctv_security(), "UTF-8") + "&" +
                                    URLEncoder.encode("klm_renang_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos.getKlm_renang(), "UTF-8") + "&" +
                                    URLEncoder.encode("parkir_motor_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos.getParkir_motor(), "UTF-8") + "&" +
                                    URLEncoder.encode("parkir_mobil_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos.getParkir_mobil(), "UTF-8") + "&" +
                                    URLEncoder.encode("tmpt_makan_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos.getTmpt_makan(), "UTF-8") + "&" +
                                    URLEncoder.encode("warnet_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos.getWarnet(), "UTF-8") + "&" +
                                    URLEncoder.encode("mall_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos.getMall(), "UTF-8") + "&" +
                                    URLEncoder.encode("apotek_dokter_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos.getApotek_dokter(), "UTF-8") + "&" +
                                    URLEncoder.encode("atm_bank_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos.getAtm_bank(), "UTF-8") + "&" +
                                    URLEncoder.encode("supermarket_identitas_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos.getSupermarket(), "UTF-8") + "&" +
                                    URLEncoder.encode("kendaraan_umum_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos.getKendaraan_umum(), "UTF-8");
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
            } else if (metode.equals("change")) {
                try {
                    URL url = new URL(url_change);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("id_pemilik", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getId_pemilik(), "UTF-8") + "&" +
                            URLEncoder.encode("id_kos", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getId_kos(), "UTF-8") + "&" +
                            URLEncoder.encode("nama_kos_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getNama_kos(), "UTF-8") + "&" +
                            URLEncoder.encode("alamat_kos_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getAlamat_kos(), "UTF-8") + "&" +
                            URLEncoder.encode("id_kelurahan_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getId_kelurahan(), "UTF-8") + "&" +
                            //URLEncoder.encode("jarak_kos_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos_ubah.getJarak_kos(), "UTF-8") + "&" +
                            URLEncoder.encode("biaya_kos_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos_ubah.getBiaya_kos(), "UTF-8") + "&" +
                            URLEncoder.encode("jk_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getJk(), "UTF-8") + "&" +
                            URLEncoder.encode("jmlh_penghuni_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos_ubah.getJmlh_penghuni(), "UTF-8") + "&" +
                            URLEncoder.encode("meja_kursi_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getMeja_kursi(), "UTF-8") + "&" +
                            URLEncoder.encode("lemari_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getLemari(), "UTF-8") + "&" +
                            URLEncoder.encode("tmpt_tidur_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getTmpt_tidur(), "UTF-8") + "&" +
                            URLEncoder.encode("km_luar_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getKm_luar(), "UTF-8") + "&" +
                            URLEncoder.encode("kipas_angin_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getKipas_angin(), "UTF-8") + "&" +
                            URLEncoder.encode("ac_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getAc(), "UTF-8") + "&" +
                            URLEncoder.encode("tv_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getTv(), "UTF-8") + "&" +
                            URLEncoder.encode("kulkas_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getKulkas(), "UTF-8") + "&" +
                            URLEncoder.encode("dispenser_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getDispenser(), "UTF-8") + "&" +
                            URLEncoder.encode("internet_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getInternet(), "UTF-8") + "&" +
                            URLEncoder.encode("ruang_tamu_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getRuang_tamu(), "UTF-8") + "&" +
                            URLEncoder.encode("dapur_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getDapur(), "UTF-8") + "&" +
                            URLEncoder.encode("mesin_cuci_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getMesin_cuci(), "UTF-8") + "&" +
                            URLEncoder.encode("pembantu_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getPembantu(), "UTF-8") + "&" +
                            URLEncoder.encode("cuci_gosok_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getCuci_gosok(), "UTF-8") + "&" +
                            URLEncoder.encode("cctv_security_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getCctv_security(), "UTF-8") + "&" +
                            URLEncoder.encode("klm_renang_identitas", "UTF-8") + "=" + URLEncoder.encode(identitasKos_ubah.getKlm_renang(), "UTF-8") + "&" +
                            URLEncoder.encode("parkir_motor_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos_ubah.getParkir_motor(), "UTF-8") + "&" +
                            URLEncoder.encode("parkir_mobil_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos_ubah.getParkir_mobil(), "UTF-8") + "&" +
                            URLEncoder.encode("tmpt_makan_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos_ubah.getTmpt_makan(), "UTF-8") + "&" +
                            URLEncoder.encode("warnet_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos_ubah.getWarnet(), "UTF-8") + "&" +
                            URLEncoder.encode("mall_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos_ubah.getMall(), "UTF-8") + "&" +
                            URLEncoder.encode("apotek_dokter_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos_ubah.getApotek_dokter(), "UTF-8") + "&" +
                            URLEncoder.encode("atm_bank_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos_ubah.getAtm_bank(), "UTF-8") + "&" +
                            URLEncoder.encode("supermarket_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos_ubah.getSupermarket(), "UTF-8") + "&" +
                            URLEncoder.encode("kendaraan_umum_identitas", "UTF-8") + "=" + URLEncoder.encode("" + identitasKos_ubah.getKendaraan_umum(), "UTF-8") + "&" +
                            URLEncoder.encode("id_tfn", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getId_tfn(), "UTF-8") + "&" +
                            //URLEncoder.encode("jarak_kos_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getJarak_kos(), "UTF-8") + "&" +
                            URLEncoder.encode("biaya_kos_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getBiaya_kos(), "UTF-8") + "&" +
                            URLEncoder.encode("jk_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getJk(), "UTF-8") + "&" +
                            URLEncoder.encode("jmlh_penghuni_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getJmlh_penghuni(), "UTF-8") + "&" +
                            URLEncoder.encode("meja_kursi_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getMeja_kursi(), "UTF-8") + "&" +
                            URLEncoder.encode("lemari_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getLemari(), "UTF-8") + "&" +
                            URLEncoder.encode("tmpt_tidur_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getTmpt_tidur(), "UTF-8") + "&" +
                            URLEncoder.encode("km_luar_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getKm_luar(), "UTF-8") + "&" +
                            URLEncoder.encode("kipas_angin_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getKipas_angin(), "UTF-8") + "&" +
                            URLEncoder.encode("ac_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getAc(), "UTF-8") + "&" +
                            URLEncoder.encode("tv_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getTv(), "UTF-8") + "&" +
                            URLEncoder.encode("kulkas_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getKulkas(), "UTF-8") + "&" +
                            URLEncoder.encode("dispenser_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getDispenser(), "UTF-8") + "&" +
                            URLEncoder.encode("internet_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getInternet(), "UTF-8") + "&" +
                            URLEncoder.encode("ruang_tamu_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getRuang_tamu(), "UTF-8") + "&" +
                            URLEncoder.encode("dapur_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getDapur(), "UTF-8") + "&" +
                            URLEncoder.encode("mesin_cuci_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getMesin_cuci(), "UTF-8") + "&" +
                            URLEncoder.encode("pembantu_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getPembantu(), "UTF-8") + "&" +
                            URLEncoder.encode("cuci_gosok_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getCuci_gosok(), "UTF-8") + "&" +
                            URLEncoder.encode("cctv_security_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getCctv_security(), "UTF-8") + "&" +
                            URLEncoder.encode("klm_renang_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getKlm_renang(), "UTF-8") + "&" +
                            URLEncoder.encode("parkir_motor_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getParkir_motor(), "UTF-8") + "&" +
                            URLEncoder.encode("parkir_mobil_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getParkir_mobil(), "UTF-8") + "&" +
                            URLEncoder.encode("tmpt_makan_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getTmpt_makan(), "UTF-8") + "&" +
                            URLEncoder.encode("warnet_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getWarnet(), "UTF-8") + "&" +
                            URLEncoder.encode("mall_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getMall(), "UTF-8") + "&" +
                            URLEncoder.encode("apotek_dokter_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getApotek_dokter(), "UTF-8") + "&" +
                            URLEncoder.encode("atm_bank_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getAtm_bank(), "UTF-8") + "&" +
                            URLEncoder.encode("supermarket_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getSupermarket(), "UTF-8") + "&" +
                            URLEncoder.encode("kendaraan_umum_fasilitas", "UTF-8") + "=" + URLEncoder.encode(fuzzyFasilitasKos_ubah.getKendaraan_umum(), "UTF-8");
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
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != "FAILURE") {
                if (metode.equalsIgnoreCase("add")) {
                    json_string = result;
                    Log.d("json_string", json_string);
                    Toast.makeText(getApplicationContext(), "Berhasil Menambah Data Kos", Toast.LENGTH_LONG).show();
                } else if (metode.equalsIgnoreCase("change")) {
                    json_string = result;
                    Log.d("json_string", json_string);
                    Toast.makeText(getApplicationContext(), "Berhasil Mengubah Data Kos", Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(kelola_detail_infokosActivity.this, kelola_infokosActivity.class);
                intent.putExtra("data", "ada");
                intent.putExtra("json_string", json_string);
                intent.putExtra("id_pemilik", Id_pemilik);
                startActivityForResult(intent, 0);
            } else if (result == "FAILURE") {
                if (metode.equalsIgnoreCase("add")) {
                    Toast.makeText(getApplicationContext(), "Tidak Berhasil Menambah Data Kos", Toast.LENGTH_LONG).show();
                } else if (metode.equalsIgnoreCase("change")) {
                    Toast.makeText(getApplicationContext(), "Tidak Berhasil Mengubah Data Kos", Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(kelola_detail_infokosActivity.this, kelola_infokosActivity.class);
                intent.putExtra("data", "tidak ada");
                intent.putExtra("id_pemilik", Id_pemilik);
                startActivityForResult(intent, 0);
            }
        }
    }
}