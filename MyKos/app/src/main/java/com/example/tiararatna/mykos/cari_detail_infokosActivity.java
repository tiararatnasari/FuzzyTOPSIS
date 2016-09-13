package com.example.tiararatna.mykos;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.tiararatna.adapter.identitasKos_cariAdapter;
import com.example.tiararatna.modul.identitas_kos;
import com.example.tiararatna.modul.pencari_kos;

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

/**
 * Created by Tiara Ratna on 4/19/2016.
 */
public class cari_detail_infokosActivity extends AppCompatActivity {
    private static Button peta, riwayat, back;
    private static ImageButton next, prev;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private ViewFlipper mViewFlipper;
    private Animation.AnimationListener mAnimationListener;
    private Context mContext;
    @SuppressWarnings("deprecation")
    private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());

    identitas_kos identitasKos;
    TextView nama_kos, alamat_kos, pemilik, telp_pemilik, kecamatan, kelurahan, univ,jarak_kos, biaya_kos, jk_p, jk_l,
            jmlh_penghuni, parkir_motor, parkir_mobil, tmpt_makan, warnet, mall, apotek_dokter, atm_bank,
            supermarket, kendaraan_umum;
    CheckBox meja_kursi, lemari, tmpt_tidur, km_luar, kipas_angin, ac, tv, kulkas, dispenser, internet, ruang_tamu,
            dapur, mesin_cuci, pembantu, cuci_gosok, cctv_security, klm_renang;
    String Id_kos, Nama_kos, Alamat_kos, Pemilik, Telp_pemilik, Kelurahan, Kecamatan, Universitas, Id_tfn, Jk,
            Meja_kursi, Lemari, Tmpt_tidur, Km_luar, Kipas_angin, Ac, Tv, Kulkas, Dispenser, Internet, Ruang_tamu,
            Dapur, Mesin_cuci, Pembantu, Cuci_gosok, Cctv_security, Klm_renang;
    double Jarak_kos;
    int Biaya_kos, Jmlh_penghuni, Parkir_motor, Parkir_mobil, Tmpt_makan, Warnet, Mall, Apotek_dokter,
            Atm_bank, Supermarket, Kendaraan_umum;
    ListView listView;
    identitasKos_cariAdapter identitasKosAdapter;
    String json_string, JSON_STRING;
    pencari_kos pencariKos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_detail_infokos);
        mViewFlipper = (ViewFlipper) this.findViewById(R.id.view_flipper);

        identitasKos = (identitas_kos) getIntent().getExtras().get("identitas_kos");
        Log.d("identitas_kos",identitasKos.getId_kos());
        pencariKos = (pencari_kos) getIntent().getExtras().get("pencari");
        Log.d("id_pencari",pencariKos.getId_pencari());


        nama_kos = (TextView) findViewById(R.id.f_cari_nama);
        alamat_kos = (TextView) findViewById(R.id.f_cari_alamat);
        pemilik = (TextView) findViewById(R.id.f_cari_pemilik);
        telp_pemilik = (TextView) findViewById(R.id.f_cari_telpon);
        kelurahan = (TextView) findViewById(R.id.f_cari_kelurahan);
        kecamatan = (TextView) findViewById(R.id.f_cari_kecamatan);
        biaya_kos = (TextView) findViewById(R.id.f_cari_biaya);
        jk_p = (TextView) findViewById(R.id.f_cari_jk1);
        jk_l = (TextView) findViewById(R.id.f_cari_jk2);
        jmlh_penghuni = (TextView) findViewById(R.id.f_cari_jmlpenghuni);
        meja_kursi = (CheckBox) findViewById(R.id.c_cari_mejakursi);
        lemari = (CheckBox) findViewById(R.id.c_cari_lemari);
        tmpt_tidur = (CheckBox) findViewById(R.id.c_cari_tempattidur);
        km_luar = (CheckBox) findViewById(R.id.c_cari_kmluar);
        kipas_angin = (CheckBox) findViewById(R.id.c_cari_kipasangin);
        ac = (CheckBox) findViewById(R.id.c_cari_ac);
        tv = (CheckBox) findViewById(R.id.c_cari_tv);
        kulkas = (CheckBox) findViewById(R.id.c_cari_kulkas);
        dispenser = (CheckBox) findViewById(R.id.c_cari_dispenser);
        internet = (CheckBox) findViewById(R.id.c_cari_internet);
        ruang_tamu = (CheckBox) findViewById(R.id.c_cari_ruangtamu);
        dapur = (CheckBox) findViewById(R.id.c_cari_dapur);
        mesin_cuci = (CheckBox) findViewById(R.id.c_cari_mesincuci);
        pembantu = (CheckBox) findViewById(R.id.c_cari_pembantu);
        cuci_gosok = (CheckBox) findViewById(R.id.c_cari_cucigosok);
        cctv_security = (CheckBox) findViewById(R.id.c_cari_cctv);
        klm_renang = (CheckBox) findViewById(R.id.c_cari_kolamrenang);
        parkir_motor = (TextView) findViewById(R.id.f_cari_parkirmotor);
        parkir_mobil = (TextView) findViewById(R.id.f_cari_parkirmobil);
        tmpt_makan = (TextView) findViewById(R.id.f_cari_restaurant);
        warnet = (TextView) findViewById(R.id.f_cari_warnet);
        mall = (TextView) findViewById(R.id.f_cari_mall);
        apotek_dokter = (TextView) findViewById(R.id.f_cari_apotek);
        atm_bank = (TextView) findViewById(R.id.f_cari_bank);
        supermarket = (TextView) findViewById(R.id.f_cari_supermarket);
        kendaraan_umum = (TextView) findViewById(R.id.f_cari_kendaraanumum);

        Id_kos = identitasKos.getId_kos();
        Log.d("id_kos", Id_kos);
        Nama_kos = identitasKos.getNama_kos();nama_kos.setText(Nama_kos);
        Alamat_kos = identitasKos.getAlamat_kos();alamat_kos.setText(Alamat_kos);
        Pemilik = identitasKos.getNama_pemilik();pemilik.setText(Pemilik);
        Telp_pemilik = identitasKos.getTelp_pemilik();telp_pemilik.setText(Telp_pemilik);
        Kelurahan = identitasKos.getKelurahan();kelurahan.setText(Kelurahan);
        Kecamatan = identitasKos.getKecamatan();kecamatan.setText(Kecamatan);
        Id_tfn = identitasKos.getId_tfn();
        Biaya_kos = identitasKos.getBiaya_kos();biaya_kos.setText(Integer.toString(Biaya_kos));
        Jk = identitasKos.getJk();
        if(Jk.equalsIgnoreCase("p")){
            jk_l.setBackgroundColor(R.color.colorAbuMuda1);
        } else if (Jk.equalsIgnoreCase("l")){
            jk_p.setBackgroundColor(R.color.colorAbuMuda1);
        }
        Jmlh_penghuni = identitasKos.getJmlh_penghuni();jmlh_penghuni.setText(Integer.toString(Jmlh_penghuni));
        Meja_kursi = identitasKos.getMeja_kursi();
        if(Meja_kursi.equalsIgnoreCase("y")){meja_kursi.setChecked(true);}else if(Meja_kursi.equalsIgnoreCase("t")){meja_kursi.setChecked(false);}
        Lemari = identitasKos.getLemari();
        if (Lemari.equalsIgnoreCase("y")){lemari.setChecked(true);}else if(Lemari.equalsIgnoreCase("t")){lemari.setChecked(false);}
        Tmpt_tidur = identitasKos.getTmpt_tidur();
        if (Tmpt_tidur.equalsIgnoreCase("y")){tmpt_tidur.setChecked(true);}else if(Tmpt_tidur.equalsIgnoreCase("t")){tmpt_tidur.setChecked(false);}
        Km_luar = identitasKos.getKm_luar();
        if (Km_luar.equalsIgnoreCase("y")){km_luar.setChecked(true);}else if(Km_luar.equalsIgnoreCase("t")){km_luar.setChecked(false);}
        Kipas_angin = identitasKos.getKipas_angin();
        if (Kipas_angin.equalsIgnoreCase("y")){kipas_angin.setChecked(true);}else if(Kipas_angin.equalsIgnoreCase("t")){kipas_angin.setChecked(false);}
        Ac = identitasKos.getAc();
        if (Ac.equalsIgnoreCase("y")){ac.setChecked(true);}else if(Ac.equalsIgnoreCase("t")){ac.setChecked(false);}
        Tv = identitasKos.getTv();
        if (Tv.equalsIgnoreCase("y")){tv.setChecked(true);}else if(Tv.equalsIgnoreCase("t")){tv.setChecked(false);}
        Kulkas = identitasKos.getKulkas();
        if (Kulkas.equalsIgnoreCase("y")){kulkas.setChecked(true);}else if(Kulkas.equalsIgnoreCase("t")){kulkas.setChecked(false);}
        Dispenser = identitasKos.getDispenser();
        if (Dispenser.equalsIgnoreCase("y")){dispenser.setChecked(true);}else if(Dispenser.equalsIgnoreCase("t")){dispenser.setChecked(false);}
        Internet = identitasKos.getInternet();
        if (Internet.equalsIgnoreCase("y")){internet.setChecked(true);}else if(Internet.equalsIgnoreCase("t")){internet.setChecked(false);}
        Ruang_tamu = identitasKos.getRuang_tamu();
        if (Ruang_tamu.equalsIgnoreCase("y")){ruang_tamu.setChecked(true);}else if(Ruang_tamu.equalsIgnoreCase("t")){ruang_tamu.setChecked(false);}
        Dapur = identitasKos.getDapur();
        if (Dapur.equalsIgnoreCase("y")){dapur.setChecked(true);}else if(Dapur.equalsIgnoreCase("t")){dapur.setChecked(false);}
        Mesin_cuci = identitasKos.getMesin_cuci();
        if (Mesin_cuci.equalsIgnoreCase("y")){mesin_cuci.setChecked(true);}else if(Mesin_cuci.equalsIgnoreCase("t")){lemari.setChecked(false);}
        Pembantu = identitasKos.getPembantu();
        if (Pembantu.equalsIgnoreCase("y")){pembantu.setChecked(true);}else if(Pembantu.equalsIgnoreCase("t")){pembantu.setChecked(false);}
        Cuci_gosok = identitasKos.getCuci_gosok();
        if (Cuci_gosok.equalsIgnoreCase("y")){cuci_gosok.setChecked(true);}else if(Cuci_gosok.equalsIgnoreCase("t")){cuci_gosok.setChecked(false);}
        Cctv_security = identitasKos.getCctv_security();
        if (Cctv_security.equalsIgnoreCase("y")){cctv_security.setChecked(true);}else if(Cctv_security.equalsIgnoreCase("t")){cctv_security.setChecked(false);}
        Klm_renang = identitasKos.getKlm_renang();
        if (Klm_renang.equalsIgnoreCase("y")){klm_renang.setChecked(true);}else if(Klm_renang.equalsIgnoreCase("t")){klm_renang.setChecked(false);}
        Parkir_motor = identitasKos.getParkir_motor();parkir_motor.setText(Integer.toString(Parkir_motor));
        Parkir_mobil = identitasKos.getParkir_mobil();parkir_mobil.setText(Integer.toString(Parkir_mobil));
        Tmpt_makan = identitasKos.getTmpt_makan();tmpt_makan.setText(Integer.toString(Tmpt_makan));
        Warnet = identitasKos.getWarnet();warnet.setText(Integer.toString(Warnet));
        Mall = identitasKos.getMall();mall.setText(Integer.toString(Mall));
        Apotek_dokter = identitasKos.getApotek_dokter();apotek_dokter.setText(Integer.toString(Apotek_dokter));
        Atm_bank = identitasKos.getAtm_bank();atm_bank.setText(Integer.toString(Atm_bank));
        Supermarket = identitasKos.getSupermarket();supermarket.setText(Integer.toString(Supermarket));
        Kendaraan_umum = identitasKos.getKendaraan_umum();kendaraan_umum.setText(Integer.toString(Kendaraan_umum));

        onClickButtonListenerPeta();
        onClickButtonListenerRiwayat();
        onClickButtonListenerNext();
        onClickButtonListenerPrev();
        onClickButtonListenerBack();
    }

    class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
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

    public void onClickButtonListenerPeta (){
        peta = (Button)findViewById(R.id.b_cari_peta);
        peta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MapsActivity.class);
                intent.putExtra("latitude_univ", pencariKos.getLatitude());
                Log.d("la_univ", pencariKos.getLatitude());
                intent.putExtra("longitude_univ", pencariKos.getLongitude());
                Log.d("lo_univ", pencariKos.getLongitude());
                intent.putExtra("latitude_kos", identitasKos.getLatitude_kos());
                Log.d("la_kos", identitasKos.getLatitude_kos());
                intent.putExtra("longitude_kos", identitasKos.getLongitude_kos());
                Log.d("lo_kos", identitasKos.getLongitude_kos());
                startActivityForResult(intent, 0);
            }
        });
    }
    public void onClickButtonListenerRiwayat (){
        riwayat = (Button)findViewById(R.id.b_cari_riwayat);
        riwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Background(cari_detail_infokosActivity.this).execute();
            }
        });
    }
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
    public void onClickButtonListenerBack (){
        back = (Button)findViewById(R.id.b_cari_kembali);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView = (ListView)findViewById(R.id.daftarkos_listview);
                identitasKosAdapter = new identitasKos_cariAdapter(cari_detail_infokosActivity.this, R.layout.activity_cari_detail_infokos, pencariKos);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(cari_detail_infokosActivity.this, "List item was clicked at " + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    class Background extends AsyncTask<Void, Void, String> {
        String url_riwayatAdd;
        Context ctx;

        public Background(Context ctx) {this.ctx = ctx;}

        @Override
        protected void onPreExecute() {url_riwayatAdd = "http://mykos.stander.id/riwayatPencarian_add.php";}

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(url_riwayatAdd);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("id_kos", "UTF-8") + "=" + URLEncoder.encode(Id_kos, "UTF-8") + "&" +
                        URLEncoder.encode("id_pencari", "UTF-8") + "=" + URLEncoder.encode(pencariKos.getId_pencari(), "UTF-8");
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

        @Override
        protected void onProgressUpdate(Void... values) {super.onProgressUpdate(values);}

        @Override
        protected void onPostExecute(String result) {
            json_string = result;
            Log.d("json_string", json_string);
            if( json_string.equalsIgnoreCase("ADA")){
                Toast.makeText(getApplicationContext(), "Data Kos Sudah Ada pada Riwayat", Toast.LENGTH_LONG).show();
            } else if (json_string.equalsIgnoreCase("SUCCESS")){
                Toast.makeText(getApplicationContext(), "Berhasil Memasukan Data Kos pada Riwayat", Toast.LENGTH_LONG).show();
            } else if(json_string.equalsIgnoreCase("FAILURE")){
                Toast.makeText(getApplicationContext(), "Tidak Berhasil Memasukan Data Kos pada Riwayat", Toast.LENGTH_LONG).show();
            }
        }
    }
}
