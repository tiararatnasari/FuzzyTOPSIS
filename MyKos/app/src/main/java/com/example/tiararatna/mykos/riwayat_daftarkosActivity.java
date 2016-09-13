package com.example.tiararatna.mykos;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tiararatna.adapter.riwayat_pencarianAdapter;
import com.example.tiararatna.fuzzy.Fuzzy;
import com.example.tiararatna.modul.identitas_kos;
import com.example.tiararatna.modul.pencari_kos;
import com.example.tiararatna.modul.riwayat_pencarian;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

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

/**
 * Created by Tiara Ratna on 4/19/2016.
 */
public class riwayat_daftarkosActivity extends AppCompatActivity {
    private static FloatingActionButton  delete;
    JSONObject jsonObject;
    JSONArray jsonArray;
    riwayat_pencarianAdapter riwayatKosAdapter;
    ListView listView;
    String data, json_string, json_identitas, JSON_STRING, metode, json_jarak;
    pencari_kos pencariKos;
    riwayat_pencarian riwayatPencarian, getRiwayatPencarian;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_daftarkos);
        data = getIntent().getExtras().getString("data");
        pencariKos = (pencari_kos) getIntent().getExtras().get("pencari");
        Log.d("la univ", pencariKos.getLatitude());
        Log.d("lo univ", pencariKos.getLongitude());
        if(data.equalsIgnoreCase("ada")) {
            json_string = getIntent().getExtras().getString("riwayat_kos");
            Log.d("json_string", json_string);
            getJSON(json_string);
        }

        onClickButtonListenerDelete();
    }

    public void getJSON(String json_string) {
        listView = (ListView) findViewById(R.id.riwayat_listview);
        riwayatKosAdapter = new riwayat_pencarianAdapter(this, R.layout.activity_riwayat_daftarkos_row, pencariKos);
        listView.setAdapter(riwayatKosAdapter);
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            String id_riwayat, id_pencari, la_univ, lo_univ, id_kos, nama_kos, alamat_kos, la_kos, lo_kos, id_pemilik, nama_pemilik, telp_pemilik,
                    id_kelurahan, kelurahan, kecamatan, id_universitas, nama_universitas, id_tfn, jk, meja_kursi,
                    lemari, tmpt_tidur, km_luar, kipas_angin, ac, tv, kulkas, dispenser, internet, ruang_tamu, dapur,
                    mesin_cuci, pembantu, cuci_gosok, cctv_security, klm_renang;
            int biaya_kos, jmlh_penghuni, parkir_motor, parkir_mobil, tmpt_makan, warnet, mall, apotek_dokter, atm_bank, supermarket, kendaraan_umum;
            while (count < jsonArray.length()) {
                JSONObject JO = jsonArray.getJSONObject(count);
                riwayat_pencarian riwayatPencarian = new riwayat_pencarian();
                id_riwayat = JO.getString("id_riwayat");
                riwayatPencarian.setId_Riwayat(id_riwayat);
                id_pencari = JO.getString("id_pencari");
                riwayatPencarian.setId_Pencari(id_pencari);
                id_kos = JO.getString("id_kos");
                riwayatPencarian.setId_kos(id_kos);
                nama_kos = JO.getString("nama_kos");
                riwayatPencarian.setNama_kos(nama_kos);
                alamat_kos = JO.getString("alamat_kos");
                riwayatPencarian.setAlamat_kos(alamat_kos);
                la_kos = JO.getString("latitude_kos");
                riwayatPencarian.setLatitude_kos(la_kos);
                lo_kos = JO.getString("longitude_kos");
                riwayatPencarian.setLongitude_kos(lo_kos);
                id_pemilik = JO.getString("id_pemilik");
                riwayatPencarian.setId_pemilik(id_pemilik);
                nama_pemilik = JO.getString("nama_pemilik");
                riwayatPencarian.setNama_pemilik(nama_pemilik);
                telp_pemilik = JO.getString("telp_pemilik");
                riwayatPencarian.setTelp_pemilik(telp_pemilik);
                id_kelurahan = JO.getString("id_kelurahan");
                riwayatPencarian.setId_kelurahan(id_kelurahan);
                kelurahan = JO.getString("nama_kelurahan");
                riwayatPencarian.setKelurahan(kelurahan);
                kecamatan = JO.getString("nama_kecamatan");
                riwayatPencarian.setKecamatan(kecamatan);
                id_tfn = JO.getString("id_tfn");
                riwayatPencarian.setId_tfn(id_tfn);

                LatLng kos = new LatLng(Float.parseFloat(riwayatPencarian.getLatitude_kos()), Float.parseFloat(riwayatPencarian.getLongitude_kos()));
                LatLng univ = new LatLng(Float.parseFloat(pencariKos.getLatitude()), Float.parseFloat(pencariKos.getLongitude()));
                Double distance = (SphericalUtil.computeDistanceBetween(univ, kos) / 1000);
                Log.d("distance", "" + distance);
                riwayatPencarian.setJarak_kos(distance);

                biaya_kos = JO.getInt("biaya_kos");
                riwayatPencarian.setBiaya_kos(biaya_kos);
                jk = JO.getString("jk");
                riwayatPencarian.setJk(jk);
                jmlh_penghuni = JO.getInt("jmlh_penghuni");
                riwayatPencarian.setJmlh_penghuni(jmlh_penghuni);
                meja_kursi = JO.getString("meja_kursi");
                riwayatPencarian.setMeja_kursi(meja_kursi);
                lemari = JO.getString("lemari");
                riwayatPencarian.setLemari(lemari);
                tmpt_tidur = JO.getString("tmpt_tidur");
                riwayatPencarian.setTmpt_tidur(tmpt_tidur);
                km_luar = JO.getString("km_luar");
                riwayatPencarian.setKm_luar(km_luar);
                kipas_angin = JO.getString("kipas_angin");
                riwayatPencarian.setKipas_angin(kipas_angin);
                ac = JO.getString("ac");
                riwayatPencarian.setAc(ac);
                tv = JO.getString("tv");
                riwayatPencarian.setTv(tv);
                kulkas = JO.getString("kulkas");
                riwayatPencarian.setKulkas(kulkas);
                dispenser = JO.getString("dispenser");
                riwayatPencarian.setDispenser(dispenser);
                internet = JO.getString("internet");
                riwayatPencarian.setInternet(internet);
                ruang_tamu = JO.getString("ruang_tamu");
                riwayatPencarian.setRuang_tamu(ruang_tamu);
                dapur = JO.getString("dapur");
                riwayatPencarian.setDapur(dapur);
                mesin_cuci = JO.getString("mesin_cuci");
                riwayatPencarian.setMesin_cuci(mesin_cuci);
                pembantu = JO.getString("pembantu");
                riwayatPencarian.setPembantu(pembantu);
                cuci_gosok = JO.getString("cuci_gosok");
                riwayatPencarian.setCuci_gosok(cuci_gosok);
                cctv_security = JO.getString("cctv_security");
                riwayatPencarian.setCctv_security(cctv_security);
                klm_renang = JO.getString("klm_renang");
                riwayatPencarian.setKlm_renang(klm_renang);
                parkir_motor = JO.getInt("parkir_motor");
                riwayatPencarian.setParkir_motor(parkir_motor);
                parkir_mobil = JO.getInt("parkir_mobil");
                riwayatPencarian.setParkir_mobil(parkir_mobil);
                tmpt_makan = JO.getInt("tmpt_makan");
                riwayatPencarian.setTmpt_makan(tmpt_makan);
                warnet = JO.getInt("warnet");
                riwayatPencarian.setWarnet(warnet);
                mall = JO.getInt("mall");
                riwayatPencarian.setMall(mall);
                apotek_dokter = JO.getInt("apotek_dokter");
                riwayatPencarian.setApotek_dokter(apotek_dokter);
                atm_bank = JO.getInt("atm_bank");
                riwayatPencarian.setAtm_bank(atm_bank);
                supermarket = JO.getInt("supermarket");
                riwayatPencarian.setSupermarket(supermarket);
                kendaraan_umum = JO.getInt("kendaraan_umum");
                riwayatPencarian.setKendaraan_umum(kendaraan_umum);
                riwayatKosAdapter.add(riwayatPencarian);
                count++;
            }
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data [" + e.getMessage() + "] " + json_string);
        }
    }

    public void onClickButtonListenerDelete() {
        delete = (FloatingActionButton) findViewById(R.id.b_riwayat_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRiwayatPencarian = riwayatKosAdapter.getRiwayatPencarian();
                Log.d("position", "" + getRiwayatPencarian.getNama_kos());
                metode = "delete";
                new Background(riwayat_daftarkosActivity.this).execute("");
                new Background(riwayat_daftarkosActivity.this).execute(getRiwayatPencarian.getId_riwayat());
            }
        });
    }

    class Background extends AsyncTask<String, Void, String> {
        String url_delete, url_jarak;
        Context ctx;

        public Background(Context ctx) {this.ctx = ctx;}

        @Override
        protected void onPreExecute() {url_delete = "http://mykos.stander.id/identitas_fasilitasKos_delete.php";}

        @Override
        protected String doInBackground(String... params) {
                String id_riwayat = params[0];
                Log.d("id_kos", id_riwayat);
                try {
                    URL url = new URL(url_delete);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("id_riwayat", "UTF-8") + "=" + URLEncoder.encode(id_riwayat, "UTF-8") + "&" +
                            URLEncoder.encode("id_pencari", "UTF-8") + "=" + URLEncoder.encode(pencariKos.getId_prodi(), "UTF-8");
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
                json_identitas = result;
                if (json_identitas != "FAILURE") {
                    Log.d("json_string", json_identitas);
                    getJSON(json_identitas);
                    Toast.makeText(getApplicationContext(), "Berhasil Menghapus Data Kos", Toast.LENGTH_LONG).show();
                } else if (json_identitas == "FAILURE") {
                    Toast.makeText(getApplicationContext(), "Tidak Berhasil Menghapus Data Kos", Toast.LENGTH_LONG).show();
                }
        }
    }
}
