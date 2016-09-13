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

import com.example.tiararatna.adapter.identitasKos_kelolaAdapter;
import com.example.tiararatna.modul.identitas_kos;

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
public class kelola_infokosActivity extends AppCompatActivity {
    private static FloatingActionButton change,add,delete;
    JSONObject jsonObject;
    JSONArray jsonArray;
    identitasKos_kelolaAdapter identitasKosAdapter;
    ListView listView;
    String data, json_string, json_identitas, json_fasilitas, json_kec, json_kel, id_pemilik, JSON_STRING;
    int position;
    identitas_kos identitasKos, getIdentitas;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_infokos);

        data = getIntent().getExtras().getString("data");
        if(data.equalsIgnoreCase("ada")) {
            json_string=getIntent().getExtras().getString("json_string");
            Log.d("json_string", json_string);
            getJSON(json_string);
        }
        id_pemilik=getIntent().getExtras().getString("id_pemilik");
        Log.d("id_pemilik", id_pemilik);


        onClickButtonListenerAdd();
        onClickButtonListenerDelete();
        onClickButtonListenerChange();
    }

    public void getJSON(String json_string){
        listView = (ListView)findViewById(R.id.listview);
        identitasKosAdapter = new identitasKos_kelolaAdapter(this, R.layout.activity_kelola_infokos_row);
        listView.setAdapter(identitasKosAdapter);
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            String id_kos,nama_kos,alamat_kos,id_pemilik,nama_pemilik,telp_pemilik,id_kelurahan,kelurahan,kecamatan,
                    id_universitas,universitas,id_tfn,jk,meja_kursi,lemari, tmpt_tidur,km_luar,kipas_angin,ac,tv,kulkas,
                    dispenser,internet,ruang_tamu,dapur,mesin_cuci,pembantu,cuci_gosok,cctv_security,klm_renang;
            int jarak_kos, biaya_kos,jmlh_penghuni,parkir_motor,parkir_mobil,tmpt_makan,warnet,mall,apotek_dokter,atm_bank,supermarket,kendaraan_umum;
            while (count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                identitas_kos identitasKos = new identitas_kos();
                id_kos= JO.getString("id_kos");identitasKos.setId_kos(id_kos);
                nama_kos= JO.getString("nama_kos"); identitasKos.setNama_kos(nama_kos);
                alamat_kos= JO.getString("alamat_kos");identitasKos.setAlamat_kos(alamat_kos);
                id_pemilik= JO.getString("id_pemilik");identitasKos.setId_pemilik(id_pemilik);
                nama_pemilik= JO.getString("nama_pemilik");identitasKos.setNama_pemilik(nama_pemilik);
                telp_pemilik= JO.getString("telp_pemilik"); identitasKos.setTelp_pemilik(telp_pemilik);
                id_kelurahan= JO.getString("id_kelurahan");identitasKos.setId_kelurahan(id_kelurahan);
                kelurahan= JO.getString("nama_kelurahan");identitasKos.setKelurahan(kelurahan);
                kecamatan= JO.getString("nama_kecamatan");identitasKos.setKecamatan(kecamatan);
                id_tfn= JO.getString("id_tfn");identitasKos.setId_tfn(id_tfn);
                biaya_kos= JO.getInt("biaya_kos");identitasKos.setBiaya_kos(biaya_kos);
                jk= JO.getString("jk");identitasKos.setJk(jk);
                jmlh_penghuni= JO.getInt("jmlh_penghuni");identitasKos.setJmlh_penghuni(jmlh_penghuni);
                meja_kursi= JO.getString("meja_kursi");identitasKos.setMeja_kursi(meja_kursi);
                lemari= JO.getString("lemari");identitasKos.setLemari(lemari);
                tmpt_tidur= JO.getString("tmpt_tidur");identitasKos.setTmpt_tidur(tmpt_tidur);
                km_luar= JO.getString("km_luar");identitasKos.setKm_luar(km_luar);
                kipas_angin= JO.getString("kipas_angin");identitasKos.setKipas_angin(kipas_angin);
                ac= JO.getString("ac");identitasKos.setAc(ac);
                tv= JO.getString("tv");identitasKos.setTv(tv);
                kulkas= JO.getString("kulkas");identitasKos.setKulkas(kulkas);
                dispenser= JO.getString("dispenser");identitasKos.setDispenser(dispenser);
                internet= JO.getString("internet");identitasKos.setInternet(internet);
                ruang_tamu= JO.getString("ruang_tamu");identitasKos.setRuang_tamu(ruang_tamu);
                dapur= JO.getString("dapur");identitasKos.setDapur(dapur);
                mesin_cuci= JO.getString("mesin_cuci");identitasKos.setMesin_cuci(mesin_cuci);
                pembantu= JO.getString("pembantu");identitasKos.setPembantu(pembantu);
                cuci_gosok= JO.getString("cuci_gosok");identitasKos.setCuci_gosok(cuci_gosok);
                cctv_security= JO.getString("cctv_security");identitasKos.setCctv_security(cctv_security);
                klm_renang= JO.getString("klm_renang");identitasKos.setKlm_renang(klm_renang);
                parkir_motor= JO.getInt("parkir_motor");identitasKos.setParkir_motor(parkir_motor);
                parkir_mobil= JO.getInt("parkir_mobil");identitasKos.setParkir_mobil(parkir_mobil);
                tmpt_makan= JO.getInt("tmpt_makan");identitasKos.setTmpt_makan(tmpt_makan);
                warnet= JO.getInt("warnet");identitasKos.setWarnet(warnet);
                mall= JO.getInt("mall");identitasKos.setMall(mall);
                apotek_dokter= JO.getInt("apotek_dokter");identitasKos.setApotek_dokter(apotek_dokter);
                atm_bank= JO.getInt("atm_bank");identitasKos.setAtm_bank(atm_bank);
                supermarket= JO.getInt("supermarket");identitasKos.setSupermarket(supermarket);
                kendaraan_umum= JO.getInt("kendaraan_umum");identitasKos.setKendaraan_umum(kendaraan_umum);
                identitasKosAdapter.add(identitasKos);
                count++;
            }
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data [" + e.getMessage()+"] "+json_string);
        }
    }

    public void onClickButtonListenerChange() {
        change = (FloatingActionButton) findViewById(R.id.b_kelola_change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIdentitas = identitasKosAdapter.getIdentitasKos();
                Log.d("position", ""+getIdentitas.getNama_kos());
                //new Background(kelola_infokosActivity.this).execute("");
                new Background(kelola_infokosActivity.this).execute("kecamatan");
                new Background(kelola_infokosActivity.this).execute("kelurahan");
                new Background(kelola_infokosActivity.this).execute("get_identitas",getIdentitas.getId_kos());
            }
        });
    }

    public void onClickButtonListenerAdd() {
        add = (FloatingActionButton) findViewById(R.id.b_kelola_add);
        new Background(kelola_infokosActivity.this).execute("");
        new Background(kelola_infokosActivity.this).execute("kecamatan");
        new Background(kelola_infokosActivity.this).execute("kelurahan");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(kelola_infokosActivity.this, kelola_detail_infokosActivity.class);
                intent.putExtra("metode", "add");
                intent.putExtra("id_pemilik", id_pemilik);
                Log.d("id_pemilik", id_pemilik);
                intent.putExtra("kecamatan", json_kec);
                Log.d("kecamatan", json_kec);
                intent.putExtra("kelurahan", json_kel);
                Log.d("kelurahan", json_kel);
                startActivityForResult(intent, 0);
            }
        });
    }

    public void onClickButtonListenerDelete() {
        delete = (FloatingActionButton) findViewById(R.id.b_kelola_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIdentitas = identitasKosAdapter.getIdentitasKos();
                Log.d("position", ""+getIdentitas.getNama_kos());
                new Background(kelola_infokosActivity.this).execute("");
                new Background(kelola_infokosActivity.this).execute("delete",getIdentitas.getId_kos());
            }
        });
    }

    class Background extends AsyncTask<String, Void, String> {
        String url_delete, url_get_identitas, url_get_fasilitas, url_kecamatan, url_kelurahan, metode;
        Context ctx;

        public Background(Context ctx) {this.ctx = ctx;}

        @Override
        protected void onPreExecute() {
            url_delete = "http://mykos.stander.id/identitas_fasilitasKos_delete.php";
            //url_get_identitas = "http://mykos.stander.id/identitasKos_get.php";
            url_get_fasilitas = "http://mykos.stander.id/fasilitasKos_getId.php";
            url_kecamatan = "http://mykos.stander.id/kecamatan.php";
            url_kelurahan = "http://mykos.stander.id/kelurahan.php";
        }

        @Override
        protected String doInBackground(String... params) {
            metode = params[0];
            Log.d("metode", metode);
            if (metode.equalsIgnoreCase("delete")) {
                String id_kos = params[1];
                Log.d("id_kos", id_kos);
                try {
                    URL url = new URL(url_delete);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("id_kos", "UTF-8") + "=" + URLEncoder.encode(id_kos, "UTF-8")+ "&" +
                            URLEncoder.encode("id_pemilik", "UTF-8") + "=" + URLEncoder.encode(id_pemilik, "UTF-8");
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
            }else if (metode.equalsIgnoreCase("get_fasilitas")) {
                String id_kos = params[1];
                Log.d("id_kos", id_kos);
                try {
                    URL url = new URL(url_get_fasilitas);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("id_kos", "UTF-8") + "=" + URLEncoder.encode(id_kos, "UTF-8");
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
            } else if (!metode.equalsIgnoreCase("")){
                try {
                    URL url = null;
                    if (metode.equalsIgnoreCase("kecamatan")) {
                        url = new URL(url_kecamatan);
                    } else if (metode.equalsIgnoreCase("kelurahan")) {
                        url = new URL(url_kelurahan);
                    }
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
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
            if (metode.equalsIgnoreCase("delete")) {
                json_identitas = result;
                Log.d("json_identitas", json_identitas);
                if(json_identitas!="FAILURE") {
                    getJSON(json_identitas);
                    Toast.makeText(getApplicationContext(), "Berhasil Menghapus Data Kos", Toast.LENGTH_LONG).show();
                } else if(json_identitas=="FAILURE"){
                    Toast.makeText(getApplicationContext(), "Tidak Berhasil Menghapus Data Kos", Toast.LENGTH_LONG).show();
                }
            }else if (metode.equalsIgnoreCase("get_fasilitas")) {
                    json_fasilitas = result;
                    Log.d("json_string", json_fasilitas);
                     if(json_fasilitas !="FAILURE") {
                        Intent intent = new Intent(kelola_infokosActivity.this, kelola_detail_infokosActivity.class);
                        intent.putExtra("metode", "change");
                        intent.putExtra("identitas_kos", getIdentitas);
                        Log.d("identitas_kos", getIdentitas.getId_kos());
                        intent.putExtra("fasilitas_kos", json_fasilitas);
                        Log.d("fasilitas_kos", json_fasilitas);
                        intent.putExtra("kecamatan", json_kec);
                        Log.d("kecamatan", json_kec);
                        intent.putExtra("kelurahan", json_kel);
                        Log.d("kelurahan", json_kel);
                        startActivityForResult(intent, 0);
                    } else if(json_fasilitas=="FAILURE") {
                        Log.d("json_fasilitas", "failure");
                }
            } else if (metode.equalsIgnoreCase("kecamatan")) {
                json_kec = result;
                Log.d("json_kec", json_kec);
                if(json_kec != "FAILURE") {
                    Log.d("kecamatan", "Berhasil Mengambil Data Kecamatan");
                } else if (json_kec == "FAILURE"){
                    Log.d("kecamatan", "Tidak Berhasil Mengambil Data Kecamatan");
                }
            } else if (metode.equalsIgnoreCase("kelurahan")) {
                json_kel = result;
                Log.d("json_kel", json_kel);
                if (json_kel != "FAILURE") {
                    Log.d("kelurahan", "Berhasil Mengambil Data Kelurahan");
                } else if (json_kel == "FAILURE"){
                    Log.d("kelurahan", "Tidak Berhasil Mengambil Data Kelurahan");
                }
            }
        }
    }
}