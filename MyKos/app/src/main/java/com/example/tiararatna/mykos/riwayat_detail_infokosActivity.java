package com.example.tiararatna.mykos;

import android.content.Context;
import android.content.Intent;
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
import com.example.tiararatna.adapter.riwayat_pencarianAdapter;
import com.example.tiararatna.modul.identitas_kos;
import com.example.tiararatna.modul.pencari_kos;
import com.example.tiararatna.modul.riwayat_pencarian;

/**
 * Created by Tiara Ratna on 4/19/2016.
 */
public class riwayat_detail_infokosActivity extends AppCompatActivity {
    private static Button peta, back;
    private static ImageButton next, prev;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private ViewFlipper mViewFlipper;
    private Animation.AnimationListener mAnimationListener;
    private Context mContext;
    @SuppressWarnings("deprecation")
    private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());

    riwayat_pencarian riwayatPencarian;
    TextView nama_kos, alamat_kos, pemilik, telp_pemilik, kecamatan, kelurahan, univ,jarak_kos, biaya_kos, jk_p, jk_l,
            jmlh_penghuni, parkir_motor, parkir_mobil, tmpt_makan, warnet, mall, apotek_dokter,
            atm_bank, supermarket, kendaraan_umum;
    CheckBox meja_kursi, lemari, tmpt_tidur, km_luar, kipas_angin, ac, tv, kulkas, dispenser, internet, ruang_tamu,
            dapur, mesin_cuci, pembantu, cuci_gosok, cctv_security, klm_renang;
    String Id_riwayat, Id_pencari, Id_kos, Nama_kos, Alamat_kos, Id_pemilik, Nama_pemilik, Telp_pemilik, Id_kelurahan,
            Kelurahan, Kecamatan, Id_universitas, Universitas, Id_tfn, Jk, Meja_kursi, Lemari, Tmpt_tidur, Km_luar,
            Kipas_angin, Ac, Tv, Kulkas, Dispenser, Internet, Ruang_tamu, Dapur, Mesin_cuci, Pembantu, Cuci_gosok,
            Cctv_security, Klm_renang;
    int Jarak_kos, Biaya_kos, Jmlh_penghuni, Parkir_motor, Parkir_mobil, Tmpt_makan, Warnet, Mall, Apotek_dokter,
            Atm_bank, Supermarket, Kendaraan_umum;
    ListView listView;
    riwayat_pencarianAdapter riwayatPencarianAdapter;
    pencari_kos pencariKos;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_detail_infokos);
        mViewFlipper = (ViewFlipper) this.findViewById(R.id.view_flipper);

        riwayatPencarian = (riwayat_pencarian) getIntent().getExtras().get("riwayat_pencarian");
        Log.d("id_pemilik",riwayatPencarian.getId_kos());
        pencariKos = (pencari_kos) getIntent().getExtras().get("pencari");
        Log.d("id_pencari",pencariKos.getId_pencari());

        nama_kos = (TextView) findViewById(R.id.f_riwayat_nama);
        alamat_kos = (TextView) findViewById(R.id.f_riwayat_alamat);
        pemilik = (TextView) findViewById(R.id.f_riwayat_pemilik);
        telp_pemilik = (TextView) findViewById(R.id.f_riwayat_telpon);
        kelurahan = (TextView) findViewById(R.id.f_riwayat_kelurahan);
        kecamatan = (TextView) findViewById(R.id.f_riwayat_kecamatan);
        biaya_kos = (TextView) findViewById(R.id.f_riwayat_biaya);
        jk_p = (TextView) findViewById(R.id.f_riwayat_jk1);
        jk_l = (TextView) findViewById(R.id.f_riwayat_jk2);
        jmlh_penghuni = (TextView) findViewById(R.id.f_riwayat_jmlpenghuni);
        meja_kursi = (CheckBox) findViewById(R.id.c_riwayat_mejakursi);
        lemari = (CheckBox) findViewById(R.id.c_riwayat_lemari);
        tmpt_tidur = (CheckBox) findViewById(R.id.c_riwayat_tempattidur);
        km_luar = (CheckBox) findViewById(R.id.c_riwayat_kmluar);
        kipas_angin = (CheckBox) findViewById(R.id.c_riwayat_kipasangin);
        ac = (CheckBox) findViewById(R.id.c_riwayat_ac);
        tv = (CheckBox) findViewById(R.id.c_riwayat_tv);
        kulkas = (CheckBox) findViewById(R.id.c_riwayat_kulkas);
        dispenser = (CheckBox) findViewById(R.id.c_riwayat_dispenser);
        internet = (CheckBox) findViewById(R.id.c_riwayat_internet);
        ruang_tamu = (CheckBox) findViewById(R.id.c_riwayat_ruangtamu);
        dapur = (CheckBox) findViewById(R.id.c_riwayat_dapur);
        mesin_cuci = (CheckBox) findViewById(R.id.c_riwayat_mesincuci);
        pembantu = (CheckBox) findViewById(R.id.c_riwayat_pembantu);
        cuci_gosok = (CheckBox) findViewById(R.id.c_riwayat_cucigosok);
        cctv_security = (CheckBox) findViewById(R.id.c_riwayat_cctv);
        klm_renang = (CheckBox) findViewById(R.id.c_riwayat_kolamrenang);
        parkir_motor = (TextView) findViewById(R.id.f_riwayat_parkirmotor);
        parkir_mobil = (TextView) findViewById(R.id.f_riwayat_parkirmobil);
        tmpt_makan = (TextView) findViewById(R.id.f_riwayat_restaurant);
        warnet = (TextView) findViewById(R.id.f_riwayat_warnet);
        mall = (TextView) findViewById(R.id.f_riwayat_mall);
        apotek_dokter = (TextView) findViewById(R.id.f_riwayat_apotek);
        atm_bank = (TextView) findViewById(R.id.f_riwayat_bank);
        supermarket = (TextView) findViewById(R.id.f_riwayat_supermarket);
        kendaraan_umum = (TextView) findViewById(R.id.f_riwayat_kendaraanumum);

        Id_riwayat = riwayatPencarian.getId_riwayat();Log.d("id_riwayat", riwayatPencarian.getId_riwayat());
        Id_pencari = riwayatPencarian.getId_pencari();Log.d("id_pencari", riwayatPencarian.getId_pencari());
        Id_kos = riwayatPencarian.getId_kos();Log.d("id_kos", Id_kos);
        Nama_kos = riwayatPencarian.getNama_kos();nama_kos.setText(Nama_kos);
        Alamat_kos = riwayatPencarian.getAlamat_kos();alamat_kos.setText(Alamat_kos);
        Id_pemilik = riwayatPencarian.getId_pemilik();Log.d("id_pemilik", riwayatPencarian.getId_pemilik());
        Nama_pemilik = riwayatPencarian.getNama_pemilik();pemilik.setText(Nama_pemilik);
        Telp_pemilik = riwayatPencarian.getTelp_pemilik();telp_pemilik.setText(Telp_pemilik);
        Id_kelurahan = riwayatPencarian.getId_kelurahan();Log.d("id_kelurahan", riwayatPencarian.getId_kelurahan());
        Kelurahan = riwayatPencarian.getKelurahan();kelurahan.setText(Kelurahan);
        Kecamatan = riwayatPencarian.getKecamatan();kecamatan.setText(Kecamatan);
        Id_tfn = riwayatPencarian.getId_tfn();
        Biaya_kos = riwayatPencarian.getBiaya_kos();biaya_kos.setText(Integer.toString(Biaya_kos));
        Jk = riwayatPencarian.getJk();
        if(Jk.equalsIgnoreCase("p")){
            jk_l.setBackgroundColor(R.color.colorAbuMuda1);
        } else if (Jk.equalsIgnoreCase("l")){
            jk_p.setBackgroundColor(R.color.colorAbuMuda1);
        }
        Jmlh_penghuni = riwayatPencarian.getJmlh_penghuni();jmlh_penghuni.setText(Integer.toString(Jmlh_penghuni));
        Meja_kursi = riwayatPencarian.getMeja_kursi();
        if(Meja_kursi.equalsIgnoreCase("y")){meja_kursi.setChecked(true);}else if(Meja_kursi.equalsIgnoreCase("t")){meja_kursi.setChecked(false);}
        Lemari = riwayatPencarian.getLemari();
        if (Lemari.equalsIgnoreCase("y")){lemari.setChecked(true);}else if(Lemari.equalsIgnoreCase("t")){lemari.setChecked(false);}
        Tmpt_tidur = riwayatPencarian.getTmpt_tidur();
        if (Tmpt_tidur.equalsIgnoreCase("y")){tmpt_tidur.setChecked(true);}else if(Tmpt_tidur.equalsIgnoreCase("t")){tmpt_tidur.setChecked(false);}
        Km_luar = riwayatPencarian.getKm_luar();
        if (Km_luar.equalsIgnoreCase("y")){km_luar.setChecked(true);}else if(Km_luar.equalsIgnoreCase("t")){km_luar.setChecked(false);}
        Kipas_angin = riwayatPencarian.getKipas_angin();
        if (Kipas_angin.equalsIgnoreCase("y")){kipas_angin.setChecked(true);}else if(Kipas_angin.equalsIgnoreCase("t")){kipas_angin.setChecked(false);}
        Ac = riwayatPencarian.getAc();
        if (Ac.equalsIgnoreCase("y")){ac.setChecked(true);}else if(Ac.equalsIgnoreCase("t")){ac.setChecked(false);}
        Tv = riwayatPencarian.getTv();
        if (Tv.equalsIgnoreCase("y")){tv.setChecked(true);}else if(Tv.equalsIgnoreCase("t")){tv.setChecked(false);}
        Kulkas = riwayatPencarian.getKulkas();
        if (Kulkas.equalsIgnoreCase("y")){kulkas.setChecked(true);}else if(Kulkas.equalsIgnoreCase("t")){kulkas.setChecked(false);}
        Dispenser = riwayatPencarian.getDispenser();
        if (Dispenser.equalsIgnoreCase("y")){dispenser.setChecked(true);}else if(Dispenser.equalsIgnoreCase("t")){dispenser.setChecked(false);}
        Internet = riwayatPencarian.getInternet();
        if (Internet.equalsIgnoreCase("y")){internet.setChecked(true);}else if(Internet.equalsIgnoreCase("t")){internet.setChecked(false);}
        Ruang_tamu = riwayatPencarian.getRuang_tamu();
        if (Ruang_tamu.equalsIgnoreCase("y")){ruang_tamu.setChecked(true);}else if(Ruang_tamu.equalsIgnoreCase("t")){ruang_tamu.setChecked(false);}
        Dapur = riwayatPencarian.getDapur();
        if (Dapur.equalsIgnoreCase("y")){dapur.setChecked(true);}else if(Dapur.equalsIgnoreCase("t")){dapur.setChecked(false);}
        Mesin_cuci = riwayatPencarian.getMesin_cuci();
        if (Mesin_cuci.equalsIgnoreCase("y")){mesin_cuci.setChecked(true);}else if(Mesin_cuci.equalsIgnoreCase("t")){lemari.setChecked(false);}
        Pembantu = riwayatPencarian.getPembantu();
        if (Pembantu.equalsIgnoreCase("y")){pembantu.setChecked(true);}else if(Pembantu.equalsIgnoreCase("t")){pembantu.setChecked(false);}
        Cuci_gosok = riwayatPencarian.getCuci_gosok();
        if (Cuci_gosok.equalsIgnoreCase("y")){cuci_gosok.setChecked(true);}else if(Cuci_gosok.equalsIgnoreCase("t")){cuci_gosok.setChecked(false);}
        Cctv_security = riwayatPencarian.getCctv_security();
        if (Cctv_security.equalsIgnoreCase("y")){cctv_security.setChecked(true);}else if(Cctv_security.equalsIgnoreCase("t")){cctv_security.setChecked(false);}
        Klm_renang = riwayatPencarian.getKlm_renang();
        if (Klm_renang.equalsIgnoreCase("y")){klm_renang.setChecked(true);}else if(Klm_renang.equalsIgnoreCase("t")){klm_renang.setChecked(false);}Parkir_motor = riwayatPencarian.getParkir_motor();parkir_motor.setText(Integer.toString(Parkir_motor));
        Parkir_mobil = riwayatPencarian.getParkir_mobil();parkir_mobil.setText(Integer.toString(Parkir_mobil));
        Tmpt_makan = riwayatPencarian.getTmpt_makan();tmpt_makan.setText(Integer.toString(Tmpt_makan));
        Warnet = riwayatPencarian.getWarnet();warnet.setText(Integer.toString(Warnet));
        Mall = riwayatPencarian.getMall();mall.setText(Integer.toString(Mall));
        Apotek_dokter = riwayatPencarian.getApotek_dokter();apotek_dokter.setText(Integer.toString(Apotek_dokter));
        Atm_bank = riwayatPencarian.getAtm_bank();atm_bank.setText(Integer.toString(Atm_bank));
        Supermarket = riwayatPencarian.getSupermarket();supermarket.setText(Integer.toString(Supermarket));
        Kendaraan_umum = riwayatPencarian.getKendaraan_umum();kendaraan_umum.setText(Integer.toString(Kendaraan_umum));

        onClickButtonListenerPeta();
        onClickButtonListenerNext();
        onClickButtonListenerPrev();
        onClickButtonListenerBack();
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

    public void onClickButtonListenerPeta (){
        peta = (Button)findViewById(R.id.b_riwayat_peta);
        peta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MapsActivity.class);
                intent.putExtra("latitude_univ", pencariKos.getLatitude());
                Log.d("la_univ", pencariKos.getLatitude());
                intent.putExtra("longitude_univ", pencariKos.getLongitude());
                Log.d("lo_univ", pencariKos.getLongitude());
                intent.putExtra("latitude_kos", riwayatPencarian.getLatitude_kos());
                Log.d("la_kos", riwayatPencarian.getLatitude_kos());
                intent.putExtra("longitude_kos", riwayatPencarian.getLongitude_kos());
                Log.d("lo_kos", riwayatPencarian.getLongitude_kos());
                startActivityForResult(intent, 0);
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
        back = (Button)findViewById(R.id.b_riwayat_kembali);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView = (ListView) findViewById(R.id.riwayat_listview);
                riwayatPencarianAdapter = new riwayat_pencarianAdapter(riwayat_detail_infokosActivity.this, R.layout.activity_riwayat_detail_infokos, pencariKos);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(riwayat_detail_infokosActivity.this, "List item was clicked at " + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
